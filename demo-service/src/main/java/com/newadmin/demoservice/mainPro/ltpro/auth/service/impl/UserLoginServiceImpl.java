
package com.newadmin.demoservice.mainPro.ltpro.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.json.JSONUtil;
import com.newadmin.democommon.service.DefaultService;
import com.newadmin.democonfig.redisCommon.util.RedisUtils;
import com.newadmin.demoservice.base.message.BaseMessageSend;
import com.newadmin.demoservice.config.core.utils.CheckUtils;
import com.newadmin.demoservice.config.core.utils.ExceptionUtils;
import com.newadmin.demoservice.config.core.utils.SecureUtils;
import com.newadmin.demoservice.mainPro.ltpro.auth.entity.UserSocialDO;
import com.newadmin.demoservice.mainPro.ltpro.auth.service.LoginService;
import com.newadmin.demoservice.mainPro.ltpro.auth.service.PermissionService;
import com.newadmin.demoservice.mainPro.ltpro.auth.service.UserSocialService;
import com.newadmin.demoservice.mainPro.ltpro.common.constant.CacheConstants;
import com.newadmin.demoservice.mainPro.ltpro.common.constant.RegexConstants;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.DisEnableStatusEnum;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.GenderEnum;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.PasswordPolicyEnum;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.helper.LoginHelper;
import com.newadmin.demoservice.mainPro.ltpro.service.OptionService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录业务实现
 *
 * @author couei
 * @since 2022/12/21 21:49
 */
@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl extends DefaultService implements LoginService {

    private final ReaiUsersService userService;
    //    private final RoleService roleService;
    private final PermissionService permissionService;
    //    private final UserRoleService userRoleService;
    private final UserSocialService userSocialService;
    private final PasswordEncoder passwordEncoder;
    private final OptionService optionService;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final BaseMessageSend baseMessageSend;

    @Override
    public String accountLogin(String username, String password, HttpServletRequest request)
        throws Exception {
        // 获取用户名对应的用户信息
        ReaiUsers user = userService.getByUsername(username);

        // 注释掉原来的错误校验代码
        // boolean isError =
        //    ObjectUtil.isNull(user) || !passwordEncoder.matches(password, user.getPassword());

        // 解密数据库中存储的加密密码
        String rawPassword = ExceptionUtils.exToNull(
            () -> SecureUtils.decryptByRsaPrivateKey(password));
        // 解密用户的加密密码
        String usePwd = ExceptionUtils.exToNull(
            () -> SecureUtils.decryptByRsaPrivateKey(user.getPassword()));

        // 判断用户是否为空或者密码是否匹配
        boolean isError = ObjectUtil.isNull(user) || !rawPassword.equals(usePwd);
        // 校验用户名和密码是否正确
        this.checkUserLocked(username, request, isError);
        // 如果错误则抛出异常
        CheckUtils.throwIf(isError, "用户名或密码错误");
        // 检查用户状态是否正常
        this.checkUserStatus(user);
        // 登录成功，返回用户登录信息
        return this.login(user);
    }

    @Override
    public String phoneLogin(String phone) {
        ReaiUsers user = userService.getByPhone(phone);
        CheckUtils.throwIfNull(user, "此手机号未绑定本系统账号");
        this.checkUserStatus(user);
        return this.login(user);
    }

    @Override
    public String emailLogin(String email) {
        // 获取用户信息
        ReaiUsers user = userService.getByEmail(email);
        // 如果用户没有注册
        if (user == null) {
            // 创建新用户对象
            user = new ReaiUsers();
            // 设置用户名
            user.setUsername(email);
            //
            ReaiUsers existsUser = userService.getByUsername(user.getUsername());
            String randomStr = RandomUtil.randomString(RandomUtil.BASE_CHAR, 3);
            if (null != existsUser || !ReUtil.isMatch(RegexConstants.USERNAME,
                user.getUsername())) {
                user.setUsername(randomStr + IdUtil.fastSimpleUUID());
            }
            user.setNickName(randomStr);
            // 设置用户邮箱
            user.setEmail(email);
            // 设置默认背景
            user.setUserCover(
                "https://alist.reaicc.com/nas/image/jpeg/2024-05/1/fb02f075-42bb-4e0a-bb0a-dafc1032e4b6.jpg");
            // 设置默认头像
            user.setAvatar(
                "https://alist.reaicc.com/nas/image/jpeg/2024-05/1/51c82bda-f7bf-422c-a8d1-fac48e863140.jpg");
            // 设置初始等级
            user.setLevel(1);
            // 设置初始经验值
            user.setExp("1");
            // 设置注册时间
            user.setRegistrationTime(new Date());
            // 设置用户类型
            user.setUserType("普通用户");
            // 设置用户状态
            user.setStatus(String.valueOf(DisEnableStatusEnum.ENABLE.getValue()));
            // 将新用户添加到数据库
            super.add("reai_users", user);
        }
        // 检查用户状态
        this.checkUserStatus(user);
        // 返回登录结果
        return this.login(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String socialLogin(AuthUser authUser) {
        String source = authUser.getSource();
        String openId = authUser.getUuid();
        UserSocialDO userSocial = userSocialService.getBySourceAndOpenId(source, openId);
        ReaiUsers user;
        if (null == userSocial) {
            String username = authUser.getUsername();
            String nickname = authUser.getNickname();
            ReaiUsers existsUser = userService.getByUsername(username);
            String randomStr = RandomUtil.randomString(RandomUtil.BASE_CHAR, 5);
            if (null != existsUser || !ReUtil.isMatch(RegexConstants.USERNAME, username)) {
                username = randomStr + IdUtil.fastSimpleUUID();
            }
            if (!ReUtil.isMatch(RegexConstants.GENERAL_NAME, nickname)) {
                nickname = source.toLowerCase() + randomStr;
            }
            user = new ReaiUsers();
            user.setUsername(username);
            user.setNickName(nickname);
            user.setGender(GenderEnum.valueOf(authUser.getGender().name()));
            user.setAvatar(authUser.getAvatar());
//            user.setDeptId(SysConstants.SUPER_DEPT_ID);
            ReaiUsers userId = userService.register(user);
//            RoleDO role = roleService.getByCode(SysConstants.ADMIN_ROLE_CODE);
//            userRoleService.add(Collections.singletonList(role.getId()), userId);
            userSocial = new UserSocialDO();
            userSocial.setUserId(userId.getUserId());
            userSocial.setSource(source);
            userSocial.setOpenId(openId);
            baseMessageSend.sendSecurityMsg(user);
        } else {
            user = BeanUtil.copyProperties(userService.getUserById(userSocial.getUserId()),
                ReaiUsers.class);
        }
        this.checkUserStatus(user);
        userSocial.setMetaJson(JSONUtil.toJsonStr(authUser));
        userSocial.setLastLoginTime(new Date());
        userSocialService.saveOrUpdate(userSocial);
        return this.login(user);
    }

//    @Override
//    public List<RouteResp> buildRouteTree(Long userId) {
//        Set<String> roleCodeSet = permissionService.listRoleCodeByUserId(userId);
//        if (CollUtil.isEmpty(roleCodeSet)) {
//            return new ArrayList<>(0);
//        }
//        // 查询菜单列表
//        Set<MenuResp> menuSet = new LinkedHashSet<>();
//        if (roleCodeSet.contains(SysConstants.ADMIN_ROLE_CODE)) {
//            menuSet.addAll(menuService.listAll());
//        } else {
//            roleCodeSet.forEach(roleCode -> menuSet.addAll(menuService.listByRoleCode(roleCode)));
//        }
//        List<MenuResp> menuList = menuSet.stream()
//            .filter(m -> !MenuTypeEnum.BUTTON.equals(m.getType())).toList();
//        // 构建路由树
//        TreeField treeField = MenuResp.class.getDeclaredAnnotation(TreeField.class);
//        TreeNodeConfig treeNodeConfig = TreeUtils.genTreeNodeConfig(treeField);
//        List<Tree<Long>> treeList = TreeUtils.build(menuList, treeNodeConfig, (m, tree) -> {
//            tree.setId(m.getId());
//            tree.setParentId(m.getParentId());
//            tree.setName(m.getTitle());
//            tree.setWeight(m.getSort());
//            tree.putExtra("type", m.getType().getValue());
//            tree.putExtra("path", m.getPath());
//            tree.putExtra("name", m.getName());
//            tree.putExtra("component", m.getComponent());
//            tree.putExtra("redirect", m.getRedirect());
//            tree.putExtra("icon", m.getIcon());
//            tree.putExtra("isExternal", m.getIsExternal());
//            tree.putExtra("isCache", m.getIsCache());
//            tree.putExtra("isHidden", m.getIsHidden());
//            tree.putExtra("permission", m.getPermission());
//        });
//        return BeanUtil.copyToList(treeList, RouteResp.class);
//    }

    /**
     * 登录并缓存用户信息
     *
     * @param user 用户信息
     * @return 令牌
     */
    private String login(ReaiUsers user) {
        String userId = user.getUserId();
        CompletableFuture<Set<String>> permissionFuture = CompletableFuture.supplyAsync(
            () -> permissionService
                .listPermissionByUserId(userId), threadPoolTaskExecutor);
        CompletableFuture<Set<String>> roleCodeFuture = CompletableFuture.supplyAsync(
            () -> permissionService
                .listRoleCodeByUserId(userId), threadPoolTaskExecutor);
//        CompletableFuture<Set<RoleDTO>> roleFuture = CompletableFuture.supplyAsync(() -> roleService
//            .listByUserId(userId), threadPoolTaskExecutor);

//        CompletableFuture<Integer> passwordExpirationDaysFuture = CompletableFuture.supplyAsync(
//            () -> optionService
//                .getValueByCode2Int(PASSWORD_EXPIRATION_DAYS.name()));
//        CompletableFuture.allOf(permissionFuture, roleCodeFuture, roleFuture);
//        LoginUser loginUser = new LoginUser(permissionFuture.join(), roleCodeFuture.join(),
//            roleFuture.join(), passwordExpirationDaysFuture.join());
//        BeanUtil.copyProperties(user, loginUser);
//        baseMessageSend.sendLoginMsg(user);
        return LoginHelper.login(user);
    }

    /**
     * 检查用户状态
     *
     * @param user 用户信息
     */
    private void checkUserStatus(ReaiUsers user) {
        CheckUtils.throwIfEqual(DisEnableStatusEnum.DISABLE, user.getStatus(),
            "此账号已被禁用，如有疑问，请联系管理员");
        //其他检查用户状态
    }

    /**
     * 检测用户是否已被锁定
     *
     * @param username 用户名
     * @param request  请求对象
     * @param isError  是否登录错误
     */
    private void checkUserLocked(String username, HttpServletRequest request, boolean isError) {
        // 不锁定
        int maxErrorCount = optionService.getValueByCode2Int(
            PasswordPolicyEnum.PASSWORD_ERROR_LOCK_COUNT.name());
//        int maxErrorCount = 5;
        if (maxErrorCount <= 0) {
            return;
        }
        // 检测是否已被锁定
        String key = CacheConstants.USER_PASSWORD_ERROR_KEY_PREFIX + RedisUtils.formatKey(username,
            JakartaServletUtil
                .getClientIP(request));
        int lockMinutes = optionService.getValueByCode2Int(
            PasswordPolicyEnum.PASSWORD_ERROR_LOCK_MINUTES.name());
//        int lockMinutes = 2;
        Integer currentErrorCount = ObjectUtil.defaultIfNull(RedisUtils.get(key), 0);
        if (currentErrorCount >= maxErrorCount) {
            throw new RuntimeException("账号锁定 " + lockMinutes + " 分钟，请稍后再试");
        }
        // 登录成功清除计数
        if (!isError) {
            RedisUtils.delete(key);
            return;
        }
        // 登录失败递增计数
        currentErrorCount++;
        RedisUtils.set(key, currentErrorCount, Duration.ofMinutes(lockMinutes));
        if (currentErrorCount >= maxErrorCount) {
            throw new RuntimeException(
                "密码错误已达 " + maxErrorCount + " 次，账号锁定 " + lockMinutes + " 分钟");
        }
    }

}

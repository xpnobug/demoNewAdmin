package com.newadmin.demoservice.base.message;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.newadmin.democore.project.ProjectProperties;
import com.newadmin.democore.util.validate.CheckUtils;
import com.newadmin.demoservice.config.websocket.util.WebSocketUtils;
import com.newadmin.demoservice.mainPro.comment.entity.ReaiComment;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.MessageTemplateEnum;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.MessageTypeEnum;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.req.MessageReq;
import com.newadmin.demoservice.mainPro.ltpro.service.MessageService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiArticleService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseMessageSend {

    private final ProjectProperties projectProperties;
    private final MessageService messageService;
    private final ReaiUsersService usersService;
    private final ReaiArticleService articleService;

    /**
     * 发送安全消息
     *
     * @param user 用户信息
     */
    public void sendSecurityMsg(ReaiUsers user) {

        MessageReq req = new MessageReq();
        MessageTemplateEnum socialRegister = MessageTemplateEnum.SOCIAL_REGISTER;
        req.setTitle(socialRegister.getTitle().formatted(projectProperties.getName()));
        req.setContent(socialRegister.getContent().formatted(user.getNickName()));
        req.setType(MessageTypeEnum.SECURITY);
        messageService.add(req, CollUtil.toList(user.getUserId()));
        List<String> tokenList = StpUtil.getTokenValueListByLoginId(user.getUserId());
        for (String token : tokenList) {
            WebSocketUtils.sendMessage(token, "1");
        }
    }

    /**
     * 发送登录消息
     *
     * @param user
     */
    public void sendLoginMsg(ReaiUsers user) {
        MessageReq req = new MessageReq();
        MessageTemplateEnum socialRegister = MessageTemplateEnum.USER_LOGIN;
        req.setTitle(socialRegister.getTitle().formatted(projectProperties.getName()));
        req.setContent(socialRegister.getContent()
            .formatted(user.getNickName() == null ? "用户" : user.getNickName()));
        req.setType(MessageTypeEnum.LOGGING);
        messageService.add(req, CollUtil.toList(user.getUserId()));
        List<String> tokenList = StpUtil.getTokenValueListByLoginId(user.getUserId());
        for (String token : tokenList) {
            WebSocketUtils.sendMessage(token, "1");
        }
    }

    public void sendCommentMsg(ReaiComment comment, String rplyToUserId) {
        //获取评论的用户
        ReaiUsers userInfo = usersService.getUserById(comment.getUserId());
        CheckUtils.throwIfNull(userInfo, "评论用户不存在");
        //评论的文章
        ReaiArticle articleInfo = articleService.getArticleInfo(comment.getArticleId());
        CheckUtils.throwIfNull(articleInfo, "评论文章不存在");

        //判断是否是自己的评论
        //如果在自己的文章下评论，则不发送消息，但是在自己的文章下评论的其他用户，需要发消息
        // 判断是否是自己的评论
        if (!userInfo.getUserId().equals(articleInfo.getUserId())) {
            // 发送消息给文章作者
            sendCommentNotification(userInfo, articleInfo.getUserId(), comment.getContent(), "3");
        }

        // 如果是回复其他用户，则发送消息给被回复的用户
        if (rplyToUserId != null && !rplyToUserId.equals(userInfo.getUserId())) {
            sendCommentNotification(userInfo, rplyToUserId, comment.getContent(), "3");
        }
    }

    public void sendCommentNotification(ReaiUsers userInfo, String targetUserId, String content,
        String type) {
        //发送消息
        MessageReq req = new MessageReq();
        MessageTemplateEnum socialRegister = MessageTemplateEnum.COMMENT_SEND;
        req.setTitle(socialRegister.getTitle().formatted(userInfo.getUsername()));
        req.setContent(socialRegister.getContent().formatted(content));
        req.setType(MessageTypeEnum.COMMENT);
        messageService.add(req, CollUtil.toList(targetUserId));
        List<String> tokenList = StpUtil.getTokenValueListByLoginId(targetUserId);
        for (String token : tokenList) {
            WebSocketUtils.sendMessage(token, type);
        }
    }
}

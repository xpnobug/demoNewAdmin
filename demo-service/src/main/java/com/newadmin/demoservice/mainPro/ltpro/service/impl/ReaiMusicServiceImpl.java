package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiMusic;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiMusicService;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-06-20
 */
@Service
public class ReaiMusicServiceImpl extends DefaultService implements ReaiMusicService {

    public static final String TABLE_NAME = "reai_music";

    @Override
    public List<ReaiMusic> songListById(String userId, String[] articleId) {
        // 构建查询条件
        ValueMap param = new ValueMap();
        param.put(ReaiMusic.USER_ID, userId);
        param.put(ReaiMusic.ARTICLE_ID, articleId);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiMusic.USER_ID)
            .and("article_id", ConditionType.IN, ReaiMusic.ARTICLE_ID);
        // 执行查询
        return super.listForBean(selectBuilder.build(), ReaiMusic::new);
    }

    @Override
    public JsonObject addSong(ReaiMusic reaiSong) {
        reaiSong.setCreateDate(new Date());
        reaiSong.setSongUrl(
            "https://music.163.com/song/media/outer/url?id=" + reaiSong.getSongId() + ".mp3");
        String sid = super.add(TABLE_NAME, reaiSong).toString();
        return new JsonObject(sid);
    }

    @Override
    public JsonObject deleteSong(String sid) {
        super.delete(TABLE_NAME, ReaiMusic.SONG_ID, new String[]{sid});
        return null;
    }
}

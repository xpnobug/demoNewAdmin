package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiMusic;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-06-20
 */
public interface ReaiMusicService {

    List<ReaiMusic> songListById(String userId, String[] articleId);

    JsonObject addSong(ReaiMusic reaiSong);

    JsonObject deleteSong(String songId);
}

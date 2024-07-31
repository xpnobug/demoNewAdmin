package com.newadmin.demoservice.mainPro.ltpro.vo;

import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Statistics extends ValueMap {

    /**
     * 发布文章数
     */
    public static final String PUBLISH_ARTICLE_COUNT = "publishArticleCount";

    /**
     * 关注
     */
    public static final String FOLLOW_COUNT = "followCount";

    /**
     * 粉丝
     */
    public static final String FANS_COUNT = "fansCount";

    public Statistics() {
    }

    public Statistics(Map<String, Object> map) {
        super(map);
    }
}

package com.newadmin.demoservice.mainPro.ltpro.vo;

import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReaiArticleList extends ValueMap {

    /***/
    public static final String TYPE_NAME = "typeName";

    /***/
    public static final String ARTICLE_LIST = "articleList";

    public ReaiArticleList() {
    }

    public ReaiArticleList(Map<String, Object> map) {
        super(map);
    }
}

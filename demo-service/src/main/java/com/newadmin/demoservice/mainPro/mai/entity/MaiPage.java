package com.newadmin.demoservice.mainPro.mai.entity;

import java.util.List;
import lombok.Data;

@Data
public class MaiPage {

    private int length;
    private int ttType;
    private int picType;
    private int pageNum;
    private int pageSize;
    private List<MaiInfo> maiInfo;

}

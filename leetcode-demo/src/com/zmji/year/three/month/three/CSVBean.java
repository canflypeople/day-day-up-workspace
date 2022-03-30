package com.zmji.year.three.month.three;

import java.io.Serializable;

import lombok.Data;

/**
 * 应用表(ApplicationInfo)实体类
 * 
 * @author : fengchun.lu
 * @date : 2021/9/3 10:52
 */
@Data
public class CSVBean implements Serializable {

    private Long stationId;

    private String data;

    private String eDay;

    private String eDayTotal;

    private String income;

    private String incomeTotal;

    private String eGridDay;

    private String eGridTotal;

}

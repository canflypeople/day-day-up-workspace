package com.zmji.year.three.month.three;

import java.io.Serializable;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

/**
 * 应用表(ApplicationInfo)实体类
 * 
 * @author : fengchun.lu
 * @date : 2021/9/3 10:52
 */
@Data
public class CSVInfo implements Serializable {

    @Alias("stationId")
    private Long stationId;

    @Alias("date")
    private String statisticsDateStr;

    @Alias("eDay")
    private String spontaneousSelfUseEDay;

    @Alias("eDayTotal")
    private String spontaneousSelfUseETotal;

    @Alias("income")
    private String spontaneousSelfUseEDayIncome;

    @Alias("incomeTotal")
    private String spontaneousSelfUseETotalIncome;

    @Alias("eGridDay")
    private String gridEDay;

    @Alias("eGridTotal")
    private String gridETotal;

}

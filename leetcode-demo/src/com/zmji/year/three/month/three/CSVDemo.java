package com.zmji.year.three.month.three;

import java.io.File;
import java.util.List;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/8 7:45 下午
 **/
public class CSVDemo {

    public static void main(String[] args) {
        // 从文件中读取CSV数据
        File direct = new File("/Users/jizhongmou/goodwe/csv/csv1");
        if (direct.isDirectory()) {
            for (File file : direct.listFiles()) {
                final CsvReader reader = CsvUtil.getReader();
                // 假设csv文件在classpath目录下
                final List<CSVInfo> result =
                    reader.read(ResourceUtil.getUtf8Reader(file.getAbsolutePath()), CSVInfo.class);
                for (CSVInfo benefitAdjustDayCreateRequest : result) {
                    String dateStr = processDateStr(benefitAdjustDayCreateRequest.getStatisticsDateStr());
                    DateTime dateTime = DateUtil.parseDate(dateStr);
                    String currDateStr = DateUtil.format(dateTime, DatePattern.NORM_DATETIME_PATTERN);
                    System.out.println(currDateStr);
                }
            }
        }
    }

    public static String processDateStr(String dateStr) {
        String[] splitArr = dateStr.split("/");
        StringBuilder sb = new StringBuilder();
        sb.append(splitArr[0]);
        sb.append("/");
        if (splitArr[1].length() <= 1) {
            sb.append(0);
        }
        sb.append(splitArr[1]);
        sb.append("/");
        if (splitArr[2].length() <= 1) {
            sb.append(0);
        }
        sb.append(splitArr[2]);
        return sb.toString();
    }
}

package com.rookiestar.starmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util class that handle data
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class DateUtil {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parse(String dateString) throws Exception{
        return SIMPLE_DATE_FORMAT.parse(dateString);
    }

    public static Date format(Date date) throws Exception{
        return SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(date));
    }

    public static String display(Date date){
        return SIMPLE_DATE_FORMAT.format(date);
    }
}

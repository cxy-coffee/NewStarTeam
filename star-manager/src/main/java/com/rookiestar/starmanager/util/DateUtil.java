package com.rookiestar.starmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parse(String dateString) throws Exception{
        return sdf.parse(dateString);
    }

    public static Date format(Date date) throws Exception{
        return sdf.parse(sdf.format(date));
    }

    public static String display(Date date){
        return sdf.format(date);
    }
}

package com.rookiestar.starmanager.util;

import com.rookiestar.starmanager.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Test class that test DateUtil
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class DateUtilTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void parseTest() throws Exception{
        logger.info(DateUtil.parse("2009-11-28").toString());
    }

    @Test
    public void formatTest() throws Exception{
        logger.info(DateUtil.format(new Date()).toString());
    }

    @Test
    public void displayTest(){
        logger.info(DateUtil.display(new Date()));
    }
}
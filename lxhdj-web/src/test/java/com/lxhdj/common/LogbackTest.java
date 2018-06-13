package com.lxhdj.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangguijun1 on 2018/6/4.
 */
public class LogbackTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void logTest() {
        LOGGER.trace("logback的--trace日志--输出了");
        LOGGER.debug("logback的--debug日志--输出了");
        LOGGER.info("logback的--info日志--输出了");
        LOGGER.warn("logback的--warn日志--输出了");
        LOGGER.error("logback的--error日志--输出了");
    }
}

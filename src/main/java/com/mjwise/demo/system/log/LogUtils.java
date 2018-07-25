package com.mjwise.demo.system.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

/**
 * Created by laiyonggang(laiyonggang@mjwise.com) on 2017/12/27.
 */
public class LogUtils {

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    /**
     * 写入错误日志文件并返回msgId
     * @param request
     * @param e
     * @return
     */
    public static String writeWebExceptionLog(HttpServletRequest request, Exception e){
        String msgId = UUID.randomUUID().toString(), params = "";
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        logger.error("【{}】程序异常！,异常信息堆栈为：【{}】 ", msgId, stringWriter.getBuffer().toString());
        return msgId;
    }
}

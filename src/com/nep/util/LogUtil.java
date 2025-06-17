package com.nep.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LogUtil {
    static {  // 类加载时执行
        init();
    }

    public static void init() {
        try {
            Logger rootLogger = Logger.getLogger("");

            // 移除所有默认Handler
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // 配置FileHandler
            new File("logs").mkdirs(); // 自动创建logs目录
            FileHandler fileHandler = new FileHandler("logs/app.log", true); // true为追加
            fileHandler.setFormatter(new SimpleFormatter() {
                private static final String FORMAT = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public String format(LogRecord record) {
                    return String.format(FORMAT,
                            new java.util.Date(record.getMillis()),
                            record.getLevel().getLocalizedName(),
                            record.getMessage()
                    );
                }
            });
            fileHandler.setLevel(Level.ALL); // 记录所有级别日志
            rootLogger.addHandler(fileHandler);

            // 设置全局日志级别
            rootLogger.setLevel(Level.FINE);

            Logger.getGlobal().info("日志系统初始化完成（仅文件输出）");
        } catch (IOException e) {
            Logger.getGlobal().severe("日志文件初始化失败: " + e.getMessage());
        }
    }

    public static Logger getLogger(Class<?> clazz) {  // 根据类，创建一个与之关联的logger实例
        return Logger.getLogger(clazz.getName());
    }
}

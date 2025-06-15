package com.nep.util;

import java.util.logging.*;

public class LogUtil {
    private static final Logger logger = Logger.getLogger(LogUtil.class.getName());

    static {  // 类加载时自动执行
        init();
    }

    public static void init() {
        try {
            // 1. 获取全局Logger（覆盖所有类的日志设置）
            Logger globalLogger = Logger.getLogger("");

            // 2. 移除默认的ConsoleHandler（避免重复输出）
            Handler[] handlers = globalLogger.getHandlers();
            for (Handler handler : handlers) {
                globalLogger.removeHandler(handler);
            }

            // 3. 添加FileHandler（输出到文件）
            FileHandler fileHandler = new FileHandler("logs/app.log", true); // true表示追加模式
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            // 4. 将Handler添加到全局Logger
            globalLogger.addHandler(fileHandler);
            globalLogger.setLevel(Level.INFO); // 设置全局日志级别

            logger.info("日志系统初始化完成");
        } catch (Exception e) {
            logger.severe("日志配置失败: " + e.getMessage());
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }
}

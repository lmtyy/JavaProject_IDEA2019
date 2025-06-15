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
            System.out.println("正在初始化日志系统..."); // 调试输出
            new File("logs").mkdirs();
            System.out.println("日志目录路径: " + new File("logs").getAbsolutePath()); // 打印绝对路径
            // 剩余代码...
        } catch (Exception e) {
            System.err.println("日志初始化异常: " + e.getMessage()); // 打印错误
            e.printStackTrace();
        }

        try {
            Logger rootLogger = Logger.getLogger("");

            // 1. 移除所有默认Handler（包括ConsoleHandler）
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // 2. 仅配置FileHandler（无ConsoleHandler）
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

            // 3. 设置全局日志级别
            rootLogger.setLevel(Level.INFO);

            Logger.getGlobal().info("日志系统初始化完成（仅文件输出）");
        } catch (IOException e) {
            Logger.getGlobal().severe("日志文件初始化失败: " + e.getMessage());
        }
    }

    public static Logger getLogger(Class<?> clazz) {  // 根据类，创建一个与之关联的logger实例
        return Logger.getLogger(clazz.getName());
    }
}

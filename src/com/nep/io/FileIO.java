package com.nep.io;

import com.nep.util.LogUtil;

import java.io.*;
import java.util.logging.Logger;

public class FileIO {
    private static final Logger logger = LogUtil.getLogger(FileIO.class);

    /**
     * 读取文件
     * @param filepath
     * @return
     */
    public static Object readObject(String filepath) {
        File file = new File(filepath);
        InputStream is = null;
        ObjectInputStream ois = null;
        Object obj = null;
        try {
            is = new FileInputStream(file);
            ois = new ObjectInputStream(is);
            obj = ois.readObject();
            logger.fine("文件读取成功: " + filepath);  // txt文件读取情况也记入日志
        } catch (Exception ex){
            logger.severe("文件读取失败: " + filepath + ", 错误: " + ex.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
                logger.severe("文件关闭失败: " + filepath + ", 错误: " + e.getMessage());
            }
        }
        return obj ;
    }

    /**
     * 写入文件
     * @param filepath
     * @param obj
     */
    public static void writeObject(String filepath, Object obj) {
        File file = new File(filepath);
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            os = new FileOutputStream(file);
            oos = new ObjectOutputStream(os);
            oos.writeObject(obj);
            oos.flush();

            logger.fine("文件写入成功: " + filepath);
        } catch (Exception ex) {
            // TODO: handle exception
            logger.severe("文件写入失败: " + filepath + ", 错误: " + ex.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
                logger.severe("文件关闭失败: " + filepath + ", 错误: " + e.getMessage());
            }
        }
    }
}

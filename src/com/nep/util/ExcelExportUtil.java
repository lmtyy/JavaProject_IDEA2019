package com.nep.util;

import com.nep.entity.AqiFeedback;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelExportUtil {

    public static void exportAqiFeedbackToExcel(List<AqiFeedback> dataList, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("AQI反馈信息");

        // 创建表头
        Row header = sheet.createRow(0);
        String[] titles = {"编号", "姓名", "省", "市", "地址", "反馈信息", "预估等级", "状态", "反馈日期"};
        for (int i = 0; i < titles.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(titles[i]);
        }

        // 填充数据
        int rowIndex = 1;
        for (AqiFeedback af : dataList) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(af.getAfId());
            row.createCell(1).setCellValue(af.getAfName());
            row.createCell(2).setCellValue(af.getProviceName());
            row.createCell(3).setCellValue(af.getCityName());
            row.createCell(4).setCellValue(af.getAddress());
            row.createCell(5).setCellValue(af.getInfomation());
            row.createCell(6).setCellValue(af.getEstimateGrade());
            row.createCell(7).setCellValue(af.getState());
            row.createCell(8).setCellValue(af.getDate());
        }

        try (FileOutputStream out = new FileOutputStream(filePath)) {
            workbook.write(out);
            System.out.println("导出成功：" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

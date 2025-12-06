package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ExcelUtility - basic helper to read/write XLSX and set cell colors.
 * Designed to be defensive (null checks) and include necessary imports.
 */
public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public XSSFCellStyle style;
    String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream localFi = new FileInputStream(path);
             XSSFWorkbook localWb = new XSSFWorkbook(localFi)) {
            XSSFSheet localSheet = localWb.getSheet(sheetName);
            if (localSheet == null) {
                return 0;
            }
            return localSheet.getLastRowNum();
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream localFi = new FileInputStream(path);
             XSSFWorkbook localWb = new XSSFWorkbook(localFi)) {
            XSSFSheet localSheet = localWb.getSheet(sheetName);
            if (localSheet == null) {
                return 0;
            }
            XSSFRow localRow = localSheet.getRow(rownum);
            if (localRow == null) {
                return 0;
            }
            return localRow.getLastCellNum();
        }
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream localFi = new FileInputStream(path);
             XSSFWorkbook localWb = new XSSFWorkbook(localFi)) {
            XSSFSheet localSheet = localWb.getSheet(sheetName);
            if (localSheet == null) {
                return "";
            }
            XSSFRow localRow = localSheet.getRow(rownum);
            if (localRow == null) {
                return "";
            }
            XSSFCell localCell = localRow.getCell(colnum);
            if (localCell == null) {
                return "";
            }

            DataFormatter formatter = new DataFormatter();
            try {
                return formatter.formatCellValue(localCell); // formatted cell value as String
            } catch (Exception e) {
                return "";
            }
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlFile = new File(path);

        // If file doesn't exist create workbook and file
        if (!xlFile.exists()) {
            try (XSSFWorkbook newWb = new XSSFWorkbook();
                 FileOutputStream newFo = new FileOutputStream(path)) {
                newWb.createSheet(sheetName);
                newWb.write(newFo);
            }
        }

        // Open existing file, update sheet/row/cell, then write back
        try (FileInputStream localFi = new FileInputStream(path);
             XSSFWorkbook localWb = new XSSFWorkbook(localFi)) {

            XSSFSheet localSheet = localWb.getSheet(sheetName);
            if (localSheet == null) {
                localSheet = localWb.createSheet(sheetName);
            }

            XSSFRow localRow = localSheet.getRow(rownum);
            if (localRow == null) {
                localRow = localSheet.createRow(rownum);
            }

            XSSFCell localCell = localRow.getCell(colnum);
            if (localCell == null) {
                localCell = localRow.createCell(colnum);
            }

            localCell.setCellValue(data);

            // write changes
            try (FileOutputStream localFo = new FileOutputStream(path)) {
                localWb.write(localFo);
            }
        }
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        changeCellFillColor(sheetName, rownum, colnum, IndexedColors.GREEN.getIndex());
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        changeCellFillColor(sheetName, rownum, colnum, IndexedColors.RED.getIndex());
    }

    // helper to change fill color (index) for a cell
    private void changeCellFillColor(String sheetName, int rownum, int colnum, short colorIndex) throws IOException {
        // open workbook
        try (FileInputStream localFi = new FileInputStream(path);
             XSSFWorkbook localWb = new XSSFWorkbook(localFi)) {

            XSSFSheet localSheet = localWb.getSheet(sheetName);
            if (localSheet == null) {
                // nothing to color
                return;
            }

            XSSFRow localRow = localSheet.getRow(rownum);
            if (localRow == null) {
                // nothing to color
                return;
            }

            XSSFCell localCell = localRow.getCell(colnum);
            if (localCell == null) {
                // nothing to color
                return;
            }

            XSSFCellStyle cellStyle = localWb.createCellStyle();
            cellStyle.setFillForegroundColor(colorIndex);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            localCell.setCellStyle(cellStyle);

            // write changes
            try (FileOutputStream localFo = new FileOutputStream(path)) {
                localWb.write(localFo);
            }
        }
    }
}

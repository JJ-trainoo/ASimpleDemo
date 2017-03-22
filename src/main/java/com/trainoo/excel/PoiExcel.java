package com.trainoo.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 使用poi操作excel
 *
 * @author zhout
 * @date 2017年3月22日
 */
@Controller
@RequestMapping("/excel")
public class PoiExcel {
	
	@RequestMapping("/download.do")
	public void downExcel(HttpServletRequest request, HttpServletResponse response){
		try {
			String filePath = request.getSession().getServletContext().getRealPath("static/excel/excelDemo.xlsx");
			OutputStream os = response.getOutputStream();
			XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(filePath));
			book.write(os);
			os.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws Exception {

		//创建工作簿
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet st = book.createSheet("sheet1");

		/*
		 * 样式调整
		 */
		HSSFCellStyle style = book.createCellStyle();
		// 左右上下边框样式
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 左右上下边框颜色(深蓝色),只有设置了上下左右边框以后给边框设置颜色才会生效
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		//给单元格设置背景：
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置了背景色才有效果
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 单元格字体
        HSSFFont font = book.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);
        font.setColor(HSSFColor.DARK_RED.index);
        style.setFont(font);
        // 设置居中对齐
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        /*
         * 创建表格
         * i 行 j 列
         */
		for(int i=0; i < 9; i++){
			HSSFRow row = st.createRow(i);
			for(int j=0; j<3; j++){
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(i+"_"+j);
				cell.setCellStyle(style);
			}
		}
		
		/*
		 * 合并表格，参数：
		 * CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol)
		 */
		CellRangeAddress region = new CellRangeAddress(0, 3, 0, 2);
		st.addMergedRegion(region);

		/*
		 * 输出到文件
		 */
		FileOutputStream output=new FileOutputStream("E:/test.xls");  
		book.write(output);
		output.close();
	}
}

package com.trainoo.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * poi操作excel
 * 
 * @author zhoutao
 * @date 2017年3月22日
 */
@Controller
@RequestMapping("/excel")
public class PoiExcel {
	
	/**
	 * XSSFWorkbook   操作xlsx文件
	 * HSSFWorkBook   操作xls文件
	 * 
	 * @author zhout
	 * @date 2017年3月23日
	 * @param request
	 * @param response
	 */
	@RequestMapping("/download.do")
	public void downExcel(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取模板路径，创建工作簿
			String filePath = request.getSession().getServletContext().getRealPath("/static/excel/excelDemo.xlsx");
			OutputStream os = response.getOutputStream();
			XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(filePath));
			XSSFSheet xs = book.getSheetAt(0);
			
			//设置样式
			XSSFCellStyle style1 = book.createCellStyle();
			XSSFCellStyle style = book.createCellStyle();
			// 上下左右边框
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// 边框颜色
			style.setLeftBorderColor(HSSFColor.BLACK.index);
			style.setRightBorderColor(HSSFColor.BLACK.index);
			style.setTopBorderColor(HSSFColor.BLACK.index);
			style.setBottomBorderColor(HSSFColor.BLACK.index);
	        // 居中对齐
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			//初始化一个表格
			XSSFRow row = xs.getRow(0);  //这里用get而不是create,是因为第一行在模板中已经有了
			XSSFCell cell = row.createCell(6);  //这里使用create,因为模板只有5列。 第七列是null
			cell.setCellValue("网站");
			cell.setCellStyle(style1);
			cell = row.createCell(7);
			cell.setCellValue("统计情况");
			cell.setCellStyle(style1);
			for(int i=1; i < 4; i++){
				row = xs.getRow(i);
				for(int j=6; j<9; j++){
					cell = row.createCell(j);
					cell.setCellStyle(style);
					cell.setCellValue("ij");
				}
			}
			xs.getRow(1).getCell(6).setCellValue("奇虎360");
			xs.getRow(1).getCell(7).setCellValue("http://hao.360.cn/");
			xs.getRow(1).getCell(8).setCellValue(11);
			xs.getRow(2).getCell(7).setCellValue("http://sh.qihoo.com/");
			xs.getRow(2).getCell(8).setCellValue(22);
			xs.getRow(3).getCell(7).setCellValue("http://video.so.com/");
			xs.getRow(3).getCell(8).setCellValue(33);
			xs.getRow(4).createCell(6).setCellValue(new Date());
			
			//合并单元格
			xs.addMergedRegion(new CellRangeAddress(0, 0, 7, 8));
			xs.addMergedRegion(new CellRangeAddress(1, 3, 6, 6));
			//设置第6列宽
			xs.autoSizeColumn(7, true);
			
			//文件输出设置
			response.setCharacterEncoding("UTF-8");
			String fileName = URLEncoder.encode("FileName", "UTF-8");  //文件名
			response.setContentType("application/vnd.ms-excel");	// 响应类型
			response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");// 响应头
			//输出流文件
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
		 * 设置样式
		 */
		HSSFCellStyle style = book.createCellStyle();
		// 上下左右边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 边框颜色
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置背景颜色，先创建背景，再填充颜色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 字体样式
        HSSFFont font = book.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);
        font.setColor(HSSFColor.DARK_RED.index);
        style.setFont(font);
        // 居中对齐
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        /*
         * 初始化表格
         * i行j列
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
		 * 合并单元格，参数如下:
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

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
 * ʹ��poi����excel
 *
 * @author zhout
 * @date 2017��3��22��
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

		//����������
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet st = book.createSheet("sheet1");

		/*
		 * ��ʽ����
		 */
		HSSFCellStyle style = book.createCellStyle();
		// �������±߿���ʽ
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// �������±߿���ɫ(����ɫ),ֻ���������������ұ߿��Ժ���߿�������ɫ�Ż���Ч
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		//����Ԫ�����ñ�����
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// �����˱���ɫ����Ч��
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        // ��Ԫ������
        HSSFFont font = book.createFont();
        font.setFontName("����");
        font.setFontHeightInPoints((short) 14);
        font.setColor(HSSFColor.DARK_RED.index);
        style.setFont(font);
        // ���þ��ж���
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        /*
         * �������
         * i �� j ��
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
		 * �ϲ���񣬲�����
		 * CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol)
		 */
		CellRangeAddress region = new CellRangeAddress(0, 3, 0, 2);
		st.addMergedRegion(region);

		/*
		 * ������ļ�
		 */
		FileOutputStream output=new FileOutputStream("E:/test.xls");  
		book.write(output);
		output.close();
	}
}

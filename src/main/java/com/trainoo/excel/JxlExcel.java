package com.trainoo.excel;

import java.io.File;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


/**
 * 使用jxl操作excel
 * @author zhoutao
 * @date 2017年3月22日
 */
public class JxlExcel {

	public static void main(String[] args) throws Exception {

		/*
		 * 读取模板，用流输出文件
		 * 
			// 获取模板路径
			String path = request.getSession().getServletContext().getRealPath("static/excel/NSXY.xls");
			// 创建工作簿
			Workbook rwb = null;
			WritableWorkbook wwb = null;
			// 获取文件
			InputStream is = new FileInputStream(path);
			rwb = Workbook.getWorkbook(is);
			// 转换成可编辑的工作簿
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			wwb = Workbook.createWorkbook(response.getOutputStream(), rwb, settings);
			// 得到第一页
			WritableSheet ws = (WritableSheet) wwb.getSheet("Sheet1");
			// 设置输出头
			response.setCharacterEncoding("UTF-8");
			String fileName = URLEncoder.encode("FileName", "UTF-8");  //文件名
			response.setContentType("application/vnd.ms-excel");	// 响应类型
			response.setHeader("Content-disposition","attachment;filename="+fileName+".xls");// 响应头
		*/
		
		
		// 创建工作簿
		WritableWorkbook wwb = Workbook.createWorkbook(new File("E:/test.xls"));
		// 得到第一页
		WritableSheet ws = wwb.createSheet("Sheet1", 0);
		// 获取样式
		WritableFont wf = new WritableFont(WritableFont.TIMES, 14, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		// 自适应
		wcf.setWrap(true);
		wcf.setShrinkToFit(true);
		// 水平居中
		wcf.setAlignment(Alignment.CENTRE);
		// 垂直居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 设置边框
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

		// 初始化表格  6列  18行
		for (int i = 0; i < 6; i++) {
			for (int j = 1; j < 18; j++) {
				ws.setColumnView(i, 28);
				ws.addCell(new Label(i, j, "", wcf));
			}
		}

		/*
		 * 表格内容
		 */
		Label label_01 = new Label(0, 0, "附件一", wcf);
		Label label_02 = new Label(0, 1, "XXXXX统计报表", wcf);
		Label label_03 = new Label(0, 2, "ASimpleDemo联合编制", wcf);
		Label label_04 = new Label(4, 2, "仅供测试使用", wcf);
		Label label_05 = new Label(0, 3, "“测试项目”成效统计", wcf);
		Label label_06 = new Label(1, 3, "是否符合要求", wcf);
		Label label_07 = new Label(1, 4, "合作银行", wcf);
		Label label_08 = new Label(3, 4, "10家", wcf);
		Label label_09 = new Label(3, 3, "是", wcf);
		Label label_10 = new Label(4, 4, "中国银行，建设银行，农业银行，工商银行", wcf);
		Label label_11 = new Label(3, 5, "省级签约银行", wcf);
		Label label_12 = new Label(4, 5, "各地签约银行", wcf);
		Label label_13 = new Label(5, 5, "合计", wcf);

		/*
		 * 合并单元格
		 * 参数（起始列标， 起始行标， 结束列标， 结束行标）
		 */
		ws.mergeCells(0, 1, 5, 1);
		ws.mergeCells(0, 2, 2, 2);
		ws.mergeCells(4, 2, 5, 2);
		ws.mergeCells(0, 3, 0, 4);
		ws.mergeCells(1, 3, 2, 3);
		ws.mergeCells(3, 3, 5, 3);
		ws.mergeCells(1, 4, 2, 4);
		ws.mergeCells(4, 4, 5, 4);

		ws.addCell(label_01);
		ws.addCell(label_02);
		ws.addCell(label_03);
		ws.addCell(label_04);
		ws.addCell(label_05);
		ws.addCell(label_06);
		ws.addCell(label_07);
		ws.addCell(label_08);
		ws.addCell(label_09);
		ws.addCell(label_10);
		ws.addCell(label_11);
		ws.addCell(label_12);
		ws.addCell(label_13);

		// 文件输出，关闭工作簿
		wwb.write();
		wwb.close();
	}
}

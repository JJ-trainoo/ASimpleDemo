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
 *
 * @author zhout
 * @date 2017年3月22日
 */
public class JxlExcel {

	public static void main(String[] args) throws Exception {

		/*
		 * 用流的方式读取模板并输出流
		 * 
			// 获取文件绝对路径
			String path = request.getSession().getServletContext().getRealPath("static/excel/NSXY.xls");
			// 创建工作簿
			Workbook rwb = null;
			WritableWorkbook wwb = null;
			// 从文件中读xls模板
			InputStream is = new FileInputStream(path);
			rwb = Workbook.getWorkbook(is);
			// 变成可编辑的
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			wwb = Workbook.createWorkbook(response.getOutputStream(), rwb, settings);
			// 获取第一页
			WritableSheet ws = (WritableSheet) wwb.getSheet("Sheet1");
			// 设置输出格式
			response.setCharacterEncoding("UTF-8");
			String fileName = URLEncoder.encode("FileName", "UTF-8");  //文件名
			response.setContentType("application/vnd.ms-excel");	// 定义输出类型 
			response.setHeader("Content-disposition","attachment;filename="+fileName+".xls");// 设定输出文件头 
		*/
		
		
		// 创建工作薄
		WritableWorkbook wwb = Workbook.createWorkbook(new File("E:/test.xls"));
		// 创建新的一页
		WritableSheet ws = wwb.createSheet("Sheet1", 0);
		// 设置字体，边框
		WritableFont wf = new WritableFont(WritableFont.TIMES, 14, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		// 自动换行
		wcf.setWrap(true);
		wcf.setShrinkToFit(true);
		// 水平居中
		wcf.setAlignment(Alignment.CENTRE);
		// 垂直居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 边框
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

		// 初始化表格
		for (int i = 0; i < 6; i++) {
			for (int j = 1; j < 18; j++) {
				ws.setColumnView(i, 28);
				ws.addCell(new Label(i, j, "", wcf));
			}
		}

		/*
		 * 相同部分表头 创建要显示的内容,创建一个单元格， 第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		 */
		Label label_01 = new Label(0, 0, "附件1", wcf);
		Label label_02 = new Label(0, 1, "“XXXX”活动推进情况统计表", wcf);
		Label label_03 = new Label(0, 2, "填表单位：XXXX", wcf);
		Label label_04 = new Label(4, 2, "单位：户、笔、万元、%", wcf);
		Label label_05 = new Label(0, 3, "银税合作机制建立情况", wcf);
		Label label_06 = new Label(1, 3, "是否建立联席会议制度", wcf);
		Label label_07 = new Label(1, 4, "与省税务机关签订协议的银行业金融机构  （截至2016年6月30日已签订协议）", wcf);
		Label label_08 = new Label(3, 4, "10家", wcf);
		Label label_09 = new Label(3, 3, "是", wcf);
		Label label_10 = new Label(4, 4, "AA银行、BB银行、CC银行、DD银行、EE银行、FF银行、GG银行、HH银行、II银行、JJ银行", wcf);
		Label label_11 = new Label(3, 5, "省级签约银行", wcf);
		Label label_12 = new Label(4, 5, "各地签约银行", wcf);
		Label label_13 = new Label(5, 5, "合计", wcf);

		/*
		 * 合并单元格 第一个参数：要合并的单元格最左上角的列号， 第二个参数：要合并的单元格最左上角的行号，
		 * 第三个参数：要合并的单元格最右角的列号， 第四个参数：要合并的单元格最右下角的行号
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

		/*
		 * 表 - 银税合作实际发放贷款情况
		 */
		Label label_14 = new Label(0, 6, "实际发放贷款情况", wcf);
		Label label_15 = new Label(1, 6, "授信/贷款笔数", wcf);
		Label label_16 = new Label(1, 9, "授信/贷款金额", wcf);
		Label label_17 = new Label(1, 12, "贷款余额", wcf);
		Label label_18 = new Label(1, 15, "不良贷款", wcf);
		Label label_19 = new Label(2, 6, "总笔数(含大中型企业)", wcf);
		Label label_20 = new Label(2, 7, "其中：小微企业合计", wcf);
		Label label_21 = new Label(2, 8, "占小微企业笔数（%）", wcf);
		Label label_22 = new Label(2, 9, "总金额(含大中型企业)", wcf);
		Label label_23 = new Label(2, 10, "其中：小微企业合计", wcf);
		Label label_24 = new Label(2, 11, "占小微企业贷款（%）", wcf);
		Label label_25 = new Label(2, 12, "总金额(含大中型企业)", wcf);
		Label label_26 = new Label(2, 13, "其中：小微企业合计", wcf);
		Label label_27 = new Label(2, 14, "占小微企业余额（%）", wcf);
		Label label_28 = new Label(2, 15, "笔数", wcf);
		Label label_29 = new Label(2, 16, "金额", wcf);
		Label label_30 = new Label(2, 17, "不良率（%）", wcf);

		ws.mergeCells(0, 6, 0, 17);
		ws.mergeCells(1, 6, 1, 8);
		ws.mergeCells(1, 9, 1, 11);
		ws.mergeCells(1, 12, 1, 14);
		ws.mergeCells(1, 15, 1, 17);

		ws.addCell(label_14);
		ws.addCell(label_15);
		ws.addCell(label_16);
		ws.addCell(label_17);
		ws.addCell(label_18);
		ws.addCell(label_19);
		ws.addCell(label_20);
		ws.addCell(label_21);
		ws.addCell(label_22);
		ws.addCell(label_23);
		ws.addCell(label_24);
		ws.addCell(label_25);
		ws.addCell(label_26);
		ws.addCell(label_27);
		ws.addCell(label_28);
		ws.addCell(label_29);
		ws.addCell(label_30);

		// 把创建的内容写入到文件中，并关闭工作薄
		wwb.write();
		wwb.close();
	}
}

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
 * ʹ��jxl����excel
 *
 * @author zhout
 * @date 2017��3��22��
 */
public class JxlExcel {

	public static void main(String[] args) throws Exception {

		/*
		 * �����ķ�ʽ��ȡģ�岢�����
		 * 
			// ��ȡ�ļ�����·��
			String path = request.getSession().getServletContext().getRealPath("static/excel/NSXY.xls");
			// ����������
			Workbook rwb = null;
			WritableWorkbook wwb = null;
			// ���ļ��ж�xlsģ��
			InputStream is = new FileInputStream(path);
			rwb = Workbook.getWorkbook(is);
			// ��ɿɱ༭��
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			wwb = Workbook.createWorkbook(response.getOutputStream(), rwb, settings);
			// ��ȡ��һҳ
			WritableSheet ws = (WritableSheet) wwb.getSheet("Sheet1");
			// ���������ʽ
			response.setCharacterEncoding("UTF-8");
			String fileName = URLEncoder.encode("FileName", "UTF-8");  //�ļ���
			response.setContentType("application/vnd.ms-excel");	// ����������� 
			response.setHeader("Content-disposition","attachment;filename="+fileName+".xls");// �趨����ļ�ͷ 
		*/
		
		
		// ����������
		WritableWorkbook wwb = Workbook.createWorkbook(new File("E:/test.xls"));
		// �����µ�һҳ
		WritableSheet ws = wwb.createSheet("Sheet1", 0);
		// �������壬�߿�
		WritableFont wf = new WritableFont(WritableFont.TIMES, 14, WritableFont.NO_BOLD, false);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		// �Զ�����
		wcf.setWrap(true);
		wcf.setShrinkToFit(true);
		// ˮƽ����
		wcf.setAlignment(Alignment.CENTRE);
		// ��ֱ����
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		// �߿�
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

		// ��ʼ�����
		for (int i = 0; i < 6; i++) {
			for (int j = 1; j < 18; j++) {
				ws.setColumnView(i, 28);
				ws.addCell(new Label(i, j, "", wcf));
			}
		}

		/*
		 * ��ͬ���ֱ�ͷ ����Ҫ��ʾ������,����һ����Ԫ�� ��һ������Ϊ�����꣬�ڶ�������Ϊ�����꣬����������Ϊ����
		 */
		Label label_01 = new Label(0, 0, "����1", wcf);
		Label label_02 = new Label(0, 1, "��XXXX����ƽ����ͳ�Ʊ�", wcf);
		Label label_03 = new Label(0, 2, "���λ��XXXX", wcf);
		Label label_04 = new Label(4, 2, "��λ�������ʡ���Ԫ��%", wcf);
		Label label_05 = new Label(0, 3, "��˰�������ƽ������", wcf);
		Label label_06 = new Label(1, 3, "�Ƿ�����ϯ�����ƶ�", wcf);
		Label label_07 = new Label(1, 4, "��ʡ˰�����ǩ��Э�������ҵ���ڻ���  ������2016��6��30����ǩ��Э�飩", wcf);
		Label label_08 = new Label(3, 4, "10��", wcf);
		Label label_09 = new Label(3, 3, "��", wcf);
		Label label_10 = new Label(4, 4, "AA���С�BB���С�CC���С�DD���С�EE���С�FF���С�GG���С�HH���С�II���С�JJ����", wcf);
		Label label_11 = new Label(3, 5, "ʡ��ǩԼ����", wcf);
		Label label_12 = new Label(4, 5, "����ǩԼ����", wcf);
		Label label_13 = new Label(5, 5, "�ϼ�", wcf);

		/*
		 * �ϲ���Ԫ�� ��һ��������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ� �ڶ���������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��кţ�
		 * ������������Ҫ�ϲ��ĵ�Ԫ�����ҽǵ��кţ� ���ĸ�������Ҫ�ϲ��ĵ�Ԫ�������½ǵ��к�
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
		 * �� - ��˰����ʵ�ʷ��Ŵ������
		 */
		Label label_14 = new Label(0, 6, "ʵ�ʷ��Ŵ������", wcf);
		Label label_15 = new Label(1, 6, "����/�������", wcf);
		Label label_16 = new Label(1, 9, "����/������", wcf);
		Label label_17 = new Label(1, 12, "�������", wcf);
		Label label_18 = new Label(1, 15, "��������", wcf);
		Label label_19 = new Label(2, 6, "�ܱ���(����������ҵ)", wcf);
		Label label_20 = new Label(2, 7, "���У�С΢��ҵ�ϼ�", wcf);
		Label label_21 = new Label(2, 8, "ռС΢��ҵ������%��", wcf);
		Label label_22 = new Label(2, 9, "�ܽ��(����������ҵ)", wcf);
		Label label_23 = new Label(2, 10, "���У�С΢��ҵ�ϼ�", wcf);
		Label label_24 = new Label(2, 11, "ռС΢��ҵ���%��", wcf);
		Label label_25 = new Label(2, 12, "�ܽ��(����������ҵ)", wcf);
		Label label_26 = new Label(2, 13, "���У�С΢��ҵ�ϼ�", wcf);
		Label label_27 = new Label(2, 14, "ռС΢��ҵ��%��", wcf);
		Label label_28 = new Label(2, 15, "����", wcf);
		Label label_29 = new Label(2, 16, "���", wcf);
		Label label_30 = new Label(2, 17, "�����ʣ�%��", wcf);

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

		// �Ѵ���������д�뵽�ļ��У����رչ�����
		wwb.write();
		wwb.close();
	}
}

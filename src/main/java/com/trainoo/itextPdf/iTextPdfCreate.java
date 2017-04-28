package com.trainoo.itextPdf;

import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * itext生成PDF
 *
 * @author zhout
 * @date 2017年4月28日
 */
public class iTextPdfCreate {

	/**
	 * pdf基本操作
	 * 
	 * @author zhout
	 * @date 2017年4月28日
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// 页面大小
		Rectangle rect = new Rectangle(PageSize.A4);
		// 页面背景色
		// rect.setBackgroundColor(BaseColor.WHITE);

		// 新建一个document对象
		Document document = new Document(rect);
		// 文件输出流
		FileOutputStream fos = new FileOutputStream("C:/Users/Administrator/Desktop/myPDF.pdf");
		// document对象转pdf
		PdfWriter writer = PdfWriter.getInstance(document, fos);

		// PDF版本(默认1.4)
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
		
		// 设置密码为："World"
		/*writer.setEncryption("Hello".getBytes(), "World".getBytes(), PdfWriter.ALLOW_SCREENREADERS,
				PdfWriter.STANDARD_ENCRYPTION_128);*/
		
		// 文档属性
		document.addTitle("Title@sample");
		document.addAuthor("Author@rensanning");
		document.addSubject("Subject@iText sample");
		document.addKeywords("Keywords@iText");
		document.addCreator("Creator@iText");
		// 页边空白（左右上下）
		document.setMargins(10, 20, 30, 40);

		// 打开文档
		document.open();
		document.add(new Paragraph("Hello World"));

		// 新增一页
		document.newPage();
		// 默认true,确保页面为空(没有任何元素)也能添加进去一个空的页面，否则是不会新增的
		writer.setPageEmpty(false);
		document.newPage();
		document.add(new Paragraph("hello world2"));

		/*
		 *  Chunk对象: a String, a Font, and some attributes
		 */
		document.newPage();
		document.add(new Chunk("China"));
		document.add(new Chunk(" "));
		Font font = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id = new Chunk("chinese", font);
		id.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id.setTextRise(6);
		document.add(id);
		document.add(Chunk.NEWLINE);

		document.add(new Chunk("Japan"));
		Font font2 = new Font(Font.FontFamily.ZAPFDINGBATS, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id2 = new Chunk("japanese", font2);
		id2.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id2.setTextRise(6);
		id2.setUnderline(0.2f, -2f);
		document.add(id2);
		document.add(Chunk.NEWLINE);

		/*
		 *  Phrase对象: a List of Chunks with leading
		 */
		document.newPage();
		document.add(new Phrase("Phrase page"));

		Phrase director = new Phrase();
		Chunk name = new Chunk("China");
		name.setUnderline(0.2f, -2f);
		director.add(name);
		director.add(new Chunk(","));
		director.add(new Chunk(" "));
		director.add(new Chunk("chinese"));
		director.setLeading(24);
		document.add(director);

		Phrase director2 = new Phrase();
		Chunk name2 = new Chunk("Japan");
		name2.setUnderline(0.2f, -2f);
		director2.add(name2);
		director2.add(new Chunk(","));
		director2.add(new Chunk(" "));
		director2.add(new Chunk("japanese"));
		director2.setLeading(24);
		document.add(director2);

		/*
		 * Paragraph对象: a Phrase with extra properties and a newline
		 */
		document.newPage();
		document.add(new Paragraph("Paragraph page"));

		Paragraph info = new Paragraph();
		info.add(new Chunk("China "));
		info.add(new Chunk("chinese"));
		info.add(Chunk.NEWLINE);
		info.add(new Phrase("Japan "));
		info.add(new Phrase("japanese"));
		document.add(info);

		/*
		 *  List对象: a sequence of Paragraphs called ListItem
		 */
		document.newPage();
		List list = new List(List.ORDERED);
		for (int i = 0; i < 2; i++) {
			ListItem item = new ListItem(String.format("%s: %d movies", "country" + (i + 1), (i + 1) * 100));
			List movielist = new List(List.ORDERED, List.ALPHABETICAL);
			movielist.setLowercase(List.LOWERCASE);
			for (int j = 0; j < 5; j++) {
				ListItem movieitem = new ListItem("Title" + (j + 1));
				List directorlist = new List(List.UNORDERED);
				for (int k = 0; k < 2; k++) {
					directorlist.add(String.format("%s, %s", "Name1" + (k + 1), "Name2" + (k + 1)));
				}
				movieitem.add(directorlist);
				movielist.add(movieitem);
			}
			item.add(movielist);
			list.add(item);
		}
		document.add(list);

		/*
		 *  Anchor对象: 超链接、锚链接
		 */
		document.newPage();
		Paragraph country = new Paragraph();
		Anchor dest = new Anchor("china", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
		dest.setName("CN");
		dest.setReference("http://www.china.com");// external
		country.add(dest);
		country.add(String.format(": %d sites", 10000));
		document.add(country);

		document.newPage();
		Anchor toUS = new Anchor("Go to first page.",
				new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
		toUS.setReference("#CN");// internal
		document.add(toUS);

		/*
		 *  Image对象
		 */
		document.newPage();
		Image img = Image.getInstance("C:/Users/Administrator/Desktop/tx.jpg");
		img.setAlignment(Image.LEFT | Image.TEXTWRAP);
		img.setBorder(Image.BOX);
		img.setBorderWidth(10);
		img.setBorderColor(BaseColor.WHITE);
		img.scaleToFit(1000, 72);// 大小
		img.setRotationDegrees(-30);// 旋转
		document.add(img);

		/*
		 *  Chapter, Section对象（目录）
		 */
		document.newPage();
		Paragraph title = new Paragraph("Title");
		Chapter chapter = new Chapter(title, 1);

		title = new Paragraph("Section A");
		Section section = chapter.addSection(title);
		section.setBookmarkTitle("bmk");
		section.setIndentation(30);
		section.setBookmarkOpen(false);
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

		Section subsection = section.addSection(new Paragraph("Sub Section A"));
		subsection.setIndentationLeft(20);
		subsection.setNumberDepth(1);
		document.add(chapter);

		
		/*
		 * 插入表格
		 */
		document.newPage();
		PdfPTable table = new PdfPTable(3);  
		PdfPCell cell;  
		cell = new PdfPCell(new Phrase("Cell with colspan 3"));  
		cell.setColspan(3);  
		table.addCell(cell);  
		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));  
		cell.setRowspan(2);  
		table.addCell(cell);  
		table.addCell("row 1; cell 1");  
		table.addCell("row 1; cell 2");  
		table.addCell("row 2; cell 1");  
		table.addCell("row 2; cell 2");  
		document.add(table);  
		
		/*
		 * 生成条形码\二维码
		 */
		document.newPage();
		String myString = "http://www.google.com";  
		Barcode128 code128 = new Barcode128();  
		code128.setCode(myString.trim());  
		code128.setCodeType(Barcode128.CODE128);  
		PdfContentByte cb = writer.getDirectContent();
		Image code128Image = code128.createImageWithBarcode(cb, BaseColor.BLUE, BaseColor.BLACK);  
		code128Image.setAbsolutePosition(10,700);  
		code128Image.scalePercent(205);  
		document.add(code128Image);  
		// 二维码
		BarcodeQRCode qrcode = new BarcodeQRCode(myString.trim(), 1, 1, null);  
		Image qrcodeImage = qrcode.getImage();  
		qrcodeImage.setAbsolutePosition(10,500);  
		qrcodeImage.scalePercent(400);  
		document.add(qrcodeImage);  
		
		
		// 关闭文档
		document.close();
		fos.flush();
		fos.close();
	}

	/**
	 * 增加水印
	 * @author zhout
	 * @date 2017年4月28日
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		// 读取文件
		PdfReader reader = new PdfReader("C:/Users/Administrator/Desktop/myPDF.pdf");
		// 文件输出
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("C:/Users/Administrator/Desktop/myPDF1.pdf"));

		// 图片水印, 亦可以用来设置背景图片
		Image img = Image.getInstance("C:/Users/Administrator/Desktop/tx.jpg");
		img.setAbsolutePosition(200, 400);
		// write under the page, 如果有设置背景色，会看不到水印，因为水印是在背景下面那层
		PdfContentByte under = stamp.getUnderContent(1);
		under.addImage(img);

		// 文字水印
		PdfContentByte over = stamp.getOverContent(2);
		over.beginText();
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		over.setFontAndSize(bf, 18);
		over.setTextMatrix(30, 30);
		over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 30);
		over.endText();

		// 关闭文档
		stamp.close();
		reader.close();
		System.out.println("end................");
	}
}

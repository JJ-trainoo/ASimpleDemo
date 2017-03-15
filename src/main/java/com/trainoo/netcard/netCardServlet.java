package com.trainoo.netcard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.trainoo.utils.DateUtil;
import com.trainoo.utils.HttpUtil;
import com.trainoo.utils.LunarCalendar;

import nl.bitwalker.useragentutils.UserAgent;

/**
 * ����һ��ip��Ƭ
 *
 * @author zhout
 * @date 2017��3��15��
 */
public class netCardServlet extends HttpServlet {

	/**
	 * @author zhout
	 * @date 2017��3��10��
	 */
	private static final long serialVersionUID = -2971903955977636139L;
	

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȡ�û���������͸�ϵͳ����
		UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
		String operatingSystem = userAgent.getOperatingSystem().getName();
		String browserName = userAgent.getBrowser().getName();
		String browVersion = userAgent.getBrowserVersion().getVersion().split("\\.")[0];
		System.out.println(operatingSystem + " " + browserName + " " + browVersion);
		// ��ȡ�û�ip��ַ
		/*
		 * HttpUtil.get("http://ip.chinaz.com/getip.aspx", "UTF-8")
		 * "{\"address\":\"�㶫ʡ������ ����\",\"ip\":\"202.104.140.90\"}"
		 */
		String json = HttpUtil.get("http://ip.chinaz.com/getip.aspx", "UTF-8");
		Map<String, String> map = (Map<String, String>) JSONObject.parse(json);
		String ip = map.get("ip");
		String address = map.get("address");
		System.out.println(map);
		//�õ������
		OutputStream os = resp.getOutputStream();
		try {
			//����ͼƬ
			String path = req.getScheme() + "://" + req.getServerName()+ ":" + req.getServerPort() ;
			createImg(os, path, operatingSystem, browserName, browVersion, ip, address);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(os != null){
				os.close();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * ���ݻ�ȡ������Ϣ����һ��ͼƬ
	 * @author zhout
	 * @date 2017��3��13��
	 * @param os  ͼƬ������
	 * @param operatingSystem ����ϵͳ
	 * @param browserName  �����
	 * @param browVersion  ������汾
	 * @param ip           ip
	 * @param address      ip��ַ
	 * @throws IOException
	 */
	private static void createImg(OutputStream os, String path, String operatingSystem, String browserName, String browVersion,
			String ip, String address) throws IOException {
		BufferedImage buffImg = new BufferedImage(300, 126, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) buffImg.getGraphics();
		//���ñ�����ɫ
		/*Color bColor = new Color(175, 175, 175, 100);
		Color bColor2 = new Color(244, 244, 244, 200);
		g.setColor(bColor);
		RoundRectangle2D roundedRectangle1 = new RoundRectangle2D.Float(0, 0, 300, 126, 10, 10);
		g.fill(roundedRectangle1);
		g.setColor(bColor2);
		RoundRectangle2D roundedRectangle2 = new RoundRectangle2D.Float(3, 3, 294, 120, 10, 10);
		g.fill(roundedRectangle2);*/
		//���ñ���ͼƬ
		URL input = new URL(path + "/ASimpleDemo/static/images/netcard/netCard.png");
		BufferedImage bImg = ImageIO.read(input);
		g.drawImage(bImg, 0, 0, null);
		//����������ɫ
		g.setColor(new Color(110, 110, 110));
		g.setFont(new Font("΢���ź�", Font.PLAIN, 11));
		//��ӡip��ַ
		g.drawString("������:" + address + "...", 30, 32);
		g.drawString(operatingSystem, 249 - operatingSystem.length()*5 , 32);
		//��ӡip
		g.drawString("IP:" + ip, 30, 54);
		g.drawString(browserName + " ��" + browVersion+"��", 249 - (2+browserName.length()+browVersion.length())*5, 54);
		//��ӡ����
		String yMD = DateUtil.getYMD_CN();
		g.drawString("������" + yMD, 30, 76);
		//��ӡ����
		String dayOfWeek = DateUtil.getDayOfWeek();
		g.drawString(dayOfWeek, 200, 76);
		//��ӡ�����Ϣ
		g.setColor(new Color(216, 65, 65));
		g.drawString("���߹���", 30, 100);
		g.setColor(new Color(72, 142, 72));
		g.drawString("zhoutao.com", 200, 100);
		//ͼƬ���
		ImageIO.write(buffImg, "png", os);
		//�ر���
		os.close();
	}
	
	public static void main(String[] args) {
		LunarCalendar lc  = LunarCalendar.getInstance();
		Calendar c = Calendar.getInstance();
		lc.set(c);
	}
}

package com.trainoo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ũ�����ת��
 *
 * @author zhout
 * @date 2017��3��13��
 */
public class LunarCalendar {

	private int lyear;

	private int lmonth;

	private int lday;

	private boolean leap;

	private String solarTerms = "";

	private int yearCyl, monCyl, dayCyl;

	private String solarFestival = "";

	private String lunarFestival = "";

	private Calendar baseDate = Calendar.getInstance();

	private Calendar offDate = Calendar.getInstance();

	private SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy��MM��dd��");

	final static long[] lunarInfo = new long[] { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0,
			0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
			0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
			0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0,
			0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573,
			0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950,
			0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
			0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970,
			0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50,
			0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
			0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260,
			0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
			0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0 };

	final static String[] Gan = new String[] { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };

	final static String[] Zhi = new String[] { "��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", "��", "��" };

	final static String[] Animals = new String[] { "��", "ţ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };

	final static String[] SolarTerm = new String[] { "С��", "��", "����", "��ˮ", "����", "����", "����", "����", "����", "С��", "â��",
			"����", "С��", "����", "����", "����", "��¶", "���", "��¶", "˪��", "����", "Сѩ", "��ѩ", "����" };

	final static long[] STermInfo = new long[] { 0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551,
			218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532,
			504758 };

	final static String chineseMonthNumber[] = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "��", "��" };

	final static String[] sFtv = new String[] { "0101*Ԫ��", "0214 ���˽�", "0308 ��Ů��", "0312 ֲ����", "0314 ���ʾ�����",
			"0315 ������Ȩ����", "0323 ����������", "0401 ���˽�", "0407 ����������", "0501*�Ͷ���", "0504 �����", "0508 ��ʮ����", "0512 ��ʿ��",
			"0515 ���ʼ�ͥ��", "0517 ���������", "0519 ȫ��������", "0531 ����������", "0601 ��ͯ��", "0605 ���绷����", "0606 ȫ��������",
			"0623 ����ƥ����", "0625 ȫ��������", "0626 ����Ʒ��", "0701 ������", "0707 ��ս������", "0711 �����˿���", "0801 ������", "0908 ����ɨä��",
			"0909 ëxx��������", "0910 ��ʦ��", "0917 ���ʺ�ƽ��", "0920 ���ʰ�����", "0922 �������˽�", "0927 ����������", "0928 ���ӵ���", "1001*�����",
			"1004 ���綯����", "1006 ���˽�", "1007 ����ס����", "1009 ����������", "1015 ����ä�˽�", "1016 ������ʳ��", "1024 ���Ϲ���", "1031 ��ʥ��",
			"1108 �й�������", "1109 ����������", "1112 ����ɽ����", "1114 ����������", "1117 ���ʴ�ѧ����", "1128 �ж���", "1201 ���簬�̲���",
			"1203 ����м�����", "1209 ����������", "1220 ���Żع�", "1225 ʥ����", "1226 ëxx����" };

	final static String[] lFtv = { "0101*����", "0115 Ԫ��", "0505 ����", "0707 ��Ϧ", "0815 ����", "0909 ����", "1208 ����",
			"1223 С��", "0100*��Ϧ" };

	final static String[] wFtv = { "0521 ĸ�׽�", "0631 ���׽�" };// ÿ��6�µ�3���������Ǹ��׽�,5�µĵ�2����������ĸ�׽�

	// ��������һ���ܵĵ�1���3��������Ҳ���ǵ�3�������ܵĵ�һ��

	//
	private LunarCalendar() {
		baseDate.setMinimalDaysInFirstWeek(7);// ����һ���µĵ�һ������һ��������
	}

	final private static int lYearDays(int y)// ====== ����ũ�� y���������
	{
		int i, sum = 348;
		for (i = 0x8000; i > 0x8; i >>= 1) {
			if ((lunarInfo[y - 1900] & i) != 0)
				sum += 1;
		}
		return (sum + leapDays(y));
	}

	final private static int leapDays(int y)// ====== ����ũ�� y�����µ�����
	{
		if (leapMonth(y) != 0) {
			if ((lunarInfo[y - 1900] & 0x10000) != 0)
				return 30;
			else
				return 29;
		} else
			return 0;
	}

	final private static int leapMonth(int y)// ====== ����ũ�� y�����ĸ��� 1-12 , û�򴫻� 0
	{
		return (int) (lunarInfo[y - 1900] & 0xf);
	}

	final public static int monthDays(int y, int m)// ====== ����ũ�� y��m�µ�������
	{
		if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
			return 29;
		else
			return 30;
	}

	final private static String AnimalsYear(int y)// ====== ����ũ�� y�����Ф
	{

		return Animals[(y - 4) % 12];
	}

	final private static String cyclical(int num)// ====== ���� ��offset ���ظ�֧,
	// 0=����
	{

		return (Gan[num % 10] + Zhi[num % 12]);
	}

	// ===== ĳ��ĵ�n������Ϊ����(��0С������)
	final private int sTerm(int y, int n) {

		offDate.set(1900, 0, 6, 2, 5, 0);
		long temp = offDate.getTime().getTime();
		offDate.setTime(new Date((long) ((31556925974.7 * (y - 1900) + STermInfo[n] * 60000L) + temp)));

		return offDate.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ����y��m��d�ն�Ӧ��ũ��.
	 *
	 */
	private void CalculateLunarCalendar(int y, int m, int d) {

		int leapMonth = 0;

		try {
			baseDate.setTime(chineseDateFormat.parse("1900��1��31��"));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		long base = baseDate.getTimeInMillis();
		try {
			baseDate.setTime(chineseDateFormat.parse(y + "��" + m + "��" + d + "��"));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		long obj = baseDate.getTimeInMillis();

		int offset = (int) ((obj - base) / 86400000L);
		// System.out.println(offset);
		// �����1900��1��31����������

		dayCyl = offset + 40;// ��֧��
		monCyl = 14;// ��֧��

		// ��offset��ȥÿũ���������
		// ���㵱����ũ���ڼ���
		// i���ս����ũ�������
		// offset�ǵ���ĵڼ���
		int iYear, daysOfYear = 0;
		for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
			daysOfYear = lYearDays(iYear);
			offset -= daysOfYear;
			monCyl += 12;
		}
		if (offset < 0) {
			offset += daysOfYear;
			iYear--;
			monCyl -= 12;
		}
		// ũ�����
		lyear = iYear;

		yearCyl = iYear - 1864;// ***********��֧��**********//

		leapMonth = leapMonth(iYear); // ���ĸ���,1-12
		leap = false;

		// �õ��������offset,�����ȥÿ�£�ũ��������������������Ǳ��µĵڼ���
		int iMonth, daysOfMonth = 0;
		for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
			// ����
			if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
				--iMonth;
				leap = true;
				daysOfMonth = leapDays(iYear);
			} else
				daysOfMonth = monthDays(iYear, iMonth);

			offset -= daysOfMonth;
			// �������
			if (leap && iMonth == (leapMonth + 1))
				leap = false;
			if (!leap)
				monCyl++;
		}
		// offsetΪ0ʱ�����Ҹղż�����·������£�ҪУ��
		if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
			if (leap) {
				leap = false;
			} else {
				leap = true;
				--iMonth;
				--monCyl;
			}
		}
		// offsetС��0ʱ��ҲҪУ��
		if (offset < 0) {
			offset += daysOfMonth;
			--iMonth;
			--monCyl;
		}
		lmonth = iMonth;
		lday = offset + 1;

		// ******************�������**********//

		if (d == sTerm(y, (m - 1) * 2))
			solarTerms = SolarTerm[(m - 1) * 2];
		else if (d == sTerm(y, (m - 1) * 2 + 1))
			solarTerms = SolarTerm[(m - 1) * 2 + 1];
		else
			solarTerms = "";

		// ���㹫������
		this.solarFestival = "";
		for (int i = 0; i < sFtv.length; i++) {
			if (Integer.parseInt(sFtv[i].substring(0, 2)) == m && Integer.parseInt(sFtv[i].substring(2, 4)) == d) {
				solarFestival = sFtv[i].substring(5);
			}
		}
		// ����ũ������
		this.lunarFestival = "";
		for (int i = 0; i < lFtv.length; i++) {
			if (Integer.parseInt(lFtv[i].substring(0, 2)) == lmonth
					&& Integer.parseInt(lFtv[i].substring(2, 4)) == lday) {
				lunarFestival = lFtv[i].substring(5);
			}
		}
		// �������ܽ���

		// System.out.println(baseDate.get(Calendar.WEEK_OF_MONTH) + ""
		// + baseDate.get(Calendar.DAY_OF_WEEK));

		for (int i = 0; i < wFtv.length; i++) {
			if (Integer.parseInt(wFtv[i].substring(0, 2)) == m
					&& Integer.parseInt(wFtv[i].substring(2, 3)) == baseDate.get(Calendar.WEEK_OF_MONTH)
					&& Integer.parseInt(wFtv[i].substring(3, 4)) == baseDate.get(Calendar.DAY_OF_WEEK)) {
				solarFestival += wFtv[i].substring(5);
			}
		}

	}

	// set����
	public void set(int y, int m, int d) {

		CalculateLunarCalendar(y, m, d);
	}

	public void set(Calendar cal) {

		CalculateLunarCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
	}

	// //get������
	public static LunarCalendar getInstance() {
		return new LunarCalendar();
	}
}

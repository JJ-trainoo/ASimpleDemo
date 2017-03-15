package com.trainoo.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ڹ�����
 */
public final class DateUtil {
	/**
	 * ��ֹʵ����
	 */
	private DateUtil() {
	}

	/**
	 * ����Ϊ����ת���ĸ�ʽ Ĭ�Ϸ�ʽ
	 */
	public static final String YMD_DATA = "yyyy-MM-dd";
	public static final String yyyyMMdd = "yyyyMMdd";
	/** ���ڸ�ʽ **/
	public static final String YMD01_DATA = "yyyy/MM/dd";

	/** ���ڸ�ʽ **/
	public static final String YMDHMS_DATA = "yyyy-MM-dd HH:mm:ss";

	/** ���ڸ�ʽ **/
	public static final String YMDSTRING_DATA = "yyyyMMddHHmmss";

	/** ���ڸ�ʽ **/
	public static final String YMDGB_DATA = "yyyy��MM��dd��";

	/** ���ڸ�ʽ **/
	public static final String YMDTHMSGB_DATA = "yyyy��MM��dd�� HHʱmm��ss��";

	/** ���ڸ�ʽ **/
	public static final String YMD24H_DATA = "yyyy-MM-dd HH:mm:ss";

	/** �������ı�ʾ */
	public static final String[] DAYNAMES = { "������", "����һ", "���ڶ�", "������",
			"������", "������", "������" };

	/** һ��ĺ����� **/
	public static final int ONE_DATE_MILLISECOND = 24 * 60 * 60 * 1000;

	/**
	 * ��ȡ��ǰ���
	 * 
	 * @return ��ǰ���
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * ��ȡ��ǰ�·�
	 * 
	 * @return ��ǰ�·�
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	/**
	 * ��ȡ��ǰ���ڣ��죩
	 * 
	 * @return ��ǰ�·�
	 */
	public static int getDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��ȡ��������(����һ�����ڶ�....)
	 * 
	 * @return ��������
	 */
	public static String getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return DAYNAMES[dayOfWeek - 1];
	}

	/**
	 * 
	 * getDateWeek(����ָ�����ڵ����� 0,1,2,3,4,5,6)
	 * 
	 * @param date
	 *            ָ�����ڸ�ʽΪ YYYY-MM-DD
	 * @return String
	 * @exception
	 */
	public static String getDateWeek(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date, "YYYY-MM-DD"));
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek + "";
	}

	/**
	 * ��ȡ��ǰʱ�����ڱ�ʾ����ʽ YYYY-MM-DD
	 * 
	 * @return ��ǰʱ�����ڱ�ʾ����ʽ YYYY-MM-DD
	 */
	public static String getYMD() {
		return getYMD("-");
	}

	/**
	 * ��ȡ��ǰʱ��Ӣ�����ڱ�ʾ����ʽ YYYY��MM��DD��
	 * 
	 * @param �ָ��
	 *            ���磺��/������-������.��������
	 * @return ��ǰʱ��Ӣ�����ڱ�ʾ����ʽ YYYY?MM?DD?
	 */
	public static String getYMD(String separator) {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR) + separator
				+ (now.get(Calendar.MONTH) + 1) + separator
				+ now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ��ȡ��ǰʱ���������ڱ�ʾ����ʽ YYYY��MM��DD��
	 * 
	 * @return ��ǰʱ���������ڱ�ʾ����ʽ YYYY��MM��DD��
	 */
	public static String getYMD_CN() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR) + "��" + (now.get(Calendar.MONTH) + 1)
				+ "��" + now.get(Calendar.DAY_OF_MONTH) + "��";
	}

	/**
	 * �� Date ����ת��Ϊ�ƶ���ʽ�ַ���
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateFormat(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				DateFormat dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * �� Calendar ����ת��Ϊ�ƶ���ʽ�ַ���
	 * 
	 * @param calendar
	 * @param format
	 * @return
	 */
	public static String getDateFormat(Calendar calendar, String format) {
		return getDateFormat(calendar.getTime(), format);
	}


	/**
	 * �� �����ַ���תΪ Date ����
	 * 
	 * @param dateStr
	 * @param format
	 * @return Date ����ʱ��
	 */
	public static Date parseDate(String dateStr, String format) {
		if (dateStr == null || dateStr.length() == 0) {
			return null;
		}

		Date date = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}

	/**
	 * 
	 * getToday(��ȡ��ǰ���ڣ�������ʱ����) (����������������������� �C ��ѡ)
	 * 
	 * @return Date
	 * @exception
	 */
	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 
	 * getDayDiff(���������������������end - start) (����������������������� �C ��ѡ)
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 * @exception
	 */
	public static int getDayDiff(Date start, Date end) {
		return (int) ((end.getTime() - start.getTime()) / ONE_DATE_MILLISECOND);
	}

	/**
	 * ��ȡ��ǰ�µĵ�һ��
	 * 
	 * @return
	 */
	public static String getMonthStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		return getDateFormat(calendar, "yyyy-MM-dd");
	}

	/**
	 * ��ȡ��Ӧ�·ݵ����һ��
	 * 
	 * @param date
	 * @return
	 */
	public static String doGetMonthEnd(String querymonth) {
		String monthend = "";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			date = sdf.parse(querymonth);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			monthend = sdf.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monthend;
	}

	/**
	 * ��ȡ���������
	 * 
	 * @param date
	 * @return
	 */
	public static Map<String, String> getThreeYM() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 1; i > -2; i--) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, i);
			// ����ж� calendar �·�Ϊ0 ʱ����·� ����
			if (calendar.get(Calendar.MONTH) == 0) {
				int year = calendar.get(Calendar.YEAR) - 1;
				map.put(year + "��" + "12��", year + "-12" + "-01");
			} else {
				map.put(calendar.get(Calendar.YEAR) + "��"
						+ calendar.get(Calendar.MONTH) + "��",
						calendar.get(Calendar.YEAR) + "-"
								+ calendar.get(Calendar.MONTH) + "-01");
			}
		}
		return map;
	}

	/**
	 * ��õ��쿪ʼʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date dayBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * ��õ����ĳ��ļ�����ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getlaterdaytime(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				+ days);
		return calendar.getTime();
	}

	/**
	 * ��ü�����ǰ��ʱ��
	 * 
	 * @param timestr
	 * @param minutes
	 * @return
	 * @throws ParseException
	 */
	public static String getNewTime(String timestr, int minutes)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YMD24H_DATA);
		long millionSeconds = sdf.parse(timestr).getTime();
		millionSeconds -= (long) minutes * 60 * 1000;
		return DateUtil.getDateFormat(new Date(millionSeconds),
				DateUtil.YMD24H_DATA);
	}

	/**
	 * ��õ����ĳ��ļ����º��ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getlatermonthstime(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
//		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	
	public static void main(String[] args) throws ParseException {
		System.out.println(getDateFormat(getlatermonthstime(new Date(),2),"yyyy-MM-dd"));
		System.out.println(getDateFormat(getlaterdaytime(new Date(),2),"yyyy-MM-dd"));
	}

	/**
	 * ��õ���Ľ���ʱ��
	 * 
	 * @param date
	 * @return
	 */

	public static Date dayEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
    /**
     *  ���ĳ�����һ��
     * @param year
     * @param month
     * @return
     */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
	}
    /**
     * ���ĳ�µ�һ��
     * @param year
     * @param month
     * @return
     */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
	}
	
	
	/**
	 * �����������
	 * @return
	 */
	public static String toDay(){
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE,0);
		String tday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return tday;
	}
	
	/**
	 * ������������
	 * @return
	 */
	public static String yeasterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}
	
	/**
	 * ����ǰ������
	 * @return
	 */
	public static String yeasterdayTwo(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -2);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}
	/**
	 * ���� n��ǰ  ����
	 * @return
	 */
	public static String dateofNumbDays(int n){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -n);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}
	
	
	/**
	 * �����ϸ��µ�һ��
	 * @return
	 */
	public static String firstDayOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// �����ʽ
		String str = df.format(calendar.getTime());
		return str;

	}
	
	/**
	 * ����n ��ǰ��һ��
	 * @return
	 */
	public static String firstDayToNumbMonth(int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, - 1*months);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// �����ʽ
		String str = df.format(calendar.getTime());
		return str;

	}

	/**
	 * �����ϸ������һ��
	 * @return
	 */
	public static String lastDayOfLastMonth() {
		
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// �����ʽ������ʾ����
		String str = sdf.format(calendar.getTime()); 
		return str;
	}
	
	/**
	 * ����N��ǰ���һ��
	 * @return
	 */
	public static String lastDayToNumbMonth(int months) {
		
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1*months);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// �����ʽ������ʾ����
		String str = sdf.format(calendar.getTime()); 
		return str;
	}
	
	/**
	 * �����ϸ������һ��������
	 */
	public static String firstDayOfLastWeek(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // ����ʱ���ʽ
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// �ж�Ҫ����������Ƿ������գ���������һ����������ģ����������⣬���㵽��һ��ȥ��
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// ����һ�����ڵĵ�һ�죬���й���ϰ��һ�����ڵĵ�һ��������һ
		int day = cal.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7);// ���������Ĺ��򣬸���ǰ���ڼ�ȥ���ڼ���һ�����ڵ�һ��Ĳ�ֵ
		
		String imptimeBegin = sdf.format(cal.getTime());
		return imptimeBegin;
		
	}
	
	/**
	 * ���� N���� ǰ ��һ
	 */
	public static String firstdaytoNumWeek(int weeks){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // ����ʱ���ʽ
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// �ж�Ҫ����������Ƿ������գ���������һ����������ģ����������⣬���㵽��һ��ȥ��
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// ����һ�����ڵĵ�һ�죬���й���ϰ��һ�����ڵĵ�һ��������һ
		int day = cal.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7*weeks);// ���������Ĺ��򣬸���ǰ���ڼ�ȥ���ڼ���һ�����ڵ�һ��Ĳ�ֵ
		
		String imptimeBegin = sdf.format(cal.getTime());
		return imptimeBegin;
		
	}
	
	/**
	 * �����ϸ����������������
	 */
	public static String lastDayOfLastWeek(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // ����ʱ���ʽ
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// �ж�Ҫ����������Ƿ������գ���������һ����������ģ����������⣬���㵽��һ��ȥ��
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);	// ����һ�����ڵĵ�һ�죬���й���ϰ��һ�����ڵĵ�һ��������һ
		int day = cal.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7);// ���������Ĺ��򣬸���ǰ���ڼ�ȥ���ڼ���һ�����ڵ�һ��Ĳ�ֵ
		
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		return imptimeEnd;
		
	}
	
	/**
	 * ���� N���� ǰ ����
	 */
	public static String lastDaytoNumWeek(int weeks){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // ����ʱ���ʽ
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// �ж�Ҫ����������Ƿ������գ���������һ����������ģ����������⣬���㵽��һ��ȥ��
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);	// ����һ�����ڵĵ�һ�죬���й���ϰ��һ�����ڵĵ�һ��������һ
		int day = cal.get(Calendar.DAY_OF_WEEK);// ��õ�ǰ������һ�����ڵĵڼ���
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7*weeks);// ���������Ĺ��򣬸���ǰ���ڼ�ȥ���ڼ���һ�����ڵ�һ��Ĳ�ֵ
		
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		return imptimeEnd;
		
	}
	
	/**
	 * �����ϸ����ȵ�һ��
	 * @return
	 */
	public static String lastSeasonFirstDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		int curMonth = cal.get(Calendar.MONTH)+1;
		int curYear = cal.get(Calendar.YEAR);
		String lastSeasonFirstDate = "";
		if(curMonth>=1 && curMonth<=3){
			curYear -= 1;
			lastSeasonFirstDate = String.valueOf(curYear) + "-10-01";
		}else if (curMonth>=4 && curMonth<=6)
			lastSeasonFirstDate = String.valueOf(curYear) + "-01-01";
		else if (curMonth>=7 && curMonth<=9)
			lastSeasonFirstDate = String.valueOf(curYear) + "-04-01";
		else if (curMonth>=10 && curMonth<=12)
			lastSeasonFirstDate = String.valueOf(curYear) + "-07-01";
		
		return lastSeasonFirstDate;
	}
	
	/**
	 * ���� N����ǰ ��һ��
	 * @return
	 */
	public static String FirstDateOfNumbSeason(int seas) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(cal.MONTH, -(seas-1)*3);
		int curMonth = cal.get(Calendar.MONTH)+1;
		int curYear = cal.get(Calendar.YEAR);
		String lastSeasonFirstDate = "";
		if(curMonth>=1 && curMonth<=3){
			curYear -= 1;
			lastSeasonFirstDate = String.valueOf(curYear) + "-10-01";
		}else if (curMonth>=4 && curMonth<=6)
			lastSeasonFirstDate = String.valueOf(curYear) + "-01-01";
		else if (curMonth>=7 && curMonth<=9)
			lastSeasonFirstDate = String.valueOf(curYear) + "-04-01";
		else if (curMonth>=10 && curMonth<=12)
			lastSeasonFirstDate = String.valueOf(curYear) + "-07-01";
		
		return lastSeasonFirstDate;
	}
	
	/**
	 * ���� N����ǰ ĩ��
	 * @return
	 */
	public static String lastDateOfNumbSeason(int seas) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(cal.MONTH, -(seas-1)*3);
		int curMonth = cal.get(Calendar.MONTH)+1;
		int curYear = cal.get(Calendar.YEAR);
		String lastSeasonLastDate = "";
		if(curMonth>=1 && curMonth<=3){
			curYear -= 1;
			lastSeasonLastDate = curYear + "-12-31";
		}else if (curMonth>=4 && curMonth<=6)
			lastSeasonLastDate = curYear + "-03-31";
		else if (curMonth>=7 && curMonth<=9)
			lastSeasonLastDate = curYear + "-06-30";
		else if (curMonth>=10 && curMonth<=12)
			lastSeasonLastDate = curYear + "-09-30";
		
		return lastSeasonLastDate;
	}
	
	/**
	 * �����ϸ�����ĩ��
	 * @return
	 */
	public static String lastSeasonLastDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curMonth = cal.get(Calendar.MONTH)+1;
		int curYear = cal.get(Calendar.YEAR);
		String lastSeasonLastDate = "";
		if(curMonth>=1 && curMonth<=3){
			curYear -= 1;
			lastSeasonLastDate = curYear + "-12-31";
		}else if (curMonth>=4 && curMonth<=6)
			lastSeasonLastDate = curYear + "-03-31";
		else if (curMonth>=7 && curMonth<=9)
			lastSeasonLastDate = curYear + "-06-30";
		else if (curMonth>=10 && curMonth<=12)
			lastSeasonLastDate = curYear + "-09-30";
		return lastSeasonLastDate;
	}
	
	/**
	 * ����ȥ���һ������
	 * @return
	 */
	public static String lastYeatFirstDate(){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear = cal.get(Calendar.YEAR);
		String lastYear = String.valueOf(curYear-1);
		
		return lastYear + "-01-01";
	}
	
	/**
	 * ���� n��ǰ ��һ������
	 * @return
	 */
	public static String firstDateOfNumbYear(int years){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear = cal.get(Calendar.YEAR)-1*years;
		return String.valueOf(curYear) + "-01-01";
	}
	
	/**
	 * ���� n��ǰ ���һ������
	 * @return
	 */
	public static String LastDateOfNumbYear(int years){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear = cal.get(Calendar.YEAR)-1*years;
		return String.valueOf(curYear) + "-12-31";
	}
	
	/**
	 * ����ȥ�����һ������
	 * @return
	 */
	public static String lastYeatLastDate(){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear = cal.get(Calendar.YEAR)-1;
		
		return String.valueOf(curYear) + "-12-31";

	}
	
	
	/**
	 * ��������int , �õ� �ٷֱ�
	 */
	public  static double getdivPercent(int divisor, int dividend) {
		double result = 0.00;
		
		if (dividend != 0){
			result = ((double)divisor) / ((double)dividend) *100 ;
			result = new BigDecimal(result).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		}
		return result;
	}
	
	/**
	 * ��������int , �õ�double
	 */
	public  static double getdivDouble(int divisor, int dividend) {
		double result = 0.00;
		
		if (dividend != 0){
			result = ((double)divisor) / ((double)dividend);
			result = new BigDecimal(result).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		}
		
		return result;
	}

	
}


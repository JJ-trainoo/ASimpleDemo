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
 * 日期工具类
 */
public final class DateUtil {
	/**
	 * 禁止实例化
	 */
	private DateUtil() {
	}

	/**
	 * 以下为日期转换的格式 默认方式
	 */
	public static final String YMD_DATA = "yyyy-MM-dd";
	public static final String yyyyMMdd = "yyyyMMdd";
	/** 日期格式 **/
	public static final String YMD01_DATA = "yyyy/MM/dd";

	/** 日期格式 **/
	public static final String YMDHMS_DATA = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式 **/
	public static final String YMDSTRING_DATA = "yyyyMMddHHmmss";

	/** 日期格式 **/
	public static final String YMDGB_DATA = "yyyy年MM月dd日";

	/** 日期格式 **/
	public static final String YMDTHMSGB_DATA = "yyyy年MM月dd日 HH时mm分ss秒";

	/** 日期格式 **/
	public static final String YMD24H_DATA = "yyyy-MM-dd HH:mm:ss";

	/** 星期中文表示 */
	public static final String[] DAYNAMES = { "星期日", "星期一", "星期二", "星期三",
			"星期四", "星期五", "星期六" };

	/** 一天的毫秒数 **/
	public static final int ONE_DATE_MILLISECOND = 24 * 60 * 60 * 1000;

	/**
	 * 获取当前年份
	 * 
	 * @return 当前年份
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 * 
	 * @return 当前月份
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	/**
	 * 获取当前日期（天）
	 * 
	 * @return 当前月份
	 */
	public static int getDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取中文星期(星期一、星期二....)
	 * 
	 * @return 中文星期
	 */
	public static String getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return DAYNAMES[dayOfWeek - 1];
	}

	/**
	 * 
	 * getDateWeek(返回指定日期的星期 0,1,2,3,4,5,6)
	 * 
	 * @param date
	 *            指定日期格式为 YYYY-MM-DD
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
	 * 获取当前时间日期表示，格式 YYYY-MM-DD
	 * 
	 * @return 当前时间日期表示，格式 YYYY-MM-DD
	 */
	public static String getYMD() {
		return getYMD("-");
	}

	/**
	 * 获取当前时间英文日期表示，格式 YYYY年MM月DD日
	 * 
	 * @param 分割符
	 *            ，如：“/”，“-”，“.”，“”
	 * @return 当前时间英文日期表示，格式 YYYY?MM?DD?
	 */
	public static String getYMD(String separator) {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR) + separator
				+ (now.get(Calendar.MONTH) + 1) + separator
				+ now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前时间中文日期表示，格式 YYYY年MM月DD日
	 * 
	 * @return 当前时间中文日期表示，格式 YYYY年MM月DD日
	 */
	public static String getYMD_CN() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR) + "年" + (now.get(Calendar.MONTH) + 1)
				+ "月" + now.get(Calendar.DAY_OF_MONTH) + "日";
	}

	/**
	 * 将 Date 对象转换为制定格式字符串
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
	 * 将 Calendar 对象转换为制定格式字符串
	 * 
	 * @param calendar
	 * @param format
	 * @return
	 */
	public static String getDateFormat(Calendar calendar, String format) {
		return getDateFormat(calendar.getTime(), format);
	}


	/**
	 * 将 日期字符串转为 Date 对象
	 * 
	 * @param dateStr
	 * @param format
	 * @return Date 类型时间
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
	 * getToday(获取当前日期，不包含时分秒) (这里描述这个方法适用条件 – 可选)
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
	 * getDayDiff(计算两个日期相差天数：end - start) (这里描述这个方法适用条件 – 可选)
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
	 * 获取当前月的第一天
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
	 * 获取对应月份的最后一天
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
	 * 获取最近三个月
	 * 
	 * @param date
	 * @return
	 */
	public static Map<String, String> getThreeYM() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 1; i > -2; i--) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, i);
			// 添加判断 calendar 月份为0 时候的月份 年月
			if (calendar.get(Calendar.MONTH) == 0) {
				int year = calendar.get(Calendar.YEAR) - 1;
				map.put(year + "年" + "12月", year + "-12" + "-01");
			} else {
				map.put(calendar.get(Calendar.YEAR) + "年"
						+ calendar.get(Calendar.MONTH) + "月",
						calendar.get(Calendar.YEAR) + "-"
								+ calendar.get(Calendar.MONTH) + "-01");
			}
		}
		return map;
	}

	/**
	 * 获得当天开始时间
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
	 * 获得当天或某天的几天后的时间
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
	 * 获得几分钟前的时间
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
	 * 获得当天或某天的几个月后的时间
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
	 * 获得当天的结束时间
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
     *  获得某月最后一天
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
     * 获得某月第一天
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
	 * 计算今天日期
	 * @return
	 */
	public static String toDay(){
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE,0);
		String tday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return tday;
	}
	
	/**
	 * 计算昨天日期
	 * @return
	 */
	public static String yeasterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}
	
	/**
	 * 计算前天日期
	 * @return
	 */
	public static String yeasterdayTwo(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -2);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}
	/**
	 * 计算 n天前  日期
	 * @return
	 */
	public static String dateofNumbDays(int n){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -n);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}
	
	
	/**
	 * 计算上个月第一天
	 * @return
	 */
	public static String firstDayOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式
		String str = df.format(calendar.getTime());
		return str;

	}
	
	/**
	 * 计算n 月前第一天
	 * @return
	 */
	public static String firstDayToNumbMonth(int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, - 1*months);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式
		String str = df.format(calendar.getTime());
		return str;

	}

	/**
	 * 计算上个月最后一天
	 * @return
	 */
	public static String lastDayOfLastMonth() {
		
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
		String str = sdf.format(calendar.getTime()); 
		return str;
	}
	
	/**
	 * 计算N月前最后一天
	 * @return
	 */
	public static String lastDayToNumbMonth(int months) {
		
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1*months);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
		String str = sdf.format(calendar.getTime()); 
		return str;
	}
	
	/**
	 * 计算上个礼拜周一所在日期
	 */
	public static String firstDayOfLastWeek(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		
		String imptimeBegin = sdf.format(cal.getTime());
		return imptimeBegin;
		
	}
	
	/**
	 * 计算 N星期 前 周一
	 */
	public static String firstdaytoNumWeek(int weeks){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7*weeks);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		
		String imptimeBegin = sdf.format(cal.getTime());
		return imptimeBegin;
		
	}
	
	/**
	 * 计算上个礼拜周日所在日期
	 */
	public static String lastDayOfLastWeek(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);	// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		return imptimeEnd;
		
	}
	
	/**
	 * 计算 N星期 前 周日
	 */
	public static String lastDaytoNumWeek(int weeks){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);	// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-7*weeks);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		return imptimeEnd;
		
	}
	
	/**
	 * 计算上个季度第一天
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
	 * 计算 N季度前 第一天
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
	 * 计算 N季度前 末天
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
	 * 计算上个季度末天
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
	 * 计算去年第一天日期
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
	 * 计算 n年前 第一天日期
	 * @return
	 */
	public static String firstDateOfNumbYear(int years){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear = cal.get(Calendar.YEAR)-1*years;
		return String.valueOf(curYear) + "-01-01";
	}
	
	/**
	 * 计算 n年前 最后一天日期
	 * @return
	 */
	public static String LastDateOfNumbYear(int years){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear = cal.get(Calendar.YEAR)-1*years;
		return String.valueOf(curYear) + "-12-31";
	}
	
	/**
	 * 计算去年最后一天日期
	 * @return
	 */
	public static String lastYeatLastDate(){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int curYear = cal.get(Calendar.YEAR)-1;
		
		return String.valueOf(curYear) + "-12-31";

	}
	
	
	/**
	 * 除法计算int , 得到 百分比
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
	 * 除法计算int , 得到double
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


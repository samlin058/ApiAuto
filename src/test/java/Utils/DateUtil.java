package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");

	/**
	 * 返回年月日：yyyymmdd
	 * @param date
	 * @return
	 */
	public static String getYMDString(Date date) {
		return ymdFormat.format(date);
	};

	/**
	 * 返回年月日:yyyyMMdd-hhmmss
	 * @param date
	 * @return
	 */
	public static String getYMDHMSString(Date date) {
		return ymdhmsFormat.format(date);
	}

	/**
	 * 获取今天的时间，格式为yyyyMMdd
	 * @return
	 */
	public static String getTodayYMDString() {
		return getYMDString(new Date());
	}

	/**
	 * 获取此时的时间，格式为yyyyMMdd-hhmmss
	 * @return
	 */
	public static String getTodayYmdHmsString() {
		return getYMDHMSString(new Date());
	}

	/**
	 * 获取当月的第一天，格式yyyy-MM-dd
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2021年12月23日 
	 *
	 */
	public static String firstDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当前日期
		Calendar cal_1 = Calendar.getInstance();
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}
	
	/**
	 * 获取以mouth为月份起点，num为月份偏移量的日期
	 * @param Mouth   日期
	 * @param num     月数
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2021年12月23日 
	 *
	 */
	public static String nextSomeMouth(String Mouth, int num)  {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date;
		try {
			date = format.parse(Mouth);
			calendar.setTime(date); 
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		 // calendar 变为当月往后延num
		calendar.add(Calendar.MONTH, num);
		String nextSomeMouth = format.format(calendar.getTime());
		return nextSomeMouth;
	}
	
	 /**
     * 以当月为基准，num为偏移量，获取偏移后的月份的最后一天日期
     * @param num 
     * @return
     * @throws ParseException String
     *
     * @author       luandao
     * @Date         2021年12月23日 
     *
     */
	public static String nextSomeMouthLastDay(int num) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);
		calendar.set(Calendar.DATE, 0);
		String nextSomeMouthLastDay = format.format(calendar.getTime());
		return nextSomeMouthLastDay;
	}
}

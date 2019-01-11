package com.helloJob.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.helloJob.commons.utils.StringUtils;
import com.helloJob.constant.SchedulerPeriodConst;

public class DateUtils {
	public static String getFormatDate(Date date , String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String formatDate = sdf.format(date);
		return formatDate;
	}
	public static String getFormatDate(String formatStr){
		Date date = new Date();
		return getFormatDate(date , formatStr);
	}
	public static int getYyyyMMdd(){
		String date = getFormatDate("yyyyMMdd");
		return Integer.parseInt(date);
	}
	public static int getYear(){
		String date = getFormatDate("yyyy");
		return Integer.parseInt(date);
	}
/*	public static int getMonth(){
		String date = getFormatDate("MM");
		return Integer.parseInt(date);
	}
	public static int getDay(){
		String date = getFormatDate("dd");
		return Integer.parseInt(date);
	}*/
	
	
	public static Long getYesterday2(){
		Date yesterday = addDay(new Date() , -1);
		return Long.valueOf(getFormatDate(yesterday, "yyyyMMddHHmmss"));
		//原来是返回int
	}
	
	/**
	 * 获取当前日期，返回格式yyyyMMdd000000
	 * **/	
	public static String getNowFormatStr000000(){
		return getNowFormatStr("YYYYMMDD");
	}
	
	/**
	 * 获取当前日期，返回格式20180511234559
	 * **/	
	public static String getNowFormatStr(){
		return getNowFormatStr("YYYYMMDDHHMISS");
	}
	
	/**
	 * format: yyyymmddhhmiss,yyyymmddhhmi,yyyymmddhh,yyyymmdd,yyyymm,yyyy
	 *         20180511234559,20180511234500,201805112300,20180511000000,20180501000000,20180101000000
	 * **/
	public static String getNowFormatStr(String format){
		return getDateFormatStr(new Date(), format);
	}
	
	
	/**
	 * format: yyyymmddhhmiss,yyyymmddhhmi,yyyymmddhh,yyyymmdd,yyyymm,yyyy
	 *         20180511234559,20180511234500,201805112300,20180511000000,20180501000000,20180101000000
	 * **/
	public static String getDateFormatStr(Date date,String format){
		String mat = format.toUpperCase();
		String re;
		if(mat.equals(SchedulerPeriodConst.YYYYMMDD)){
			re=getFormatDate(date, "yyyyMMdd")+"000000";
		}else if (mat.equals(SchedulerPeriodConst.YYYYMMDDHHMISS)){
			re=getFormatDate(date, "yyyyMMddHHmmss");
		}else if (mat.equals(SchedulerPeriodConst.YYYYMMDDHHMI)){
			re=getFormatDate(date, "yyyyMMddHHmm")+"00";
		}else if (mat.equals(SchedulerPeriodConst.YYYYMMDDHH)){
			re=getFormatDate(date, "yyyyMMddHH")+"0000";
		}else if (mat.equals(SchedulerPeriodConst.YYYYMM)){
			re=getFormatDate(date, "yyyyMM")+"01000000";
		}else if (mat.equals(SchedulerPeriodConst.YYYY)){
			re=getFormatDate(date, "yyyy")+"0101000000";
		}else {
			re=getFormatDate(date, "yyyyMMddHHmmss");
		}
		return re;
	}
	
	
	
	public static Long getYesterdayLong(){
		Date yesterday = addDay(new Date() , -1);
		return Long.valueOf(getFormatDate(yesterday, "yyyyMMddHHmmss"));
		//原来是返回int
	}
	
	public static String getYesterday(String format){
		Date yesterday = addDay(new Date() , -1);
		return getFormatDate(yesterday, format);
	}
	/**对日期进行加减
	 * */
	public static Date addDay(Date date,int amount){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}
	/**对日期进行加减
	 * */
	public static Date addMonth(Date date,int amount){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, amount);
		return c.getTime();
	}
	public static String getTTime(Date date){
		return getFormatDate(date,"yyyyMMdd");
	}
	public static String getHour(Date date){
		return getFormatDate(date,"HH");
	}
	public static String getMinute(Date date){
		return getFormatDate(date,"mm");
	}
	public static Date getDate(int flag){
		Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,flag);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
         return date;		
	}
	public static int getYear(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return Integer.parseInt(formatter.format(date));
	}
	public static int getMonth(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return Integer.parseInt(formatter.format(date));
	}  
	public static int getDay(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return Integer.parseInt(formatter.format(date));
	}
	/**获取上个月第一天
	 * */
	public static Date getLastMonthFirstDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	/**获取上个月最后一天
	 * */
	public static Date getLastMonthEndDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	/**获取指定月份最后一天
	 * */
	public static String getEndDay(Date assignDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(assignDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return getFormatDate(calendar.getTime(), "dd");
	}
	public static void main(String[] args){
		Date queryDate = DateUtils.parse("201612", "yyyyMM");
		String endDay = DateUtils.getEndDay(queryDate);
		System.out.println(endDay);
	}
	public static Date parse(String dateStr , String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**判断该日期是否属于昨天
	 * **/
	public static boolean isYesterDay(Date queryDate){
		String queryDateStr = getFormatDate(queryDate, "yyyyMMdd");
		String yesterDay = getFormatDate(addDay(new Date(), -1), "yyyyMMdd");
		if(queryDateStr.equals(yesterDay)){
			return true;
		}else{
			return false;
		}
	}
	public static String getCreateTime() {
		return getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	public static String getFormatTime(Date date){
		return getFormatDate(date,"yyyy-MM-dd HH:mm:ss");
	}
	public static String getFormatTime(long ms){
		return getFormatDate(new Date(ms),"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 仿python relativedelta实现日期相加操作
	 * 示例： offsetsStr：[year=+2, month=8, day=-16]  年份加2，月份为8，日期减16 
	 * @param dateStr 支持yyyyMMddHHmmss和yyyy-MM-dd HH:mm:ss
	 * @param offsetsStr delta日期的单位与长度,分隔符分别为逗号与等号
	 *        单位支持有year,month,day,hour,minute,second；
	 *        长度支持+N,-N,N
	 *        示例有year=+2, month=8, day=-16
	 */
	public static Date relativedelta(String dateStr, String offsetsStr){
		String ds = dateStr.replace(" ", "").replace("-", "").replace(":", "");
		Date date=parse(ds, "yyyyMMddHHmmss");
		return relativedelta(date,offsetsStr);
	}
	
	
	/**
	 * 仿python relativedelta实现日期相加操作
	 * 示例： offsetsStr：[year=+2, month=8, day=-16]  年份加2，月份为8，日期减16   
	 * @param date 日期类型
	 * @param offsetsStr delta日期的单位与长度,分隔符分别为逗号与等号
	 *        单位支持有year,month,day,hour,minute,second；
	 *        长度支持+N,-N,N
	 *        示例有year=+2, month=8, day=-16
	 */
	public static Date relativedelta(Date date, String offsetsStr){
		if(StringUtils.isNotBlank(offsetsStr)){
			String[] offsets = offsetsStr.split(",");
			return relativedelta(date,offsets);
		}
		else {
			return date;
		}
	}
	
	/**
	 * 仿python relativedelta实现日期相加操作
	 * 示例： offsets：[year=+2, month=8, day=-16]  年份加2，月份为8，日期减16   
	 * @param date 日期类型
	 * @param offsets delta日期的单位与长度。
	 *        单位支持有year,month,day,hour,minute,second；
	 *        长度支持+N,-N,N
	 *        示例有year=+2, month=8, day=-16
	 */
	public static Date relativedelta(Date date, String... offsets){
		if (offsets==null || offsets.length==0){
			return date;
		}
		else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			for (int i = 0; i < offsets.length; i++) {
				String[] offset = offsets[i].trim().split("=");
				int field = offsetUnit(offset[0].trim());
				String value = offset[1];
				if(value.contains("+") || value.contains("-")){
					cal.add( field, Integer.parseInt(offset[1]));
				}
				else{
					cal.set( field, Integer.parseInt(offset[1]));
				}
			}
			return cal.getTime();
		}
	}
	
	/**
	 * year,month,day,hour,minute,second字符串转化Calendar的常量INT类型
	 * @param offsetStr
	 * @return
	 */
	public static int offsetUnit(String offsetStr){
		if(offsetStr.equals("year")) return Calendar.YEAR;
		else if(offsetStr.equals("month")) return Calendar.MONTH;
		else if(offsetStr.equals("day")) return Calendar.DAY_OF_MONTH;
		else if(offsetStr.equals("hour")) return Calendar.HOUR;
		else if(offsetStr.equals("minute")) return Calendar.MINUTE;
		else if(offsetStr.equals("second")) return Calendar.SECOND;
		else return -1;
	}
	
	
	
}

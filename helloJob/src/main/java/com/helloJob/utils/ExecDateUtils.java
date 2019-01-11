package com.helloJob.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecDateUtils {
	
	/**
	 * exec_date=20181012131415
	 * 格式有4个：
	 * ${sys.exec.dt1}  20181012
	 * ${sys.exec.dm1}  20181012131415
	 * ${sys.exec.dt2} 2018-10-12
	 * ${sys.exec.dm2} 2018-10-12 13:14:15
	 * 计算：示例${sys.exec.dt1:year=+2,month=8,day=-16}  是在exec_date基础上，年份加2，月份为8，日期减16，输出格式为${sys.exec.dt}；
	 *       示例2：${sys.exec.dt2:day=1,day=-1} 上月月末 2018-09-30
	 * 特殊(日期相加)：
	 * ${sys.exec.d[t1|t2|m1|m2][+-]n},示例${sys.exec.dt1-1},${sys.exec.dt2+1} 分别对应昨天20181011,2018-10-13
	 * @param input 输入
	 * @param execdate
	 * @return
	 */
	public static String execDateVariablesReplace(String input,String execdate){
		//${dt1},${dt2}
		input=input.replace("${dt1}", "${sys.exec.dt1:day=-1}").replace("${dt2}", "${sys.exec.dt2:day=-1}");
        //${sys.exec.dt1-1}  转化成${sys.exec.dt1:day=-1}
        String REGEX1 = "(\\$\\{)(sys\\.exec\\.d[tm][12])([\\+\\-])([0-9]+)(\\})";
        Pattern p1 = Pattern.compile(REGEX1);
        Matcher m1 = p1.matcher(input);
        while(m1.find()){
        	String newStr = m1.group(1)+m1.group(2) +":day="+m1.group(3) +m1.group(4) +m1.group(5); 
        	input=input.replace(m1.group(0), newStr);
        	m1 = p1.matcher(input);
        }
        
        String REGEX = "(\\$\\{)(sys\\.exec\\.d[tm][12])(:?)([\\+\\-,=a-z0-9]*)(\\})";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher m = pattern.matcher(input);
        while(m.find()){
        	Date val = DateUtils.relativedelta(execdate, m.group(4));
            String valStr = execDateVariablesFormat(val, m.group(2));
            input=input.replace(m.group(0), valStr);
            m = pattern.matcher(input);
        }
		return input;
	}
	
	/**
	 * 4种格式的输出日期str
	 * @param date
	 * @param format
	 * @return
	 */
	public static String execDateVariablesFormat(Date date,String format){
		if(format.equals("sys.exec.dt1")) {
			return DateUtils.getFormatDate(date,"yyyyMMdd");
		}else if (format.equals("sys.exec.dt2")) {
			return DateUtils.getFormatDate(date,"yyyy-MM-dd");
		}else if (format.equals("sys.exec.dm1")) {
			return DateUtils.getFormatDate(date,"yyyyMMddHHmmss");
		}else if (format.equals("sys.exec.dm2")) {
			return DateUtils.getFormatDate(date,"yyyy-MM-dd HH:mm:ss");
		}
		return DateUtils.getFormatDate(date,"YYYYMMDDHHMISS");
	}

	public static void main(String[] args) {
		String input = "${dt2},,,,${sys.exec.dt1}, my name is ${sys.exec.dm2:day=1,day=-1,year=+1}||| "
				+ "${sys.exec.dm1-365},${sys.exec.dm2+365},"
				+ "${sys.exec.dm2:day=1,day=-1,year=+1} ||| ${sys.exec.dt1:day=13}";
		execDateVariablesReplace(input,"20180405101213");
	}

}

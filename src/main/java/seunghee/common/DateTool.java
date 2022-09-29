package seunghee.common;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

// getDifference 초 분 시 년

public class DateTool {

    /** 날짜포맷(시) */
    public static final String FMT_HOUR          = "HH";

    /** 날짜포맷(일시) */
    public static final String FMT_DATE_TIME    = "yyyy-MM-dd HH:mm:ss";

    /** 날짜포맷(일시 14자리) */
    public static final String FMT_DATE_TIME_NUM        = "yyyyMMddHHmmss";

    /** 날짜포맷(일자) */
    public static final String FMT_DATE        = "yyyy-MM-dd";

    /** 날짜포맷(일자 8자리) */
    public static final String FMT_DATE_NUM         = "yyyyMMdd";


    /**
     * 현재시간을 "yyyy-MM-dd HH:mm:ss" 형태로 반환
     * Example :
     * DateTool.getCurrent() ==> 2011-12-20 12:30:59
     * @return              현재시간(yyyy-MM-dd HH:mm:ss)
     */
    public static final String getCurrent() {
        return getCurrent(FMT_DATE_TIME);
    }
    /**
     * 현재시간을 dateFormat 형태로 반환
     * Example :
     * DateTool.getCurrent("yyyy MM dd HH mm ss") ==> 2011 12 20 15 42 14
     * DateTool.getCurrent("yyyy-MM-dd") ==> 2011-12-20
     * @param dateFormat    출력하고자하는형식("yyyyMMddHHmmss" 로 이루어진 포맷)
     * @return              현재시간
     */
    public static final String getCurrent(String dateFormat) {
        return CalendarTool.getCurrent(Calendar.getInstance(Locale.KOREAN), dateFormat);
    }


    /**
     * 현재시간에서 amount 초를 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     */
    public static final String addSeconds(String dateFormat, int amount) throws Exception {
        return CalendarTool.addSeconds(Calendar.getInstance(Locale.KOREAN), dateFormat, amount);
    }
    /**
     * 입력받은시간 dateStr 에서 amount 초를 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateStr       날짜문자열
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String addSeconds(String dateStr, String dateFormat, int amount) throws Exception {
        return addTime(dateStr, dateFormat, Calendar.SECOND, amount);
    }


    /**
     * 현재시간에서 amount 분을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     */
    public static final String addMinutes(String dateFormat, int amount) throws Exception {
        return CalendarTool.addMinutes(Calendar.getInstance(Locale.KOREAN), dateFormat, amount);
    }
    /**
     * 입력받은시간 dateStr 에서 amount 분을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateStr       날짜문자열
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String addMinutes(String dateStr, String dateFormat, int amount) throws Exception {
        return addTime(dateStr, dateFormat, Calendar.MINUTE, amount);
    }


    /**
     * 현재시간에서 amount 시를 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     */
    public static final String addHours(String dateFormat, int amount) throws Exception {
        return CalendarTool.addHours(Calendar.getInstance(Locale.KOREAN), dateFormat, amount);
    }
    /**
     * 입력받은시간 dateStr 에서 amount 시를 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateStr       날짜문자열
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String addHours(String dateStr, String dateFormat, int amount) throws Exception {
        return addTime(dateStr, dateFormat, Calendar.HOUR, amount);
    }


    /**
     * 현재시간에서 amount 일을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateFormat    날짜출력형식(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     */
    public static final String addDays(String dateFormat, int amount) {
        return CalendarTool.addDays(Calendar.getInstance(Locale.KOREAN), dateFormat, amount);
    }
    /**
     * 입력받은시간 dateStr 에서 amount 일을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateStr       날짜문자열
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String addDays(String dateStr, String dateFormat, int amount) throws Exception {
        return addTime(dateStr, dateFormat, Calendar.DAY_OF_MONTH, amount);
    }


    /**
     * 현재시간에서 amount 월을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String addMonths(String dateFormat, int amount) {
        return CalendarTool.addMonths(Calendar.getInstance(Locale.KOREAN), dateFormat, amount);
    }
    /**
     * 입력받은시간 dateStr 에서 amount 월을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateStr       날짜문자열
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String addMonths(String dateStr, String dateFormat, int amount) throws Exception {
        return addTime(dateStr, dateFormat, Calendar.MONTH, amount);
    }


    /**
     * 현재시간에서 amount 년을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String addYears(String dateFormat, int amount) {
        return CalendarTool.addYears(Calendar.getInstance(Locale.KOREAN), dateFormat, amount);
    }
    /**
     * 입력받은시간 dateStr 에서 amount 월을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateStr       날짜문자열
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param amount        가감량
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String addYears(String dateStr, String dateFormat, int amount) throws Exception {
        return addTime(dateStr, dateFormat, Calendar.YEAR, amount);
    }


    /**
     * 입력받은시간 dateStr 에서 해당필드 field 의 amount 시간을 가감하고 전달받은 dateFormat 형태로 반환
     * @param dateStr       날짜문자열
     * @param dateFormat    날짜포맷(yyyyMMddHHmmss)
     * @param field         가감하고자 하는 필드(Calendar.SECOND, MINUTE, HOUR_OF_DAY, DAY_OF_MONTH, WEEK_OF_MONTH, MONTH, YEAR)
     * @param amount        가감량
     * @return              날짜문자열
     */
    private static final String addTime(String dateStr, String dateFormat, int field, int amount) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar date = Calendar.getInstance(Locale.KOREAN);

        try {
            date.setTime(sdf.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        date.add(field, amount);

        return CalendarTool.format(date, dateFormat);
    }


    /**
     * 입력받은시간 dateStr 이 현재시간보다 과거날짜인지 확인
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날짜포맷(yyyyMMddHHmmss)
     * @return              과거날짜여부
     * @throws Exception    예외
     */
    public static final boolean isPast(String dateStr, String dateFormat) throws Exception {
        if(StringUtils.isBlank(dateStr) || StringUtils.isBlank(dateFormat)) {
            throw new Exception(String.format("날짜:%s, 날짜형식:%s", dateStr, dateFormat));
            // ValidationException -> Exception
        }
        String curr = getCurrent(dateFormat);

        return (curr.compareTo(dateStr) > 0);
    }


    /**
     * 현재시간이 두 날짜 사이인지 여부
     * @param  startDate    시작날짜 (yyyyMMdd)
     * @param  endDate      끝날짜 (yyyyMMdd)
     * @return              현재시간이 두 날짜 사이인지 여부
     * @throws Exception    예외
     */
    public static final boolean isBetween(String startDate, String endDate) {
        return isBetween(startDate, endDate, FMT_DATE_NUM);
    }
    /**
     * 현재시간이 두 날짜 사이인지 여부
     * @param  startDate    시작날짜
     * @param  endDate      끝날짜
     * @param  dateFormat   날짜형식
     * @return              현재시간이 두 날짜 사이인지 여부
     * @throws Exception    예외
     */
    public static final boolean isBetween(String startDate, String endDate, String dateFormat) {
        String curr = getCurrent(dateFormat);
        return (curr.compareTo(startDate) >= 0 && curr.compareTo(endDate) <= 0);
    }


    /**
     * 입력받은 두 날짜 사이의 차이일수를 반환
     * @param  startDate    시작날짜
     * @param  endDate      끝날짜
     * @param  dateFormat   날짜형식
     * @return              두 날짜의 차이일수
     * @throws Exception    예외
     */
    public static final int getDifferenceInDays(String startDate, String endDate, String dateFormat) {
        return CalendarTool.getDifferenceInDays(toCalendar(startDate, dateFormat), toCalendar(endDate, dateFormat));
    }
    /**
     * 현재시간과 입력받은시간 dateStr 사이의 차이일수를 반환
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날짜형식
     * @return              두 날짜의 차이일수
     * @throws Exception    예외
     */
    public static final int getDifferenceInDaysFromCurrent(String dateStr, String dateFormat) {
        return CalendarTool.getDifferenceInDays(toCalendar(dateStr, dateFormat), toCalendar(getCurrent(), dateFormat));
    }


    /**
     * 입력받은 두 날짜 사이의 차이월수를 반환
     * @param  startDate    시작날짜
     * @param  endDate      끝날짜
     * @param  dateFormat   날짜형식
     * @return              두 날짜의 차이월수
     * @throws Exception    예외
     */
    public static final int getDifferenceInMonths(String startDate, String endDate, String dateFormat) throws Exception {
        return CalendarTool.getDifferenceInMonths(toCalendar(endDate, dateFormat), toCalendar(startDate, dateFormat));
    }
    /**
     * 현재시간과 입력받은시간 dateStr 사이의 차이월수를 반환
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날짜형식
     * @return              두 날짜의 차이월수
     * @throws Exception    예외
     */
    public static final int getDifferenceInMonthsFromCurrent(String dateStr, String dateFormat) {
        return CalendarTool.getDifferenceInMonths(toCalendar(dateStr, dateFormat), toCalendar(getCurrent(), dateFormat));
    }


    /**
     * 입력받은시간 dateFormat 의 dateStr 을 Calendar 로 반환
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날짜형식
     * @return              캘린더
     * @throws Exception    예외
     */
    public static final Calendar toCalendar(String dateStr, String dateFormat) {
        Calendar date = Calendar.getInstance(Locale.KOREAN);
        DateFormat sdf = new SimpleDateFormat(dateFormat);

        try {
            date.setTime(sdf.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }


    /**
     * 현재월의 첫 날을 dateFormat 으로 반환
     * @param  dateFormat   날짜형식
     * @return              날짜문자열
     */
    public static final String getFirstDayOfMonth(String dateFormat) {
        Calendar date = Calendar.getInstance(Locale.KOREAN);
        date.set(Calendar.DAY_OF_MONTH, 1);
        return CalendarTool.format(date, dateFormat);
    }
    /**
     * 입력받은시간 dateStr 의 해당달 첫 날을 dateFormat 으로 반환
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날짜형식
     * @param  outFormat    반환할 날짜형식
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String getFirstDayOfMonth(String dateStr, String dateFormat, String outFormat) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar date = Calendar.getInstance(Locale.KOREAN);

        try {
            date.setTime(sdf.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        date.set(Calendar.DAY_OF_MONTH, 1);

        return CalendarTool.format(date, outFormat);
    }


    /**
     * 현재월의 마지막 날을 dateFormat 으로 반환
     * @param  dateFormat   날짜형식
     * @return              날짜문자열
     */
    public static final String getLastDayOfMonth(String dateFormat) {
        Calendar date = Calendar.getInstance(Locale.KOREAN);
        date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DATE));
        return CalendarTool.format(date, dateFormat);
    }
    /**
     * 입력받은시간 dateStr 의 해당달 마지막 날을 dateFormat 으로 반환
     * @param  dateStr      날짜
     * @param  dateFormat   날짜형식
     * @param  outFormat    반환할 날짜형식
     * @return              날짜문자열
     * @throws Exception    예외
     */
    public static final String getLastDayOfMonth(String dateStr, String dateFormat, String outFormat) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar date = Calendar.getInstance(Locale.KOREAN);

        try {
            date.setTime(sdf.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DATE));

        return CalendarTool.format(date, outFormat);
    }


    /**
     * 입력받은 두날짜 사이에 있는 날짜들을 목록으로 반환
     * @param  startDate    시작날짜
     * @param  endDate      큰날짜
     * @param  dateFormat   날짜형식
     * @return              날짜문자열목록
     * @throws Exception    예외
     */
    public static final List<String> getDateRangeList(String startDate, String endDate, String dateFormat) {
        List<String> list = new ArrayList<>();

        Calendar startCalendar = toCalendar(startDate, dateFormat);
        Calendar endCalendar   = toCalendar(endDate, dateFormat);
        int diff = CalendarTool.getDifferenceInDays(endCalendar, startCalendar);

        list.add(startDate);
        for(int ii = 0; ii < diff; ii++) {
            list.add(CalendarTool.addTime(startCalendar, dateFormat, Calendar.DAY_OF_MONTH, 1));
        }

        return list;
    }


    /**
     * 입력받은 두날짜 사이에 있는 월들을 목록으로 반환
     * @param  startDate    시작날짜
     * @param  endDate      큰날짜
     * @param  dateFormat   날짜형식
     * @param  outFormat	반환할 날짜형식
     * @return				날짜문자열목록
     * @throws Exception	예외
     */
    public static final List<String> getMonthRangeList(String startDate, String endDate, String dateFormat, String outFormat) {
        List<String> list = new ArrayList<>();

        Calendar startCalendar = toCalendar (startDate, dateFormat);
        Calendar endCalendar = toCalendar (endDate, dateFormat);
        int diff = CalendarTool.getDifferenceInMonths (endCalendar, startCalendar);

        list.add (format (startDate, outFormat));
        for (int ii=0;  ii<diff;  ii++) {
            list.add(CalendarTool.addTime(startCalendar, outFormat, Calendar.MONTH, 1));
        }

        return list;
    }


    /**
     * 입력받은시간 dateStr 을 milliSeconds 로 반환
     * @param  dateStr	    날짜문자열
     * @param  dateFormat   날짜형식
     * @return              milliSeconds
     * @throws Exception	예외
     */
    public static long getTimeMillis(String dateStr, String dateFormat) {
        long tm = 0L;

        try {
            tm = new SimpleDateFormat(dateFormat).parse(dateStr).getTime();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return tm;
    }


    /**
     * 흘러간 시간을 반환
     * @param  ts
     * @param  field    시간필드
     * @return
     */
    public static long getElapsed(Timestamp ts, int field) {
        long	elapsed		= 0;
        long	milliSeconds	= Calendar.getInstance (Locale.KOREAN).getTimeInMillis();

        switch (field) {
            case Calendar.MILLISECOND:
                elapsed = milliSeconds - ts.getTime();
                break;
            case Calendar.SECOND:
                elapsed = (milliSeconds - ts.getTime()) / 1000;
                break;
            case Calendar.MINUTE:
                elapsed = (milliSeconds - ts.getTime()) / (1000 * 60);
                break;
            case Calendar.HOUR:
                elapsed = (milliSeconds - ts.getTime()) / (1000 * 60 * 60);
                break;
            default:
                break;
        }

        return elapsed;
    }


    /**
     * "yyyyMMddHHmmss" 형식의 날짜문자열을 받아 "yyyy-MM-dd HH:mm:ss" 으로 반환
     * Example :
     * DateTool.format("20111231123059") ==> 2011-12-31 12:30:59
     * DateTool.format("20111231") ==> 2011-12-31 00:00:00
     * @param  dateStr      날짜문자열
     * @return 			    날짜문자열
     * @throws Exception	예외
     */
    public static final String format(String dateStr) {
        return format(dateStr, FMT_DATE_TIME);
    }
    /**
     * yyyyMMddHHmmss 형식의 날짜문자열을 받아 dateFormat 으로 반환
     * Example :
     * DateTool.format("20111231123059", "yyyyMMddHHmmss") ==> 20111231123059
     * DateTool.format("20111231123059", "yyyy-MM-dd") ==> 2011-12-31
     * DateTool.format("20111231", "yyyyMMddHHmmss") ==> 20111231000000
     * DateTool.format("20111231", "yyyy-MM-dd") ==> 2011-12-31
     * @param  dateStr      날짜문자열
     * @param  outFormat	출력형식
     * @return	            날짜문자열
     * @throws Exception	예외
     */
    public static final String format(String dateStr, String outFormat) {
        if(StringUtils.isBlank(dateStr) || StringUtils.isBlank(outFormat)) {
            return StringUtils.EMPTY;
        }
        long time = getTimeMillis(StringUtils.rightPad(dateStr,14,'0'), FMT_DATE_TIME_NUM);
        SimpleDateFormat sdf = new SimpleDateFormat(outFormat, Locale.KOREAN);
        return sdf.format(time);
    }
    /**
     * 입력받은시간 dateStr 의 문자열 형태를 dateFormat 형식에서 outFormat 형식으로 변경
     * @param	dateStr	    날짜문자열
     * @param	dateFormat	입력형식
     * @param	outFormat	출력형식
     * @return	            날짜문자열
     * @throws	Exception	예외
     */
    public static final String format(String dateStr, String dateFormat, String outFormat) {
        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(dateFormat) || StringUtils.isBlank(outFormat)) {
            return StringUtils.EMPTY;
        }
        long time = getTimeMillis(dateStr, dateFormat);
        SimpleDateFormat sdf = new SimpleDateFormat(outFormat, Locale.KOREAN);
        return sdf.format(time);
    }


    /**
     * 입력받은 밀리초 milliSeconds 를 시간문자열로 변환
     * @param  milliSeconds 밀리초
     * @return              날짜문자열
     */
    public static final String miliSecondToText(long milliSeconds) {
        StringBuilder sb = new StringBuilder();
        long ms	= milliSeconds;
        long s	= 0L;
        long m	= 0L;
        long h	= 0L;
        long d	= 0L;

        if(milliSeconds > 999L) {
            s	= milliSeconds / 1000;
            ms	= milliSeconds % 1000;
        }
        if(s > 59L) {
            m	= s / 60;
            s	= s % 60;
        }
        if(m > 59L) {
            h	= m / 60;
            m	= m % 60;
        }
        if(h > 23L) {
            d	= h / 24;
            h	= h % 24;
        }

        if(d > 0L) {
            sb.append (d+ " 일 ");
            sb.append (h+ " 시간 ");
            sb.append (m+ " 분 ");
            sb.append (s+ " 초 ");
        } else if(h > 0L) {
            sb.append (h+ " 시간 ");
            sb.append (m+ " 분 ");
            sb.append (s+ " 초 ");
        } else if(m > 0L) {
            sb.append (m+ " 분 ");
            sb.append (s+ " 초 ");
        } else if(s > 0L) {
            sb.append (s+ " 초 ");
        }
        sb.append(ms + " 밀리초 ");

        return sb.toString();
    }


    /**
     * 입력받은시간 dateStr 이 지정한 날짜형식 dateFormat 에 맞는지 검사
     * @param  dateStr      날짜문자열
     * @param  dateFormat	날짜형식
     * @return	            유효여부
     */
    public static boolean isValidDateStr(String dateStr, String dateFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            return sdf.parse(dateStr, new ParsePosition(0)) != null;
        } catch(Exception e) {
            return false;
        }
    }


    /**
     * 현재시간의 요일을 구한다 (일~토, 1~7)
     * Example :
     * DateTool.dayOfWeek()
     * @return              일~토, 1~7
     */
    public static final int dayOfWeek() {
        String currentYmd = DateTool.getCurrent(DateTool.FMT_DATE);
        return dayOfWeek(currentYmd, DateTool.FMT_DATE);
    }
    /**
     * 입력받은시간 dateStr 의 요일을 구한다 (일~토, 1~7)
     * Example :
     * DateTool.dayOfWeek("2019-03-11", DateTool.FMT_DATE)
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날짜형식
     * @return              일~토, 1~7
     */
    public static final int dayOfWeek(String dateStr, String dateFormat) {
        Calendar date = DateTool.toCalendar(dateStr, dateFormat);
        return date.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 입력받은시간 dateStr 의 요일을 구한다 (일~토)
     * Example :
     * DateTool.dayOfWeek("2018-03-14", "yyyy-MM-dd");	=> 목
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날자형식
     * @return              일~토
     */
    public static final String dayOfWeekKor(String dateStr, String dateFormat) {
        int dayOfWeek = DateTool.dayOfWeek(dateStr, dateFormat);
        String dayOfWeekKor = "";

        switch(dayOfWeek) {
            case 1: dayOfWeekKor = "일"; break;
            case 2: dayOfWeekKor = "월"; break;
            case 3: dayOfWeekKor = "화"; break;
            case 4: dayOfWeekKor = "수"; break;
            case 5: dayOfWeekKor = "목"; break;
            case 6: dayOfWeekKor = "금"; break;
            case 7: dayOfWeekKor = "토"; break;
        }

        return dayOfWeekKor;
    }


    /**
     * 입력받은시간 dateStr 의 형식을 dateFormat 에서 outFormat 으로 변환한다
     * Example :
     * DateTool.convertDateFormat("2019-03-15", "yyyy-mm-dd", "mm/dd");		=> 03/15
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날짜형식
     * @Param  outFormat    출력형식
     * @return              날짜문자열
     */
    public static final String convertDateFormat(String dateStr, String dateFormat, String outFormat) throws Exception {
        SimpleDateFormat dt = new SimpleDateFormat(dateFormat);
        Date date = dt.parse(dateStr);
        SimpleDateFormat dt1 = new SimpleDateFormat(outFormat);
        return dt1.format(date);
    }



    /**
     * 입력받은시간 dateStr 의 형태에 따라 Calendar, Date 로 변환한다
     * @param dateStr       날짜문자열
     * @param dateFormat    날짜형식
     * @param destClass     변환하려는 class
     * @return              Calendar, Date
     */
    public static <T> T parseDate(String dateStr, String dateFormat, Class<T> destClass) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.KOREAN);
        Date date = sdf.parse(dateStr);

        if(destClass.getTypeName().equals(Calendar.class.getName())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis( date.getTime() );
            return destClass.cast(calendar);
        } else if (destClass.getTypeName().equals(Date.class.getName())) {
            return destClass.cast(date);
        }

        return null;
    }


    /**
     * 입력받은시간 dateStr 의 형태에 따라 Calendar, Date 로 변환한다
     * @param date          날짜(Calendar, Date)
     * @param dateFormat    날짜형식
     * @return              날짜문자열
     */
    public static <T> String convertToStringDate(T date, String dateFormat) {
        String dateStr = "";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.KOREAN);

        if(date instanceof Calendar) {
            dateStr = sdf.format(((Calendar)date).getTime());
        } else if(date instanceof Date) {
            dateStr = sdf.format((Date)date);
        }

        return dateStr;
    }


    /**
     * 입력받은시간 dateStr 의 시간을 반환
     * @param  dateStr      날짜문자열 "yyyy-MM-dd HH:mm:ss"
     * @return	            01~24
     * @throws	Exception	예외
     */
    public static String getHours(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(FMT_DATE_TIME);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FMT_HOUR);
        Date date = sdf.parse(dateStr);
        return sdf2.format(date);
    }


    /**
     * 입력받은시간 dateStr 을 Time 으로 변경
     * @param  dateStr      날짜문자열
     * @param  dateFormat   날짜형식
     * @return              Time
     * @throws Exception
     */
    public static final Time convertToTime(String dateStr, String dateFormat) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date parsedDate = sdf.parse(dateStr);
        Time time = new Time(parsedDate.getTime());
        return time;
    }
}

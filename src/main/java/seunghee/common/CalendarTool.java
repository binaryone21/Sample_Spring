package seunghee.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// date 를 입력받는 메소드에 대해서 현재시간 메소드도 추가
// DateTool 참고

public class CalendarTool {

    /** 날짜포맷(일시) */
    public static final String FMT_DATE_TIME    = "yyyy-MM-dd HH:mm:ss";

    /**
     * 현재시간을 "yyyy-MM-dd HH:mm:ss" 형태로 반환
     * Example :
     * CalendarTool.getCurrent() ==> 2011-12-20 12:30:59
     * @return              현재시간(yyyy-MM-dd HH:mm:ss)
     */
    public static final String getCurrent() {
        return getCurrent(FMT_DATE_TIME);
    }
    /**
     * 현재시간을 dateFormat 형태로 반환
     * Example :
     * CalendarTool.getCurrent("yyyy MM dd HH mm ss") ==> 2011 12 20 15 42 14
     * CalendarTool.getCurrent("yyyy-MM-dd") ==> 2011-12-20
     * @param dateFormat    출력하고자하는형식("yyyyMMddHHmmss" 로 이루어진 포맷)
     * @return              현재시간
     */
    public static final String getCurrent(String dateFormat) {
        return getCurrent(Calendar.getInstance(Locale.KOREAN), dateFormat);
    }

    /**
     * 입력받은시간 date 을 dateFormat 형태로 반환
     * Example :
     * DateTool.getCurrent (Calendar.getInstance (Locale.KOREAN), "yyyy MM dd HH mm ss") ==> 2011 12 20 15 42 14
     * DateTool.getCurrent (Calendar.getInstance (Locale.KOREAN), "yyyy-MM-dd") ==> 2011-12-20
     * @param date          시간
     * @param dateFormat    출력하고자하는형식("yyyyMMddHHmmss" 로 이루어진 포맷)
     * @return 현재시간
     */
    public static final String getCurrent(Calendar date, String dateFormat) {
        return format(date, dateFormat);
    }

    /**
     * 입력받은시간 date 에서 amount 초를 가감하고 전달받은 dateFormat 형태로 반환
     * @param date          날짜
     * @param dateFormat    날짜출력형식
     * @param amount        가감량
     *
     * @return 시간을 가감한 날짜형식에 맞춘 날짜
     */
    public static final String addSeconds(Calendar date, String dateFormat, int amount) {
        date.add(Calendar.SECOND, amount);
        return format(date, dateFormat);
    }

    /**
     * 입력받은시간 date 에서 amount 분을 가감하고 전달받은 dateFormat 형태로 반환
     * @param date          날짜
     * @param dateFormat    날짜출력형식
     * @param amount        가감량
     * @return              시간을 가감한 날짜형식에 맞춘 날짜
     */
    public static final String addMinutes(Calendar date, String dateFormat, int amount) {
        date.add(Calendar.MINUTE, amount);
        return format(date, dateFormat);
    }

    /**
     * 입력받은시간 date 에서 amount 시를 가감하고 전달받은 dateFormat 형태로 반환
     * @param date          날짜
     * @param dateFormat    날짜출력형식
     * @param amount        가감량
     * @return              시간을 가감한 날짜형식에 맞춘 날짜
     */
    public static final String addHours(Calendar date, String dateFormat, int amount) {
        date.add(Calendar.HOUR, amount);
        return format(date, dateFormat);
    }

    /**
     * 입력받은시간 date 에서 amount 일을 가감하고 전달받은 dateFormat 형태로 반환
     * @param date          날짜
     * @param dateFormat    날짜출력형식
     * @param amount        가감량
     * @return              시간을 가감한 날짜형식에 맞춘 날짜
     */
    public static final String addDays(Calendar date, String dateFormat, int amount) {
        date.add(Calendar.DAY_OF_MONTH, amount);
        return format(date, dateFormat);
    }

    /**
     * 입력받은시간 date 에서 amount 월을 가감하고 전달받은 dateFormat 형태로 반환
     * @param date          날짜
     * @param dateFormat    날짜출력형식
     * @param amount        가감량
     * @return 시간을 가감한 날짜형식에 맞춘 날짜
     */
    public static final String addMonths(Calendar date, String dateFormat, int amount) {
        date.add(Calendar.MONTH, amount);
        return format(date, dateFormat);
    }

    /**
     * 입력받은시간 date 에서 amount 월을 가감하고 전달받은 dateFormat 형태로 반환
     * @param date          날짜
     * @param dateFormat    날짜출력형식
     * @param amount        가감량
     * @return 시간을 가감한 날짜형식에 맞춘 날짜
     */
    public static final String addYears(Calendar date, String dateFormat, int amount) {
        date.add(Calendar.YEAR, amount);
        return format(date, dateFormat);
    }

    /**
     * 입력받은시간 date 에서 해당필드 field 의 amount 시간을 가감하고 전달받은 dateFormat 형태로 반환
     * @param  date         날짜
     * @param  dateFormat   날짜형식
     * @param  field        가감하고자 하는 날짜 필드
     * @param  amount       가감량
     * @return              시간을 가감한 날짜형식에 맞춘 날짜
     */
    public static final String addTime(Calendar date, String dateFormat, int field, int amount) {
        date.add(field, amount);
        return format(date, dateFormat);
    }

    /**
     * 입력받은 두 날짜 사이의 차이일수를 반환
     * @param startDate     시작날짜
     * @param endDate       끝날짜
     * @return              두 날짜의 차이일수
     */
    public static final int getDifferenceInDays(Calendar startDate, Calendar endDate) {
        return (int)((endDate.getTimeInMillis() - startDate.getTimeInMillis()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 입력받은 두 날짜 사이의 차이월수를 반환
     * @param endDate       큰날짜
     * @param startDate     작은날짜
     * @return              두 날짜의 차이월수
     */
    public static final int getDifferenceInMonths(Calendar startDate, Calendar endDate) {
        int yearDifference = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
        int monthDifference = endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);
        return (yearDifference*12 + monthDifference);
    }

    /**
     * 입력받은시간 date 을 dateFormat 형식에 맞게 문자열 반환
     *
     * @param	date	    캘린더
     * @param	dateFormat	반환할 날짜형식
     * @return				날짜문자열
     */
    public static String format(Calendar date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.KOREAN);
        return sdf.format(date.getTime());
    }
}

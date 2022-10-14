package seunghee.common;

import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;


public class CheckTool {

    // 이메일 패턴
    public static final String PATTERN_EMAIL = "^(\\w[-.\\w]*)@[-\\w]+(\\.[\\w]+)+$";

    // 아이디 패턴(6자 이상의 영문, 숫자 조합)
    private final static String PATTERN_ID = "^\\w\\w\\\\w\\\\w\\w\\w+$";

    // 비밀번호 패턴(8자 이상의 영문, 숫자 조합)
    private final static String PATTERN_PASSWD = "^\\w\\w\\w\\w\\w\\w\\w\\w+$";

    /**
     * 패턴 테스트
     * @param pattern 검사할패턴
     * @param value   검사할문자열
     * @return 패턴에 부합되면 true
     */
    public static final boolean isValidPattern(final String pattern, final String value) {
        return Pattern.compile(pattern).matcher(value).find();
    }

    /**
     * 입력값이 숫자값인지 체크(.* 포함)
     * @param input 입력값
     * @return 숫자이면 true
     */
    public static boolean isNumCheck(String input) {
        String chars = "0123456789.*-+/";

        if(input == null)       return false;
        if("".equals(input))    return false;
        for(int inx=0; inx<input.length(); inx++) {
            if(chars.indexOf(input.charAt(inx)) == -1) {
                return false;
            }
        }

        return true;
    }

    /**
     * 문자열의 길이가 최소,최대길이 조건에 맞는지 검사
     * @param s   문자열
     * @param min 최소길이
     * @param max 최대길이
     * @return 조건이 맞으면 true, 아니면 false
     */
    public static final boolean isValidLength(final String s, final int min, final int max) {
        int len = StringUtils.length(s);
        return (len >= min && len <= max);
    }
    public static final boolean isNotValidLength(final String s, final int min, final int max) {
        return !isValidLength(s, min, max);
    }

    /**
     * 문자열의 바이트 길이가 최소길이 조건에 맞는지 검사
     * @param s   문자열
     * @param min 최소길이(바이트)
     * @return 조건이 맞으면 true, 아니면 false
     * @throws Exception 예외
     */
/*
    public static final boolean isOverByteLength(final String s, final int min) throws Exception {
        return StringTool.getByteLength(s) >= min;
    }
    public static final boolean isNotOverByteLength(final String s, final int min) throws Exception {
        return !isOverByteLength(s, min);
    }
 */

    /**
     * 이메일 형식이 올바른지 검사
     * @param email 이메일주소
     * @return 형식이 올바르면  true, 아니면 false
     */
    public static final boolean isValidEmail(final String email) {
        if(email == null) return false;
        return isValidPattern(PATTERN_EMAIL, email);
    }
    public static final boolean isNotValidEmail(final String email) {
        return !isValidEmail(email);
    }

    /**
     * 아이디 형식 체크(6~12자 영문,숫자 조합인지)
     * @param id 아이디
     * @return 아이디 형식이 올바르면  true, 아니면 false
     */
    public static final boolean isValidId(final String id) {
        String lowerId = StringUtils.lowerCase(id);
        return StringUtils.length(lowerId) < 13 && isValidPattern(PATTERN_ID, lowerId);
    }
    public static final boolean isNotValidId(final String id) {
        return !isValidId(id);
    }

    /**
     * 비밀번호 형식 체크(8~20자 이상의 영문,숫자 조합인지)
     * @param passwd 비밀번호
     * @return 비밀번호 형식이 올바르면  true, 아니면 false
     */
    public static final boolean isValidPasswd(final String passwd) {
        return StringUtils.length(passwd) < 21 && isValidPattern(PATTERN_PASSWD, passwd);
    }
    public static final boolean isNotValidPasswd(final String passwd) {
        return !isValidPasswd(passwd);
    }

    /**
     * 휴대폰번호 형식이 올바른지 검사
     * @param cellNo 휴대폰번호
     * @return 형식이 올바르면  true, 아니면 false
     */
    public static final boolean isValidCellNo(final String cellNo) {
        int len = StringUtils.length(cellNo);
        return ((len == 10 || len == 11) && (StringUtils.isNumeric(cellNo)) && (cellNo.startsWith("01"))) ? true : false;
    }
    public static final boolean isNotValidCellNo(final String cellNo) {
        return !isValidCellNo(cellNo);
    }

    /**
     * 전화번호 형식이 올바른지 검사.
     * @param telNo 전화번호
     * @return 형식이 올바르면  true, 아니면 false
     */
    public static final boolean isValidTelNo(final String telNo) {
        int len = StringUtils.length(telNo);
        return ((len > 7 && len < 12) && (StringUtils.isNumeric(telNo))) ? true : false;
    }
    public static final boolean isNotValidTelNo(final String telNo) {
        return !isValidCellNo(telNo);
    }

    /**
     * 사업자번호 유효성 체크
     * Examples :
     * isValidBussRegNo("1298687864") returns true
     * isValidBussRegNo("129-86-87864") returns true
     * isValidBussRegNo("1111111111") returns false
     * @param pBussRegNo
     * @return 유효할 경우 true, 나머지 false
     */
    public static boolean isValidBussRegNo(final String pBussRegNo) {
        String bussRegNo = StringUtils.defaultString(pBussRegNo).replaceAll("[^0-9]", "");

        if(StringUtils.length(bussRegNo) != 10 || StringUtils.isNumeric(bussRegNo) == false) {
            return false;
        }
        int sum = 0;
        String checkKey = "137137135";
        for(int i=0; i<checkKey.length(); i++) {
            sum += Character.getNumericValue(bussRegNo.charAt(i)) * Character.getNumericValue(checkKey.charAt(i));
        }
        sum += Character.getNumericValue(bussRegNo.charAt(8)) * 5 / 10;
        sum %= 10;
        sum = (sum == 0) ? 0 : 10 - sum;

        return (sum == Character.getNumericValue(bussRegNo.charAt(9))) ? true : false;
    }

    public static boolean isNotValidBussRegNo(final String pBussRegNo) {
        return !isValidBussRegNo(pBussRegNo);
    }
}

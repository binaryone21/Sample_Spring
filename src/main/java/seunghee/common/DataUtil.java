package seunghee.common;

public class DataUtil {

    // 문자열이 null 값이면 value 로 변환
    public static String ifNull(String key, String value) {
        return (key == null) ? value : key;
    }

    // 숫자값이 0 이면 value 로 변환
    public static int ifNull(int key, int value) {
        return (key == 0) ? value : key;
    }

    // 주어진 하나의 문자열을 json 형태로 변환
    public static String json(String str) {
        return "{ \"data\" : \"" + str + "\"}";
    }
}

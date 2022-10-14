package seunghee.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;



import java.sql.Timestamp;
import java.util.Map;

public class MapTool {

    /**
     * Map으로부터 key에 해당하는 value를 반환한다.
     * @param	<T>		해당 오브젝트
     * @param	map		맵
     * @param	key		키
     * @return	key에 해당하는 값
     */
    public static final <T> T get(Map map, Object key) {
        return get(map, key, null);
    }

    /**
     * Map으로부터 key에 해당하는 value를 반환한다. 맵이 null 이거나 값이 없을 경우 defaultValue를 반환한다.
     * @param   <T>			    defaultVal 타입의 오브젝트
     * @param   map			    맵
     * @param   key			    키
     * @param   defaultValue    맵이 null 이거나 해당값이 null 일 경우 기본값
     * @return	key에 해당하는 값, 없으면 defaultValue
     */
    public static final <T> T get(Map map, Object key, T defaultValue) {
        if(map == null)     return defaultValue;

        T value = (T)map.get(key);
        return (value == null) ? defaultValue : value;
    }

    /**
     * 맵 초기화.
     * @param	m	초기화할 맵
     */
    public static final void clear(Map m) {
        if(m != null)       m.clear();
    }

    /**
     * Map에서 Long value 가져오기.
     * @param   map
     * @param   name
     * @return  값이 없을 경우 null을 리턴 한다.
     */
    public static Long getLong(Map<String,? extends Object> map, String name) {
        Long retValue = null;

        Object value = map.get(name);
        if(value instanceof String) {
            String strValue = (String)value;
            if(NumberUtils.isDigits(strValue)) {
                retValue = Long.valueOf((String)value);
            } else {
                retValue = 0L;
            }
        } else if(value instanceof Integer) {
            retValue = Long.valueOf((int)value);
        } else if (value instanceof Long) {
            retValue = (Long)value;
        }

        return retValue;
    }

    /**
     * Map에서 Long value 가져오기.
     * @param   map
     * @param   name
     * @param   defaultValue
     * @return  값이 null이면 defaultValue 값을 리턴 한다.
     */
    public static long getLong(Map<String,Object> map, String name, long defaultValue) {
        long retValue;

        Object value = map.get(name);
        if(value == null) {
            retValue = defaultValue;
        } else if(value instanceof String) {
            String strValue = (String)value;
            if(NumberUtils.isDigits(strValue)) {
                retValue = Long.valueOf(strValue);
            } else {
                retValue = 0L;
            }
        } else if(value instanceof Integer) {
            retValue = Long.valueOf((int)value);
        } else if(value instanceof Long) {
            retValue = (Long)value;
        } else {
            retValue = defaultValue;
        }

        return retValue;
    }

    /**
     * Map에서 Integer value 가져오기. 값이 없으면 0을 리턴 한다.
     * @param   map
     * @param   name
     * @return
     */
    public static int getInteger(Map<String,? extends Object> map, String name) {
        return getInteger(map, name, 0);
    }

    public static int getInteger(Map<String,? extends Object> map, String name, int defaultValue) {
        int retValue;

        Object value = map.get(name);
        if(value == null) {
            retValue = defaultValue;
        } else if(value instanceof String) {
            String strValue = (String)value;
            if(NumberUtils.isDigits(strValue)) {
                retValue = Integer.valueOf(strValue);
            } else {
                retValue = defaultValue;
            }
        } else if(value instanceof Long) {
            retValue = ((Long)value).intValue();
        } else if(value instanceof Integer) {
            retValue = ((Integer)value).intValue();
        } else if(value instanceof Double) {
            retValue = ((Double)value).intValue();
        } else {
            retValue = defaultValue;
        }

        return retValue;
    }

    /**
     * Map에서 Double 값 가져오기. 없으면 0.0을 리턴 한다.
     * @param   map
     * @param   name
     * @return
     */
    public static Double getDouble(Map<String,? extends Object> map, String name) {
        Double retValue = null;

        Object value = map.get(name);
        if(value == null) {
            retValue = 0.0;
        } else if(value instanceof String) {
            String strValue = (String)value;
            if(NumberUtils.isNumber(strValue)) {
                retValue = Double.valueOf(strValue);
            } else {
                retValue = 0.0;
            }
        } else if (value instanceof Double) {
            retValue = (Double)value;
        } else {
            retValue = 0.0;
        }

        return retValue;
    }


    /**
     * Map에서 Timestamp 값 가져오기
     * @param   map
     * @param   name
     * @return
     */
    public static Timestamp getTimestamp(Map<String,Object> map, String name) {
        Timestamp retValue = null;

        Object value = map.get(name);
        if(value instanceof String) {
            String strValue = (String)value;
            if(StringUtils.isNotEmpty(strValue)) {
                retValue = Timestamp.valueOf((String)value);
            }
        } else if(value instanceof Long) {
            retValue = new Timestamp((Long)value);
        } else if(value instanceof Timestamp) {
            retValue = (Timestamp) value;
        }

        return retValue;
    }

    public static String convertMapToQueryString(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();

        for(String key : map.keySet()) {
            stringBuilder.append(String.format("%s%s=%s", (stringBuilder.length() > 0) ? "&" : "", key, MapUtils.getString(map, key)));
        }

        return stringBuilder.toString();
    }

    public static final JSONObject convertMapToJson(Map<String, Object> map) throws Exception {
        String str = new ObjectMapper().writeValueAsString(map);
        JSONObject json = JSONObject.fromObject(str);
        return json;
    }
}
package seunghee.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.reflect.FieldUtils;

import java.util.Map;

public class ObjectTool {

    /**
     * Object 에서 fldNm에 해당되는 Object 반환.
     * @param   obj		    타겟 오브젝트
     * @param   fldNm		꺼낼 대상 이름
     * @return	해당Object
     */
    @SuppressWarnings({ "rawtypes" })
    public static final Object  getObject(Object obj, String fldNm) {
        Object value = null;

        try {
            if(obj instanceof Map) {
                value = MapUtils.getObject((Map)obj, fldNm);
            } else {
                value = FieldUtils.readField(obj, fldNm, true);
            }
        } catch(IllegalAccessException e) {
            e.printStackTrace();
            value = null;
        }

        return value;
    }


    /**
     * Object 에서 fldNm에 해당되는 Object 를 String 으로 반환.
     * @param   obj		타겟 오브젝트
     * @param   fldNm	꺼낼 대상 이름
     * @return	문자열
     */
    public static final String getString(Object obj, String fldNm) {
        Object value = getObject(obj, fldNm);
        return value.toString();
    }

    /**
     * Object 에서 fldNm에 해당되는 Object 를 int 로 반환.
     * @param   obj		타겟 오브젝트
     * @param   fldNm	꺼낼 대상 이름
     * @return	정수
     */
    public static final int getInt(Object obj, String fldNm) {
        return NumberUtils.toInt(getString(obj, fldNm));
    }

    /**
     * Object 에서 fldNm에 해당되는 Object 를 long 으로 반환.
     * @param   obj		타겟 오브젝트
     * @param   fldNm	꺼낼 대상 이름
     * @return	long
     */
    public static final long getLong(Object obj, String fldNm) {
        return NumberUtils.toLong(getString(obj, fldNm));
    }

    /**
     * Object 를 toString()
     * @param   obj		오브젝트
     * @return	String
     */
    public static final String objectToString(Object obj) {
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.MULTI_LINE_STYLE );
    }

    public static final String writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

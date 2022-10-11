package seunghee.common;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.*;

public class ListTool {

    /**
     * 리스트의 사이즈 반환.
     * @param	list	리스트
     * @return	리스트의 사이즈, 리스트가 null 이면 0 을 반환
     */
    public static final int size(Collection<?> list) {
        return (list == null) ? 0 : list.size();
    }

    /**
     * 문자열을 받아 구분자로 자르고 blank가 아닌 값들만 List 에 담아 리턴.
     * @param	s			구분자로 구분된 문자열
     * @param	separator	구분자
     * @return	List        값이 없으면 사이즈가 0인 리스트를 반환
     */
    public static final List<String> toStringList(String s, char separator) {
        return toStringList(s, new Character(separator).toString());
    }

    /**
     * 문자열을 받아 구분자로 자르고 blank가 아닌 값들만 List 에 담아 리턴.
     * @param	s			구분자로 구분된 문자열
     * @param	separator	구분자
     * @return	List        값이 없으면 사이즈가 0인 리스트를 반환
     */
    public static final List<String> toStringList(String s, String separator) {
        List<String> list = new ArrayList<>();
        if(StringUtils.isBlank(s))
            return list;

        String[] arr = StringUtils.splitPreserveAllTokens(s, separator);
        for(String sub : arr) {
            if(StringUtils.isNotBlank(sub)) {
                list.add(StringUtils.trim(sub));
            }
        }

        return list;
    }

    /**
     * 문자열을 받아 구분자로 자르고 값이 blank 가 아닌 값들만 trim한 후 대문자로 변환해 List 에 담아 리턴.
     * @param	str			구분자로 구분된 문자열
     * @param	separator	구분자
     * @return	유효한 값들만 담은 List
     */
    public static final List<String> toUpperStringList(String str, char separator) {
        List<String> list = toStringList(str, separator);

        int size = size(list);
        for(int i=0;  i<size; i++) {
            list.set(i, list.get(i).trim().toUpperCase());
        }

        return list;
    }

    /**
     * 문자열을 받아 구분자로 자르고 blank가 아닌 값들만 int 로 변환하여 List 에 담아 리턴.
     * @param	s			구분자로 구분된 문자열
     * @param	separator	구분자
     * @return	List        값이 없으면 사이즈가 0인 리스트를 반환
     */
    public static final List<Integer> toIntList(String s, char separator) {
        return toIntList(s, new Character(separator).toString());
    }

    /**
     * 문자열을 받아 구분자로 자르고 blank가 아닌 값들만 int 로 변환하여 List 에 담아 리턴.
     * @param	s			구분자로 구분된 문자열
     * @param	separator	구분자
     * @return	List        값이 없으면 사이즈가 0인 리스트를 반환
     */
    public static final List<Integer> toIntList(String s, String separator) {
        List<Integer> list = new ArrayList<>();

        if(StringUtils.isBlank(s))
            return list;

        String[] arr = StringUtils.splitPreserveAllTokens(s, separator);
        for(String sub : arr) {
            if(StringUtils.isNotBlank(sub)) {
                list.add(NumberUtils.toInt(StringUtils.trim(sub)));
            }
        }

        return list;
    }

    /**
     * 문자열을 받아 구분자로 자르고 blank가 아닌 값들만 long 으로 변환하여 List 에 담아 리턴.
     * @param	s			구분자로 구분된 문자열
     * @param	separator	구분자
     * @return	List        값이 없으면 사이즈가 0인 리스트를 반환
     */
    public static final List<Long> toLongList(String s, char separator) {
        return toLongList(s, new Character(separator).toString());
    }

    /**
     * 문자열을 받아 구분자로 자르고 blank가 아닌 값들만 long 으로 변환하여 List 에 담아 리턴.
     * @param	s			구분자로 구분된 문자열
     * @param	separator	구분자
     * @return	List        값이 없으면 사이즈가 0인 리스트를 반환
     */
    public static final List<Long> toLongList(String s, String separator) {
        List<Long> list = new ArrayList<>();

        if(StringUtils.isBlank(s))
            return list;

        String[] arr = StringUtils.splitPreserveAllTokens(s, separator);
        for(String sub : arr) {
            if(StringUtils.isNotBlank(sub)) {
                list.add(NumberUtils.toLong(StringUtils.trim (sub)));
            }
        }

        return list;
    }

    /**
     * 정렬된 list 를 생성해서 반환 (sourceList 에 속한 element 를 deep copy 하지는 않음).
     * @param	sourceList      목록
     * @param	c               정렬 Comparator
     * @return	정렬된 list       원본 리스트의 순서는 바뀌지 않음
     */
    public static <T> List<T> getSortedList(List<T> sourceList, Comparator<? super T> c) {
        List<T> sortedList = new ArrayList<>();
        sortedList.addAll(sourceList);
        Collections.sort(sortedList, c);
        return sortedList;
    }


    /**
     * 리스트의 특정 값에 대해 unique 한 값들을 오름차순으로 정렬된 리스트로 반환.
     * @param	sourceList		키값을 추출할 목록
     * @param	key				추출할 키
     * @return					key 에 해당되는 unique 한 값들의 오름차순 정렬된 목록
     * @throws	Exception		예외
     */
    public static <T> List<T> getDistinctKeyList(List<?> sourceList, String key) throws Exception {
        return getDistinctKeyList(sourceList, key, false);
    }

    /**
     * 엘리먼트의 특정 값에 대해 unique 한 값들을 정렬된 리스트로 반환.
     * @param	sourceList		키값을 추출할 목록
     * @param	key				추출할 키
     * @param	descending		내림차순 여부
     * @return					key 에 해당되는 unique 한 값들의 정렬된 목록
     * @throws	Exception		예외
     */
    public static <T> List<T> getDistinctKeyList(List<?> sourceList, String key, boolean descending) throws Exception {
        return getDistinctKeyList(sourceList, null, null, key, descending);
    }

    /**
     * 엘리먼트의 부모들에게 속하는 특정 값에 대해 unique 한 값들을 정렬된 리스트로 반환.
     * @param	sourceList		키값을 추출할 목록
     * @param	parentKeys		부모키들 (최상위 부모부터 차례대로)
     * @param	parentValues	부모값들 (최상위 부모부터 차례대로, parentKeys 와 쌍으로 적용됨)
     * @param	key				추출할 키
     * @param	descending		내림차순 여부
     * @return					key 에 해당되는 unique 한 값들의 정렬된 목록
     * @throws	Exception		예외
     */
    @SuppressWarnings("unchecked")
    public static <T, T1> List<T> getDistinctKeyList(List<?> sourceList, String[] parentKeys, T1[] parentValues, String key, boolean descending) throws Exception {
        int	parentKeyCount		= ArrayUtils.getLength(parentKeys);
        int	parentValueCount	= ArrayUtils.getLength(parentValues);

        if(parentKeyCount != parentValueCount) {
            throw new Exception ("부모키,부모값 갯수는 일치해야 합니다");
        }

        List<T> keyList = new ArrayList<>();

        for(Object source : sourceList) {
            T value = (T) ObjectTool.getObject(source, key);
            if(keyList.contains(value)) {
                continue;
            }

            boolean	addingYn = true;
            for(int ii = 0;  ii < parentKeyCount;  ii++) {
                T1 pValue = (T1) ObjectTool.getObject(source, parentKeys[ii]);
                if(pValue.toString().equals (parentValues[ii].toString()) == false) {
                    addingYn = false;
                    continue;
                }
            }

            if(addingYn == true) {
                keyList.add(value);
            }
        }

        // 내림차순
        if(descending) {
            Collections.sort(keyList, new Comparator<T>() {
                @Override
                public int compare(T t1, T t2) {
                    if(t1 instanceof Long) {
                        long l1 = (Long) t1;
                        long l2 = (Long) t2;
                        if(l1 > l2)	{return -1;}
                        if(l1 < l2)	{return 1;}
                    }
                    if(t1 instanceof Integer) {
                        int i1 = (Integer) t1;
                        int i2 = (Integer) t2;
                        if(i1 > i2)	{return -1;}
                        if(i1 < i2)	{return 1;}
                    }
                    if(t1 instanceof String) {
                        return ((String) t2).compareTo((String) t1);
                    }
                    return 0;
                }
            });
        } else {        // 오름차순
            Collections.sort(keyList, new Comparator<T>() {
                @Override
                public int compare(T t1, T t2) {
                    if(t1 instanceof Long) {
                        long l1 = (Long) t1;
                        long l2 = (Long) t2;
                        if(l1 > l2)	{return 1;}
                        if(l1 < l2)	{return -1;}
                    }
                    if(t1 instanceof Integer) {
                        int i1 = (Integer) t1;
                        int i2 = (Integer) t2;
                        if(i1 > i2)	{return 1;}
                        if(i1 < i2)	{return -1;}
                    }
                    if(t1 instanceof String) {
                        return ((String) t1).compareTo((String) t2);
                    }
                    return 0;
                }
            });
        }

        return keyList;
    }

    /**
     * 부모들에게 속하는 엘리먼트들을 반환.
     * @param	sourceList		자식을 추출할 목록
     * @param	parentKeys		부모키들 (최상위 부모부터 차례대로)
     * @param	parentValues	부모값들 (최상위 부모부터 차례대로, parentKeys 와 쌍으로 적용됨)
     * @return					부모들에게 속하는 엘리먼트 목록
     * @throws	Exception		예외
     */
    @SuppressWarnings("unchecked")
    public static <T, T1> List<T> getChildList(List<T> sourceList, String[] parentKeys, T1[] parentValues) throws Exception {
        int	parentKeyCount		= ArrayUtils.getLength (parentKeys);
        int	parentValueCount	= ArrayUtils.getLength (parentValues);
        if(parentKeyCount != parentValueCount) {
            throw new Exception ("부모키,부모값 갯수는 일치해야 합니다");
        }

        List<T> childList = new ArrayList<>();
        for(T source : sourceList) {
            boolean	addingYn = true;
            for(int ii = 0; ii < parentKeyCount; ii++) {
                T1 pValue = (T1) ObjectTool.getObject(source, parentKeys[ii]);
                if(pValue.toString().equals(parentValues[ii].toString()) == false) {
                    addingYn = false;
                    break;
                }
            }

            if(addingYn == true) {
                childList.add(source);
            }
        }

        return childList;
    }

    /**
     * List안에 비교대상 Object와 동일한 갯수.
     * @param	list			리스트
     * @param	compareObj		비교대상 Object
     * @param	compareEndIdx	마지막 비교할 idx(0보다작으면 전제list 모두 비교)
     * @return	List
     */
    @Deprecated
    public static final int getEqualsCount(List<?> list, Object compareObj, int compareEndIdx ) {
        int equalsCnt = 0;

        for(int i=0 ; i<list.size() ; i++) {
            Object obj = list.get(i);
            if(obj.equals(compareObj)) {
                equalsCnt++;
            }

            if(compareEndIdx>=0 && i >= compareEndIdx) break;
        }

        return equalsCnt;
    }

    /**
     * 리스트에서 중복을 제거한다.
     * @author	kmbaek
     * @since	2018-03-23
     * @param	list
     * @return
     */
    public static final List<?> getDuplicateIgnoreList(List<?> list) {
        return new ArrayList<>(new HashSet<>(list));
    }
}

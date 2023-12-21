package seunghee.common;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

// 내가 직접 안함

/**
 * 출력 메소드.
 *
 * @author	박관렬
 * @since	2018-01-23
 */
public class PrintTool
{

	/**
	 * 생성자.
	 */
	private PrintTool()
	{

	}

	/**
	 * 문자열을 콘솔에 로깅.
	 *
	 * @param s		로깅할 문자열
	 */
	public static final void print (String s)
	{
		print (s, null);
	}

	/**
	 * 문자열을 로거에 로깅(로거가 null이면 콘솔에 로깅).
	 *
	 * @param s			로깅할 문자열
	 * @param logger	로거
	 */
	public static final void print (String s, Logger logger)
	{
		if (logger == null)
		{
			System.out.println (s);
		}
		else
		{
			logger.info (s);
		}
	}

	/**
	 * Exception을 콘솔에 로깅.
	 *
	 * @param e			예외
	 */
	public static final void print (Exception e)
	{
		print (e, null);
	}

	/**
	 * Exception을 로거에 로깅(로거가 null이면 콘솔에 로깅).
	 *
	 * @param e			예외
	 * @param logger	로거
	 */
	public static final void print (Exception e, Logger logger)
	{
		if (logger == null)
		{
			e.printStackTrace();
		}
		else
		{
			logger.error ("", e);
		}
	}

	/**
	 * FileItem 을 콘솔에 출력.
	 *
	 * @param item	파일아이템
	 */
	public static final void print (FileItem item)
	{
		print (item, null);
	}

	/**
	 * FileItem 을 로거에 출력.
	 *
	 * @param item		파일아이템
	 * @param logger	로거
	 */
	public static final void print (FileItem item, Logger logger)
	{

		if (item == null)
		{
			print ("is null...", logger);
			return;
		}

		print ("", logger);
		print ("\t### item.getFieldName()  =[" +item.getFieldName()+ "]", logger);
		if (item.isFormField())
		{
			// print ("\t### item.getString()     =[" +StringTool.enToUtf (item.getString())+ "]", logger);
		}
		else
		{
			print ("\t### item.getName()       =[" +item.getName()+ "]", logger);
			print ("\t### item.getContentType()=[" +item.getContentType()+ "]", logger);
		}
		print ("\t### item.getSize()       =[" +item.getSize()+ "]", logger);
		print ("\t### item.isInMemory()    =[" +item.isInMemory()+ "]", logger);

	}

	/**
	 * MultipartFile 을 콘솔에 출력.
	 *
	 * @param mf	multipart file
	 */
	public static final void print (MultipartFile mf)
	{
		print (mf, null);
	}

	/**
	 * MultipartFile 을 로거에 출력.
	 *
	 * @param mf		multipart file
	 * @param logger	로거
	 */
	public static final void print (MultipartFile mf, Logger logger)
	{
		if (mf == null)
		{
			print ("is null...", logger);
			return;
		}

		print ("", logger);

		print ("\t### originalFilename =[" +mf.getOriginalFilename()+ "]", logger);		// 원래파일명
		print ("\t### size             =[" +mf.getSize()+ "]", logger);					// 파일크기
		print ("\t### input name       =[" +mf.getName()+ "]", logger);					// input name
		print ("\t### contentType      =[" +mf.getContentType()+ "]", logger);			// content type ("image/png",...)
	}

	/**
	 * MultipartFile 을 로거에 출력.
	 *
	 * @param mfArr		multipart file
	 * @param logger	로거
	 */
	public static final void print (MultipartFile[] mfArr, Logger logger)
	{
		if (mfArr == null)
		{
			print ("is null...", logger);
			return;
		}

		for (MultipartFile mf : mfArr)
		{
			print (mf, logger);
		}
	}



	/**
	 * 맵 콘솔에 출력.
	 *
	 * <pre>
	 * java.util.Map
	 * java.util.HashMap
	 *
	 * java.util.Hashtable
	 * java.util.Properties
	 *
	 * </pre>
	 *
	 * @param	m	출력해보고자 하는 맵
	 */
	public static final void print (Map<?,?> m)
	{
		print (m, null);
	}

	/**
	 * 맵을 키이름으로 오름차순으로 정렬해서 값을 콘솔에 출력.
	 *
	 * @param m	출력할 맵
	 */
	public static final void printOrdered (Map<?,?> m)
	{
		printOrdered (m, false);
	}

	/**
	 * 맵을 키이름으로 정렬해서 값을 콘솔에 출력.
	 *
	 * @param m				출력할 맵
	 * @param descending	내림차순 여부
	 */
	public static final void printOrdered (Map<?,?> m, boolean descending)
	{
		printOrdered (m, descending, null);
	}

	/**
	 * 맵 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	m		출력해보고자 하는 맵
	 * @param	logger	로거
	 */
	public static final void print(Map<?,?> m, Logger logger)
	{

		if (m == null)
		{
			print ("is null...", logger);
			return;
		}

		for (Entry<?,?> entry : m.entrySet())
		{
			String logString = "[" +entry.getKey()+ "]=[" +entry.getValue()+ "]";
			print (logString, logger);
		}

	}

	/**
	 * 맵을 키이름으로 오름차순으로 정렬해서 값을 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	m		출력할 맵
	 * @param	logger	로거
	 */
	public static final void printOrdered (Map<?,?> m, Logger logger)
	{
		printOrdered (m, false, logger);
	}

	/**
	 * 맵을 키이름으로 정렬해서 값을 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	m			출력할 맵
	 * @param	descending	내림차순 여부
	 * @param	logger		로거
	 */
	public static final void printOrdered (Map<?,?> m, boolean descending, Logger logger)
	{

		if (m == null)
		{
			print ("is null...", logger);
			return;
		}

		TreeSet<Object> treeSet = new TreeSet<Object>(m.keySet());
		Iterator<Object> it = descending ? treeSet.descendingIterator() : treeSet.iterator();
		while (it.hasNext())
		{
			Object key = it.next();
			String logString = "[" +key+ "]=[" +m.get(key)+ "]";
			print (logString, logger);
		}

	}



	/**
	 * 리스트 콘솔에 출력.
	 *
	 * @param list	출력할 리스트
	 */
	public static final void print (List<?> list)
	{
		print (list, null);
	}

	/**
	 * 리스트 값을 오름차순으로 정렬해서 콘솔에 출력.
	 *
	 * @param list			출력할 리스트
	 */
	public static final void printOrdered (List<?> list)
	{
		printOrdered (list, false);
	}

	/**
	 * 리스트 값을 정렬해서 콘솔에 출력.
	 *
	 * @param list			출력할 리스트
	 * @param descending	내림차순 여부
	 */
	public static final void printOrdered (List<?> list, boolean descending)
	{
		printOrdered (list,descending, null);
	}

	/**
	 * 리스트 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	list	출력할 리스트
	 * @param	logger	로거
	 */
	public static final void print (List<?> list, Logger logger)
	{

		if (list == null)
		{
			print ("is null...", logger);
			return;
		}

		int listCount = ListTool.size (list);
		for (int ii=0;  ii<listCount;  ii++)
		{
			Object obj = list.get(ii);
			if (obj instanceof FileItem)
			{
				print ((FileItem)obj, logger);
			}
			else
			{
				String logString = "[" +obj+ "]";
				print (logString, logger);
			}
		}
	}

	/**
	 * 리스트 값을 오름차순으로 정렬해서 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	list	출력할 리스트
	 * @param	logger	로거
	 */
	public static final void printOrdered (List<?> list, Logger logger)
	{
		printOrdered (list, false, logger);
	}

	/**
	 * 리스트 값을 정렬해서 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	list		출력할 리스트
	 * @param	descending	내림차순 여부
	 * @param	logger		로거
	 */
	public static final void printOrdered (List<?> list, boolean descending, Logger logger)
	{

		if (list == null)
		{
			print ("is null...", logger);
			return;
		}

		TreeSet<Object> treeSet = new TreeSet<Object>(list);
		Iterator<Object> it = descending ? treeSet.descendingIterator() : treeSet.iterator();
		while (it.hasNext())
		{
			Object obj = it.next();
			if (obj instanceof FileItem)
			{
				print ((FileItem)obj, logger);
			}
			else
			{
				String logString = "[" +obj+ "]";
				print (logString, logger);
			}
		}
	}



	/**
	 * 배열 콘솔에 출력.
	 *
	 * @param arr			출력할 배열
	 */
	public static final void print (Object[] arr)
	{
		print (arr, null);
	}

	/**
	 * 배열의 값을 오름차순으로 정렬해서 콘솔에 출력.
	 *
	 * @param arr			출력할 배열
	 */
	public static final void printOrdered (Object[] arr)
	{
		printOrdered (arr, false);
	}

	/**
	 * 배열의 값을 정렬해서 콘솔에 출력.
	 *
	 * @param arr			출력할 배열
	 * @param descending	내림차순 여부
	 */
	public static final void printOrdered (Object[] arr, boolean descending)
	{
		printOrdered (arr, descending, null);
	}

	/**
	 * 배열 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	arr		출력할 배열
	 * @param	logger	로거
	 */
	public static final void print (Object[] arr, Logger logger)
	{

		if (arr == null)
		{
			print ("is null...", logger);
			return;
		}

		int arrCount = arr.length;
		for (int ii=0;  ii<arrCount;  ii++)
		{
			String logString = "[" +arr[ii]+ "]";
			print ("\t[" +ii+ "] = " +logString, logger);
		}
	}

	/**
	 * 배열의 값을 오름차순으로 정렬해서 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	arr		출력할 배열
	 * @param	logger	로거
	 */
	public static final void printOrdered (Object[] arr, Logger logger)
	{
		printOrdered (arr, false, logger);
	}

	/**
	 * 배열의 값을 정렬해서 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	arr			출력할 배열
	 * @param	descending	내림차순 여부
	 * @param	logger		로거
	 */
	public static final void printOrdered (Object[] arr, boolean descending, Logger logger)
	{

		if (arr == null)
		{
			print ("is null...", logger);
			return;
		}

		TreeSet<Object> treeSet = new TreeSet<Object>(Arrays.asList (arr));
		Iterator<Object> it = descending ? treeSet.descendingIterator() : treeSet.iterator();
		while (it.hasNext())
		{
			String logString = "[" +it.next()+ "]";
			print (logString, logger);
		}
	}









	/**
	 * request에 담긴 값을 콘솔에 출력.
	 *
	 * @param	req			출력할 request
	 */
	public static final void print (HttpServletRequest req)
	{
		print (req, null);
	}

	/**
	 * request에 담긴 값을 이름순으로 오름차순으로 정렬해서 로거에 출력.
	 *
	 * @param	req			출력할 request
	 */
	public static final void printOrdered (HttpServletRequest req)
	{
		printOrdered (req, false);
	}

	/**
	 * request에 담긴 값을 이름순으로 정렬해서 콘솔에 출력.
	 *
	 * @param	req			출력할 request
	 * @param	descending	내림차순 여부
	 */
	public static final void printOrdered (HttpServletRequest req, boolean descending)
	{
		printOrdered (req, descending, null);
	}

	/**
	 * request에 담긴 값을 로거에 출력.
	 *
	 * <p>로거가 null이면 콘솔에 출력</p>
	 *
	 * @param	req		출력할 request
	 * @param	logger	로거
	 */
	public static final void print (HttpServletRequest req, Logger logger)
	{

		if (req == null)
		{
			print ("is null...", logger);
			return;
		}

		print ("", logger);
		print ("----------------------------------------------------------------", logger);
		print ("[getRequestURL()]  = [" +req.getRequestURL()+ "]", logger);
		print ("[getRequestURI()]  = [" +req.getRequestURI()+ "]", logger);
		print ("[getQueryString()] = [" +req.getQueryString()+ "]", logger);
		print ("[getMethod()]      = [" +req.getMethod()+ "]", logger);
		print ("[getContentType()] = [" +req.getContentType()+ "]", logger);
		print ("[getRemoteAddr()]  = [" +req.getRemoteAddr()+ "]", logger);

		for (Enumeration<String> _e = req.getParameterNames(); _e.hasMoreElements(); )
		{
			String _name = _e.nextElement();
			String[] arr = req.getParameterValues (_name);
			int arrCount = arr.length;
			for (int ii=0;  ii<arrCount;  ii++)
			{
				print ("[" +_name+ "][" +ii+ "] = [" +arr[ii]+ "]", logger);
			}
		}
//		print("[request body] = [" +SFWebTool.getBody(req)+ "]", logger);
		print ("----------------------------------------------------------------", logger);
	}

	/**
	 * request에 담긴 값을 이름순으로 오름차순으로 정렬해서 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	req		출력할 request
	 * @param	logger	로거
	 */
	public static final void printOrdered (HttpServletRequest req, Logger logger)
	{
		printOrdered (req, false, logger);
	}

	/**
	 * request에 담긴 값을 이름순으로 정렬해서 로거에 출력.
	 *
	 * <p>로거가 null이면 콘솔에 출력</p>
	 *
	 * @param	req			출력할 request
	 * @param	descending	내림차순 여부
	 * @param	logger		로거
	 */
	public static final void printOrdered (HttpServletRequest req, boolean descending, Logger logger)
	{

		if (req == null)
		{
			print ("is null...", logger);
			return;
		}

		print ("", logger);
		print ("----------------------------------------------------------------", logger);
		print ("[getRequestURL()]  = [" +req.getRequestURL()+ "]", logger);
		print ("[getRequestURI()]  = [" +req.getRequestURI()+ "]", logger);
		print ("[getQueryString()] = [" +req.getQueryString()+ "]", logger);
		print ("[getMethod()]      = [" +req.getMethod()+ "]", logger);
		print ("[getContentType()] = [" +req.getContentType()+ "]", logger);
		print ("[getRemoteAddr()]  = [" +req.getRemoteAddr()+ "]", logger);

		TreeSet<String> treeSet = new TreeSet<String>();
		for (Enumeration<String> _e = req.getParameterNames(); _e.hasMoreElements(); )
		{
			treeSet.add (_e.nextElement());
		}

		Iterator<String> it = descending ? treeSet.descendingIterator() : treeSet.iterator();
		while (it.hasNext())
		{
			String _name = it.next();
			String[] arr = req.getParameterValues (_name);
			int arrCount = arr.length;
			for (int ii=0;  ii<arrCount;  ii++)
			{
				print ("[" +_name+ "][" +ii+ "] = [" +arr[ii]+ "]", logger);
			}
		}
	}


	/**
	 * 쿠키들의 값을 로거에 출력.
	 *
	 * <p>로거가 null이면 콘솔에 출력</p>
	 *
	 * @param	req			출력할 request
	 * @param	logger		로거
	 *
	 * @throws	Exception	예외
	 */
	public static final void printCookies (HttpServletRequest req, Logger logger) throws Exception
	{

		Cookie[] cookies = req.getCookies();
		if (cookies == null)
		{
			return;
		}

		StringBuilder sb = new StringBuilder (50);
		int n = 0;
		for (Cookie cookie : cookies)
		{
			sb.append (++n + ",Name=[" +cookie.getName()+ "]\n");
			sb.append (n + ",Value=[" +cookie.getValue()+ "]\n");
			sb.append (n + ",Domain  =[" +cookie.getDomain()+ "]\n");
			sb.append (n + ",Path    =[" +cookie.getPath()+ "]\n");
			sb.append (n + ",Expires =[" +cookie.getMaxAge()+ "]\n");
			sb.append (n + ",Secure  =[" +cookie.getSecure()+ "]\n\n");
		}

		// print (StringTool.decode (sb.toString(), CharEncoding.UTF_8), logger);
	}
	public static final void printCookies (HttpServletRequest req) throws Exception
	{
		printCookies (req, null);
	}



	/**
	 * Object의 필드값과 클래스들의 필드값들을 콘솔에 출력.
	 *
	 * @param	obj			타겟 오브젝트
	 */
	public static final void printFieldAndClass (Object obj)
	{
		printFieldAndClass (obj, null);
	}

	/**
	 * Object의 필드값과 클래스들의 필드값들을 로거에 출력.
	 *
	 * @param	obj			타겟 오브젝트
	 * @param	logger		로거
	 */
	public static final void printFieldAndClass (Object obj, Logger logger)
	{

		if (obj == null)
		{
			print ("is null...", logger);
			return;
		}

		try
		{

			// 필드값 출력
			print ("", logger);
			print (String.format("### [%s]", obj.getClass().getName()), logger);
			printFldsVal (obj, logger);
			print ("----------------------------------------------------------", logger);

			// obj 에 선언된 클래스들의 필드값 출력
			for (Class<?> c : obj.getClass().getDeclaredClasses())
			{
				print ("", logger);
				print (String.format("### [%s]", c.getName()), logger);
				printFldsVal (c.newInstance(), logger);
				print ("----------------------------------------------------------", logger);
			}
		}
		catch (InstantiationException e)
		{
			logger.error ("", e);
		}
		catch (IllegalAccessException e)
		{
			logger.error ("", e);
		}

	}


	/**
	 * Object의 필드값과 클래스들의 필드값들을 오름차순으로 정렬해서 콘솔에 출력.
	 *
	 * @param	obj			타겟 오브젝트
	 */
	public static final void printFieldAndClassOrdered (Object obj)
	{
		printFieldAndClassOrdered (obj, false, null);
	}

	/**
	 * Object의 필드값과 클래스들의 필드값들을 오름차순으로 정렬해서 로거에 출력.
	 *
	 * @param	obj			타겟 오브젝트
	 * @param	logger		로거
	 */
	public static final void printFieldAndClassOrdered (Object obj, Logger logger)
	{
		printFieldAndClassOrdered (obj, false, logger);
	}

	/**
	 * Object의 필드값과 클래스들의 필드값들을 정렬해서 로거에 출력.
	 *
	 * @param	obj			타겟 오브젝트
	 * @param	descending	내림차순 여부
	 * @param	logger		로거
	 */
	public static final void printFieldAndClassOrdered (Object obj, boolean descending, Logger logger)
	{

		if (obj == null)
		{
			print ("is null...", logger);
			return;
		}

		try
		{

			// 필드값 출력
			print ("", logger);
			print (String.format("### [%s]", obj.getClass().getName()), logger);
			printFldsValOrdered (obj, descending, logger);
			print ("----------------------------------------------------------", logger);

			// obj 에 선언된 클래스들의 필드값 출력
			for (Class<?> c : obj.getClass().getDeclaredClasses())
			{
				print ("", logger);
				print (String.format("### [%s]", c.getName()), logger);
				if (c.isEnum()) {
					print(c.getEnumConstants(), logger);
				}
				else {
					printFldsValOrdered(c.newInstance(), descending, logger);
				}
				print ("----------------------------------------------------------", logger);
			}
		}
		catch (InstantiationException e)
		{
			logger.error ("", e);
		}
		catch (IllegalAccessException e)
		{
			logger.error ("", e);
		}

	}

	/**
	 * Object의 필드값들을 콘솔에 출력.
	 *
	 * @param	obj			타겟 오브젝트
	 */
	public static final void printFldsVal (Object obj)
	{
		printFldsVal (obj, null);
	}

	/**
	 * Object의 필드값들을 필드명으로 오름차순으로 정렬해서 콘솔에 출력.
	 *
	 * @param	obj			타겟 오브젝트
	 */
	public static final void printFldsValOrdered (Object obj)
	{
		printFldsValOrdered (obj, false);
	}

	/**
	 * Object의 필드값들을 필드명으로 정렬해서 콘솔에 출력.
	 *
	 * @param	obj			타겟 오브젝트
	 * @param	descending	내림차순 여부
	 */
	public static final void printFldsValOrdered (Object obj, boolean descending)
	{
		printFldsValOrdered (obj, descending, null);
	}

	/**
	 * Object의 필드값들을 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	obj			타겟 오브젝트
	 * @param	logger		로거
	 */
	public static final void printFldsVal (Object obj, Logger logger)
	{

		if (obj == null)
		{
			print ("is null...", logger);
			return;
		}

		Field[] fields = obj.getClass().getDeclaredFields();
		int fieldCount = fields==null ? 0 : fields.length;

		for (int ii=0;  ii<fieldCount;  ii++)
		{
			String fldNm = fields[ii].getName();

			String logString = "\t" +String.format("%-30s = [%s]", fldNm, ObjectTool.getObject (obj, fldNm));
			print (logString, logger);
		}

	}

	/**
	 * Object의 필드값들을 필드명으로 오름차순으로 정렬해서 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	obj			타겟 오브젝트
	 * @param	logger		로거
	 */
	public static final void printFldsValOrdered (Object obj, Logger logger)
	{
		printFldsValOrdered (obj, false, logger);
	}

	/**
	 * Object의 필드값들을 필드명으로 정렬해서 로거에 출력.
	 *
	 * <pre>
	 * 로거가 null이면 콘솔에 출력
	 * </pre>
	 *
	 * @param	obj			타겟 오브젝트
	 * @param	descending	내림차순 여부
	 * @param	logger		로거
	 */
	public static final void printFldsValOrdered (Object obj, boolean descending, Logger logger)
	{

		if (obj == null)
		{
			print ("is null...", logger);
			return;
		}

		Field[] fields = obj.getClass().getDeclaredFields();
		int fieldCount = fields==null ? 0 : fields.length;

		TreeSet<String> treeSet = new TreeSet<String>();
		for (int ii=0;  ii<fieldCount;  ii++)
		{
			treeSet.add (fields[ii].getName());
		}

		Iterator<String> it = descending ? treeSet.descendingIterator() : treeSet.iterator();
		while (it.hasNext())
		{
			String fldNm = it.next();
			String logString = "\t" +String.format("%-30s = [%s]", fldNm, ObjectTool.getObject (obj, fldNm));
			print (logString, logger);
		}

	}



	/**
	 * 시스템 속성값 콘솔에 출력.
	 */
	public static final void printSystemProperties()
	{
		print (System.getProperties());
	}

	/**
	 * 시스템 속성값 오름차순으로 콘솔에 출력.
	 */
	public static final void printSystemPropertiesOrdered()
	{
		printOrdered (System.getProperties(), false);
	}

	/**
	 * 시스템 속성값 정렬해서 콘솔에 출력.
	 *
	 * @param	descending	내림차순 여부
	 */
	public static final void printSystemPropertiesOrdered (boolean descending)
	{
		printOrdered (System.getProperties(), descending);
	}

	/**
	 * 시스템 속성값 로거에 출력.
	 *
	 * @param	logger	로거
	 */
	public static final void printSystemProperties (Logger logger)
	{
		print (System.getProperties(), logger);
	}

	/**
	 * 시스템 속성값 오름차순으로 로거에 출력.
	 *
	 * @param	logger	로거
	 */
	public static final void printSystemPropertiesOrdered (Logger logger)
	{
		printOrdered (System.getProperties(), false, logger);
	}

	/**
	 * 시스템 속성값 정렬해서 로거에 출력.
	 *
	 * @param	descending	내림차순 여부
	 * @param	logger		로거
	 */
	public static final void printSystemPropertiesOrdered (boolean descending,Logger logger)
	{
		printOrdered (System.getProperties(), descending, logger);
	}



	/**
	 * HttpServletRequest 의 헤더값들을 콘솔에 출력.
	 *
	 * @param req	request
	 */
	public static final void printHeader (HttpServletRequest req)
	{
		printHeader (req, null);
	}

	/**
	 * HttpServletRequest 의 헤더값들을 헤더이름순으로 오름차순으로 정렬해서 콘솔에 출력.
	 *
	 * @param req	request
	 */
	public static final void printHeaderOrdered (HttpServletRequest req)
	{
		printHeaderOrdered (req, false);
	}

	/**
	 * HttpServletRequest 의 헤더값들을 헤더이름순으로 정렬해서 콘솔에 출력.
	 *
	 * @param	req			request
	 * @param	descending	내림차순 여부
	 */
	public static final void printHeaderOrdered (HttpServletRequest req, boolean descending)
	{
		printHeaderOrdered(req, descending, null);
	}

	/**
	 * HttpServletRequest 의 헤더값들을 로거에 출력.
	 *
	 * @param req		request
	 * @param logger	로거
	 */
	public static final void printHeader (HttpServletRequest req, Logger logger)
	{

		for (Enumeration<String> _e = req.getHeaderNames(); _e.hasMoreElements(); )
		{
			String _name	= _e.nextElement();
			String logString = "[" +_name+ "] = [" +req.getHeader(_name)+ "]";
			print (logString, logger);
		}
	}

	/**
	 * HttpServletRequest 의 헤더값들을 헤더이름순으로 오름차순으로 정렬해서 로거에 출력.
	 *
	 * @param	req			request
	 * @param	logger		로거
	 */
	public static final void printHeaderOrdered (HttpServletRequest req, Logger logger)
	{
		printHeaderOrdered (req, false, logger);
	}

	/**
	 * HttpServletRequest 의 헤더값들을 헤더이름순으로 정렬해서 로거에 출력.
	 *
	 * @param	req			request
	 * @param	descending	내림차순 여부
	 * @param	logger		로거
	 */
	public static final void printHeaderOrdered (HttpServletRequest req, boolean descending, Logger logger)
	{

		if (req == null)
		{
			print ("is null...", logger);
			return;
		}

		TreeSet<String> treeSet = new TreeSet<String>();
		for (Enumeration<String> _e = req.getHeaderNames(); _e.hasMoreElements(); )
		{
			treeSet.add (_e.nextElement());
		}

		Iterator<String> it = descending ? treeSet.descendingIterator() : treeSet.iterator();
		while (it.hasNext())
		{
			String _name = it.next();
			String logString = "[" +_name+ "] = [" +req.getHeader(_name)+ "]";
			print (logString, logger);
		}
	}


	/**
	 * JSP 페이지의 버퍼 사이즈와 남아 있는 버퍼 사이즈를 콘솔에 로깅.
	 *
	 * @param out	JspWriter
	 */
	public static final void printBuffer (JspWriter out)
	{
		System.out.println (String.format ("\tJSP BUFFER: 출력버퍼크기=[%d] bytes, 남은버퍼=[%d] bytes", out.getBufferSize(), out.getRemaining()));
	}

}

package seunghee.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;

public class CellAttrVO {
	/** */
	private static final long serialVersionUID = -1657970682687852649L;

	/** 셀 스타일 */
	private CellStyle style;

	private CellType type	= CellType._NONE;

	/** 셀 값 */
	private Object		value;


	/**
	 * 생성자.
	 */
	public CellAttrVO()
	{
	}

	/**
	 * 생성자.
	 *
	 * @param style	셀의 스타일
	 * @param type	셀의 타입
	 * @param value	셀의 값
	 */
	public CellAttrVO (CellStyle style, CellType type, Object value)
	{
		this.style	= style;
		this.type	= type;
		this.value	= value;
	}


	public CellStyle getStyle()
	{
		return style;
	}
	public void setStyle (CellStyle style)
	{
		this.style = style;
	}

	public CellType getType()
	{
		return type;
	}
	public void setType (CellType type)
	{
		this.type = type;
	}

	public Object getValue()
	{
		return value;
	}
	public void setValue (Object value)
	{
		this.value = value;
	}
}

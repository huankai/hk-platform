package com.hk.emi.vo;

import com.hk.commons.poi.excel.annotations.ReadExcelField;
import com.hk.commons.poi.excel.annotations.WriteExcelField;
import com.hk.commons.util.StringUtils;
import com.hk.commons.validator.constraints.EnumDict;
import com.hk.emi.domain.City;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 城市导入Vo
 *
 * @author kevin
 * @date 2018-04-13 16:34
 */
@Data
@SuppressWarnings("serial")
public class CityExcelVo implements Serializable {

	/**
	 * 上级名称
	 */
	@ReadExcelField(start = 0)
	private String parentName;

	/**
	 * 行政代码
	 */
	@ReadExcelField(start = 1)
	@WriteExcelField(index = 0, value = "行政代码")
	@NotEmpty
	@Length(max = 20)
	private String code;

	/**
	 * 全称
	 */
	@ReadExcelField(start = 2)
	@WriteExcelField(index = 1, value = "全称")
	@NotEmpty
	@Length(max = 50)
	private String fullName;

	/**
	 * 简称
	 */
	@ReadExcelField(start = 3)
	@WriteExcelField(index = 2, value = "简称")
	private String shortName;

	/**
	 * 邮编
	 */
	@ReadExcelField(start = 4)
	@WriteExcelField(index = 3, value = "邮政编码")
	private String postOffice;

	/**
	 * 城市类型
	 */
	@ReadExcelField(start = 5)
	@NotNull
	@EnumDict(codeId = City.CITY_TYPE_DICT_ID)
	private Byte cityType;

	/**
	 * 描述
	 */
	@ReadExcelField(start = 6)
	private String description;

	/**
	 */
	public String getShortName() {
		return StringUtils.isEmpty(shortName) ? StringUtils.substring(fullName, 0, fullName.length() - 1) : shortName;
	}
}

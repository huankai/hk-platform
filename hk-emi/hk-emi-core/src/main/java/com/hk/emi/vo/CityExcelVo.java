package com.hk.emi.vo;

import com.hk.commons.poi.excel.annotations.ReadExcelField;
import com.hk.commons.poi.excel.annotations.WriteExcelField;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    @Length(max = 20)
    @WriteExcelField(index = 0, value = "编号")
    private String code;

    /**
     * 全称
     */
    @ReadExcelField(start = 2)
	@WriteExcelField(index = 1, value = "区域编号")
    @NotEmpty
    @Length(max = 50)
    private String areaCode;


    /**
     * 简称
     */
    @ReadExcelField(start = 3)
    @WriteExcelField(index = 2, value = "简称")
    private Byte cityType;

    /**
     * 邮编
     */
    @ReadExcelField(start = 4)
    @WriteExcelField(index = 3, value = "全称")
    private String fullName;

    /**
     * 城市类型
     */
    @ReadExcelField(start = 5)
    @WriteExcelField(index = 4, value = "简称")
    private String shortName;

    /**
     * 描述
     */
    @ReadExcelField(start = 6)
    private String pinyin;

    @ReadExcelField(start = 7)
    private String postOffice;

    @ReadExcelField(start = 8)
    private Double longitude;

    @ReadExcelField(start = 9)
    private Double latitude;

}

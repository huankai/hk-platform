package com.hk.emi.vo;

import com.hk.commons.poi.excel.annotations.ReadExcel;
import com.hk.commons.poi.excel.annotations.WriteExcel;
import com.hk.commons.util.StringUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 城市导入Vo
 *
 * @author: kevin
 * @date 2018-04-13 16:34
 */
@Data
public class CityExcelVo implements Serializable {

    /**
     * 上级名称
     */
    @ReadExcel(start = 0)
    private String parentName;

    /**
     * 行政代码
     */
    @ReadExcel(start = 1)
    @WriteExcel(index = 0, value = "行政代码")
    @NotBlank(message = "")
    @Length(max = 20, message = "")
    private String code;

    /**
     * 全称
     */
    @ReadExcel(start = 2)
    @WriteExcel(index = 1, value = "全称")
    @NotBlank(message = "")
    @Length(max = 50, message = "")
    private String fullName;

    /**
     * 简称
     */
    @ReadExcel(start = 3)
    @WriteExcel(index = 2, value = "简称")
    private String shortName;

    /**
     * 邮编
     */
    @ReadExcel(start = 4)
    @WriteExcel(index = 3, value = "邮政编码")
    private String postOffice;

    /**
     * 城市类型
     *
     * @see com.hk.emi.domain.City.CityType
     */
    @ReadExcel(start = 5)
    @NotNull
    @Range(min = 1, max = 6, message = "")
    private Byte cityType;

    /**
     * 描述
     */
    @ReadExcel(start = 6)
    private String description;

    /**
     * @return
     */
    public String getShortName() {
        return StringUtils.isEmpty(shortName) ? StringUtils.substring(fullName, 0, fullName.length() - 1) : shortName;
    }
}

package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * 用户身份证信息实体
 *
 * @author: kevin
 * @date: 2018-08-31 11:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user_card")
public class SysUserCard extends AbstractAuditable {

    /**
     * 用户Id
     */
    @NotEmpty
    @Column(value = "user_id")
    private String userId;

    /**
     * 卡编号
     */
    @NotEmpty
    @Length(max = 18)
    @Column(value = "id_card")
    private String idCard;

    /**
     * 卡类型
     */
    @Column(value = "card_type")
    private Byte cardType;

    /**
     * 用户真实名称
     */
    @NotEmpty
    @Length(max = 20)
    @Column(value = "real_name")
    private String realName;

    /**
     * 过期时间
     */
    @NotNull
    @Past
    @Column(value = "expire_date")
    private LocalDate expireDate;

    /**
     * 正面图
     */
    @NotEmpty
    @Column(value = "face_image")
    private String faceImage;

    /**
     * 反面图
     */
    @NotEmpty
    @Column(value = "back_image")
    private String backImage;

    /**
     * 地址
     */
    @NotEmpty
    @Length(max = 100)
    private String address;

    /**
     * 发卡时间
     */
    @Past
    @Column(value = "card_date")
    private LocalDate cardDate;
}

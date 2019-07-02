package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * 用户身份证信息实体
 *
 * @author kevin
 * @date 2018-08-31 11:40
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_user_card")
public class SysUserCard extends AbstractSnowflakeAuditable {

    /**
     * 用户Id
     */
    @NotNull
    @Column(name = "user_id")
    private String userId;

    /**
     * 卡编号
     */
    @NotEmpty
    @Length(max = 18)
    @Column(name = "id_card")
    private String idCard;

    /**
     * 卡类型
     */
    @Column(name = "card_type")
    private Byte cardType;

    /**
     * 用户真实名称
     */
    @NotEmpty
    @Length(max = 20)
    @Column(name = "real_name")
    private String realName;

    /**
     * 过期时间
     */
    @NotNull
    @Past
    @Column(name = "expire_date")
    private LocalDate expireDate;

    /**
     * 正面图
     */
    @NotEmpty
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 反面图
     */
    @NotEmpty
    @Column(name = "back_image")
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
    @Column(name = "card_date")
    private LocalDate cardDate;
}

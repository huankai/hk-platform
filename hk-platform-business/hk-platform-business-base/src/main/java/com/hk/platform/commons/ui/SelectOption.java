package com.hk.platform.commons.ui;

import lombok.Data;

/**
 * @author kevin
 * @date 2019-9-10 15:48
 */
@Data
public class SelectOption {

    private Long id;

    private Boolean disabled = false;

    private String name;
}

package com.hk.pms.commons.tree;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author kevin
 * @date 2018-08-29 09:08
 */
@Data
@Builder
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public class ResourceTree{

    private String resourceUri;

    private String icon;

    private String target;

    private Byte resourceType;
}

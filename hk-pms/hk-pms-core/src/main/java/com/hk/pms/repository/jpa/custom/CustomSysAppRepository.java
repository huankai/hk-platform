package com.hk.pms.repository.jpa.custom;

import com.hk.platform.commons.ui.SelectOption;

import java.util.List;

/**
 * @author kevin
 * @date 2019-9-10 15:51
 */
public interface CustomSysAppRepository {

    List<SelectOption> getSelectOptionList();

}

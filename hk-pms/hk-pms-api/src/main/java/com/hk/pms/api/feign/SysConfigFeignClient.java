package com.hk.pms.api.feign;

import com.hk.commons.util.TextValueItem;
import com.hk.core.authentication.api.SecurityContextUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/sysconfig")
@FeignClient(name = PmsService.SERVICE_NAME, path = PmsService.CONTEXT_PATH)
public interface SysConfigFeignClient {
	
	/**
	 * 获取当前系统配置信息
	 * @return
	 */
	default List<TextValueItem> getCurrentSystemConfig(){
		return getSystemConfig(SecurityContextUtils.getPrincipal().getAppInfo().getAppId());
	}
	
	@GetMapping("{appId}")
	List<TextValueItem> getSystemConfig(@PathVariable Long appId);

}

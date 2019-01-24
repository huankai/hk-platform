package org.apereo.cas.web;

import java.util.Map;

import org.apereo.cas.CasEmbeddedContainerUtils;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class CasWebApplicationServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		final Map<String, Object> properties = CasEmbeddedContainerUtils.getRuntimeProperties(Boolean.FALSE);
		return builder.sources(CasWebApplication.class).properties(properties)
				.banner(CasEmbeddedContainerUtils.getCasBannerInstance());
	}
}

package org.apereo.cas.web;

import org.apereo.cas.CasEmbeddedValueResolver;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.TaskManagementConfigUtils;

import lombok.ToString;

@ToString
public class CasWebApplicationContext extends AnnotationConfigEmbeddedWebApplicationContext {

	@Override
	protected void onRefresh() {
		final ScheduledAnnotationBeanPostProcessor sch =
	            (ScheduledAnnotationBeanPostProcessor) getBeanFactory()
	                .getBean(TaskManagementConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME, BeanPostProcessor.class);
	        sch.setEmbeddedValueResolver(new CasEmbeddedValueResolver(this));
	        super.onRefresh();
	}
}

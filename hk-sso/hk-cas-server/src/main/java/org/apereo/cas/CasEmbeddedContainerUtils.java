package org.apereo.cas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apereo.cas.util.spring.boot.AbstractCasBanner;
import org.apereo.cas.util.spring.boot.DefaultCasBanner;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.boot.Banner;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class CasEmbeddedContainerUtils {

	/**
     * Property to dictate to the environment whether embedded container is running CAS.
     */
    public static final String EMBEDDED_CONTAINER_CONFIG_ACTIVE = "CasEmbeddedContainerConfigurationActive";

    /**
     * Gets runtime properties.
     *
     * @param embeddedContainerActive the embedded container active
     * @return the runtime properties
     */
    public static Map<String, Object> getRuntimeProperties(final Boolean embeddedContainerActive) {
        final Map<String, Object> properties = new LinkedHashMap<>();
        properties.put(EMBEDDED_CONTAINER_CONFIG_ACTIVE, embeddedContainerActive);
        return properties;
    }

    /**
     * Gets cas banner instance.
     *
     * @return the cas banner instance
     */
    public static Banner getCasBannerInstance() {
        final String packageName = CasEmbeddedContainerUtils.class.getPackage().getName();
        final Reflections reflections =
            new Reflections(new ConfigurationBuilder()
                .filterInputsBy(new FilterBuilder().includePackage(packageName))
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new SubTypesScanner(true)));

        final Set<Class<? extends AbstractCasBanner>> subTypes = reflections.getSubTypesOf(AbstractCasBanner.class);
        subTypes.remove(DefaultCasBanner.class);

        if (subTypes.isEmpty()) {
            return new DefaultCasBanner();
        }
        try {
            final Class<? extends AbstractCasBanner> clz = subTypes.iterator().next();
            return clz.getDeclaredConstructor().newInstance();
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }
        return new DefaultCasBanner();
    }
}

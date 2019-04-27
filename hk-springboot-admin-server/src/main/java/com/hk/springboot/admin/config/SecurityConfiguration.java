package com.hk.springboot.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

/**
 * @author sjq-278
 * @date 2018-12-05 10:30
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter*/ {


    private final String adminContextPath;

    public SecurityConfiguration(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
//        successHandler.setDefaultTargetUrl(adminContextPath + "/");
//        http.authorizeRequests()
//                .antMatchers(adminContextPath + "/assets/**").permitAll()
//                .antMatchers(adminContextPath + "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
//                .logout().logoutUrl(adminContextPath + "/logout").and()
//                .httpBasic().and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .ignoringAntMatchers(
//                        adminContextPath + "/instances",
//                        adminContextPath + "/actuator/**"
//                );
//
//    }
}

hk-app:
暂时未现实任何功能

hk-discovery:

-- hk-discovery-eureka: eureka Server 发现组件
   
hk-emi:
-- hk-emi-api: feign emi api接口定义

-- hk-emi-commons: emi commons 包

-- hk-emi-web: emi 字典管理系统

hk-fs:
-- hk-fs-api: feign fs api接口定义

-- hk-fs-web: fs 文件管理系统

hk-platform-business:

hk-platform-business-base: 业务公用包

hk-platform-business-emi:  业务公用emi包

hk-pms:
-- hk-pms-api: feign pms api接口定义

-- hk-pms-commons: pms commons 包

-- hk-pms-web: pms 统一用户与权限管理系统

hk-sso:
-- hk-sso-server: oauth2 单点登陆认证服务器

hk-gateway
-- hk-gateway-zuul: zuul 网关

完成 zuul 网关整合，可以使用 `http://127.0.0.1:8771/${projectName}/xxx ` 来访问 Pms 、emi、 sso Server 服务，完成单点登陆
主要是在 application.yml文件中配置 hk.authentication.gate-way-host 参数。
主要思路是 重写spring security 的 `org.springframework.security.web.savedrequest.RequestCache` 的默认实现，更多详细信息，请查看 `com.hk.core.authentication.security.savedrequest.GateWayHttpSessionRequestCache` .

添加统一配置中心，配置文件请查看  https://github.com/huankai/hk-config

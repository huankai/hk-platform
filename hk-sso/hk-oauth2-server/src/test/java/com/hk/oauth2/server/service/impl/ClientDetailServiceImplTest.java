package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.IDGenerator;
import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.oauth2.AuthenticationType;
import com.hk.core.test.BaseTest;
import com.hk.oauth2.server.Oauth2ServerApplication;
import com.hk.oauth2.server.entity.Oauth2ClientDetails;
import com.hk.oauth2.server.service.Oauth2ClientDetailsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Junit Test
 */
@SpringBootTest(classes = {Oauth2ServerApplication.class})
public class ClientDetailServiceImplTest extends BaseTest {

    @Autowired
    private Oauth2ClientDetailsService oauth2ClientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* ********************** insert test *****************************************/

    @Test
    public void testInsert() {
        Oauth2ClientDetails clientDetails = new Oauth2ClientDetails();
        String secret = IDGenerator.STRING_UUID.generate();
        System.out.println(secret);
        clientDetails.setClientSecret(passwordEncoder.encode(secret));
        clientDetails.setScope(ArrayUtils.asHashSet("all"));
        clientDetails.setAuthorizedGrantTypes(ArrayUtils.asHashSet(AuthenticationType.authorization_code.name(),
                AuthenticationType.password.name()));
        clientDetails.setRedirectUri(ArrayUtils.asHashSet("http://127.0.0.1:8681/login"));
        clientDetails.setAutoapprove(ArrayUtils.asHashSet("true"));
        clientDetails.setAccessTokenValidity(7200);
        clientDetails.setRefreshTokenValidity(3600 * 24 * 7);
        clientDetails.setAppCode("APP-01");
        clientDetails.setAppName("app-01");
        clientDetails.setAppStatus(ByteConstants.ONE);
        System.out.println(oauth2ClientDetailsService.insert(clientDetails));
    }

    @Test
    public void testBatchInsert() {
//        List<SysApp> list = new ArrayList<>();
//        SysApp item;
//        for (int i = 0; i < 50; i++) {
//            item = new SysApp();
//            item.setAppIcon("a.png");
//            item.setAppHost("127.0.0.1");
//            item.setLocalApp(false);
//            item.setAppStatus(ByteConstants.ONE);
//            item.setStartDate(LocalDateTime.now());
//            item.setAppCode("TEST_BATCH-" + i);
//            item.setAppName(item.getAppCode());
//            list.add(item);
//        }
//        sysAppService.batchInsert(list);
    }

    /* ********************** delete test **************************************** */
    @Test
    public void testDteCompositeCondition() {
//        CompositeCondition condition = new CompositeCondition(new SimpleCondition("app_code", "TEST-0"),
//                new SimpleCondition("app_name", Operator.LIKEANYWHERE, "Test"));
//        sysAppService.delete(condition);
    }

    @Test
    public void testDeleteById() {
//        String id = "00241aa048bd4a9e8e5478378fd27045";
//        sysAppService.deleteById(id);
    }

    @Test
    public void testDeleteByIds() {
//        String[] ids = {"06714f2db996482b97d67f2d621654bc", "07d7bc001a004b20a3a9237ae73eba44"};
//        sysAppService.deleteByIds(ids);
    }

    @Test
    public void testDeleteT() {
//        SysApp app = new SysApp();
//        app.setId("19b845b749344f60bb88aa892570d356");
//        app.setAppCode("TEST_BATCH-30");
//        sysAppService.delete(app);
    }

    @Test
    public void testDeleteIterableOfT() {
//        List<SysApp> list = new ArrayList<>();
//        List<String> ids = ArrayUtils.asArrayList("19b845b749344f60bb88aa892570d356", "1d9838f1078e49dbbc9d705658dbfce5");
//        SysApp item;
//        for (String id : ids) {
//            item = new SysApp();
//            item.setId(id);
//            list.add(item);
//        }
//        sysAppService.delete(list);
    }

    /* ********************** update test **************************************** */
    @Test
    public void testUpdateByIdSelective() {
//        SysApp sysApp = new SysApp();
//        sysApp.setId("2cdf0b9f9d8e4035ae78d932964aafd6");
//        sysApp.setAppIcon("b.png");
//        sysAppService.updateByIdSelective(sysApp);
    }

    @Test
    public void testUpdateById() {
//        SysApp sysApp = new SysApp();
//        sysApp.setId("37003dce5f854bc18bf47ea713b7976c");
//        sysApp.setAppCode("TEST_BATCH-42");
//        sysApp.setAppName("TEST_BATCH-42");
//        sysApp.setAppHost("127.0.0.1");
//        sysApp.setAppIcon("c.png");
//        sysApp.setLocalApp(false);
//        sysApp.setAppStatus(ByteConstants.ONE);
//        sysApp.setStartDate(LocalDateTime.now());
//        sysAppService.updateById(sysApp);
    }

    /* ********************** select test **************************************** */
    @Test
    public void testFindAllTOrderArray() {
//        System.out.println("-----------------------------");
//        SysApp sysApp = new SysApp();
//        sysApp.setLocalApp(true);
//        ListResult<SysApp> result = sysAppService.findAll(sysApp);
//        System.out.println(JsonUtils.serialize(result, true));
    }

    @Test
    public void testQueryForPageQueryModelOfT() {
//        QueryModel<SysApp> queryModel = new QueryModel<>();
//        queryModel.addOrders(Order.asc("start_date"));
//        QueryPage<SysApp> pageResult = sysAppService.queryForPage(queryModel);
//        System.out.println(JsonUtils.serialize(pageResult, true));
    }

    @Test
    public void testQueryForPageSelectArguments() {
//        SelectArguments arguments = new SelectArguments();
////        arguments.setDistinct(true);
//        arguments.getConditions().addCondition(new SimpleCondition("app_status", "1"));
////        arguments.fields("app_host,COUNT(app_status) AS count ");
////        arguments.setGroupBy(ArrayUtils.asArrayList("app_host"));
//        QueryPage<SysApp> pageResult = sysAppService.queryForPage(arguments);
//        System.out.println(JsonUtils.serialize(pageResult, true));
    }

    @Test
    public void testGetById() {
        Oauth2ClientDetails details = oauth2ClientDetailsService.findById(306757202583097344L).orElse(null);
        String json = JsonUtils.serialize(details, true);
        System.out.println(json);
        Oauth2ClientDetails deserialize = JsonUtils.deserialize(json, Oauth2ClientDetails.class);
        System.out.println(deserialize.getAppName());
    }

    @Test
    public void testGetOneT() {
//        SysApp sysApp = new SysApp();
//        sysApp.setLocalApp(false);
//        System.out.println(JsonUtils.serialize(sysAppService.getOne(sysApp), true));

    }

    @Test
    public void testFindOneT() {
//        SysApp app = new SysApp();
//        Optional<SysApp> serviceOne = sysAppService.findOne(app);
//        System.out.println(JsonUtils.serialize(serviceOne, true));
    }

    @Test
    public void testFindOneCompositeCondition() {
//        CompositeCondition condition = new CompositeCondition();
//        Optional<SysApp> serviceOne = sysAppService.findOne(condition);
//        System.out.println(JsonUtils.serialize(serviceOne, true));

    }

    @Test
    public void testGetOneCompositeCondition() {
//        CompositeCondition condition = new CompositeCondition();
//        SysApp serviceOne = sysAppService.getOne(condition);
//        System.out.println(JsonUtils.serialize(serviceOne, true));
    }

    @Test
    public void testCountT() {
//        SysApp app = new SysApp();
//        System.out.println(sysAppService.count(app));
    }

    @Test
    public void testFindAllCompositeConditionCollectionOfStringOrderArray() {
//        CompositeCondition condition = new CompositeCondition();
//        condition.addCondition(new SimpleCondition("app_status", "1"));
////        ListResult<SysApp> listResult = sysAppService.findAll(condition, Order.asc("start_date"));
//        ListResult<SysApp> listResult = sysAppService.findAll(condition, ArrayUtils.asArrayList("id"), Order.asc("start_date"));
//        System.out.println(JsonUtils.serialize(listResult, true));
    }

    @Test
    public void testFindById() {
//        Optional<SysApp> sysApp = sysAppService.findById("");
//        System.out.println(JsonUtils.serialize(sysApp, true));
    }

    @Test
    public void testFindAllOrderArray() {
//        SysApp app = new SysApp();
//        ListResult<SysApp> result = sysAppService.findAll(app);
//        System.out.println(JsonUtils.serialize(result, true));
    }

    @Test
    public void testFindByIds() {
//        String[] ids = {"", ""};
//        Iterable<SysApp> list = sysAppService.findByIds(ids);
//        System.out.println(JsonUtils.serialize(list, true));

    }

    @Test
    public void testExistsById() {
//        System.out.println(sysAppService.existsById(""));
    }

    @Test
    public void testExists() {
//        SysApp app = new SysApp();
//        System.out.println(sysAppService.exists(app));

    }

    @Test
    public void testCount() {
//        System.out.println(sysAppService.count());
    }

}

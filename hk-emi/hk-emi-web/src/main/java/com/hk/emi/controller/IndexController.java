package com.hk.emi.controller;

import com.hk.platform.commons.web.BaseController;
import com.hk.stream.order.OrderOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kevin
 * @date 2018-08-14 16:19
 */
@Api(value = "首页管理")
@Controller
public class IndexController extends BaseController {

//    @ApiOperation(value = "首页")
//    @GetMapping({"/", "/index"})
//    public JsonResult<UserPrincipal> index() {
//        return JsonResult.success(getPrincipal());
//    }


    @Autowired
    private OrderOutput orderOutput;

    @ApiOperation(value = "首页")
    @GetMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
//        OrderPayload orderPayload = new OrderPayload();
//        orderPayload.setId(IDGenerator.STRING_UUID.generate());
//        orderPayload.setBody("body");
//        orderOutput.generateOrder().send(MessageBuilder.withPayload(orderPayload).build());
        return "index";
    }
}

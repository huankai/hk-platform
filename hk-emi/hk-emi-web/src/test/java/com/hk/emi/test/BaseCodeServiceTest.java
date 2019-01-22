package com.hk.emi.test;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.test.LoginBaseTest;
import com.hk.emi.EMIApplication;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.domain.ChildCode;
import com.hk.emi.service.BaseCodeService;
import com.hk.emi.service.ChildCodeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangkai
 * @date 2019-01-22 14:55
 */
@SpringBootTest(classes = EMIApplication.class)
public class BaseCodeServiceTest extends LoginBaseTest {

    @Autowired
    private BaseCodeService baseCodeService;

    @Autowired
    private ChildCodeService childCodeService;

    @Test
    public void insertTest() {
        BaseCode baseCode = new BaseCode();
        baseCode.setBaseCode("JZGZT");
        baseCode.setCodeName("教职工状态");
        baseCode = baseCodeService.insert(baseCode);

//        List<String> status = ArrayUtils.asArrayList("休学", "退学", "停学", "复学", "流失", "毕业", "结业", "肄业", "转学（转出)", "死亡", "保留入学资格", "公派出国", "开除", "下落不明", "其他");
//        List<String> codes = ArrayUtils.asArrayList("XIUXUE", "TUIXUE", "TINGXUE", "FUXUE", "LIUSHI", "BIYE", "JIEYE", "YIYE", "ZHUANXUE", "SIWANG", "BLRXZG", "GPCG", "KAICHU", "XLBM", "QITA");

//        List<String> status = ArrayUtils.asArrayList("应届", "初始化", "往届");
//        List<String> codes = ArrayUtils.asArrayList("YJ", "CSH", "WJ");
        List<String> status = ArrayUtils.asArrayList("在职", "退休", "离休", "死亡", "返聘", "调出", "辞职", "离职", "开除", "下落不明", "延聘", "待退休", "长病假", "因公出国", "停薪留职", "待岗", "其他");
        List<String> codes = ArrayUtils.asArrayList("ZAIZHI", "TUIXIU", "LIXIU", "SIWANG", "FANPIN", "DIAOCHU", "CIZHI", "LIZHI", "KAICHU", "XLBM", "YANPIN", "DAITUIXIU", "CHANGBINGJIA", "YGCG", "TXLZ", "DAIGANG", "QITA");
        List<ChildCode> list = new ArrayList<>();
        byte index = 1;
        for (String item : status) {
            ChildCode childCode = new ChildCode();
            childCode.setBaseCodeId(baseCode.getId());
            childCode.setChildCode(codes.get(index - 1));
            childCode.setCodeName(item);
            childCode.setCodeValue(index);
            childCode.setState(ByteConstants.ONE);
            childCode.setIsGb(Boolean.TRUE);
            list.add(childCode);
            index++;
        }
        childCodeService.batchInsert(list);
    }

}

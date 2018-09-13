package com.hk.emi.api;

import com.hk.emi.domain.ChildCode;
import com.hk.emi.service.ChildCodeService;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-08-21 09:25
 */
@RestController
@RequestMapping("/api/code")
public class CodeRestController extends BaseController {

    private static final String UN_KNOWN = "UNKNOWN";

    private final ChildCodeService childCodeService;

    @Autowired
    public CodeRestController(ChildCodeService childCodeService) {
        this.childCodeService = childCodeService;
    }

    /**
     * 查询子级
     *
     * @param parentId parentId
     * @param ignores  ignores
     * @return {@link ChildCode }
     */
    @GetMapping(path = "child")
    public List<ChildCode> findChildList(@RequestParam("parentId") String parentId,
                                         @RequestParam(required = false, value = "ignore") String[] ignores) {
        return childCodeService.findByBaseCodeIgnoreChildCodes(parentId, ignores);
    }

    /**
     * 查询名称，会根据codeValues参数值排序，
     * 如果不存在，该 List对应的 index为 {@value UN_KNOWN}
     *
     * @param parentId   parentId
     * @param codeValues codeValues
     * @return {@link String}
     */
    @GetMapping(path = "childnames")
    public List<String> childNameList(@RequestParam("parentId") String parentId,
                                      @RequestParam(value = "code_values") byte[] codeValues) {
        List<ChildCode> childCodes = childCodeService.findByBaseCodeIgnoreChildCodes(parentId);
        List<String> childNameList = new ArrayList<>(codeValues.length);
        for (byte codeValue : codeValues) {
            Optional<ChildCode> childCodeOptional = childCodes.stream()
                    .filter(item -> item.getCodeValue() == codeValue)
                    .findFirst();
            if (childCodeOptional.isPresent()) {
                childNameList.add(childCodeOptional.get().getCodeName());
            } else {
                childNameList.add(UN_KNOWN);
            }
        }
        return childNameList;
    }


}

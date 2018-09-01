package com.liangfeng.study.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictApiQueryResponsebody
 * @Description:
 * @date  2018/9/1 0001 下午 4:52
 */
@Data
public class DictApiQueryResponsebody {

    /**
     * 字典集合 dictCode-dictDesc
     */
    private Map<String, String> dictMap;

}

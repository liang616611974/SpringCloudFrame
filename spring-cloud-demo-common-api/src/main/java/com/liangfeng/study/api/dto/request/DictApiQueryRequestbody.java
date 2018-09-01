package com.liangfeng.study.api.dto.request;


import lombok.Data;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictApiQueryRequestbody
 * @Description:
 * @date  2018/9/1 0001 下午 4:31
 */
@Data
public class DictApiQueryRequestbody {

    /**
     * 系统编码
     */
    private String sysCode;


    /**
     * 字典组编码集合
     */
    private String[] groupCodes;

}

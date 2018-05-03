package com.liangfeng.study.goods.web.response;


import com.liangfeng.study.core.web.dto.response.QueryPageResponsebody;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * @Title: Goods
 * @Description:
 * @author Liangfeng
 * @date 2018-05-01
 * @version 1.0
 */
@Data
public class GoodsQueryResponsebody extends QueryPageResponsebody<GoodsGetResponsebody>{


    @Override
    public String toString() {return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);}

}



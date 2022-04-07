package com.dxj.skc.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@Data
public class PageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页号")
    private int pageIndex;

    @ApiModelProperty(value = "页面大小")
    private int pageSize;

    @ApiModelProperty(value = "排序字段")
    private String sort;

    @ApiModelProperty(value = "排序方式 asc/desc")
    private String order;
}

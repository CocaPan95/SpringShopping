package com.coca.shoppingmodel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分页基础类")
@Data
public class PageParam {
    @ApiModelProperty("分页位置")
    private int PageNum;
    @ApiModelProperty("分页大小")
    private int PageSize;
}

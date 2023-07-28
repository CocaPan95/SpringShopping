package com.coca.shoppingmodel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@ApiModel("用户登录信息类")
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam implements Serializable {
    @ApiModelProperty("用户名")
    @NotEmpty
    private String username;

    @ApiModelProperty("密码")
    @NotEmpty
    private String password;
}


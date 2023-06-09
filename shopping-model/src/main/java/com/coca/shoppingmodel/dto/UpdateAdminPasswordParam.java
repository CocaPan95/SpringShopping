package com.coca.shoppingmodel.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 修改用户名密码参数
 * Created by macro on 2019/10/9.
 */
@Getter
@Setter
public class UpdateAdminPasswordParam implements Serializable {
    @NotEmpty
    //@ApiModelProperty(value = "用户名", required = true)
    private String username;
    @NotEmpty
    //@ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;
    @NotEmpty
    //@ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}


package com.coca.shoppingmodel.entity.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 产品属性分类表
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@ApiModel("产品属性分类实体类")
@TableName("pms_product_attribute_category")
public class PmsProductAttributeCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("产品属性分类名称")
    private String name;

    /**
     * 属性数量
     */
    @ApiModelProperty("属性数量")
    private Integer attributeCount;

    /**
     * 参数数量
     */
    @ApiModelProperty("参数数量")
    private Integer paramCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttributeCount() {
        return attributeCount;
    }

    public void setAttributeCount(Integer attributeCount) {
        this.attributeCount = attributeCount;
    }

    public Integer getParamCount() {
        return paramCount;
    }

    public void setParamCount(Integer paramCount) {
        this.paramCount = paramCount;
    }

    @Override
    public String toString() {
        return "PmsProductAttributeCategory{" +
                "id=" + id +
                ", name=" + name +
                ", attributeCount=" + attributeCount +
                ", paramCount=" + paramCount +
                "}";
    }
}

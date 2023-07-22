package com.coca.shoppingmodel.entity.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 存储产品参数信息的表
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@TableName("pms_product_attribute_value")
public class PmsProductAttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private Long productAttributeId;

    private String productAttributeName;
    private Integer productAttributeType;
    /**
     * 手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开
     */
    private String value;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public String getProductAttributeName() {
        return productAttributeName;
    }

    public void setProductAttributeName(String productAttributeName) {
        this.productAttributeName = productAttributeName;
    }

    public Integer getProductAttributeType() {
        return productAttributeType;
    }

    public void setProductAttributeType(Integer productAttributeType) {
        this.productAttributeType = productAttributeType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PmsProductAttributeValue{" +
                "id=" + id +
                ", productId=" + productId +
                ", productAttributeId=" + productAttributeId +
                ", value=" + value +
                "}";
    }
}

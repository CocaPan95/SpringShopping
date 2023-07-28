package com.coca.shoppingmodel.entity.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 商品属性参数表
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@ApiModel("产品属性实体类")
@TableName("pms_product_attribute")
public class PmsProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("属性分类Id")
    private Long productAttributeCategoryId;

    @ApiModelProperty("属性名字")
    private String name;

    /**
     * 属性选择类型：0->唯一；1->单选；2->多选
     */
    @ApiModelProperty("属性选择类型")
    private Integer selectType;

    /**
     * 属性录入方式：0->手工录入；1->从列表中选取
     */
    @ApiModelProperty("属性录入方式")
    private Integer inputType;

    /**
     * 可选值列表，以逗号隔开
     */
    @ApiModelProperty("可选值列表")
    private String inputList;

    /**
     * 排序字段：最高的可以单独上传图片
     */
    @ApiModelProperty("排序字段")
    private Integer sort;

    /**
     * 分类筛选样式：1->普通；1->颜色
     */
    @ApiModelProperty("分类筛选样式")
    private Integer filterType;

    /**
     * 检索类型；0->不需要进行检索；1->关键字检索；2->范围检索
     */
    @ApiModelProperty("检索类型")
    private Integer searchType;

    /**
     * 相同属性产品是否关联；0->不关联；1->关联
     */
    @ApiModelProperty("相同属性产品是否关联")
    private Integer relatedStatus;

    /**
     * 是否支持手动新增；0->不支持；1->支持
     */
    @ApiModelProperty("是否支持手动新增")
    private Integer handAddStatus;

    /**
     * 属性的类型；0->规格；1->参数
     */
    @ApiModelProperty("属性的类型")
    private Integer type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getInputList() {
        return inputList;
    }

    public void setInputList(String inputList) {
        this.inputList = inputList;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getRelatedStatus() {
        return relatedStatus;
    }

    public void setRelatedStatus(Integer relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    public Integer getHandAddStatus() {
        return handAddStatus;
    }

    public void setHandAddStatus(Integer handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PmsProductAttribute{" +
                "id=" + id +
                ", productAttributeCategoryId=" + productAttributeCategoryId +
                ", name=" + name +
                ", selectType=" + selectType +
                ", inputType=" + inputType +
                ", inputList=" + inputList +
                ", sort=" + sort +
                ", filterType=" + filterType +
                ", searchType=" + searchType +
                ", relatedStatus=" + relatedStatus +
                ", handAddStatus=" + handAddStatus +
                ", type=" + type +
                "}";
    }
}

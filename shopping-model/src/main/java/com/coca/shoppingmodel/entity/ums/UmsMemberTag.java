package com.coca.shoppingmodel.entity.ums;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户标签表
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@TableName("ums_member_tag")
public class UmsMemberTag implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 自动打标签完成订单数量
     */
    private Integer finishOrderCount;

    /**
     * 自动打标签完成订单金额
     */
    private BigDecimal finishOrderAmount;


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

    public Integer getFinishOrderCount() {
        return finishOrderCount;
    }

    public void setFinishOrderCount(Integer finishOrderCount) {
        this.finishOrderCount = finishOrderCount;
    }

    public BigDecimal getFinishOrderAmount() {
        return finishOrderAmount;
    }

    public void setFinishOrderAmount(BigDecimal finishOrderAmount) {
        this.finishOrderAmount = finishOrderAmount;
    }

    @Override
    public String toString() {
        return "UmsMemberTag{" +
        "id=" + id +
        ", name=" + name +
        ", finishOrderCount=" + finishOrderCount +
        ", finishOrderAmount=" + finishOrderAmount +
        "}";
    }
}

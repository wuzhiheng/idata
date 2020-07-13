package com.wonders.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单信息
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单时间
     */
    private Date orderTime;

    /**
     * 订单类型，1：购买 2：续费 3：升级
     */
    private String orderType;

    /**
     * 套餐ID
     */
    private Integer packageId;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 购买时长（月）
     */
    private String period;

    /**
     * 套餐费用
     */
    private BigDecimal packageFee;

    /**
     * 扣减费用
     */
    private BigDecimal discount;

    /**
     * 支付费用
     */
    private BigDecimal payFee;

    /**
     * 支付方式
     */
    private String payWay;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 数据是否有效
     */
    private String removed;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}

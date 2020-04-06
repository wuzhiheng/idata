package com.wonders.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wonders.global.PricePeriodEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 套餐定价信息
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_package_price")
public class PackagePriceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 套餐ID
     */
    private Integer packageId;

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
     * 序号
     */
    private Integer seq;

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

    @TableField(exist = false)
    private PackageEntity packageInfo;


    public String getPeriodStr(){

        for (PricePeriodEnum value : PricePeriodEnum.values()) {
            if (value.getMonth().equals(this.period))
                return value.getText();
        }
        return null;
    }


}

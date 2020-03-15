package com.wonders.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 综管指标维护
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TB_ZG_INDICATOR")
public class ZgIndicatorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 指标编码
     */
    @TableField("ZBBM")
    private String zbbm;

    /**
     * 指标名称
     */
    @TableField("ZBMC")
    private String zbmc;

    /**
     * 指标单位
     */
    @TableField("ZBDW")
    private String zbdw;

    /**
     * 表名
     */
    @TableField("HZBM")
    private String hzbm;

    /**
     * 主题名称
     */
    @TableField("ZTMC")
    private String ztmc;

    /**
     * 图表名称
     */
    @TableField("TBMC")
    private String tbmc;

    /**
     * 获取数据SQL
     */
    @TableField("DATA_SQL")
    private String dataSql;

    /**
     * 删除数据SQL
     */
    @TableField("DELETE_SQL")
    private String deleteSql;

    /**
     * 是否有效，1-有效 | 0-无效
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;


}

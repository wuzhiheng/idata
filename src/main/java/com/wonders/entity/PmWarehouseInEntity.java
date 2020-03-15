package com.wonders.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 药库入库监控
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("RHIN_TR.TB_PM_WAREHOUSE_IN")
public class PmWarehouseInEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 医院代码
     */
    @TableField("MEDICAL_INSTITUTION_CODE")
    private String medicalInstitutionCode;

    /**
     * 医院名称
     */
    @TableField("MEDICAL_INSTITUTION_NAME")
    private String medicalInstitutionName;

    /**
     * 流水号
     */
    @TableField("WAREHOUSE_IN_SERIAL_NUMBER")
    private String warehouseInSerialNumber;

    /**
     * 科室编码
     */
    @TableField("DEPT_CODE")
    private String deptCode;

    /**
     * 科室名称
     */
    @TableField("DEPT_NAME")
    private String deptName;

    /**
     * 项目类型代码
     */
    @TableField("ITEM_TYPE_CODE")
    private String itemTypeCode;

    /**
     * 项目类型名称
     */
    @TableField("ITEM_TYPE_NAME")
    private String itemTypeName;

    /**
     * 项目编码
     */
    @TableField("ITEM_CODE")
    private String itemCode;

    /**
     * 项目名称
     */
    @TableField("ITEM_NAME")
    private String itemName;

    /**
     * 入库日期
     */
    @TableField("IN_STORAGE_DATE")
    private Date inStorageDate;

    /**
     * 入库来源
     */
    @TableField("IN_STORAGE_SOURCE")
    private String inStorageSource;

    /**
     * 入库数量
     */
    @TableField("IN_AMOUNT")
    private Integer inAmount;

    /**
     * 最小单位代码
     */
    @TableField("MIN_UNIT_CODE")
    private String minUnitCode;

    /**
     * 最小单位名称
     */
    @TableField("MIN_UNIT_NAME")
    private String minUnitName;

    /**
     * 进货价格
     */
    @TableField("STOCK_IN_PRICE")
    private Integer stockInPrice;

    /**
     * 批发价格
     */
    @TableField("WHOLESALE_PRICE")
    private Integer wholesalePrice;

    /**
     * 批发金额
     */
    @TableField("WHOLESALE_ALL_AMOUNT")
    private Integer wholesaleAllAmount;

    /**
     * 零售价
     */
    @TableField("RETAIL_PRICE")
    private Integer retailPrice;

    /**
     * 销售金额
     */
    @TableField("SELL_ALL_AMOUNT")
    private Integer sellAllAmount;

    /**
     * 库房代码
     */
    @TableField("DRUGROOM_NUMBER")
    private String drugroomNumber;

    /**
     * 库房名称
     */
    @TableField("DRUGROOM_NAME")
    private String drugroomName;

    /**
     * 入库单号
     */
    @TableField("IN_STOCK_SHEET_NUM")
    private String inStockSheetNum;

    /**
     * 生产日期
     */
    @TableField("PRODUCTION_DATE")
    private Date productionDate;

    /**
     * 有效期(月)
     */
    @TableField("VALID_DATE")
    private Integer validDate;

    /**
     * 生产批号
     */
    @TableField("PRODUCTION_BATCH_NO")
    private String productionBatchNo;

    /**
     * 药品类型代码
     */
    @TableField("DRUG_TYPE_CODE")
    private String drugTypeCode;

    /**
     * 药品类型名称
     */
    @TableField("DRUG_TYPE_NAME")
    private String drugTypeName;

    /**
     * 登记时间(系统)
     */
    @TableField("RECORD_TIME")
    private Date recordTime;

    /**
     * 登记人员代码
     */
    @TableField("RECORD_STAFF_CODE")
    private String recordStaffCode;

    /**
     * 登记人员名称
     */
    @TableField("RECORD_STAFF_NAME")
    private String recordStaffName;

    /**
     * 登记机构代码
     */
    @TableField("RECORD_INSTITUTION_CODE")
    private String recordInstitutionCode;

    /**
     * 登记机构名称
     */
    @TableField("RECORD_INSTITUTION_NAME")
    private String recordInstitutionName;

    /**
     * 最后修改时间
     */
    @TableField("LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    /**
     * 最后修改人员代码
     */
    @TableField("LAST_UPDATE_STAFF_CODE")
    private String lastUpdateStaffCode;

    /**
     * 最后修改人员名称
     */
    @TableField("LAST_UPDATE_STAFF_NAME")
    private String lastUpdateStaffName;

    /**
     * 最后修改机构代码
     */
    @TableField("LAST_UPDATE_INSTITUTION_CODE")
    private String lastUpdateInstitutionCode;

    /**
     * 最后修改机构名称
     */
    @TableField("LAST_UPDATE_INSTITUTION_NAME")
    private String lastUpdateInstitutionName;

    @TableField("SECRECY_LEVEL")
    private String secrecyLevel;

    @TableField("REVISE_SIGN")
    private String reviseSign;

    @TableField("SUPPLIED_DATE")
    private Date suppliedDate;

    @TableField("LOCK_FLAG")
    private Integer lockFlag;

    @TableField("ERROR")
    private String error;

    /**
     * 批次号
     */
    @TableField("BATCH_NO")
    private String batchNo;

    /**
     * 接入点代码，也做为数据来源点
     */
    @TableField("F_POINT_CD")
    private String fPointCd;

    @TableId(value = "PID", type = IdType.UUID)
    private String pid;

    @TableField("ETL_DATE")
    private Date etlDate;

    /**
     * 科室类型
     */
    @TableField("DEPT_CLASS")
    private String deptClass;

    /**
     * 科室类型名称
     */
    @TableField("DEPT_CLASS_NAME")
    private String deptClassName;

    /**
     * 已拉取标志，‘0’：未拉取 ‘1’：已拉取
     */
    @TableField("CHK_FLAG")
    private String chkFlag;


}

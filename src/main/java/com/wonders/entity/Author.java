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
import java.util.List;

/**
 * <p>
 * 起点作者表
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("idata2.tb_qidian_author")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 平台(0:未知,1:起点,)
     */
    private String platform;

    /**
     * 作者id
     */
    private Long authorid;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 作者头像
     */
    private String authorFaceImg;

    /**
     * 作者等级
     */
    private Integer authorLevel;

    /**
     * 作者粉丝称号
     */
    private String authorFansTitle;

    /**
     * 作品总数
     */
    private String totalBooks;

    /**
     * 累计字数
     */
    private Long totalWords;

    /**
     * 创作天数
     */
    private Integer totalDays;

    /**
     * 粉丝数
     */
    private Integer totalFans;

    /**
     * 粉丝称号人数
     */
    private Integer fansTitleTotal;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private List<Book> books;


}

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
 * 小说
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("idata2.tb_qidian_book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 平台(0:未知,1:起点,)
     */
    private String platform;

    /**
     * 频道(0:未知,1:男频,2:女频)
     */
    private String channel;

    /**
     * 更新状态(完本,连载,3天内有更新,7天内有更新,30天内有更新)
     */
    private String actionName;

    /**
     * 是否签约
     */
    private String contractName;

    /**
     * VIP/免费
     */
    private String vipName;

    /**
     * 书籍id
     */
    private Long bookid;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 作者id
     */
    private Long authorid;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 书籍封面
     */
    private String bookFaceImg;

    /**
     * 分类名称
     */
    private String categorName;

    /**
     * 小分类名称
     */
    private String subCategorName;

    /**
     * 书籍评分
     */
    private Float grade;

    /**
     * 评分人数
     */
    @TableField("userCount")
    private Integer userCount;

    /**
     * 作者自定义标签
     */
    private String tags;

    /**
     * 书籍字数
     */
    private Float wordsCntAll;

    /**
     * 总粉丝数
     */
    private Integer fansAll;

    /**
     * 总推荐票
     */
    private Integer recommendAll;

    /**
     * 总打赏
     */
    private Integer rewardAll;

    /**
     * 总月票
     */
    private Integer ticketAll;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Integer rank1;

    private Integer rank2;

    private Integer rank3;

    private Integer rank4;

    @TableField(exist = false)
    private BookRank rank;
    @TableField(exist = false)
    private BookBaseInfo baseInfo;


}

package com.wonders.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wonders.util.TreeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 09:30 2020-03-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("TB_ZG_ORG_INFO")
public class OrgEntity extends TreeUtil.TreeNode implements Serializable {

    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    //父节点
    @TableField(value = "PID")
    private String pid;

    //机构代码
    @TableField("ORG_CODE")
    private String orgCode;

    //机构名称
    @TableField("ORG_NAME")
    private String orgName;

    //法人代表
    @TableField("PEOPLE")
    private String people;

    //执业期限
    @TableField("PRACTICE_TERM")
    private String practiceTerm;

    //执业范围
    @TableField("PRACTICE_RANGE")
    private String practiceRange;

    //创建时间
    @TableField("CREATE_TIME")
    private Date createTime;

    public String getName(){
        return this.orgName;
    }

}

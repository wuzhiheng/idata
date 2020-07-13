package com.wonders.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author : wuzhiheng
 * @Description : 全站排名情况
 * @Date Created in 2:48 下午 2020/7/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BookRank implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer rank1;
    private Integer rank2;
    private Integer rank3;
    private Integer rank4;

    private Integer rank1Rate;
    private Integer rank2Rate;
    private Integer rank3Rate;
    private Integer rank4Rate;

}

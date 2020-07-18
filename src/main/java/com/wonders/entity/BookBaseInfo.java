package com.wonders.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 2:51 下午 2020/7/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BookBaseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer wordCnt;
    private Integer ticket;
    private Integer reward;
    private Integer recommend;

    private Double wordCntRate;
    private Integer ticketRate;
    private Integer rewardRate;
    private Integer recommendRate;

}

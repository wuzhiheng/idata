package com.wonders.entity;

import com.wonders.util.CommonUtil;
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

    private Integer wordCnt2;
    private Integer ticket2;
    private Integer reward2;
    private Integer recommend2;

    public String wordCntRate(){
        return CommonUtil.calculateRate(wordCnt,wordCnt2);
    }

    public String ticketRate(){
        return CommonUtil.calculateRate(ticket,ticket2);
    }

    public String rewardRate(){
        return CommonUtil.calculateRate(reward,reward2);
    }

    public String recommendRate(){
        return CommonUtil.calculateRate(recommend,recommend2);
    }


}

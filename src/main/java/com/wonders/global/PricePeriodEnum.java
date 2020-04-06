package com.wonders.global;

import lombok.Getter;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 7:57 下午 2020/4/6
 */
@Getter
public enum PricePeriodEnum {

    MONTH("1","月"),
    QUARTER("3","季度"),
    HALF_YEAR("6","半年"),
    YEAR("12","年")
    ;


    private String month;
    private String text;

    PricePeriodEnum(String month, String text) {
        this.month = month;
        this.text = text;
    }
}

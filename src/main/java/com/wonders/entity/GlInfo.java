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
public class GlInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer updateBookCnt;
    private Integer authorCnt;
    private Integer chapterCnt;
    private Integer newBookCnt;

    private Integer updateBookCnt2;
    private Integer authorCnt2;
    private Integer chapterCnt2;
    private Integer newBookCnt2;

    public String updateBookCntRate(){
        return String.valueOf(updateBookCnt - updateBookCnt2);
    }

    public String authorCntRate(){
        return String.valueOf(authorCnt - authorCnt2);
    }
    public String chapterCntRate(){
        return String.valueOf(chapterCnt - chapterCnt2);
    }
    public String newBookCntRate(){
        return String.valueOf(newBookCnt - newBookCnt2);
    }
}

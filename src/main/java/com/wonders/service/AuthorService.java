package com.wonders.service;

import com.wonders.entity.Author;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 起点作者表 服务类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-07-13
 */
public interface AuthorService extends IService<Author> {

    Author getAuthor(String authorId);

}

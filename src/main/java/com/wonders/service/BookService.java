package com.wonders.service;

import com.wonders.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 小说 服务类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-07-13
 */
public interface BookService extends IService<Book> {

    List<Book> getAllBookByAuthor(String authorId);

    Book getBook(String authorId,String bookId);

}

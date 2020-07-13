package com.wonders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wonders.entity.Author;
import com.wonders.dao.AuthorDao;
import com.wonders.service.AuthorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 起点作者表 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-07-13
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorDao, Author> implements AuthorService {

    @Autowired
    private BookService bookService;

    @Override
    public Author getAuthor(String authorId) {

        QueryWrapper<Author> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Author::getAuthorid, authorId);
        Author author = getOne(wrapper);
        if (author != null) {
            author.setBooks(bookService.getAllBookByAuthor(authorId));
        }

        return author;
    }
}

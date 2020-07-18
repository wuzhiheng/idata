package com.wonders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wonders.dao.BookDao;
import com.wonders.dao.IndexDao;
import com.wonders.entity.Book;
import com.wonders.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 小说 服务实现类
 * </p>
 *
 * @author wuzhiheng
 * @since 2020-07-13
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {

    @Autowired
    private IndexDao indexDao;

    @Override
    public List<Book> getAllBookByAuthor(String authorId) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Book::getAuthorid, authorId)
                .orderByDesc(Book::getGrade)
                .orderByDesc(Book::getRecommendAll);
        return list(wrapper);
    }

    @Override
    public Book getBook(String authorId, String bookId) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Book::getAuthorid, authorId)
                .eq(Book::getBookid, bookId);

        Book book = getOne(wrapper);
        if (book != null) {
            book.setRank(indexDao.rank(bookId, authorId,"20200619"));
            book.setBaseInfo(indexDao.baseInfo(bookId, authorId,"20200619"));
        }
        return book;
    }
}

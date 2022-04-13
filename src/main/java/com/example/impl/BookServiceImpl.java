package com.example.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.BookDao;
import com.example.pojo.Book;
import com.example.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Boolean save(Book book) {
        int count = bookDao.insert(book);
        return count > 0;
    }

    @Override
    public Boolean update(Book book) {
        int count = bookDao.updateById(book);
        return count > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        int count = bookDao.deleteById(id);
        return count > 0;
    }

    @Override
    public Book getById(Integer id) {
        return bookDao.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.selectList(null);
    }

    @Override
    public List<Book> getByCondition(Book book) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>(book);

        return bookDao.selectList(queryWrapper);
    }

    @Override
    public IPage<Book> getByPageWithCondition(int current, int size, Book book) {
        QueryWrapper<Book> queryWrapper = null;

        if (book != null) {
            queryWrapper = WrapCondition(book);
        }

        IPage<Book> bookIPage = new Page<>();
        bookIPage.setCurrent(current);
        bookIPage.setSize(size);

        return bookDao.selectPage(bookIPage, queryWrapper);
    }

    private QueryWrapper<Book> WrapCondition(Book book) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();

        Class<? extends Book> bookClass = book.getClass();

        Field[] declaredFields = bookClass.getDeclaredFields();

        try {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                if (declaredField.get(book) != null) {
                    queryWrapper.like(!(String.valueOf(declaredField.get(book)).equals("null") || (String.valueOf(declaredField.get(book)).length() == 0)),
                            declaredField.getName(), declaredField.get(book));
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }

        return queryWrapper;
    }
}

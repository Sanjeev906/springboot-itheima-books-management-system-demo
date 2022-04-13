package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.pojo.Book;

import java.util.List;

public interface BookService {
    Boolean save(Book book);
    Boolean update(Book book);
    Boolean delete(Integer id);
    Book getById(Integer id);
    List<Book> getAll();
    List<Book> getByCondition(Book book);
    IPage<Book> getByPageWithCondition(int current, int size, Book book);
}

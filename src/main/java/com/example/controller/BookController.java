package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.controller.utils.R;
import com.example.pojo.Book;
import com.example.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public R getAll() {
        boolean flag = false;
        Object data = bookService.getAll();
        if (data != null) {
            flag = true;
        }

        return new R(flag, data);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) throws IOException {
        boolean flag = false;
        Object data = bookService.getById(id);

        if (data != null) {
            flag = true;
        }

        return new R(flag, data);
    }

    @PostMapping
    public R save(@RequestBody Book book) {
        boolean flag;

        flag = bookService.save(book);

        return new R(flag, null);
    }

    @PutMapping
    public R Update(@RequestBody Book book) {
        boolean flag;

        flag = bookService.update(book);

        return new R(flag, null);
    }

    @DeleteMapping("/{id}")
    public R Delete(@PathVariable("id") Integer id) {
        boolean flag;

        flag = bookService.delete(id);

        return new R(flag, null);
    }

    @GetMapping("/{current}/{size}")
    public R getPage(@PathVariable("current") int current, @PathVariable("size") int size, Book book) {
        boolean flag = false;

        IPage<Book> data = bookService.getByPageWithCondition(current, size, book);
        // 如果当前页码值超过最大页码，返回第一页数据
        if (current > data.getPages()){
            data = bookService.getByPageWithCondition(1, size, book);
        }

        if (data != null) {
            flag = true;
        }

        return new R(flag, data);
    }
}

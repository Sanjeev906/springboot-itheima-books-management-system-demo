package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.BookDao;
import com.example.pojo.Book;
import com.example.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootSsmpApplicationTests {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    @Test
    void testSelectAll() {
        System.out.println(bookDao.selectList(null));
    }

    @Test
    void testSelectById(){
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSelectByWrapper(){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<Book>();
        queryWrapper.eq("type", "计算机理论");

        System.out.println(bookDao.selectList(queryWrapper));
    }

    @Test
    void testInsert(){
        Book book = new Book();
        book.setName("测试数据123");
        book.setType("测试数据123");
        book.setDescription("测试数据123");

        System.out.println(bookDao.insert(book));
    }

    @Test
    void testUpdate(){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "测试数据123");

        Book book = new Book();
        book.setType("测试数据abcdefg");

        System.out.println(bookDao.update(book, queryWrapper));
    }

    @Test
    void testDelete(){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "测试数据123");

        System.out.println(bookDao.delete(queryWrapper));
    }

    @Test
    void testPageSelect(){
        IPage<Book> iPage = new Page<>();
        iPage.setCurrent(2);
        iPage.setSize(5);

        IPage<Book> result = bookDao.selectPage(iPage, null);

        System.out.println(result.getRecords());
    }

    @Test
    void testGetByCondition(){
        Book book = new Book();
        book.setType("市场营销");

        System.out.println(bookService.getByCondition(book));
    }

    @Test
    void testGetByPageWithCondition(){
        Book book = new Book();
        book.setType("计算机理论");
        book.setName("Spring");
        book.setDescription("Spring");

        int current = 1;
        int size = 5;

        System.out.println(bookService.getByPageWithCondition(current, size, book));
    }

}

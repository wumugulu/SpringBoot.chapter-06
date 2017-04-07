package com.wumugulu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wumugulu.entity.Book;
import com.wumugulu.repository.BookRepository;

@Service
public class BookService {
	
	@Resource
	private BookRepository bookRepository;

	// 获取全部
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	// 获取单个
	// 第一次从数据库获取Book对象成功之后就把Book保存到缓存中，key值为"bookCacheData:{id}-ById"
	// 以后再获取book时，先看缓存中是否有数据，有的话就会直接从缓存中读取并返回了
	@Cacheable(value="bookCache", key="'bookCacheData:' + #id + '-ById'")
	public Book findOne(Integer id){
		System.out.println("read Book From Database...");
		return bookRepository.findOne(id);
	}
	
	//新增
	public Book save(Book book){
		Book tmpBook = bookRepository.findOne(book.getId());
		if (tmpBook!=null) {
			return null;
		}
		
		return bookRepository.save(book);
	}
	
	// 修改
	// 数据库中的Book被修改成功之后，把缓存中对应的Book清除掉，key值为"bookCacheData:{book.id}-ById"
	@CacheEvict(value="bookCache", key="'bookCacheData:' + #book.id + '-ById'")
	public Book update(Book book){
		Book tmpBook = bookRepository.findOne(book.getId());
		if (tmpBook==null) {
			return null;
		}
		
		tmpBook.setName(book.getName());
		tmpBook.setAuthor(book.getAuthor());
		tmpBook.setTotalPage(book.getTotalPage());
		tmpBook.setPublishDate(book.getPublishDate());
		
		return bookRepository.save(tmpBook);
	}
	
	// 删除
	// 数据库中的Book被删除成功之后，把缓存中对应的Book清除掉，key值为"bookCacheData:{id}-ById"
	@CacheEvict(value="bookCache", key="'bookCacheData:' + #id + '-ById'")
	public Integer delete(Integer id){
		Book tmpBook = bookRepository.findOne(id);
		if (tmpBook==null) {
			return null;
		} else{
			bookRepository.delete(id);
			return 0;
		}
	}

}

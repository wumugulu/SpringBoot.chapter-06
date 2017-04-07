package com.wumugulu.controller;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wumugulu.entity.Book;
import com.wumugulu.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Resource
	private BookService bookService;
	
	// get-查询全部Book
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public List<Book> getAll(){
		return bookService.findAll();
	}
	
	// get-查询单个Book
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Book getOne(@PathVariable Integer id){
		System.out.println("got parameter bookId = " + id);
		return bookService.findOne(id);
	}

	// post-新增Book
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public String create(Book book){
		System.out.println("got parameter Book = " + book.toString());
		if(book.getId()!=null){
			System.out.println("Warning：id参数不需要填写，由数据库自动生成，所以写了也没用！");
		}
		
		book = bookService.save(book);
		if (book==null) {
			return "failed: book id already exist";
		} else {
			System.out.println("after save to DB： " + book.toString());
			return "success add Book: " + book.getId();
		}
	}

	// put-修改Book
	@RequestMapping(value={"/{bookId}"}, method=RequestMethod.PUT)
	@ResponseBody
	public String update(@PathVariable Integer bookId, Book book){
		System.out.println("got parameter bookId = " + bookId);
		System.out.println("got parameter Book = " + book.toString());
		if(bookId.intValue()!=book.getId().intValue()){
			return "Error：请求参数的id值不匹配";
		}

		book = bookService.update(book);
		if (book==null) {
			return "failed: not found book";
		} else {
			return "success update Book: " + book.getId();
		}
	}

	// delete-删除Book
	@RequestMapping(value={"/{id}"}, method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable Integer id){
		System.out.println("got parameter id=" + id);
		if(id<=0){
			return "Error：参数错误，id不能为负数：" + id;
		}
		Integer result = bookService.delete(id);
		if(result == null){
			return "error: " + result;
		}

		if (result.intValue()==0) {
			return "success delete Book: " + id;
		} else {
			return "failed: " + result;
		}
	}

}

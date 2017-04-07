package com.wumugulu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="t_book")
public class Book {

	@Id
	@GeneratedValue
	private Integer id;			//书籍的唯一编号
	@Column(name="book_name")
	private String name;		//书籍的名称
	@Column(name="book_author")
	private String author;		//书籍的作者
	@Column(name="book_totalpage")
	private Integer totalPage;	//书籍的总页数
	@Column(name="book_publishdate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date publishDate;	//书籍的出版时间

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", totalPage=" + totalPage
				+ ", publishDate=" + publishDate + "]";
	}

}

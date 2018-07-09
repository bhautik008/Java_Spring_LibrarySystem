package edu.npu.cs548.domain;

//import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "book")
public class Book {

	private int bookId;
	private String title;
	private String content;
	private int categoryId;
	private String categoryName;
	
	public Book(){}
	
	public Book(int bookId, String title, String content, int categoryId) {
		this.bookId = bookId;
		this.title = title;
		this.content = content;
		this.categoryId = categoryId;
	}

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
}

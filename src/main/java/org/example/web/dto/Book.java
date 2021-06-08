package org.example.web.dto;

public class Book {

    private Integer id;
    private String author;
    private String title;
    private String pages;

    public Book() {
    }

    public Book(Integer id, String author, String title, String pages) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public boolean hasAttributes() {
        return (title != null && !title.isEmpty())
                || (author != null && !author.isEmpty())
                || (pages != null && !pages.isEmpty());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pages='" + pages + '\'' +
                '}';
    }
}

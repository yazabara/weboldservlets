package org.zabara.oldwebapp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Yaroslav on 14.06.2014.
 */
public class Book implements Serializable {

    private int id;
    private String name;
    private String description;
    private Date createDate;
    private String author;

    public Book() {
    }

    public Book(int id, String name, String description, Date createDate, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.author = author;
    }

	public boolean isCorrect() {
		if (name == null || name.isEmpty() || author == null || author.isEmpty()) {
			return false;
		}
		return true;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (!author.equals(book.author)) return false;
        if (!createDate.equals(book.createDate)) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (!name.equals(book.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + createDate.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", author='" + author + '\'' +
                '}';
    }
}

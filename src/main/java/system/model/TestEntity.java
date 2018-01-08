package system.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Created by vladimir on 24.12.2017.
 */
@Entity
@Table(name = "test", schema = "test")
public class TestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, unique = true, nullable = false, updatable = false)
    private int id;
    @Basic
    @Column(name = "title")
    @NotEmpty(message = "Введите название книги")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "author")
    @NotEmpty(message = "Введите имя автора")
    private String author;

    @Basic
    @Column(name = "isbn")
    @NotEmpty(message = "Введите номер ISBN")
    private String isbn;

    @Basic
    @Column(name = "printYear")
    @Min(value = 1000, message = "Год издания должен быть введен в формате YYYY")
    private int printYear;

    @Basic
    @Column(name = "readAlready")
    private byte readAlready;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPrintYear() {
        return printYear;
    }

    public void setPrintYear(int printYear) {
        this.printYear = printYear;
    }

    public byte getReadAlready() {
        return readAlready;
    }

    public void setReadAlready(byte readAlready) {
        this.readAlready = readAlready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (id != that.id) return false;
        if (printYear != that.printYear) return false;
        if (readAlready != that.readAlready) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if ((description != null && !description.equals("")) ? !description.equals(that.description) : (that.description != null && !that.description.equals(""))) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + printYear;
        result = 31 * result + (int) readAlready;
        return result;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", printYear=" + printYear +
                ", readAlready=" + readAlready +
                '}';
    }
}

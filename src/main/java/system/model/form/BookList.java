package system.model.form;

import system.model.TestEntity;

import java.util.List;

/**
 * Created by vladimir on 29.12.2017.
 */
public class BookList {
    private List<TestEntity> bookList;

    public BookList(List<TestEntity> bookList) {
        this.bookList = bookList;
    }

    public List<TestEntity> getBookList() {
        return bookList;
    }

    public void setBookList(List<TestEntity> bookList) {
        this.bookList = bookList;
    }

    public void add(TestEntity entity) {
        this.bookList.add(entity);
    }
}


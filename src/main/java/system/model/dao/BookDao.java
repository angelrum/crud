package system.model.dao;

import system.model.TestEntity;

import java.util.List;

/**
 * Created by vladimir on 24.12.2017.
 */
public interface BookDao {

    List<TestEntity> getAllBookByPage(String page);

    TestEntity getBookById(int id);

    Integer insertOrUpdate(TestEntity book);

    boolean deleteBook(int id);

    int getPage();

    boolean isPrevPage();

    boolean isNextPage();

    void setReadBook(int id);

    List<TestEntity> getBookByLine(String line);
}

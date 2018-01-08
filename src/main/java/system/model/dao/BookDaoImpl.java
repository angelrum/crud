package system.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import system.model.TestEntity;
import system.model.helper.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladimir on 24.12.2017.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookDaoImpl implements BookDao {
    private List<TestEntity> bookList = new ArrayList<TestEntity>();
    private int page;
    private boolean prevPage;
    private boolean nextPage;
    private boolean update;

    public void init() {
        this.page = 1;
        this.prevPage = false;
        this.nextPage = false;
        this.update = true;
    }

    private SessionFactory sessionFactory;

    @Autowired
    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        init();
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<TestEntity> getAllBookByPage(String page) {
        if (this.update | bookList.isEmpty()) {
            Session session = getSession();
            Query<TestEntity> query = session.createQuery("FROM TestEntity ");
            bookList = query.getResultList();
            this.update = false;
        }
        int n = page==null ? 1 : Integer.parseInt(page);

        this.page = n;
        this.prevPage = getBookListByPage(n - 1)!=null;
        this.nextPage = getBookListByPage(n + 1)!=null;

        return getBookListByPage(n);
    }

    public TestEntity getBookById(int id) {
        return getSession().get(TestEntity.class, id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Integer insertOrUpdate(TestEntity book) {
        this.update = true;
        Session session = getSession();
        if (book.getId()==0)
            return (Integer) session.save(book);
        session.update(book);

        return book.getId();
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean deleteBook(int id) {
        TestEntity entity = getBookById(id);
        if (entity!=null) {
            Session session = getSession();
            session.delete(entity);
            this.update = true;
            return true;
        }
        return false;
    }

    protected void destroy() {
        this.sessionFactory.close();
    }

    private List<TestEntity> getBookListByPage(int n) {
        if (n < 0) return null;
        int end = (n * 10) >= this.bookList.size() + 1 ? this.bookList.size() : n * 10;
        int start = n * 10 - 10;
        if (start >= 0
                && start < this.bookList.size()
                && end < this.bookList.size()) {
            List<TestEntity> entityList = this.bookList.subList(start, end);
            return entityList;
        }
        else if (start >= 0 && start < this.bookList.size())
        {
            List<TestEntity> entityList = this.bookList.subList(start, this.bookList.size());
            return entityList;
        }
        else
            return null;
    }

    public List<TestEntity> getBookByLine(String line) {
        List<TestEntity> result = new ArrayList<TestEntity>();
        String[]searchMas = line.split(" ");
        for (TestEntity entity : this.bookList) {
            String entityLine = Helper.toLine(entity);
            boolean flag = false;
            for (String search : searchMas) {
                if (entityLine.contains(search.toLowerCase())) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) result.add(entity);
        }
        return result;
    }

    @Transactional(readOnly = false)
    public void setReadBook(int id) {
        TestEntity entity = getBookById(id);
        if (entity!=null) {
            entity.setReadAlready((byte)1);
            insertOrUpdate(entity);
        }
    }

    public int getPage() {
        return page;
    }

    public boolean isPrevPage() {
        return prevPage;
    }

    public boolean isNextPage() {
        return nextPage;
    }
}

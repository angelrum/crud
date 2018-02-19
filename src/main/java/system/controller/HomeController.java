package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.model.TestEntity;
import system.model.dao.BookDao;
import system.model.form.BookList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vladimir on 24.12.2017.
 */
@Controller
public class HomeController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping(value = {"", "/home"}, method = RequestMethod.GET)
    public ModelAndView showHomePage(HttpServletRequest request) {
        return getHomePage("1");
    }

    @RequestMapping(value = {"", "/home"}, method = RequestMethod.GET, params = "page")
    public ModelAndView showConcretePAge(HttpServletRequest request) {
        return getHomePage(request.getParameter("page"));
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage(HttpServletRequest request) {
        ModelAndView mav = getHomePage(true);
        mav.addObject("message", "Не были выбраны записи для редактирования");
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET, params = "id")
    public ModelAndView editBookPage(HttpServletRequest request) {
        String[] select = request.getParameterValues("id");
        ModelAndView mav = new ModelAndView("edit");
        List<TestEntity> list = new ArrayList<TestEntity>();
        for (String s : select)
            list.add(bookDao.getBookById(Integer.parseInt(s)));

        BookList bookList = new BookList(list);
        bookList.setBookList(list);

        mav.addObject("editList", bookList);
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET, params = {"delete", "id"})
    public ModelAndView deleteBook(HttpServletRequest request) {
        String[] ids = request.getParameterValues("id");
        for (String id : ids)
            this.bookDao.deleteBook(Integer.parseInt(id));
        ModelAndView mav = getHomePage(true);
        if (ids.length==0){
            mav.addObject("message", "Не были выбраны книги для удаления");
            return mav;
        } else
            mav.addObject("message", "Книга(и) удалены");
        return mav;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET, params = "book")
    public ModelAndView editBookPageRead(HttpServletRequest request) {
        String bookId = request.getParameter("book");
        if (bookId!=null)
            bookDao.setReadBook(Integer.parseInt(bookId));
        return getHomePage(true);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchPage(@ModelAttribute("search") String search) {
        if (search.equals("")) {
            ModelAndView mav = getHomePage(true);
            mav.addObject("searchMessage", "Введите значение поиска");
            return mav;
        }
        ModelAndView mav = new ModelAndView("home");
        List<TestEntity> list = this.bookDao.getBookByLine(search);
        if (!list.isEmpty()) {
            mav.addObject("bookList", list);
            mav.addObject("searchParam", true);
        }
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView resultPage(@ModelAttribute("editList") BookList bookList) {
        boolean result = false;
        for (TestEntity book : bookList.getBookList()) {
            if (book.getId() > 0 && !book.equals(this.bookDao.getBookById(book.getId()))) {
                book.setReadAlready((byte)0);
                this.bookDao.insertOrUpdate(book);
                result = true;
            } else if (book.getId()==0){
                book.setReadAlready((byte)0);
                this.bookDao.insertOrUpdate(book);
                result = true;
            }
        }
        ModelAndView mav = getHomePage(true);
        if (result)
            mav.addObject("message", "Данные успешно изменены");
        else
            mav.addObject("message", "Изменения отсутствуют");
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, params = "cancel")
    public ModelAndView cancelPage(HttpServletRequest request) {
        return getHomePage(true);
    }


    private ModelAndView getHomePage(String page, boolean reset) {
        ModelAndView mav = new ModelAndView("home");

        if (reset) {
            page = String.valueOf(bookDao.getPage());
        }

        if (bookDao!=null)
            mav.addObject("bookList", bookDao.getAllBookByPage(page));

        mav.addObject("thisPage", bookDao.getPage());
        mav.addObject("prevPage", bookDao.isPrevPage());
        mav.addObject("nextPage", bookDao.isNextPage());
        return mav;
    }

    private ModelAndView getHomePage(String page) {
        return getHomePage(page, false);
    }

    private ModelAndView getHomePage(boolean reset) {
        return getHomePage(null, reset);
    }

}

package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import system.model.TestEntity;
import system.model.dao.BookDao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by vladimir on 05.01.2018.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String createBook(Model model) {
        model.addAttribute("entity", new TestEntity());
        return "book/new";
    }

    @RequestMapping(method = RequestMethod.POST, params = "new")
    public String saveBook(@ModelAttribute("entity") @Valid TestEntity entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/book/new";
        }
        return "redirect:/book/" + this.bookDao.insertOrUpdate(entity);
    }

    @RequestMapping(method = RequestMethod.GET, params = "cancel")
    public String cancelSaveBook(HttpServletRequest request) {
        return "redirect:home";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showBook(@PathVariable int id, Model model) {
        model.addAttribute("book", this.bookDao.getBookById(id));
        return "book/book";
    }

}

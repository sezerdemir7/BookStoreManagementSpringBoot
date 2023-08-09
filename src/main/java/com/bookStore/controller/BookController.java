package com.bookStore.controller;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor

public class BookController {
    private final BookService bookService;
    private final MyBookListService myBookListService;

    @GetMapping("/")
    public String home() throws IOException {

        return "home";
    }
    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }
    @GetMapping("/avaliable_books")
    public ModelAndView getAllBook(){
        List<Book> list=bookService.getAllBook();
      /*  ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("bookList");
        modelAndView.addAllObjects("book",list);

       */
        return new ModelAndView("bookList","book",list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book){

        bookService.save(book);

        return "redirect:/avaliable_books";
    }
    @GetMapping("/my_books")
    public String getMyBooks(Model model){

        List<MyBookList> list=myBookListService.getAllMyBooks();
        model.addAttribute("book",list);
        return "myBooks";
    }

    @RequestMapping("mylist/{id}")
    public String getMyList(@PathVariable("id") int id){

        Book book=bookService.getBookById(id);
        MyBookList myBookList=new MyBookList(book.getId(), book.getName(),book.getAuthor(),book.getPrice());
        myBookListService.saveMyBooks(myBookList);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id,Model model){
       Book book= bookService.getBookById(id);
       model.addAttribute("book",book);
        return "bookEdit";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable ("id") int id){
        bookService.deleteById(id);
        return "redirect:/avaliable_books";
    }


    @GetMapping("/kitap-arama")
    public String kitapAra(@RequestParam("aramaKelimesi") String name,Model model){
        List<Book> kitaplar=bookService.kitapAra(name);
        model.addAttribute("kitaplar",kitaplar);
        return "kitap-sonuclari";
    }

    @Transactional
    @RequestMapping("/begen/{id}")
    public String kitapBegen(@PathVariable("id") int id){

        bookService.increaseLikeCount(id);
        //model.addAttribute("book",book);
        return "redirect:/avaliable_books";
    }

}

package com.bookStore.controller;

import com.bookStore.service.MyBookListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class MyBookListController {
    private final MyBookListService myBookListService;
    @RequestMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") int id){
        myBookListService.deleteById(id);
        return "redirect:/my_books";

    }
}

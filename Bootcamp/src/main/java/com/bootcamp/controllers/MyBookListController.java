/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.controllers;


import com.bootcamp.models.BookList;
import com.bootcamp.repositories.BookListRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Handles and retrieves person request
 * Управляет и возвращает запрос
 */
@Controller
@RequestMapping("/main")
public class MyBookListController {

    protected static Logger logger = Logger.getLogger("controller");

    @Autowired
    private BookListRepository bookListRepository;

    /**
     * Handles and retrieves users and show it in a JSP page
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String getBook(Model model) {

        logger.debug("Received request to show all books");

        // Retrieve all users by delegating the call to repository
        Iterable<BookList> Books = bookListRepository.findAll();

        // Attach users to the Model
        model.addAttribute("Books", Books);

        return "mybooks.html";
    }

}
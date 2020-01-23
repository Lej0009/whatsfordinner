package com.whatsfordinner.app.controller;

import com.whatsfordinner.app.dao.IngredientDao;
import com.whatsfordinner.app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/")
public class MainController implements WebMvcConfigurer {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IngredientDao ingredientDao;

    @RequestMapping(value = "")
    public String landing(Model model) {
        return "index";
    }

}


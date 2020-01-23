package com.whatsfordinner.app.controller;

import com.whatsfordinner.app.dao.IngredientDao;
import com.whatsfordinner.app.dao.UserDao;
import com.whatsfordinner.app.models.Ingredient;
import com.whatsfordinner.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;

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

    @RequestMapping(value = "updatepassword")
    public String updatepassword(Model model) {
        return "updatepassword";
    }

    @GetMapping("/home")
    public String displayExpenses(Model model, User user) {
        model.addAttribute("ingredient", ingredientDao.findAll());
        model.addAttribute("title", "Your Ingredients");
    return "home";
    }

//    @GetMapping("/home/add")
//    public String displayAddIngredientForm(Model model, User user) {
//        model.addAttribute("title", "Add Ingredient");
//        return "add";
//    }

//    @GetMapping("/home/add")
    @RequestMapping(value = "home/add", method = RequestMethod.GET)
    public ModelAndView add(Model model, User user) {
        ModelAndView modelAndView = new ModelAndView();
        Ingredient ingredient = new Ingredient();
        modelAndView.addObject("ingredient", ingredient);
        model.addAttribute("title", "Add Ingredient");
        modelAndView.setViewName("add"); // resources/template/add.html
        return modelAndView;
    }



    @PostMapping("/home/add")
    public String processAddIngredientForm(User user, Model model, @ModelAttribute @Valid Ingredient newIngredient,
                                           Errors errors) {

        model.addAttribute(newIngredient);

        if(!errors.hasErrors()) {

//            User currentUser = userDao.findByEmail(principal.getName());
//            Integer id = currentUser.getUserId();
//
//            newIngredient.setUserId(id);
            ingredientDao.save(newIngredient);
            return "redirect:/home";
        }
        model.addAttribute("title", "Add Ingredient");
        return "add";
    }

    @GetMapping("/home/delete")
    public String displayRemoveIngredientForm(Model model) {

        model.addAttribute("expenses", ingredientDao.findAll());
        model.addAttribute("title", "Remove Ingredient");
        return "delete";
    }

    @PostMapping("/home/delete")
    public String processRemoveIngredientForm(@RequestParam Integer[] ingredientIds) {

        for (Integer ingredientId : ingredientIds) {
            ingredientDao.deleteById(ingredientId);
        }
        return "redirect:/home";
    }

}


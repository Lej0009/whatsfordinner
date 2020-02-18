package com.whatsfordinner.app.controller;

import com.whatsfordinner.app.dao.IngredientDao;
import com.whatsfordinner.app.dao.UserDao;
import com.whatsfordinner.app.models.Ingredient;
import com.whatsfordinner.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class IngredientController implements WebMvcConfigurer {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/home")
    public String displayIngredients(Model model) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User customUser = (User)authentication.getPrincipal();
//        Integer userId = customUser.getUserId();

        // TODO this code gives error "user cannot be cast to class com.whatsfordinner.app.models.User"
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Integer userId = user.getUserId();

//        model.addAttribute("ingredients", ingredientDao.findAllByUserId(userId));
        model.addAttribute("ingredients", ingredientDao.findAll());
        model.addAttribute("title", "Your Ingredients");

        return "home";
    }

    @GetMapping("/home/add")
    public String displayAddIngredientForm(Model model) {
        model.addAttribute("title", "Add Ingredient");
        return "add";
    }

    @PostMapping("/home/add")
    public String processAddIngredientForm(Model model, @ModelAttribute @Valid Ingredient newIngredient,
                                        Errors errors) {

        model.addAttribute(newIngredient);

        if(errors.hasErrors()) {

            //TODO get user ID to save with the ingredient
//            Optional<User> currentUser = userDao.findByEmail(principal.getName());
//            Integer id = currentUser.getUserId();
//
//            newIngredient.setUserId(id);
            model.addAttribute("title", "Add Ingredient");
            return "add";
        }
        ingredientDao.save(newIngredient);
        return "redirect:/home";
    }

    @GetMapping("/home/delete")
    public String displayRemoveIngredientForm(Model model, User user, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User customUser = (User)authentication.getPrincipal();
        Integer userId = customUser.getUserId();

        model.addAttribute("ingredients", ingredientDao.findAllByUserId(userId));
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

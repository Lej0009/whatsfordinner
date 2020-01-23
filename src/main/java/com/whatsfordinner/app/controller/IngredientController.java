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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IngredientController implements WebMvcConfigurer {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/home")
    public String displayIngredients(Model model, User user) {
        model.addAttribute("ingredients", ingredientDao.findAll());
        model.addAttribute("title", "Your Ingredients");

        return "home";
    }

    @GetMapping("/home/add")
    public String displayAddIngredientForm(Model model, User user) {
        model.addAttribute("title", "Add Ingredient");
        return "add";
    }

    @PostMapping("/home/add")
    public String processAddIngredientForm(User user, Model model, @ModelAttribute @Valid Ingredient newIngredient,
                                        Errors errors,  Principal principal) {

        model.addAttribute(newIngredient);

        if(!errors.hasErrors()) {

//            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//            String name=auth.getName();
//            UserDetails userDetail= (UserDetails)auth.getPrincipal();
//
//            User currentUser= userDao.findByPassword(userDetail.getPassword());
//            Integer id= currentUser.getUserId();

            //TODO get user ID to save along with the ingredient
//            Optional<User> currentUser = userDao.findByEmail(principal.getName());
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

        model.addAttribute("ingredients", ingredientDao.findAll());
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

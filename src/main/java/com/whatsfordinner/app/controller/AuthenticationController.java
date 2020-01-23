package com.whatsfordinner.app.controller;

import com.whatsfordinner.app.models.User;
import com.whatsfordinner.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("title", "Login");
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        model.addAttribute("title", "Register");
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @PostMapping("/register")
    public String processRegisterForm(Model model, @ModelAttribute @Valid User newUser, Errors errors,
                                      String verifypassword) {

        if (userService.isUserAlreadyPresent(newUser) == true) {
            model.addAttribute( "duplicateEmailError", "This email is already registered with an account");
        }

        model.addAttribute(newUser);

        boolean passwordsMatch = true;
        if (newUser.getPassword() == null || verifypassword == null
                || !newUser.getPassword().equals(verifypassword)) {
            passwordsMatch = false;
            newUser.setPassword("");
            model.addAttribute("verifyError", "Passwords must match");
        }

        if (!errors.hasErrors() && passwordsMatch) {
            userService.saveUser(newUser);
            return "login";
        }
        model.addAttribute("title", "Register");
        return "register";
    }

}

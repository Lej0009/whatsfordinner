package com.whatsfordinner.app.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import com.whatsfordinner.app.models.User;
import com.whatsfordinner.app.service.EmailService;
import com.whatsfordinner.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Display forgotPassword page
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }

    // Process form submission from forgotPassword page
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView,
                                                  @RequestParam("email") String userEmail,
                                                  HttpServletRequest request) {

        // Lookup user in database by e-mail
        User userLookup = userService.findUserByEmail(userEmail);

        if (!userService.isUserAlreadyPresent(userLookup)) {
            modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
        } else {

            // Generate random 36-character string token for reset password
//            User user = userLookup.get();
            userLookup.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            userService.saveUser(userLookup);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@whatsfordinner.com");
            passwordResetEmail.setTo(userLookup.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + userLookup.getResetToken());

            emailService.sendEmail(passwordResetEmail);

            // TODO add message to view
            // Add success message to view
//            modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        }

        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    // Display form to reset password
    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        User user = userService.findUserByResetToken(token);

        if (userService.isUserAlreadyPresent(user)) { // Token found in DB
            modelAndView.addObject("resetToken", token);
        } else { // Token not found in DB
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
        }

        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    // Process reset password form
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam String password,
                                       @RequestParam String verifypassword, @RequestParam Map<String,
                                        String> requestParams, RedirectAttributes redir) {

        // Find the user associated with the reset token
        User user = userService.findUserByResetToken(requestParams.get("token"));

        // This should always be non-null but we check just in case
        if (userService.isUserAlreadyPresent(user) && password.equals(verifypassword)) {

//            User resetUser = user.get();

            // Set new password
            user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

            // Set the reset token to null so it cannot be used again
            user.setResetToken(null);

            // Save user
            userService.saveUser(user);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("redirect:login");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:login");
    }


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //TODO verify host and port
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        // TODO mask username and password
        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}

package com.michelle.Kali.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michelle.Kali.models.User;
import com.michelle.Kali.services.UserService;
import com.michelle.Kali.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
    
    private UserValidator userValidator;
    
    // NEW
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
   //login page
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model,HttpSession session,HttpServletRequest request) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        if(session.getAttribute("userId") != null) {
        	return"redirect:/";
        }
        return "loginPage.jsp";
    }
    
    
    @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user, BindingResult result) {
        return "registrationPage.jsp";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,HttpSession session) {
        // NEW
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registrationPage.jsp";
        }
        session.setAttribute("userId",user.getId());
        userService.saveWithUserRole(user);
        return "redirect:/";
    }
}

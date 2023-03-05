package com.skujevska.baiba.controller;

import com.skujevska.baiba.frontmodel.UserFront;
import com.skujevska.baiba.model.User;
import com.skujevska.baiba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("user", new UserFront());
        return "home";
    }
    @GetMapping("/signin")
    public String login(Model model) {
        model.addAttribute("user", new UserFront());
        return "login";
    }

    @GetMapping("/signup")
    public String register(Model model) {
        model.addAttribute("user", new UserFront());
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView registerUser(@ModelAttribute("user") UserFront userFront,
                                     RedirectAttributes redirectAttributes) {
        if (userService.existsByUsername(userFront.getUsername())) {
            final RedirectView redirectView = new RedirectView("/signup", true);
            redirectAttributes.addAttribute("userNameTaken", true);
            return redirectView;
        }
        final RedirectView redirectView = new RedirectView("/signin", true);
        User savedUser = userService.save(userFront);
        redirectAttributes.addAttribute("savedUser", savedUser.getUsername());
        redirectAttributes.addAttribute("addUserSuccess", true);
        return redirectView;
    }

    @GetMapping("/accessDenied")
    public String denied(Model model) {
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "accessDenied";
    }
}

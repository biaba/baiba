package com.skujevska.baiba.controller;

import com.skujevska.baiba.model.User;
import com.skujevska.baiba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }
    @GetMapping("/signin")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/signup")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView registerUser(@ModelAttribute("user") User user,
                                     RedirectAttributes redirectAttributes) {
        if (userService.existsByUsername(user.getUsername())) {
            final RedirectView redirectView = new RedirectView("/signup", true);
            redirectAttributes.addAttribute("userNameTaken", true);
            return redirectView;
        }
        final RedirectView redirectView = new RedirectView("/signin", true);
        User savedUser = userService.save(user);
        redirectAttributes.addAttribute("savedUser", savedUser.getUsername());
        redirectAttributes.addAttribute("addUserSuccess", true);
        return redirectView;
    }
}

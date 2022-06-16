package com.skujevska.baiba.controller;

import com.skujevska.baiba.model.Role;
import com.skujevska.baiba.model.RoleE;
import com.skujevska.baiba.model.User;
import com.skujevska.baiba.repository.RoleRepository;
import com.skujevska.baiba.repository.UserRepository;
import com.skujevska.baiba.service.RoleService;
import com.skujevska.baiba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

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

    @PostMapping("/signin")
    public RedirectView login(@ModelAttribute("user") User user,
                                     RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/", true);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        System.out.println(" IN CONTROLLER");
        if(authentication == null ) {
            final RedirectView redirectViewBack = new RedirectView("/signin", true);
            redirectAttributes.addFlashAttribute("wrongCredentials", true);
            return redirectViewBack;
        }
        redirectAttributes.addFlashAttribute("user", user);

        return redirectView;
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
            redirectAttributes.addFlashAttribute("userNameTaken", true);
            return redirectView;
        }
        final RedirectView redirectView = new RedirectView("/signin", true);
        // Create new user's account
        User newUser = new User(user.getUsername(),
                encoder.encode(user.getPassword()));

        Set<String> strRoles = null;
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(RoleE.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(RoleE.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleService.findByName(RoleE.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        newUser.setRoles(roles);
        User savedUser = userService.save(newUser);
        redirectAttributes.addFlashAttribute("savedUser", savedUser);
        redirectAttributes.addFlashAttribute("addUserSuccess", true);
        return redirectView;
    }
}

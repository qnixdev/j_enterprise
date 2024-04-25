package com.security_system.Controller;

import com.security_system.Model.UserModel;
import com.security_system.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(
        UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping
    public String form(Model model) {
        UserModel userModel = new UserModel();
        model.addAttribute("user", userModel);

        return "registration";
    }

    @PostMapping("/process")
    public String process(
        @ModelAttribute("user") UserModel userModel,
        BindingResult bindingResult,
        Model model
    ) {
        var existUser = this.userService.read(userModel.getEmail());

        if (null != existUser) {
            bindingResult.rejectValue("email", "user.email", "This email already exists.");
        }
        if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
            bindingResult.rejectValue("password", "user.password", "Passwords do not match.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userModel);

            return "registration";
        }

        this.userService.create(
            userModel.getEmail(),
            userModel.getPassword()
        );

        return "redirect:/home";
    }
}
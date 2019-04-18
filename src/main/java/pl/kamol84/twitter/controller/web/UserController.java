package pl.kamol84.twitter.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamol84.twitter.entity.User;
import pl.kamol84.twitter.repository.UserRepository;
import pl.kamol84.twitter.service.UserService;
import pl.kamol84.twitter.validationGroup.FullValidation;
import pl.kamol84.twitter.validationGroup.PartValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Validated({PartValidation.class}) User user, BindingResult err,
                        Model model,
                        HttpSession sesion,
                        HttpServletRequest request) {
        if (err.hasErrors()) {
            return "user/login";
        }

        try {
            User logedUser = userService.checkLogin(user.getEmail(), user.getPassword());
            sesion.setAttribute("user", logedUser);
            return "redirect:" + request.getContextPath() + "/";
        } catch (Exception e) {
            sesion.removeAttribute("user");
            model.addAttribute("errorMsg", e.getMessage());
            return "user/login";
        }
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @GetMapping("/form/{id}")
    public String form(Model model, @PathVariable Long id) {
        model.addAttribute("user", userRepository.getOne(id));
        return "user/form";
    }

    @PostMapping("/form")
    public String form(@Valid User user, BindingResult err,
                       @Validated({FullValidation.class}) User userFull, BindingResult errFull,
                       HttpServletRequest request, Model model) {
        //new user
        if (user.getId() == null && errFull.hasErrors()) {
            model.addAttribute("user", userFull);
            return "user/form";
        }
        if (user.getId() != null && err.hasErrors()) {
            return "user/form";
        }

        userService.save(user);
        return "redirect:" + request.getContextPath() + "/user/list";
    }


    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }


}

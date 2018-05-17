package com.ucbcba.taller.controllers;



import com.ucbcba.taller.entities.City;
import com.ucbcba.taller.entities.User;
import com.ucbcba.taller.services.CityService;
import com.ucbcba.taller.services.SecurityService;
import com.ucbcba.taller.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CityService cityService;

    //@Autowired
    //private UserValidator userValidator;

    @Autowired
    public void setUserService(UserService userService){this.userService=userService;}


    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationInit(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        cityService.saveCity((City) cityService);
        userService.save(user);
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());
        return "redirect:/bienvenidos";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }



    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    @RequestMapping(value = {"/admin/"}, method = RequestMethod.GET)
    public String admin(Model model) {

        return "welcome";
    }
    @RequestMapping(value = {"/bienvenidos"}, method = RequestMethod.GET)
    public String welcome2(Model model) {
        return "bienvenidos";
    }

    @RequestMapping(value = {"/makeAdmin"}, method = RequestMethod.GET)
    public  String makeAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        boolean adm = true;
        user.setAdmin(adm);
        return "redirect:/bienvenidos";
    }

}
package website.opinion.opinion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import website.opinion.opinion.dao.UserDao;

@Controller
public class AuthController {
    @Autowired
    UserDao userDao;

    @GetMapping("/login")
    public String showAuth(){
        return "auth";
    }
}

package com.JS.DA.User.Controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Component("userAboutController")
@Controller
public class AboutController {
    @GetMapping("/about")
    public String about() {
        return "/user/about/index";
    }
}
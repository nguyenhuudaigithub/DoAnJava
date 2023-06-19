package com.JS.DA.Admin.Controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Component("adminHomeController")
@Controller
public class HomeController {
    @GetMapping("/admin")
    public String home() {
        return "/admin/home/index";
    }
}

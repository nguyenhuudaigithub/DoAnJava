package com.JS.DA.User.Controller;

import com.JS.DA.Admin.Model.EmailSubribe;
import com.JS.DA.Admin.Service.EmailSubribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Component("UserEmailSubribeController")
@Controller
public class EmailSubribeController {
    @Autowired
    private EmailSubribeService emailSubribeService;
    @GetMapping("/addEmailSubribe")
    public String addEmailSubribeForm(Model model) {
        EmailSubribe emailSubribe = new EmailSubribe();
        model.addAttribute("emailSubribe", emailSubribe);
        return "/user/EmailSubribe/addEmailSubribe";
    }

    @PostMapping("/addEmailSubribe")
    public String addNeww(@ModelAttribute EmailSubribe emailSubribe) {
        emailSubribeService.addEmailSubribe(emailSubribe);
        return "redirect:/addEmailSubribe";
    }

}

package com.JS.DA.User.Controller;

import com.JS.DA.Admin.Model.EmailForm;
import com.JS.DA.Admin.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/contact")
    public String showEmailForm(Model model) {
        model.addAttribute("emailForm", new EmailForm());
        return "user/contact/index";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute("emailForm") EmailForm emailForm) {
        emailService.sendHtmlEmail(emailForm);
        return "redirect:/contact";
    }
}
package com.JS.DA.Admin.Controller;

import com.JS.DA.Admin.Model.EmailSubribe;
import com.JS.DA.Admin.Service.EmailSubribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EmailSubribeController {
    @Autowired
    private EmailSubribeService emailSubribeService;

    @GetMapping("/admin/listEmail")
    public String emailSubribeList(Model model) {
        List<EmailSubribe> emailSubribeList = emailSubribeService.getAllEmailSubribe();
        model.addAttribute("emailSubribeList", emailSubribeList);
        return "/admin/emails/emailSubribeList";
    }

    @GetMapping("/admin/emailSubribe/delete/{id}")
    public String deleteEmailSubribe(@PathVariable("id") Long id) {
        emailSubribeService.deleteEmailSubribe(id);
        return "redirect:/admin/listEmail";
    }
}

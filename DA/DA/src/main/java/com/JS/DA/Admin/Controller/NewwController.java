package com.JS.DA.Admin.Controller;

import com.JS.DA.Admin.Model.Neww;
import com.JS.DA.Admin.Service.NewwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Component("adminNewwController")
@Controller
public class NewwController {
    @Autowired
    private NewwService newwService;

    @GetMapping("/admin/news")
    public String NewwsList(Model model) {
        List<Neww> newws = newwService.getAllNeww();
        model.addAttribute("newws", newws);
        return "/admin/new/newsList";
    }

    @GetMapping("/admin/news/add")
    public String addNewwForm(Model model) {
        Neww neww = new Neww();
        model.addAttribute("neww", neww);
        return "/admin/new/addNewForm";
    }

    @PostMapping("/admin/news/add")
    public String addNeww(@ModelAttribute Neww neww) {
        newwService.addNeww(neww);
        return "redirect:/admin/news";
    }

    @GetMapping("/admin/news/delete/{id}")
    public String deleteNeww(@PathVariable("id") Long id) {
        newwService.deleteNeww(id);
        return "redirect:/admin/news";
    }

    @GetMapping("/admin/news/edit/{id}")
    public String editNewwForm(@PathVariable("id") Long id, Model model) {
        Neww neww = newwService.getNewwById(id);
        model.addAttribute("neww", neww);
        return "/admin/new/editNewForm";
    }

    @PostMapping("/admin/news/edit/{id}")
    public String editNeww(@PathVariable("id") Long id, @ModelAttribute("neww") Neww updatedNeww) {
        Neww existingNeww = newwService.getNewwById(id);
        existingNeww.setName(updatedNeww.getName());
        existingNeww.setImage(updatedNeww.getImage());
        existingNeww.setDescription(updatedNeww.getDescription());
        existingNeww.setDetail(updatedNeww.getDetail());
        existingNeww.setIs_activate(updatedNeww.getIs_activate());
        existingNeww.setIs_page(updatedNeww.isIs_page());
        newwService.updatedNeww(existingNeww);
        return "redirect:/admin/news";
    }
}

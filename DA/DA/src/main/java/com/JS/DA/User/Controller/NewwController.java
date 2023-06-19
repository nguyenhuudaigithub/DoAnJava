package com.JS.DA.User.Controller;

import com.JS.DA.Admin.Model.Neww;
import com.JS.DA.Admin.Model.Post;
import com.JS.DA.Admin.Service.NewwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Component("userNewwController")
@Controller
public class NewwController {
    @Autowired
    private NewwService newwService;
    @GetMapping("/news")
    public String NewwsList(Model model) {
        List<Neww> newws = newwService.getAllNeww();
        List<Neww> filteredNewws = new ArrayList<>();
        for (Neww neww : newws) {
            if (neww.getIs_activate()) {
                filteredNewws.add(neww);
            }
        }
        model.addAttribute("newws", filteredNewws);
        return "/user/new/newsList";
    }

    @GetMapping("/news/{id}")
    public String readNewwForm(@PathVariable("id") Long id, Model model) {
        Neww neww = newwService.getNewwById(id);
        model.addAttribute("neww", neww);
        List<Neww> newws = newwService.getAllNeww();
        List<Neww> filteredNewws = new ArrayList<>();
        for (Neww newwl : newws) {
            if (newwl.getIs_activate()) {
                filteredNewws.add(newwl);
            }
        }
        model.addAttribute("newws", filteredNewws);
        return "user/new/readNewwForm";
    }
}

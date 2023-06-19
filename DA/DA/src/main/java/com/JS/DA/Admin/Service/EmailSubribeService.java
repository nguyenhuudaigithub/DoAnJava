package com.JS.DA.Admin.Service;

import com.JS.DA.Admin.Model.EmailSubribe;
import com.JS.DA.Admin.Repository.EmailSubribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSubribeService {
    @Autowired
    private EmailSubribeRepository emailSubribeRepository;

    public List<EmailSubribe> getAllEmailSubribe(){
        return emailSubribeRepository.findAll();
    }

    public void addEmailSubribe(EmailSubribe emailSubribe) {
        emailSubribeRepository.save(emailSubribe);
    }

    public void deleteEmailSubribe(Long id){
        emailSubribeRepository.deleteById(id);
    }
}

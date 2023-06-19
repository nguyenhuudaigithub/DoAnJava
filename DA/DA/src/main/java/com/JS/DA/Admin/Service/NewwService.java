package com.JS.DA.Admin.Service;

import com.JS.DA.Admin.Model.Neww;
import com.JS.DA.Admin.Repository.NewwRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewwService {
    @Autowired
    private NewwRepository NewwRepository;

    public List<Neww> getAllNeww(){
        return NewwRepository.findAll();
    }

    public Neww getNewwById(Long id){
        Optional<Neww> optionalNeww = NewwRepository.findById(id);
        if(optionalNeww.isPresent()){
            return  optionalNeww.get();
        }else{
            throw new RuntimeException("Neww not found");
        }
    }
    public void addNeww(Neww Neww) {
        NewwRepository.save(Neww);
    }
    public void updatedNeww(Neww Neww) {
        NewwRepository.save(Neww);
    }
    public Neww saveNeww(Neww Neww){
        return  NewwRepository.save(Neww);
    }

    public void deleteNeww (Long id){
        NewwRepository.deleteById(id);
    }
}
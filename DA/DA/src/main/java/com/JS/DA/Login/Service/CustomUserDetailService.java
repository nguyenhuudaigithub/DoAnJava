package com.JS.DA.Login.Service;

import com.JS.DA.Login.Model.CustomUserDetail;
import com.JS.DA.Login.Model.User;
import com.JS.DA.Login.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(("User not found"));
        return new CustomUserDetail(user, userRepository);
    }
}

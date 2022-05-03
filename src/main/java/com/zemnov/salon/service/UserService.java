package com.zemnov.salon.service;

import com.zemnov.salon.model.Contact;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.ContactRepo;
import com.zemnov.salon.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public void deleteUserById(User user){
        contactRepo.deleteById(user.getId()+1);
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public void saveContact(Contact contact){
        contactRepo.save(contact);
    }
}

package com.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class UserService  {

    @Autowired
    UserRepositoryDatabase userRepositryDatabase;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        userRepositryDatabase.findAll().forEach(users1 -> users.add(users1));
        return users;
    }

    public User getUserById(Long userId){
        return userRepositryDatabase.findById(userId).get();
    }

    public void saveOrUpdate(User user){
        userRepositryDatabase.save(user);
    }

    public void delete(Long id){
        userRepositryDatabase.deleteById(id);
    }

    public void update(User user, int bookId){
        userRepositryDatabase.save(user);
    }

}

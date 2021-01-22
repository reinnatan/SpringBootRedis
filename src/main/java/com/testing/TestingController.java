package com.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController

class TestingController<s> {

    @Autowired
    private UserRepository userRepository;

    public TestingController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public Map<String, User> GetAll(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable String id){
        return userRepository.findById(id);
    }

    @PostMapping("/add")
    public User add(@RequestBody User user){
        userRepository.save(user);
        return userRepository.findById(user.getId());
    }

    @PutMapping("/update")
    public User Update(@RequestBody User user){
        userRepository.update(new User(user.getId(), user.getName(), user.getSalary()));
        return userRepository.findById(user.getId());
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,String> Delete(@PathVariable String id){
        User userFind = userRepository.findById(id);
        if (userFind != null){
            userRepository.delete(userFind.getId());
            Map<String, String> response = new HashMap<String, String>();
            response.put("message", "Success deleted");
            return response;
        }else{
            Map<String, String> response = new HashMap<String, String>();
            response.put("message", "User not found with id "+id);
            return response;
        }

    }




}
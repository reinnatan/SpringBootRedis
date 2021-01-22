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

    @PutMapping("/update/{id}")
    public Map<String, String> Update(@PathVariable String id, @RequestBody User user){
        User userFind = userRepository.findById(id);
        if (userFind != null){
            userFind.setName(user.getName());
            userFind.setSalary(user.getSalary());
            userRepository.update(userFind);
            Map<String, String> response = new HashMap<String, String>();
            response.put("message", "Success update");
            return response;
        }else{
            Map<String, String> response = new HashMap<String, String>();
            response.put("message", "User not found with id "+id);
            return response;
        }

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
package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public String Home(){
        return "Home";
    }

    @GetMapping("/getallusers")
    public String getAllUsers(Model model){
        //List<User> users=userService.getAllUsers();
        return findPaginated(1,model);
    }

    @GetMapping("/adduser")
    public String addUser(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "addUser";
    }

    @PostMapping("/adduser/save")
    public String addUser(Model mode, @ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateuser/{id}")
    public String updateUser(Model model, @PathVariable Long id){
        User user=userService.getUserById(id);
        model.addAttribute("user",user);
        return "UpdateUser";
    }

    @PostMapping("/updateuser/save")
    public String updateUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize=5;
        Page<User> page=userService.findPaginated(pageNo,pageSize);
        List<User> listUsers=page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listUsers",listUsers);
        return "Users";
    }
}

package com.example.demo.Service;

import com.example.demo.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public void addUser(User user);
    public User getUserById(Long id);
    public void saveUser(User user);
    public void deleteUser(Long id);
    Page<User> findPaginated(int pageNo, int pageSize);
}

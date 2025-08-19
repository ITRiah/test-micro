package com.lgcns.study_micros.user;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private List<User> users = new ArrayList<>();

    @PostMapping
    public User createUser(@RequestBody User user) {
        this.users.add(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        User oldUser = this.users.stream()
                .filter(u -> Objects.equals(u.getId(), user.getId())).findFirst().orElse(null);
        if (oldUser == null) {return null;}
        this.users.set(this.users.indexOf(oldUser), user);
        return user;
    }

    @DeleteMapping
    public void deleteUser(@RequestParam("id") Integer id) {
        this.users.removeIf(u -> Objects.equals(u.getId(), id));
    }

    @GetMapping
    public List<User> getUsers() {
        return this.users;
    }
}

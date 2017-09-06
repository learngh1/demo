package com.example;

import com.example.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private Map<Long, User> usersMap;

    public RestController() {
        usersMap = new HashMap<>();

        Long id;
        for (int i = 1; i <= 4; i++) {
            id = Long.valueOf(i);
            usersMap.put(id, new User(id, "Name_" + id, "Desc_" + id));
        }
    }

    @CrossOrigin(origins = "http://localhost:8090") //webpack dev server
    @RequestMapping("/get_user_list")
    public User[] getUserList() {
        Collection<User> users = usersMap.values();
        return users.toArray(new User[users.size()]);
    }

    @CrossOrigin(origins = "http://localhost:8090") //webpack dev server
    @RequestMapping("/get_user_details")
    public User getUserDetails(@RequestParam(name = "id") Long id) {
        return usersMap.get(id);
    }
}
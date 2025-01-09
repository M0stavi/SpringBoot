package com.mahin.restful.restfulApi.users;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    public static List<User> userList = new ArrayList<>();
    private static Integer userCount = 0;

    static {
        userList.add(new User(++userCount, "adam", LocalDate.now().minusYears(30)));
        userList.add(new User(++userCount, "eve", LocalDate.now().minusYears(25)));
        userList.add(new User(++userCount, "john", LocalDate.now().minusYears(20)));
    }

    public List<User> getAllUser(){
        return userList;
    }

    public User saveUser(User user){
        user.setId(++userCount);
        userList.add(user);
        return user;
    }

    public User retrieveUserById(Integer id){
        for (User user: userList){
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    public void removeUser(Integer id){
        userList.removeIf(user -> user.getId().equals(id));
    }
}

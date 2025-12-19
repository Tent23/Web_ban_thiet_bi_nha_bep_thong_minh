package com.webthietbibep.services;

import com.webthietbibep.dao.AuthDao;
import com.webthietbibep.model.User;

public class AuthService {
    AuthDao authDao = new AuthDao();

    public User login(String username, String password) {
        User u= authDao.getUserByUsername(username);
        if(u!=null && u.getPassword_hash().equals(password)){
            u.setPassword_hash(null);
            return u;
        }
        return null;
    }
}

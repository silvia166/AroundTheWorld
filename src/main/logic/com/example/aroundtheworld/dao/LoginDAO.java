package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.exception.UserNotFoundException;
import com.example.aroundtheworld.model.UserProfile;

public interface LoginDAO {
    UserProfile checkUser(String username, String password) throws UserNotFoundException;
}

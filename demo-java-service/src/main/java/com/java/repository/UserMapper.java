package com.java.repository;

import com.java.dto.User;

/**
 * Created by iss on 18/2/9.
 */
public interface UserMapper {
    public User getUserById(Integer userId);
}

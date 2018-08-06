package com.hd.mapper;

import com.hd.pojo.User;

 
public interface UserMapper {
 
    public int add(User user);
    public User select(int id);
    
}

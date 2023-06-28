package com.example.repository;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT userName, entryTime, exitTime FROM user")
    List<User> getUsersWithTime();
}

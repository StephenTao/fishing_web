package com.augmentum.fishing.dao;

import java.util.List;

import com.augmentum.common.base.IBaseDao;
import com.augmentum.fishing.dto.UserDTO;
import com.augmentum.fishing.model.User;

public interface UserDao extends IBaseDao<User, Integer> {

    UserDTO getByName(String userName);

    List<UserDTO> findAll();

}

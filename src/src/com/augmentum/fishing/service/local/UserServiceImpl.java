package com.augmentum.fishing.service.local;

import java.util.List;

import com.augmentum.common.base.BaseService;
import com.augmentum.fishing.Constants;
import com.augmentum.fishing.dao.UserDao;
import com.augmentum.fishing.dto.UserDTO;
import com.augmentum.fishing.model.User;
import com.augmentum.fishing.service.UserService;

public class UserServiceImpl extends BaseService implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDTO getByName(String userName) {
        return userDao.getByName(userName.trim());
    }

    @Override
    public List<UserDTO> findAll() {
        return userDao.findAll();
    }

    @Override
    public Boolean register(UserDTO userDTO) {
        String registerType = userDTO.getRegisterType();
        User user = new User();
        if (Constants.PHONE_NUMBER.equals(registerType)) {
            user.setPhone_number(userDTO.getPhone_number());
        }
        return null;
    }

}

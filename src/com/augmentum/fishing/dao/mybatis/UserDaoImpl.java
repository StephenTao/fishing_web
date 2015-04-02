package com.augmentum.fishing.dao.mybatis;

import java.util.List;

import com.augmentum.common.base.BaseDao;
import com.augmentum.fishing.dao.UserDao;
import com.augmentum.fishing.dto.UserDTO;
import com.augmentum.fishing.model.User;

public class UserDaoImpl extends BaseDao<User, Integer> implements UserDao {

    private final static String CLASS_NAME = User.class.getName();
    private final static String SQL_ID_GET_BY_NAME = ".getByName";
    private static final String SQL_ID_FIND_ALL = ".findAll";

    @Override
    public UserDTO getByName(String userName) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_BY_NAME, userName);
    }

    @Override
    public List<UserDTO> findAll() {
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ALL);
    }

}

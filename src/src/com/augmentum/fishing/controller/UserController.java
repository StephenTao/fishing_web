package com.augmentum.fishing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.augmentum.common.base.BaseController;
import com.augmentum.common.base.JsonMessage;
import com.augmentum.common.base.JsonMessage.MessageEntry;
import com.augmentum.common.util.StringUtil;
import com.augmentum.fishing.Constants;
import com.augmentum.fishing.dto.UserDTO;
import com.augmentum.fishing.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MessageEntry login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.REQUEST, request);
        //EventManager.dispatchEvent(Constants.EVENT_BEFORE_LOGIN, params);
//        String returnMsg = (String) params.get(AppConstants.EVENT_RETURN_MSG_KEY);
//        if (returnMsg != null && !returnMsg.isEmpty()) {
//            return JsonMessage.ok(returnMsg);
//        }
        UserDTO user = userService.getByName(userDTO.getUserName());
        // user name is wrong
        if (user == null) {
            //String errorMsg = SpringUtil.getMessage(Constants.KEY_ERROR_MSG_USER_NAME_ERROR);
            String errorMsg = Constants.KEY_ERROR_MSG_USER_NAME_ERROR;
            return JsonMessage.ok(errorMsg);
        }
        // password is wrong
        if (!user.getPassword().equals(StringUtil.MD5(userDTO.getPassword()))) {
            //String errorMsg = SpringUtil.getMessage(AppConstants.KEY_ERROR_MSG_PASSWORD_ERROR);
            String errorMsg = Constants.KEY_ERROR_MSG_PASSWORD_ERROR;
            return JsonMessage.ok(errorMsg);
        }
        user.setPassword(null);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.USER, user);
        return JsonMessage.ok(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public MessageEntry logout() {
        return JsonMessage.ok(Boolean.TRUE);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public MessageEntry findAll() {
        List<UserDTO> users = userService.findAll();
        return JsonMessage.ok(users);
    }

}

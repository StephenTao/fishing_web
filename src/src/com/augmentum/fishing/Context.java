package com.augmentum.fishing;

import java.util.HashMap;

import com.augmentum.fishing.dto.UserDTO;

public class Context {

    private static final ThreadLocal<Context> appContext = new ThreadLocal<Context>();
    private final HashMap<String, Object> values = new HashMap<String, Object>();

    public static Context getContext() {
        Context context = appContext.get();
        if (context == null) {
            context = new Context();
            appContext.set(context);
        }
        return context;
    }

    public void clear() {
        Context context = appContext.get();
        if (context != null) {
            context.values.clear();
        }
        context = null;
    }

    public void addObject(String key, Object value) {
        values.put(key, value);
    }

    public Object getObject(String key) {
        return values.get(key);
    }

    public String getUserName() {
        UserDTO userDTO = getUser();
        if (userDTO == null) {
            return "";
        }
        return userDTO.getUserName();
    }

    public Integer getUserId() {
        UserDTO userDTO = getUser();
        if (userDTO == null) {
            return 0;
        }
        return userDTO.getId();
    }

    public UserDTO getUser() {
        UserDTO userDTO = (UserDTO) values.get(Constants.USER);
        return userDTO;
    }

}

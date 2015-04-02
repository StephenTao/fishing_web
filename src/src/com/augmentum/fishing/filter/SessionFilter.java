package com.augmentum.fishing.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.augmentum.common.util.AppUtil;
import com.augmentum.common.util.StringUtil;
import com.augmentum.fishing.Constants;
import com.augmentum.fishing.Context;
import com.augmentum.fishing.dto.UserDTO;

public class SessionFilter implements Filter {

    public SessionFilter() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO)session.getAttribute(Constants.USER);
        @SuppressWarnings("unchecked")
        //List<Role> roles = (List<Role>)session.getAttribute(AppConstants.ROLES);
        //String currentRoleCode = (String) session.getAttribute(AppConstants.CURRENT_ROLE_CODE);
        Context appContext = Context.getContext();
        if (user != null) {
            appContext.addObject(Constants.USER, user);
        }

//        // Internationalization
//        String locale = request.getParameter(AppConstants.LOCALE);
//        if (StringUtil.isEmpty(locale)) {
//            locale = (String) session.getAttribute(AppConstants.LOCALE);
//            if (StringUtil.isEmpty(locale)) {
//                locale = AppConstants.LOCALE_ZH_CN;
//            }
//        } else if (AppConstants.LOCALE_EN_US.equals(locale)) {
//            // empty here
//        } else {
//            locale = AppConstants.LOCALE_ZH_CN;
//        }
//        session.setAttribute(AppConstants.LOCALE, locale);
//        appContext.addObject(AppConstants.LOCALE, locale);

        // put staticURL into request for being called in JSP
       // request.setAttribute(AppConstants.STATIC_URL, AppUtil.getPropertyValue(AppConstants.STATIC_URL));

        //String uri =  request.getRequestURI();
       // String requestedUri = uri.substring(request.getContextPath().length() + 1);
        //appContext.addObject(AppConstants.REQUESTED_URI, requestedUri);

        try {
            chain.doFilter(request, servletResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } finally {
            appContext.clear();
        }

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}

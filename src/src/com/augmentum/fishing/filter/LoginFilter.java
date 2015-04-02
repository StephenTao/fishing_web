package com.augmentum.fishing.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.augmentum.fishing.Constants;
import com.augmentum.fishing.dto.UserDTO;

/*
 *  This class be used for test to mock up a logged user.
 *
 */
public class LoginFilter implements Filter {

    private static final String URL_LOGIN = "/admin/user/login";
    private static final String URL_LOGOUT = "/admin/user/logout";
    private static final String NOT_LOGIN = "NotLogin";
    private static final String SPRIT = "/";
    private static final String AJAX_HEAD = "X-Requested-With";
    private static final String URL_UPLOAD = "/admin/upload";

    public LoginFilter() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        Boolean isLogin = uri.equals(request.getContextPath() + SPRIT) || uri.endsWith(URL_LOGIN)
                || uri.endsWith(URL_LOGOUT);
        Boolean isUpload = uri.contains(URL_UPLOAD);
        boolean isNotAdmin = false;
        if (session.getAttribute(Constants.KEY_ERROR_MSG_NOT_ADMIN) != null) {
            isNotAdmin = (Boolean) session.getAttribute(Constants.KEY_ERROR_MSG_NOT_ADMIN);
        }
        try {
            if (!isUpload && !isLogin && !isNotAdmin) {
                UserDTO user = (UserDTO) session.getAttribute(Constants.USER);
                if (user == null) {
                    if (request.getHeader(AJAX_HEAD) == null) {
                        response.sendRedirect(request.getContextPath());
                        return;
                    }
                    response.getWriter().print(NOT_LOGIN);
                    return;
                }
            }
            chain.doFilter(request, servletResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}

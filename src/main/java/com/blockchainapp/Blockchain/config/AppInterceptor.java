package com.blockchainapp.Blockchain.config;

import com.blockchainapp.Blockchain.Models.Users;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AppInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if(request.getRequestURI().startsWith("/app")){
            HttpSession session = request.getSession();

            String token = (String)session.getAttribute("token");
            Users user = (Users)session.getAttribute("user");

            if(token == null || user == null) {
//                RedirectAttributes redirectAttributes = new RedirectAttributes()
//
//                redirectAttributes.addFlashAttribute("error", "Unauthorized Access, please login")
//
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Inside PostHandle method");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("Inside afterCompletion method");
    }
}

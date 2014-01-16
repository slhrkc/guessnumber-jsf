/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.guessnumber.filters;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javaeetutorial.guessnumber.TestBean;
import javax.inject.Inject;

/**
 *
 * @author salih
 */
public class LoggedInFilter implements Filter {

    FilterConfig fc;
    
    @Inject
    private TestBean loginBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fc = filterConfig;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        

        //TestBean loginBean = (TestBean) ((HttpServletRequest) request).getSession().getAttribute("testBean");

        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully
        if (loginBean == null || !loginBean.isLoggedIn()) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
        } else if (((HttpServletRequest) request).getRequestURI().contains("login.xhtml")) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/secured/welcome.xhtml");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

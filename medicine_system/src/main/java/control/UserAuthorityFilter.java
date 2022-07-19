package control;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

import java.io.IOException;

@WebFilter(filterName = "UserAuthorityFilter", urlPatterns = "*.jsp", dispatcherTypes = {})
public class UserAuthorityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        System.out.println("UserAuthorityFilter doFilter");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user=(User)request.getSession().getAttribute("user");
        String url = request.getRequestURI();

        System.out.println("请求的url：" + url);
        /*登录页面不需要过滤*/

        int idx = url.lastIndexOf("/");
        String endWith = url.substring(idx + 1);

        //chain.doFilter(request, response);
        if (!endWith.equals("login.jsp")&&!endWith.equals("")) {
            /*不是登录页面  进行拦截处理*/

            System.out.println("不是登录页面，进行拦截处理");
            if(endWith.equals("main_manage_back.jsp")||endWith.equals("main_manage_front.jsp")){
            	if(user.getAuthority()==1) {
            		chain.doFilter(req, resp);
            	}else {
            		response.getWriter().print("<script language=javascript>window.parent.location.href='login.jsp'</script>");
            		//response.sendRedirect("login.jsp");
            		
            	}
            }
            else if(endWith.equals("branch_manage_front.jsp")||endWith.equals("branch_manage_back.jsp")){
            	if(user.getAuthority()==0) {
            		chain.doFilter(req, resp);
            	}else {
            		response.getWriter().print("<script language=javascript>window.parent.location.href='login.jsp'</script>");
//            		response.sendRedirect("login.jsp");
            		
            	}
            }
            else if(endWith.equals("medicine_purchase.jsp")){
            	if(user.getAuthority()==1) {
            		chain.doFilter(req, resp);
            	}else {
            		response.getWriter().print("<script language=javascript>window.parent.location.href='login.jsp'</script>");
            		//response.sendRedirect("login.jsp");
            		
            	}
            }
            else if(endWith.equals("add_user.jsp")||endWith.equals("user_manage.jsp")){
            	if(user.getAuthority()==1) {
            		chain.doFilter(req, resp);
            	}else {
            		response.getWriter().print("<script language=javascript>window.parent.location.href='login.jsp'</script>");
            		//response.sendRedirect("login.jsp");
            		
            	}
            }
            else if(endWith.equals("medicine_apply.jsp")){
            	if(user.getAuthority()==0) {
            		chain.doFilter(req, resp);
            	}else {
            		response.getWriter().print("<script language=javascript>window.parent.location.href='login.jsp'</script>");
            		//response.sendRedirect("login.jsp");
            		
            	}
            }
            
            else {
            	System.out.println("不属于拦截页面");
            	chain.doFilter(req, resp);
            }
            
            
            	
            
            
        } else {

            System.out.println("是登录页面，不进行拦截处理");
            chain.doFilter(req, resp);
        }


    }


    

    public void init(FilterConfig config) throws ServletException {
        System.out.println("UserAuthorityFilter  init");
    }

}


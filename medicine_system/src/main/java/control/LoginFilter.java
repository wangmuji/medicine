package control;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "*.jsp", dispatcherTypes = {})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        System.out.println("LoginFilter doFilter");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String url = request.getRequestURI();

        System.out.println("�����url��" + url);
        /*��¼ҳ�治��Ҫ����*/

        int idx = url.lastIndexOf("/");
        String endWith = url.substring(idx + 1);
        
        System.out.println("�����endWith��" + endWith);
        if (!endWith.equals("login.jsp")&&!endWith.equals("")) {
            /*���ǵ�¼ҳ��  �������ش���*/

            System.out.println("���ǵ�¼ҳ�棬�������ش���");

            if (!isLogin(request)) {
                System.out.println("û�е�¼�������˺����������ת����¼����");
                response.getWriter().print("<script language=javascript>window.parent.location.href='login.jsp'</script>");
                //response.sendRedirect("login.jsp");
            } else {
                System.out.println("�Ѿ���¼��������һ��");
                chain.doFilter(req, resp);
            }

        } else {

            System.out.println("�ǵ�¼ҳ�棬���������ش���");
            chain.doFilter(req, resp);
        }


    }


    private boolean isLogin(HttpServletRequest request) {

    	User user=(User)request.getSession().getAttribute("user");

        String account = "";
        String pwd = "";
        if(user!=null) {
        	return true;
        }
        return false;
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("LoginFilter  init");
    }

}


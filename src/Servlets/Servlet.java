package Servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Servlet" , urlPatterns = "/servlet_api")
public class Servlet extends HttpServlet {

    private int count = 0;

    private void requestIndex(HttpServletRequest request ,HttpServletResponse response) throws IOException {

        count++;
        HttpSession session = request.getSession();
        session.setAttribute("count",count);


        HashMap<String,String> cookiesValue = new HashMap<>();

       ServletContext context = request.getServletContext();
       context.setAttribute("age",2);

        Cookie [] cookies;
        cookies = request.getCookies();


        for(Cookie cookie : cookies){
            cookiesValue.put("Cookie Name = ",cookie.getName());
            cookiesValue.put("Cookie Comment = ",cookie.getComment());
            cookiesValue.put("Cookie Domain",cookie.getDomain());
            cookiesValue.put("Cookie Path",cookie.getPath());
            cookiesValue.put("Cookie Value",cookie.getValue());
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
            out.println("<head>");
                out.println("<title>Servlet</title>");
                out.println("<link rel='stylesheet' href='"+request.getContextPath()+"/css/style.css' />");
            out.println("</head>");
            out.println("<body>");
                out.println("<div>" +
                                 "<p>Wow It works from " + request.getContextPath() + " " + request.getAuthType() + " " + request.getRequestURI() + " " + request.getMethod() + " " + "</p>" +
                                 "<img id='image_the_first' src='"+request.getContextPath()+"/images/image_1.jpg' />"+
                                 "<p> p1 parameter = " + request.getParameter("p1") + ",p2 parameter = " + request.getParameter("p2") + "</p>"+
                                 "<p>" +session.getAttribute("count") + "</p>"+
                                 "<p>" +session.getId()+" "+ session.getLastAccessedTime() + " " +request.getCookies().length +"</p>"+
                           "</div>");

                for(Cookie cookie : cookies) {
                    out.println("<p> Cookie fields : " + cookie.getName() + "</p>" +
                                "<p> Cookie fields : " + cookie.getComment() + "</p>" +
                                "<p> Cookie fields : " + cookie.getDomain() + "</p>" +
                                "<p> Cookie fields : " + cookie.getPath() + "</p>" +
                                "<p> Cookie fields : " + cookie.getValue() + "</p>" +
                                "<p> Cookie fields : " + cookie.getVersion() + "</p>");
                }

                out.println("<h1>HashMap :</h1>");
                for(Map.Entry<String,String> cookie : cookiesValue.entrySet()){
                    out.println("<p>"+cookie.getKey() + " " +cookie.getValue() +"</p>");
                }
                /*
                response.sendError(1);
                */

            out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestIndex(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestIndex(request,response);
    }
}

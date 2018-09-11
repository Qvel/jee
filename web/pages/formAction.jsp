<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Action</title>
</head>
<body>
      <h1>hi form</h1>
     <div>
         <%= "Hi " + request.getParameter("name") %>
         <%
             ServletContext context = request.getServletContext();
             context.setAttribute("name",request.getParameter("name"));
         %>
         <div>
             <%= context.getAttribute("name")%>
         </div>
     </div>
</body>
</html>

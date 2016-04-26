
<%@page import="java.util.concurrent.atomic.*, com.test.ctos.AllInOneServlet"%>    
    
 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- 2.Declaration: member variable and method -->  
<%!   
  
    private AtomicInteger count = new AtomicInteger(1);  
    private ThreadLocal<Integer> curCountStorage = new ThreadLocal<Integer>();  
      
    private int getCount() {  
        int curCount = count.getAndIncrement();  
        curCountStorage.set(curCount);  
        return curCount;  
    }  
%> 

<!-- 3.JSP code & 4.Built-in object -->  
<%  
    Object curCount = session.getAttribute("count");  
    if (curCount == null) {  
        curCount = getCount();  
        session.setAttribute("count", curCount);  
    }  
      
    
%>  
  
<br> This is main.jsp. You're the <%=curCount%> visitor.  

</body>
</html>
package com.test.ctos;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

@WebServlet("/Log4jInit")  
public class Log4jInit extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 public void destroy() {
	  super.destroy();
	 }

	 public Log4jInit() {
	  super();
	 }

	 /**
	  * Initialization of the servlet. <br>
	  *
	  * @throws ServletException if an error occurs
	  */
	 public void init(ServletConfig config) throws ServletException {
		 
		 
	  String file =config.getInitParameter("log4j");//从web.xml配置读取，名字一定要和web.xml配置一致
	  System.out.println(file);
	  
	  
	  ServletContext sc = config.getServletContext(); 
	  if (file == null) {  
          System.err.println("*** 没有 log4j  初始化的文件, 所以使用 BasicConfigurator初始化");  
          BasicConfigurator.configure();  
      }else
      {
    	  String webAppPath = sc.getRealPath("/");  
          String log4jProp = webAppPath + file;  
          
          System.out.println("log4jProp="+log4jProp);
          
          File yoMamaYesThisSaysYoMama = new File(log4jProp);  
          if (yoMamaYesThisSaysYoMama.exists()) {  
              System.out.println("使用: " + log4jProp+"初始化日志设置信息");  
              PropertyConfigurator.configure(log4jProp);  
          } else {  
              System.err.println("*** " + log4jProp + " 文件没有找到， 所以使用 BasicConfigurator初始化");  
              BasicConfigurator.configure();  
          }
          
      }
	  
	  
	  super.init(config);  
	  
	   
	 }
}

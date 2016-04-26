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
		 
		 
	  String file =config.getInitParameter("log4j");//��web.xml���ö�ȡ������һ��Ҫ��web.xml����һ��
	  System.out.println(file);
	  
	  
	  ServletContext sc = config.getServletContext(); 
	  if (file == null) {  
          System.err.println("*** û�� log4j  ��ʼ�����ļ�, ����ʹ�� BasicConfigurator��ʼ��");  
          BasicConfigurator.configure();  
      }else
      {
    	  String webAppPath = sc.getRealPath("/");  
          String log4jProp = webAppPath + file;  
          
          System.out.println("log4jProp="+log4jProp);
          
          File yoMamaYesThisSaysYoMama = new File(log4jProp);  
          if (yoMamaYesThisSaysYoMama.exists()) {  
              System.out.println("ʹ��: " + log4jProp+"��ʼ����־������Ϣ");  
              PropertyConfigurator.configure(log4jProp);  
          } else {  
              System.err.println("*** " + log4jProp + " �ļ�û���ҵ��� ����ʹ�� BasicConfigurator��ʼ��");  
              BasicConfigurator.configure();  
          }
          
      }
	  
	  
	  super.init(config);  
	  
	   
	 }
}

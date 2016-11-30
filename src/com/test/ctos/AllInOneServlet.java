package com.test.ctos;

//http://localhost:8080/ctos/j2ee
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class AllInOneServlet extends HttpServlet {
	Map<String, Integer> addrMap = null;
	Set<Entry<String, Integer>> addrset = null;
	StringBuffer pageBuf = null;

	public AllInOneServlet() {

	}

	@Override
	public void init() {
		addrMap = new HashMap<String, Integer>();
		
	}

	@Override
	public void destroy() {

		addrMap = null;
		addrset = null;
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Logger logger = Logger.getLogger("pay-log");
		// 2.Set response header
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");

		// 3.Get useful info from TCP & HTTP header
		Integer count = 0;
		String remoteAddr = getIpAddr(request);// ���ط�������Ŀͻ�����������
		
		System.out.println("remoteAddr===" +remoteAddr);
		
		if (addrMap.containsKey(remoteAddr)) {
			count = addrMap.get(remoteAddr);
		}
		count++;
		addrMap.put(remoteAddr, count);
		// 4.Print html(out is built-in object in JSP)

		addrset = addrMap.entrySet();
		pageBuf = new StringBuffer(); 
		 

		for (Entry<String, Integer> entry : addrset) {

			 
			pageBuf.append(entry.getKey() + " ip access number is : "
					+ entry.getValue());
			 		 
		}
		
		//����������
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();			
			pageBuf.append(paramName);
			pageBuf.append("=");
			pageBuf.append( request.getParameter(paramName));
			pageBuf.append("<br>");
		}
		
		
		
		logger.debug(pageBuf.toString());		

		DataOutputStream out = new DataOutputStream(response.getOutputStream());
		//out.writeUTF("<br>");
		
		out.writeUTF(pageBuf.toString());
		
		out.close(); 

	}
	
	
 
	

	private String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// ��������ȡ�������õ�IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// ����ͨ�����������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

}

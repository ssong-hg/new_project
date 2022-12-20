package com.example.project;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Alert {

	public void alertAndBack(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter w = response.getWriter();
			w.write("<script>alert('"+msg+"');history.go(-1);</script>");
			w.flush();
			w.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

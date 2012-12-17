/**
 * 
 */
package br.com.sixtec.webmedia.rest;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * @author maicon
 *
 */
public class RestServlet extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject o = new JSONObject();
		o.put("id", 1);
		o.put("nome", "Nelson");
		o.put("outro", "info1");
		
		OutputStream os = response.getOutputStream();  
	    os.write(o.toString().getBytes());
	    os.flush(); 
		
	}

}

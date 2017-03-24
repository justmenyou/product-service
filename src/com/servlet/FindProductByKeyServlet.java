package com.servlet;

import java.io.IOException;
import javax.servlet.http.*;
import org.codehaus.jackson.map.ObjectMapper;

import com.dao.*;
import com.entity.*;

@SuppressWarnings("serial")
public class FindProductByKeyServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp)throws IOException {

	
		try {
			
			ProductDAO dao = new ProductDAO();
				Product product = dao.findProductByKey(req.getParameter("key"));
			dao.closeEntityManager();

			ObjectMapper mapper = new ObjectMapper();
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), product);
			resp.getOutputStream().flush();

		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}

	}

}
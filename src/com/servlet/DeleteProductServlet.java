package com.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.ProductDAO;


public class DeleteProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			ProductDAO dao = new ProductDAO();
			String keyString = req.getParameter("key");
			dao.deleteProduct(keyString);
			dao.closeEntityManager();
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");
			resp.setContentType("application/json");
			resp.getWriter().print(1);
			resp.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}
}
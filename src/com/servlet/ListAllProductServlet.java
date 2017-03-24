package com.servlet;



import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import com.dao.ProductDAO;
import com.entity.Product;

public class ListAllProductServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		try {
			ProductDAO facade = new ProductDAO();
			List<Product> productList = facade.getAllProduct();
			facade.closeEntityManager();
			
			ObjectMapper mapper = new ObjectMapper();
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), productList);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}
}

package com.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import com.dao.ProductDAO;
import com.entity.Product;

public class SaveProductServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ProductDAO facade = new ProductDAO();
			Product prod = mapper.readValue(req.getReader(), Product.class);
			if (prod != null)
				facade.saveProduct(prod);
			facade.closeEntityManager();
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
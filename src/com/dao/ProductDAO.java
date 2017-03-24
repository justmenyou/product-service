package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.entity.Product;


public class ProductDAO {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");

	private EntityManager em = null;

	public ProductDAO() {
		em = emfInstance.createEntityManager();
	}

	public List<Product> getAllProduct() {

		List<Product> products = null;

		try {
			Query q = em.createQuery("select p from Product p");
			products = q.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;

	}

	public Product findProductByKey(String key) {

		Product product = null;

		try {
			Query q = em.createQuery("select u from Product u where u.key = :key");
			q.setParameter("key", key);
			product = (Product) q.getSingleResult();

		} catch (NoResultException e) {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return product;

	}


	public void saveProduct(Product product) {

		try {
			em.persist(product);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateProduct(Product product) {

		try {
			em.merge(product);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteProduct(String key) {

		Product product = this.findProductByKey(key);
		if (product != null)
			em.remove(product);
	}

	public void closeEntityManager() {

		if (em != null)

			em.close();

	}

}

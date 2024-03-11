package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Repository
public class ProductDao implements BaseDao<Product> {
	@Autowired
	ProductRepository repository;

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Product findById(Integer id) throws DataNotFoundException {
		return repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(Product product) {
		this.repository.save(product);
	}

	@Override
	public void deleteById(Integer id) {
		try {
			Product product = this.findById(id);
			this.repository.deleteById(product.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}
}

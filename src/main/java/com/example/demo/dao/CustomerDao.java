package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Repository
public class CustomerDao implements BaseDao<Customer> {
	@Autowired
	CustomerRepository repository;

	@Override
	public List<Customer> findAll() {
		return repository.findAll();
	}

	@Override
	public Customer findById(Integer id) throws DataNotFoundException {
		return repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(Customer customer) {
		this.repository.save(customer);
	}

	@Override
	public void deleteById(Integer id) {
		try {
			Customer customer = this.findById(id);
			this.repository.deleteById(customer.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}
}

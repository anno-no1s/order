package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;

@Repository
public class OrderDetailDao implements BaseDao<OrderDetail> {
	@Autowired
	OrderDetailRepository repository;

	@Override
	public List<OrderDetail> findAll() {
		return repository.findAll();
	}

	@Override
	public OrderDetail findById(Integer id) throws DataNotFoundException {
		return repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(OrderDetail orderDetail) {
		this.repository.save(orderDetail);
	}

	@Override
	public void deleteById(Integer id) {
		try {
			OrderDetail orderDetail = this.findById(id);
			this.repository.deleteById(orderDetail.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}
}

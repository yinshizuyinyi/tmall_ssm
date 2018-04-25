package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Property;

public interface PropertyService {
	void add(Property c);

	void delete(int id);

	void updeate(Property c);

	Property get(int id);

	List<Property> list(int cid);

	void deleteByCategory(int cid);

}

package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.how2java.tmall.mapper.PropertyMapper;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyExample;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.service.PropertyValueService;

@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	PropertyMapper propertyMapper;

	@Autowired
	PropertyValueService propertyValueService;

	@Override
	public void add(Property c) {
		propertyMapper.insert(c);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
	public void delete(int id) {
		propertyValueService.deleteByProperty(id);
		propertyMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void updeate(Property p) {
		propertyMapper.updateByPrimaryKeySelective(p);
	}

	@Override
	public Property get(int id) {
		return propertyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Property> list(int cid) {
		PropertyExample example = new PropertyExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("id desc");
		return propertyMapper.selectByExample(example);
	}

	@Override
	public void deleteByCategory(int cid) {
		List<Property> pts = list(cid);
		for (Property property : pts) {
			delete(property.getId());
		}
	}

}

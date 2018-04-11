package com.how2java.tmall.service.impl;

import java.beans.PropertyEditor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.PropertyMapper;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyExample;
import com.how2java.tmall.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	PropertyMapper propertyMapper;

	@Override
	public void add(Property c) {
		propertyMapper.insert(c);
	}

	@Override
	public void delete(int id) {
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

}

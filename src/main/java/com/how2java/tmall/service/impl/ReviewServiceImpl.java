package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.ReviewMapper;
import com.how2java.tmall.pojo.Review;
import com.how2java.tmall.pojo.ReviewExample;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.ReviewService;
import com.how2java.tmall.service.UserService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	UserService userService;

	@Override
	public void add(Review c) {
		reviewMapper.insert(c);
	}

	@Override
	public void delete(int id) {
		reviewMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Review c) {
		reviewMapper.updateByPrimaryKeySelective(c);
	}

	@Override
	public Review get(int id) {
		return reviewMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Review> list(int pid) {
		ReviewExample example = new ReviewExample();
		example.createCriteria().andPidEqualTo(pid);
		example.setOrderByClause("id desc");

		List<Review> reviews = reviewMapper.selectByExample(example);
		setUser(reviews);
		return reviews;
	}

	private void setUser(List<Review> reviews) {
		for (Review review : reviews) {
			setUser(review);
		}

	}

	private void setUser(Review review) {
		int uid = review.getUid();
		User user = userService.get(uid);
		review.setUser(user);
	}

	@Override
	public int getCount(int pid) {
		return list(pid).size();
	}

	@Override
	public void deleteByProduct(int pid) {
		List<Review> reviews= listByProduct(pid);
		for (Review review : reviews) {
			delete(review.getId());
		}
		
	}

	private List<Review> listByProduct(int pid) {
		ReviewExample example = new ReviewExample();
		example.createCriteria().andPidEqualTo(pid);
		return reviewMapper.selectByExample(example);
	}

}

package com.how2java.tmall.mapper;
 
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.util.Page;

import java.util.List;
 
public interface CategoryMapper {
    List<Category> list(Page page);
    
    public int total();
    
    public void add(Category category);
    
    public void delete(int id);
    
    public Category get(int id);
}
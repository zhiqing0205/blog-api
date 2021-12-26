package com.ziuch.blog.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziuch.blog.api.domain.Category;
import com.ziuch.blog.api.domain.CategoryExample;
import com.ziuch.blog.api.mapper.CategoryMapper;
import com.ziuch.blog.api.req.CategoryQueryReq;
import com.ziuch.blog.api.req.CategorySaveReq;
import com.ziuch.blog.api.resp.CategoryQueryResp;
import com.ziuch.blog.api.resp.PageResp;
import com.ziuch.blog.api.util.CopyUtil;
import com.ziuch.blog.api.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    public List<CategoryQueryResp> all(){

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        //列表copy
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);
        return list;
    }

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req){

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();

        if(!ObjectUtils.isEmpty(req.getName()))
            criteria.andNameLike("%" + req.getName() + "%");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> info = new PageInfo<>(categoryList);
        LOG.info("总行数：{}", info.getTotal());
        LOG.info("总页数：{}", info.getPages());

//        List<CategoryResp> categoryRespList = new ArrayList<>();

//        for(Category category : list) {
////            CategoryResp categoryResp = new CategoryResp();
////            BeanUtils.copyProperties(category, categoryResp);
//            //对象copy
//            CategoryResp categoryResp = CopyUtil.copy(category, CategoryResp.class);
//            categoryRespList.add(categoryResp);
//        }

        //列表copy
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        PageResp<CategoryQueryResp>  pageResp = new PageResp();
        pageResp.setTotal(info.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);

        if(ObjectUtils.isEmpty(req.getId())) {
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }
        else {
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }
}

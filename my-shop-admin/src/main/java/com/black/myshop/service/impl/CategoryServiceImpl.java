package com.black.myshop.service.impl;

import com.black.myshop.dao.CategoryDao;
import com.black.myshop.entity.Category;
import com.black.myshop.entity.Result;
import com.black.myshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryDao> implements CategoryService {


    @Autowired
    private CategoryDao categoryDao;

    /**
     * 查找所有子节点
     * @param category
     * @return
     */
    @Override
    public Result getChildren(Category category) {
        List<Category> list = categoryDao.getChildren(category);
        if(list!=null){
            Result result = Result.success("", list);
            return result;
        }else{
            Result result = Result.fail("查找失败");
            return result;
        }
    }

    /**
     * 修改节点
     * @param category
     * @return
     */
    @Override
    @Transactional
    public Result update(Category category) {
        //获取到修改前的category,他的父类还没改变
        Category preCategory = categoryDao.getById(category);
        Category preParent = categoryDao.getById(preCategory.getParent());
        //执行修改操作
        Result result = super.update(category);
        //如果此时preParent没有子类了
        if(categoryDao.getChildren(preParent)==null){
            setIsParentFalse(preParent.getId());
        }

        //而你现在添加子类的父目录,不管之前他的isparent怎么样,都要设置成1
        Category nowPatent = categoryDao.getById(category.getParent());
        setIsParentTrue(nowPatent.getId());
        return result;
    }

    /**
     * 添加子节点
     * @param category
     * @return
     */
    @Override
    @Transactional
    public Result save(Category category) {
        setIsParentTrue(category.getParent().getId());
        return super.save(category);
    }

    /**
     * 删除非父节点
     * @param category
     * @return
     */
    @Override
    @Transactional
    public Result delete(Category category) {
        List<Category> list = categoryDao.getChildren(category);
        if(list.isEmpty()){
            //获取到修改前的category,他的父类还没改变
            Category preCategory = categoryDao.getById(category);
            Category preParent = categoryDao.getById(preCategory.getParent());
            //执行修改操作
            Result result = super.delete(category);
            //如果此时preParent没有子类了
            if(categoryDao.getChildren(preParent)==null){
                setIsParentFalse(preParent.getId());
            }
        return result;

        }else{
            return Result.fail("不能删除拥有子节点的节点!!");
        }
    }

    /**
     * 设置为是父节点
     * @param id
     */
    @Override
    public void setIsParentTrue(Integer id) {
        categoryDao.setIsParentTrue(id);
    }

    /**
     * 设置为不是父节点
     * @param id
     */
    @Override
    public void setIsParentFalse(Integer id) {
        categoryDao.setIsParentFalse(id);
    }

    /**
     * 获取树表
     * @param category
     * @return
     */
    @Override
    public Result getCategoryTreeTable(Category category) {
        List<Category> list = categoryDao.getTreeTable(category);
        List newList = getSub(list, list.get(0));
        Result result=null;
        if(newList.isEmpty()){
            result = Result.fail("查询失败!!");
        }else{
            result = Result.success("", newList);
        }
        return result;
    }
    /**
     * 将集合排序,为树表做准备
     */
    public List getSub(List<Category> srceList,Category category){
        List<Category> desList = new ArrayList<>();
        desList.add(category);
        for(Category cat: srceList){
            if(cat.getParent().getId()==category.getId()){
                List sortList = getSub(srceList, cat);
                desList.addAll(sortList);
            }
       }
        return desList;
    }
}

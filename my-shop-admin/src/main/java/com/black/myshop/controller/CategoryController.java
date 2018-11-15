package com.black.myshop.controller;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.PageResult;
import com.black.myshop.entity.Result;
import com.black.myshop.entity.Category;
import com.black.myshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取category列表
     * @param category
     * @param pageResult
     * @param model
     * @param result1
     * @return
     */
    @RequestMapping("/getCategoryPage")
    public String getCategoryPage(Category category, PageResult<Category> pageResult, Model model, @ModelAttribute(name = "result") Result result1) {
        category.setPageResult(pageResult);
        Result result = categoryService.findPage(category);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            result.setMessage(result1.getMessage());
            if (result1.getStatus() == Result.ERROR_STATUS) {
                result.setStatus(Result.ERROR_STATUS);
            }
            model.addAttribute("result", result);
            return "category/list";
        } else {
            model.addAttribute("result", result);
            return "category/list";
        }
    }




    /**
     * 获取form页面
     *
     * @return
     */
    @RequestMapping(value = "/getForm", method = RequestMethod.GET)
    public String getForm(Category category, Model model) {
        if (category.getId() == null) {
            return "category/form";
        } else {
            return "redirect:/category/getCategoryById?id=" + category.getId();
        }
    }

    /**
     * 将用户数据存入数据库
     *
     * @param category
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String saveCategory(Category category, Model model, RedirectAttributes redirectAttributes) {
        Result result = categoryService.save(category);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            result.setMessage("添加成功!");
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:/category/getCategoryPage?current=1&pageSize=10";
        } else {
            model.addAttribute("result", result);
            return "category/form";
        }
    }

    /**
     * 根据ID获取Category信息
     *
     * @param category
     * @param model
     * @return
     */

    @RequestMapping(value = "/getCategoryById", method = RequestMethod.GET)
    public String getCategoryById(@ModelAttribute(value = "category") Category category, Model model, RedirectAttributes redirectAttributes) {
        Result result = categoryService.getById(category);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            model.addAttribute("result", result);
            return "category/form";
        } else {
            redirectAttributes.addAttribute("msg", result.getMessage());
            return "redirect:/category/getCategoryPage";
        }
    }

    /**
     * 编辑内容信息
     */
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute(value = "category") Category category, Model model, RedirectAttributes redirectAttributes) {
        Result result = categoryService.update(category);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            redirectAttributes.addAttribute("msg", "更新成功!");
            return "redirect:/category/getCategoryPage";
        } else {
            model.addAttribute("result", result);
            return "category/form";
        }
    }

    /**
     * 删除内容记录
     *
     * @param category
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(Category category, RedirectAttributes redirectAttributes, Model model) {
        Result result = categoryService.delete(category);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            result.setMessage("删除成功");
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:/category/getCategoryPage";
        } else {
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:/category/getCategoryPage";
        }
    }

    /**
     * 获取到树数据的方法
     *
     * @param category
     * @return
     */

    @RequestMapping(value = "/treeData", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String treeData(Category category) {
        Result result = null;
        if (category.getId() == null) {
            Category category1 = new Category();
            category1.setId(0);
            Category category2 = new Category();
            category2.setParent(category1);
            result = categoryService.getChildren(category1);

        } else {
            result = categoryService.getChildren(category);
        }
        List<Category> list = (List<Category>) result.getData();
        String s = JSON.toJSONString(list);
        return s;

    }

    /**
     * 表单form是公用的,url写的是/formDispatcher,根据是否有ID值,来这里转发到不同的操作
     *
     * @param category
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/formDispatcher")
    public String formDispatcher(Category category, RedirectAttributes redirectAttributes) {
        Integer id = category.getId();
        if (id == null) {
            //新建
            redirectAttributes.addFlashAttribute("category", category);
            return "redirect;/category/saveCategory";
        } else {
            //修改
            redirectAttributes.addFlashAttribute("category", category);
            return "redirect;/category/updateCategory";
        }
    }



}

package com.black.myshop.controller;

import com.alibaba.fastjson.JSON;
import com.black.myshop.entity.Content;
import com.black.myshop.entity.PageResult;
import com.black.myshop.entity.Result;
import com.black.myshop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/getContentPage")
    public String getContentPage(Content content, PageResult<Content> pageResult, Model model, @ModelAttribute("result") Result result1){
        content.setPageResult(pageResult);
        Result result = contentService.findPage(content);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            result.setMessage(result1.getMessage());
            if (result1.getStatus() == Result.ERROR_STATUS) {
                result.setStatus(Result.ERROR_STATUS);
            }
            model.addAttribute("result", result);
            return "content/list";
        } else {
            model.addAttribute("result", result);
            return "content/list";
        }
    }


    /**
     * 获取form页面
     *
     * @return
     */
    @RequestMapping(value = "/getForm", method = RequestMethod.GET)
    public String getForm(Content content, Model model) {
        if (content.getId() == null) {
            return "content/form";
        } else {
            return "redirect:/content/getContentById?id=" + content.getId();
        }
    }

    /**
     * 将用户数据存入数据库
     *
     * @param content
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/saveContent", method = RequestMethod.POST)
    public String saveCategory(Content content, Model model, RedirectAttributes redirectAttributes) {
        String detail = content.getDetail();
        try {
            UUID uuid = UUID.randomUUID();
            FileOutputStream fos = new FileOutputStream("E:/data/content/"+uuid.toString());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(detail.getBytes());
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        content.setDetail("");
        Result result = contentService.save(content);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            result.setMessage("添加成功!");
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:/content/getContentPage?current=1&pageSize=10";
        } else {
            model.addAttribute("result", result);
            return "content/form";
        }
    }

    /**
     * 根据ID获取Category信息
     *
     * @param content
     * @param model
     * @return
     */

    @RequestMapping(value = "/getContentById", method = RequestMethod.GET)
    public String getContentById(@ModelAttribute(value = "content") Content content, Model model, RedirectAttributes redirectAttributes) {
        Result result = contentService.getById(content);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            model.addAttribute("result", result);
            return "content/form";
        } else {
            redirectAttributes.addAttribute("msg", result.getMessage());
            return "redirect:/content/getContentPage";
        }
    }

    /**
     * 编辑内容信息
     */
    @RequestMapping(value = "/updateContent", method = RequestMethod.POST)
    public String updateContent(@ModelAttribute(value = "content") Content content, Model model, RedirectAttributes redirectAttributes) {
        Result result = contentService.update(content);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            result.setMessage("更新成功!");
            redirectAttributes.addFlashAttribute("result",result);
            return "redirect:/content/getContentPage";
        } else {
            model.addAttribute("result", result);
            return "content/form";
        }
    }

    /**
     * 删除内容记录
     *
     * @param content
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteContent", method = RequestMethod.GET)
    public String deleteCategory(Content content, RedirectAttributes redirectAttributes, Model model) {
        Result result = contentService.delete(content);
        if (result.getStatus() == Result.SUCCESS_STATUS) {
            result.setMessage("删除成功");
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:/content/getContentPage";
        } else {
            redirectAttributes.addFlashAttribute("result", result);
            return "redirect:/content/getContentPage";
        }
    }


    /**
     * 表单form是公用的,url写的是/formDispatcher,根据是否有ID值,来这里转发到不同的操作
     *
     * @param content
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/formDispatcher")
    public String formDispatcher(Content content, RedirectAttributes redirectAttributes) {
        Integer id = content.getId();
        if (id == null) {
            //新建
            redirectAttributes.addFlashAttribute("content", content);
            return "redirect;/content/saveContent";
        } else {
            //修改
            redirectAttributes.addFlashAttribute("content", content);
            return "redirect;/content/updateContent";
        }
    }


    @RequestMapping(value = "/upload",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        //后缀名
        String suffix = fileName.substring(index);
        String nFileName = UUID.randomUUID().toString().replaceAll("-","") + suffix;
        String path = "E:\\Java\\1803_IDEA\\my-shop\\upload\\"+nFileName;
        file.transferTo(new File(path));
         Map map =new HashMap();
         map.put("errno", 0);
        ArrayList<String> list = new ArrayList<>();
        list.add("http://localhost:8080/content/getPic?fileName="+nFileName);
        map.put("data",list);
       String x= JSON.toJSONString(map);
        return x;
    }

    @RequestMapping(value = "/getPic" )
    public void getPic(String fileName, HttpServletResponse response){
        int index = fileName.lastIndexOf(".");
        //后缀名
        String suffix = fileName.substring(index);
        if(".jpg".equals(suffix)){
            response.setContentType("image/jpeg");
        }else if(".png".equals(suffix)){
            response.setContentType("image/png");
        }
        FileInputStream is=null;
        ServletOutputStream os=null;
        try {
            is = new FileInputStream("E:/Java/1803_IDEA/my-shop/upload/"+fileName);
            os = response.getOutputStream();
            byte[] bytes=new byte[512];
            int len=0;
            while((len=is.read(bytes))>0){
                os.write(bytes,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null){
                     is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

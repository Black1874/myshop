package com.black.myshop.webSupport;


import lombok.Data;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.io.Writer;

@Data
public class PageTag extends BodyTagSupport {
    //    在jsp中通过引用标签传入参数
    private int count;
    private int countPerPage;
    private int current;

    @Override
    public int doStartTag() throws JspException {

        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        try {
//        计算总页数
            int pageCount;
            if (count % countPerPage != 0) {
                pageCount = count / countPerPage+1;
            } else {
                pageCount = count / countPerPage;
            }


//        页面导航显示的页面信息

            int start = current - 2 <=0 ? 1 : current - 2;
            int end = current + 2 >=pageCount ? pageCount : current + 2;

            Writer out = pageContext.getOut();
            int prePage=current-1<1?1:current-1;
            int nextPage=current+1>pageCount?pageCount:current+1;
            out.write("<li><a href=\"javaScript:page(1)\"> 首页 </li>");

            out.write("<li><a href=\"javaScript:page("+(prePage)+")\"> << </li>");

            for (int i = start; i <= end; i++) {
                if(i==current){
                    out.write("<li><a href=\"javaScript:void(0)\">" + i + "</li>");
                }else{
                    out.write("<li><a href=\"javaScript:page("+i+")\">" + i + "</li>");
                }
            }

                out.write("<li><a href=\"javaScript:page("+(nextPage)+")\"> >> </li>");

            out.write("<li><a href=\"javaScript:page("+pageCount+")\"> 尾页 </li>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.SKIP_BODY;
    }
}

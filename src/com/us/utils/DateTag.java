package com.us.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * ����ҳ�� jstlʱ���ʽ��
 *
 * @Title: JSTLDateUtils.java
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author eleven.song(����)
 * @date 2014 -3- 4 ����06:28:51
 */
public class DateTag extends TagSupport {

    private static final long serialVersionUID = 6464168398214506236L;
   
    private String value;
   
    @Override
    public int doStartTag() throws JspException {
        String vv = ""+value ;
        long time = Long.valueOf(vv);
        Calendar c = Calendar. getInstance();
        c.setTimeInMillis(time);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String s = dateformat.format(c.getTime());
        try {
            pageContext.getOut().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super .doStartTag();
    }

    public void setValue(String value) {
        this.value = value;
    }

}
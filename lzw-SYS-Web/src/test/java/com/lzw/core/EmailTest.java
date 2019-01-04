package com.lzw.core;

import com.csource.fastdfs.FileInfo;
import com.lzw.core.support.email.Email;
import com.lzw.core.util.EmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@ComponentScan
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:Spring-config.xml"})
public class EmailTest {
    @Test
    public void sendEmail() {
        Email email = new Email("lzw@126.com", "先生，恭喜您",
            "您好：<br/><div style='text-indent:2em'>很高兴认识您！</div>" + "<div style='text-indent:2em'>有任务疑问请和我联系！</div>");
        EmailUtil.sendEmail(email);
    }

    @Test
    public void listtoMap() {
        List<Email> list1= new ArrayList<>();
        List<Email> list2= new ArrayList<>();
        for(int i=0; i<500000; i++){
            Email em1 = new Email();
            em1.setName(i+"");
            Email em2 = new Email();
            em2.setName(i+"");
            em2.setBody("2Tom"+i);
            list1.add(em1);
            list2.add(em2);
        }

        long t1=System.nanoTime();
        Map<String, String> map1= new HashMap<String, String>();
        for(Email st:list2){
            map1.put(st.getName(), st.getBody());
        }
        long t2=System.nanoTime();
        System.out.println("线性表中顺序查找:"+(t2-t1));

        t1=System.nanoTime();
        Map<String, String> map2 = list2.stream().collect(Collectors.toMap(Email::getName, Email :: getBody));
        t2=System.nanoTime();
        System.out.println("散列表中查找:"+(t2-t1));

//        //线性表中顺序查找:
//        long t1=System.nanoTime();
//        for(Email st1:list1){
//            for(Email st2:list2){
//                if(st1.getName().equals(st2.getName())){
//                    st1.setBody(st2.getBody());
//                    break;
//                }
//            }
//        }
//        long t2=System.nanoTime();
//        System.out.println("线性表中顺序查找:"+(t2-t1));  //137259306
//
//        //散列表中的散列查找:
//        t1=System.nanoTime();
//        for(Email st1:list1){
//            st1.setBody(map.get(st1.getName()));
//        }
//        t2=System.nanoTime();
//        System.out.println("散列表中查找:"+(t2-t1));  //539351

    }
}

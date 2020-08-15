package com.org.example.springboot.web;

import com.org.example.springboot.domain.MyData;
import com.org.example.springboot.domain.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Controller
public class HelloController {

    @Autowired
    MyDataRepository myDataRepository;


    @PostConstruct
    public void init(){
        MyData d1=new MyData();
        MyData d2=new MyData();
        d1.builder().name("Kim").age(24).mail("nugu@naver.com").memo("Hi~").build();
        d2.builder().name("Shin").age(23).mail("wlgp2500@naver.com").memo("Hello~").build();
        myDataRepository.saveAndFlush(d1);
        myDataRepository.saveAndFlush(d2);
    }
    @GetMapping("/")
    public ModelAndView index(@ModelAttribute("forModel") MyData myData, ModelAndView mav){
        mav.setViewName("index");
        mav.addObject("msg","this is sample content");
        Iterable<MyData> list=myDataRepository.findAll();
        mav.addObject("datalist",list);
        return mav;
    }
    @PostMapping("/")
    @Transactional(readOnly = false)
    public ModelAndView form(@ModelAttribute("forModel") MyData myData, ModelAndView mav){
        myDataRepository.saveAndFlush(myData);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{dataNo}")
    public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int dataNo,ModelAndView mav){
        mav.setViewName("edit");
        mav.addObject("title","Edit MyData");
        Optional<MyData> data = myDataRepository.findById((long)dataNo);
        mav.addObject("forModel",data);
        return mav;
    }
    @PostMapping("/edit")
    @Transactional(readOnly = false)
    public ModelAndView update(@ModelAttribute MyData mydata,ModelAndView mav){
        myDataRepository.saveAndFlush(mydata);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int dataNo,ModelAndView mav){
        mav.setViewName("delete");
        mav.addObject("title","delete mydata.");
        Optional<MyData> data = myDataRepository.findById((long)dataNo);
        mav.addObject("forModel",data);
        return mav;
    }
    @PostMapping("/delete")
    @Transactional(readOnly = false)
    public ModelAndView remove(@RequestParam long dataNo,ModelAndView mav){
        myDataRepository.deleteById(dataNo);
        return new ModelAndView("redirect:/");
    }
}

package com.org.example.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping(value="/",method= RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("msg","이름을 적어서 전송해주세요");
        return "index";
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String send(@RequestParam("text1")String str, Model model){
        model.addAttribute("msg","안녕하세요!"+str+"님!");
        model.addAttribute("value",str);
        return "index";
    }
}

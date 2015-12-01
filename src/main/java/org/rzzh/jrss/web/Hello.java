package org.rzzh.jrss.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raozhanghui on 15.12.1.
 */
@Controller
@RequestMapping(value = "/")
public class Hello {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public Map<String,Object> hello(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("hello",name);
        return map;
    }

    @RequestMapping
    @ResponseBody
    public Map<String,Object> auth(){
        Map<String, Object> map = new HashMap<>();
        map.put("api_version",1);
        map.put("auth",1);
        return map;
    }

    public void groups(){
        Map<String, Object> map = new HashMap<>();

        
        Map<String, Object> group = new HashMap<>();

    }
}

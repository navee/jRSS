package org.rzzh.jrss.controller;

import org.apache.http.HttpRequest;
import org.rzzh.jrss.vo.Group;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by raozhanghui on 15/1/3.
 */
@Controller
@RequestMapping(value = "/")
public class TestController {
    @RequestMapping(params = "api")
    @ResponseBody
    public Map api(@RequestParam(value = "api_key")String apiKey,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String, Object>();
        System.out.println("apiKey:"+apiKey);
        result.put("api_version",0);
        result.put("auth",true);
        result.put("last_refreshed_on_time",new Date());
        return result;
    }

    @RequestMapping(params = {"api","groups"})
    @ResponseBody
    public Map groups(){
        System.out.println("groups");
        List<Group> groups = new ArrayList<Group>();
        Group g1 = new Group();
        g1.setTitle("title1");
        g1.setId(1);
        Group g2 = new Group();
        g2.setId(2);
        g2.setTitle("title2");
        groups.add(g1);
        groups.add(g2);

        List<Map<String,Object>> feedsGroupsList = new ArrayList<Map<String,Object>>();
        Map<String,Object> feedsGroups1 = new HashMap<String, Object>();
        feedsGroups1.put("group_id", 1);
        feedsGroups1.put("feed_ids", "3,4,5");

        Map<String,Object> feedsGroups2 = new HashMap<String, Object>();
        feedsGroups2.put("group_id", 2);
        feedsGroups2.put("feed_ids", "6,7");

        feedsGroupsList.add(feedsGroups1);
        feedsGroupsList.add(feedsGroups2);

        Map<String,Object> result = new HashMap<String, Object>();
        result.put("groups",groups);
        result.put("feeds_groups",feedsGroupsList);

        result.put("last_refreshed_on_time",new Date());
        return result;
    }

    @RequestMapping(params = {"api","feeds"})
    @ResponseBody
    public void feeds(){
        System.out.println("feeds");
    }

    @RequestMapping(params = {"api","favicons"})
    @ResponseBody
    public void favicons(){
        System.out.println("favicons");
    }

    @RequestMapping(params = {"api","links"})
    @ResponseBody
    public void links(){
        System.out.println("links");
    }

    @RequestMapping(params = {"api","unread_item_ids"})
    @ResponseBody
    public Map unreadItems(){
        System.out.println("unread_item_ids");
        Map<String,Object> result = new HashMap<String, Object>();
        List<Long> list = new ArrayList<Long>();
        list.add(5L);
        list.add(6L);
        result.put("unread_item_ids","10,11");
        result.put("last_refreshed_on_time",new Date());
        return result;
    }
}

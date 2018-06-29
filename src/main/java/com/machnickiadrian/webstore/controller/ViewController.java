package com.machnickiadrian.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Adrian Machnicki
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping(value = {"/navbar"},
            method = {RequestMethod.GET, RequestMethod.POST})
    public String getNavbar() {
        return "fragments/navbar";
    }

    @RequestMapping(value = {"/admin-sidebar"},
            method = {RequestMethod.GET, RequestMethod.POST})
    public String getAdminSidebar() {
        return "fragments/admin-sidebar";
    }

    @RequestMapping(value = {"/user-sidebar"},
            method = {RequestMethod.GET, RequestMethod.POST})
    public String getUserSidebar() {
        return "fragments/user-sidebar";
    }

}
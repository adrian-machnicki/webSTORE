package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.enums.AdminTab;
import com.machnickiadrian.webstore.user.UserService;
import com.machnickiadrian.webstore.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Adrian Machnicki
 */
@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {

    private static final String ADMIN_TAB = "adminTab";
    private final UserService userService;

    @Autowired
    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsersManagement(Model model, @RequestParam(defaultValue = "") String search) {
        List<UserDto> users;

        if (search.equals(""))
            users = userService.findAll();
        else
            users = userService.search(search);

        model.addAttribute("users", users);
        model.addAttribute(ADMIN_TAB, AdminTab.USERS);
        return "admin/users";
    }

    @GetMapping("/block-unblock")
    public String blockOrUnblockUser(@RequestParam Long id) {
        userService.setEnabledDisabled(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/remove")
    public String removeUser(@RequestParam Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

}
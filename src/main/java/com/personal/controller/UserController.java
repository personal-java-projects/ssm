package com.personal.controller;

import com.personal.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.personal.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryUserPage")
    @ResponseBody
    public List<User> queryUserPage(Integer page) {
        int pageNow = page == null ? 1 : page;
        int pageSize = 5;
        int startRows = pageSize*(pageNow-1);
        return userService.queryUserPage(startRows);
    }

    @ApiOperation(value = "所有用户列表")
    @RequestMapping("/selectUserPage")
    @ResponseBody
    public R selectUserPage(String userName, String userSex, Integer page) {
        int pageNow = page == null ? 1 : page;
        int pageSize = 5;
        int startRows = pageSize*(pageNow-1);

        List<User> userList = userService.selectUserPage(userName, userSex, startRows);

        System.out.println("测试 " + R.ok().data("list", userList));

        return R.ok().data("list", userList);
    }


    @RequestMapping("/getRowCount")
    @ResponseBody
    public R getRowCount(String userName, String userSex) {
        System.out.println("测试1111 " + userService.getRowCount(userName, userSex));
        Integer total = userService.getRowCount(userName, userSex);
        return R.ok().data("pageTotal", total);
    }

    @RequestMapping("/createUser")
    @ResponseBody
    public Integer createUser(User user) {
        Random random = new Random();
        Integer number = random.nextInt(9000) + 1000;
        user.setUserId(System.currentTimeMillis() + String.valueOf(number));
        return userService.createUser(user);
    }

    @RequestMapping("/deleteUserById")
    @ResponseBody
    public Integer deleteUserById(String userId) {
        return userService.deleteUserById(userId);
    }

    @RequestMapping(value = "/deleteUserByIdList")
    @ResponseBody
    public Integer deleteUserByIdList(String userIdList) {
        String userIdListSub = userIdList.substring(0, userIdList.length()-1);
//        String[] userIds = userIdList.split(",");

        List userIds = new ArrayList();
        for (String userIdStr: userIdListSub.split(",")){
            userIds.add(userIdStr.trim());
        }
        return userService.deleteUserByIdList(userIds);
    }

    @RequestMapping("/updateUserById")
    @ResponseBody
    public Integer updateUserById(User user) {
        return userService.updateUserById(user);
    }


}

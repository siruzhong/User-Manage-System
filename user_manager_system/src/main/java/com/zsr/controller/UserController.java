package com.zsr.controller;

import com.zsr.pojo.User;
import com.zsr.service.UserService;
import com.zsr.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.ssl.SSLContextImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin //解决跨域问题
public class UserController {
    @Autowired
    private UserService userService;

    //查询所有用户
    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    //添加用户
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        Result result = new Result();
        try {
            userService.add(user);
            result.setMsg("用户添加成功!");
        } catch (Exception e) {
            result.status = false;
            result.setMsg("保存用户失败,请稍后再试...");
        }
        return result;
    }

    //修改用户
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        Result result = new Result();
        try {
            userService.update(user);
            result.setMsg("用户信息修改成功!");
        } catch (Exception e) {
            result.status = false;
            result.setMsg("用户信息修改失败,请稍后再试...");
        }
        return result;
    }

    //删除指定用户
    @GetMapping("/deleteByID")
    public Result deleteByID(int id) {
        Result result = new Result();
        try {
            userService.deleteByID(id);
            result.setMsg("用户删除成功!");
        } catch (Exception e) {
            result.status = false;
            result.setMsg("删除用户失败,请稍后再试...");
        }
        return result;
    }

    //分页查询
    @GetMapping("/findByPage")
    public Map<String, Object> findByPage(Integer nowPage, Integer rows) {
        HashMap<String, Object> result = new HashMap<>();
        nowPage = nowPage == null ? 1 : nowPage;
        rows = rows == null ? 4 : rows;
        List<User> users = userService.findByPage(nowPage, rows);
        Long totals = userService.findTotals();
        result.put("users", users);
        result.put("total", totals);
        return result;
    }
}

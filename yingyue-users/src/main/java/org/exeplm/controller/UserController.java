package org.exeplm.controller;

import org.exeplm.dto.UserDto;
import org.exeplm.entity.User;
import org.exeplm.service.UserService;
import org.exeplm.utils.TaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户(User)表控制层
 *
 * @author makejava
 * @since 2022-07-07 09:05:27
 */
@RestController
@RequestMapping("users")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<TaoResult> getUserPage(@RequestParam(required = false) Map map){
        HashMap<String, Object> hashMap = new HashMap<>();
        Object page =  map.getOrDefault("page", 1);
        map.put("page",page);
        Object per_page =  map.getOrDefault("per_page", 5);
        map.put("per_page",per_page);
        List<UserDto> userPage = userService.getUserPage(map);
        Long total = userService.getTotal(map);
        hashMap.put("items",userPage);
        hashMap.put("total_count",total);
        return new ResponseEntity<>(TaoResult.ok(hashMap), HttpStatus.OK);
    }

}


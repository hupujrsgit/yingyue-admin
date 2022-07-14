package org.exeplm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.exeplm.dto.AdminDTO;
import org.exeplm.entity.Admin;
import org.exeplm.service.AdminService;
import org.exeplm.utils.RedisUtil;
import org.exeplm.utils.TaoResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2022-07-05 21:04:34
 */
@RestController
@Slf4j
public class AdminController {
    /**
     * 服务对象
     */
    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("/tokens")
    public ResponseEntity<TaoResult> tokens(@RequestBody Admin admin, HttpSession session) throws JsonProcessingException {

        Admin reqadmin = adminService.login(admin);
        if (ObjectUtils.isEmpty(reqadmin)){
            return new ResponseEntity<>(TaoResult.build(403,"账号或密码错误！请检查"), HttpStatus.FORBIDDEN);
        }
        String token = session.getId();
        redisUtil.set(token,reqadmin,30,TimeUnit.MINUTES);
        redisUtil.set("name","lisi");
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        return new ResponseEntity<>(TaoResult.ok(map),HttpStatus.OK);
    }
    @GetMapping("/admin-user")
    public ResponseEntity<TaoResult> adminUser(String token){
        Admin admin = (Admin) redisUtil.get(token);
        AdminDTO adminDTO = new AdminDTO();
        try {
            BeanUtils.copyProperties(admin,adminDTO);
        }catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(TaoResult.build(403,"获取不到用户数据信息"),HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(TaoResult.ok(adminDTO),HttpStatus.OK);
    }
    @DeleteMapping("/tokens/{token}")
    public ResponseEntity<TaoResult> logout(@PathVariable("token") String token){
        redisUtil.del(token);

        return new ResponseEntity<>(TaoResult.build(200,"退出成功!"),HttpStatus.OK);
    }

}


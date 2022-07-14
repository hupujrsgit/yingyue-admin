package org.exeplm.service.impl;

import org.exeplm.entity.Admin;
import org.exeplm.dao.AdminDao;
import org.exeplm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;


import java.nio.charset.StandardCharsets;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2022-07-05 21:04:34
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;


    @Override
    public Admin login(Admin admin) {
        Admin adminDB = adminDao.getByName(admin.getUsername());
        if (ObjectUtils.isEmpty(adminDB)){
            return null;
        }
        String md5passwrde = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8));
        if (md5passwrde.equals(adminDB.getPassword())){
            return adminDB;
        }
        return null;

    }
}

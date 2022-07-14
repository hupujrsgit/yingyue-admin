package org.exeplm.service.impl;

import org.exeplm.dto.UserDto;
import org.exeplm.entity.User;
import org.exeplm.dao.UserDao;
import org.exeplm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户(User)表服务实现类
 *
 * @author makejava
 * @since 2022-07-07 09:05:28
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public List<UserDto> getUserPage(Map<String, Object> map) {
        map.put("rows",Integer.parseInt((String)map.get("per_page")));
        Integer page = Integer.parseInt((String) map.get("page")) ;

        map.put("page",(page-1)*Integer.parseInt((String) map.get("per_page")));
        List<UserDto> userPage = userDao.getUserPage(map);
        return userPage;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        User user = new User();
        user.setId((Integer) map.get("id"));
        user.setName((String) map.get("name"));
        user.setPhone((String) map.get("phone"));
        long count = userDao.count(user);
        return count;
    }
}

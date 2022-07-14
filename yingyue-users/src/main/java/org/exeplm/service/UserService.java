package org.exeplm.service;

import org.exeplm.dto.UserDto;
import org.exeplm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 用户(User)表服务接口
 *
 * @author makejava
 * @since 2022-07-07 09:05:28
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<User> queryByPage(User user, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<UserDto> getUserPage(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);
}

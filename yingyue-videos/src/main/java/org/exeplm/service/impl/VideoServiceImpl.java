package org.exeplm.service.impl;

import org.exeplm.dto.VideoDTO;
import org.exeplm.entity.Video;
import org.exeplm.dao.VideoDao;
import org.exeplm.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频(Video)表服务实现类
 *
 * @author makejava
 * @since 2022-07-07 10:24:11
 */
@Service("videoService")
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoDao videoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Video queryById(Integer id) {
        return this.videoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param video 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Video> queryByPage(Video video, PageRequest pageRequest) {
        long total = this.videoDao.count(video);
        return new PageImpl<>(this.videoDao.queryAllByLimit(video, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video insert(Video video) {
        this.videoDao.insert(video);
        return video;
    }

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video update(Video video) {
        this.videoDao.update(video);
        return this.queryById(video.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoDao.deleteById(id) > 0;
    }

    @Override
    public List<VideoDTO> getVideoList(Integer page, Integer rows, Integer id, String name, Integer categoryId, String uploaderName) {
        page = (page-1)*rows;
        List<VideoDTO> videoList = videoDao.getVideoList(page, rows, id, name, categoryId, uploaderName);
        return videoList;
    }

    @Override
    public Long getTotal(Integer id, String name, Integer categoryId, String uploaderName) {

        return videoDao.getTotal(id,name,categoryId,uploaderName);
    }
}

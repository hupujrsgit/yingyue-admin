package org.exeplm.controller;

import org.exeplm.dto.VideoDTO;
import org.exeplm.entity.Video;
import org.exeplm.service.VideoService;
import org.exeplm.utils.TaoResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 视频(Video)表控制层
 *
 * @author makejava
 * @since 2022-07-07 10:24:11
 */
@RestController
@RequestMapping("videos")
public class VideoController {
    /**
     * 服务对象
     */
    @Resource
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<TaoResult> getVideoList(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "per_page",defaultValue = "5")Integer rows,@RequestParam(value = "id",required = false)Integer id,
                                  @RequestParam(value = "name",required = false) String name,@RequestParam(value = "category_id",required = false)Integer categoryId,@RequestParam(value = "uploader_name",required = false)String uploaderName ){
        List<VideoDTO> videoList = videoService.getVideoList(page, rows, id, name, categoryId, uploaderName);
        Long total = videoService.getTotal(id, name, categoryId, uploaderName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",videoList);
        map.put("total_count",total);
        return new ResponseEntity<>(TaoResult.ok(map), HttpStatus.OK);
    }
}


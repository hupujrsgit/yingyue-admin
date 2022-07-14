package org.exeplm.dto;

import lombok.Data;
import org.exeplm.entity.Video;
@Data
public class VideoDTO extends Video {

    private String category;

    private UserDto uploader;
}

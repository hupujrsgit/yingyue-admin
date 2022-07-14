package org.exeplm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdminDTO {
    @JsonProperty("name")
    private String username;
    private String avatar;
}

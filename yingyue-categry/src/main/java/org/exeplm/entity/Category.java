package org.exeplm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 分类(Category)实体类
 *
 * @author makejava
 * @since 2022-07-06 11:52:59
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category implements Serializable {
    private static final long serialVersionUID = -21274827023157959L;
    
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级分类id
     */
    @JsonProperty("parent_id")
    private Integer parentId;
    
    private Date createdAt;
    
    private Date updatedAt;
    
    private Date deletedAt;

    private List<Category> children;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

}


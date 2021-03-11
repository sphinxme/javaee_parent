package com.dosx.javase.service.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Project实体表
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("workflow_project")
@ApiModel(value="Project对象", description="Project实体表")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    public Project(Long masterId, String name) {
        this.masterId = masterId;
        this.name = name;
    }

    public Project() { }

    @ApiModelProperty(value = "Project ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Project 名称")
    private String name;

    @ApiModelProperty(value = "主要负责人的UserID")
    private Long masterId;

    @ApiModelProperty(value = "本Project的工作流的第一个IssueID")
    private Long headerIssueId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "头像图床地址")
    private String imgPath = "https://s3.ax1x.com/2021/03/11/6YgXU1.jpg";

    @TableField(exist = false)
    private List<String> tags;

    @TableField(exist = false)
    private String desc;

    @TableField(exist = false)
    private MinUser manager;


}

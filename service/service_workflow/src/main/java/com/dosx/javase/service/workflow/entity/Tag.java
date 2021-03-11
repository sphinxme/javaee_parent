package com.dosx.javase.service.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Project可用标签列表
tag使用base64编码存储
 * </p>
 *
 * @author lucky us
 * @since 2021-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("workflow_tag")
@ApiModel(value="Tag对象", description="Project可用标签列表 tag使用base64编码存储")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    public Tag() {}
    public Tag(String tag) {
        this.tag = tag;
    }

    @ApiModelProperty(value = "Tag Name")
    @TableId(value = "tag", type = IdType.NONE)
    private String tag;



}

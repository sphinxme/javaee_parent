package com.dosx.javase.service.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Issue实体表
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("workflow_issue")
@ApiModel(value="Issue对象", description="Issue实体表")
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Issue ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "指向下一个Issue的ID")
    private Long nextId;

    @ApiModelProperty(value = "父Issue的ID")
    private Long parentId;

    @ApiModelProperty(value = "所属Project的ID")
    private Long projectId;

    @ApiModelProperty(value = "Issue类型(备用)")
    private Integer type;

    @ApiModelProperty(value = "当前Issue的状态(待定)")
    private Integer status;

    @ApiModelProperty(value = "Issue的开始时间")
    private Date startTime;

    @ApiModelProperty(value = "Issue的预定截至时间")
    private Date deadline;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Boolean isDeleted;


}

package com.springaft.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 名称：字典表<br>
 * 描述：字典表<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 数据值
     */
    @NotBlank(message = "字典项数据值不能为空")
    private String value;
    /**
     * 标签名
     */
    @NotBlank(message = "字典项标签不能为空")
    private String label;
    /**
     * 类型
     */
    @NotBlank(message = "字典项数据类型不能为空")
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序（升序）
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;
}

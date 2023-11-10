package com.zjx.courese.course.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 19:53:45
 */
@Data
@TableName("courses")
public class CoursesEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//username待查询，不映射到数据库中的列
	@TableField(exist = false)
    private String username = "暂无信息";
	@TableField(exist = false)
	private String category = "暂无信息";
	/**
	 * 
	 */
	@TableId
	private Integer courseId;
	/**
	 * 
	 */
	private String courseName;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private Integer creatorId;
	/**
	 * 
	 */
	private Integer categoryId;
	/**
	 * 
	 */
	private Date creationDate;

	//课程图片
	private String courseImg ;

	//课程评级
	private String courseLevel;

    //课程学时
	private Integer courseTime;

	//课程学习人数

	private Integer coursePeople;

}

package com.zjx.courese.course.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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

}

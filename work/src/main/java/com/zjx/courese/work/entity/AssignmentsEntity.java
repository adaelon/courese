package com.zjx.courese.work.entity;

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
 * @date 2023-10-19 11:07:34
 */
@Data
@TableName("assignments")
public class AssignmentsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer assignmentId;
	/**
	 *
	 */
	private Integer courseId;
	/**
	 *
	 */
	private String title;
	/**
	 *
	 */
	private String description;
	/**
	 *
	 */
	private Date dueDate;
	/**
	 *
	 */
	private Integer categoryId;

	private Date startDate;

	private Integer score;

	private Integer status;

}

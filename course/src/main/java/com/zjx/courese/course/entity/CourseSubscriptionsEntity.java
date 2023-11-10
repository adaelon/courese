package com.zjx.courese.course.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @date 2023-10-19 19:53:44
 */
@Data
@TableName("course_subscriptions")
public class CourseSubscriptionsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private Integer userId ;
	/**
	 * 
	 */
	private Integer courseId;
	/**
	 * 
	 */
	private Date subscriptionDate;

	//课程实体
	@TableField(exist = false)
	private CoursesEntity course = null;

}

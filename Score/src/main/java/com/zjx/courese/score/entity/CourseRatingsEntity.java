package com.zjx.courese.score.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:11:20
 */
@Data
@TableName("course_ratings")
public class CourseRatingsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer ratingId;
	/**
	 * 
	 */
	private Integer courseId;
	/**
	 * 
	 */
	private Integer raterId;
	/**
	 * 
	 */
	private BigDecimal ratingValue;
	/**
	 * 
	 */
	private String feedback;
	/**
	 * 
	 */
	private Date ratingDate;

}

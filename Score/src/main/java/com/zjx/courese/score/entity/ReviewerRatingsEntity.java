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
@TableName("reviewer_ratings")
public class ReviewerRatingsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer ratingId;
	/**
	 * 
	 */
	private Integer reviewerId;
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

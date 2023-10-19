package com.zjx.courese.peerevaluation.entity;

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
 * @date 2023-10-19 20:06:29
 */
@Data
@TableName("peer_reviews")
public class PeerReviewsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer reviewId;
	/**
	 * 
	 */
	private Integer submissionId;
	/**
	 * 
	 */
	private Integer reviewerId;
	/**
	 * 
	 */
	private BigDecimal score;
	/**
	 * 
	 */
	private String feedback;
	/**
	 * 
	 */
	private Date reviewDate;

}

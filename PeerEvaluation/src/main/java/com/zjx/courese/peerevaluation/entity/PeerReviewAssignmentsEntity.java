package com.zjx.courese.peerevaluation.entity;

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
 * @date 2023-10-19 20:06:29
 */
@Data
@TableName("peer_review_assignments")
public class PeerReviewAssignmentsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer reviewerId;
	/**
	 * 
	 */
	private Integer submissionId;

	//评价状态
	private Integer status;



}

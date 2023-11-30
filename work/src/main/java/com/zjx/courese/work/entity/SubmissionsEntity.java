package com.zjx.courese.work.entity;

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
 * @date 2023-10-19 11:07:34
 */
@Data
@TableName("submissions")
public class SubmissionsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer submissionId;
	/**
	 *
	 */
	private Integer assignmentId;
	/**
	 *
	 */
	private Integer userId;
	/**
	 *
	 */
	private String content;
	/**
	 *
	 */
	private Date submissionDate;
	/**
	 *
	 */
	private BigDecimal similarityScore;
	/**
	 *
	 */
	private BigDecimal preliminaryScore;

	private BigDecimal finalScore;


}

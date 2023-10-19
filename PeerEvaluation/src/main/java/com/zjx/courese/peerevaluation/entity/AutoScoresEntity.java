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
@TableName("auto_scores")
public class AutoScoresEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer autoScoreId;
	/**
	 * 
	 */
	private Integer submissionId;
	/**
	 * 
	 */
	private String scoreAlgorithm;
	/**
	 * 
	 */
	private BigDecimal score;

}

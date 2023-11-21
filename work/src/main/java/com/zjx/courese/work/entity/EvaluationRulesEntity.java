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
 * @date 2023-10-19 11:07:33
 */
@Data
@TableName("evaluation_rules")
public class EvaluationRulesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer ruleId;
	/**
	 *
	 */
	private Integer assignmentId;
	/**
	 *
	 */
	private String ruleDescription;

	private Integer peerNum;

	private Date startDate;

	private Date dueDate;

	private String type;
}

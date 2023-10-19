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
@TableName("assignment_categories")
public class AssignmentCategoriesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer categoryId;
	/**
	 * 
	 */
	private String categoryName;
	/**
	 * 
	 */
	private String description;

}

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
 * @date 2023-10-19 11:07:31
 */
@Data
@TableName("uploaded_files")
public class UploadedFilesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer fileId;
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
	private String fileName;
	/**
	 * 
	 */
	private String fileType;
	/**
	 * 
	 */
	private Long fileSize;
	/**
	 * 
	 */
	private String ossUrl;
	/**
	 * 
	 */
	private Date uploadDate;

}

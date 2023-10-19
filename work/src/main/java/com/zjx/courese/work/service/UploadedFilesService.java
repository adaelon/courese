package com.zjx.courese.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.common.utils.PageUtils;
import com.zjx.courese.work.entity.UploadedFilesEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 11:07:31
 */
public interface UploadedFilesService extends IService<UploadedFilesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.zjx.courese.work.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.Query;

import com.zjx.courese.work.dao.UploadedFilesDao;
import com.zjx.courese.work.entity.UploadedFilesEntity;
import com.zjx.courese.work.service.UploadedFilesService;


@Service("uploadedFilesService")
public class UploadedFilesServiceImpl extends ServiceImpl<UploadedFilesDao, UploadedFilesEntity> implements UploadedFilesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UploadedFilesEntity> page = this.page(
                new Query<UploadedFilesEntity>().getPage(params),
                new QueryWrapper<UploadedFilesEntity>()
        );

        return new PageUtils(page);
    }

}
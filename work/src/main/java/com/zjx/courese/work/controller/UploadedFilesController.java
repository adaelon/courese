package com.zjx.courese.work.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.work.entity.UploadedFilesEntity;
import com.zjx.courese.work.service.UploadedFilesService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;



/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 11:56:34
 */
@RestController
@RequestMapping("work/uploadedfiles")
public class UploadedFilesController {
    @Autowired
    private UploadedFilesService uploadedFilesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("work:uploadedfiles:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = uploadedFilesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fileId}")
    //@RequiresPermissions("work:uploadedfiles:info")
    public R info(@PathVariable("fileId") Integer fileId){
		UploadedFilesEntity uploadedFiles = uploadedFilesService.getById(fileId);

        return R.ok().put("uploadedFiles", uploadedFiles);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("work:uploadedfiles:save")
    public R save(@RequestBody UploadedFilesEntity uploadedFiles){
		uploadedFilesService.save(uploadedFiles);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("work:uploadedfiles:update")
    public R update(@RequestBody UploadedFilesEntity uploadedFiles){
		uploadedFilesService.updateById(uploadedFiles);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("work:uploadedfiles:delete")
    public R delete(@RequestBody Integer[] fileIds){
		uploadedFilesService.removeByIds(Arrays.asList(fileIds));

        return R.ok();
    }

}

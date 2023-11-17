package com.zjx.courese.work.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.commons.lang.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.zjx.courese.work.entity.UploadedFilesEntity;
import com.zjx.courese.work.service.UploadedFilesService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


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

    @Value("${fileServer.url}")
    String fileUrl;
    @RequestMapping(value = "fileUpload",method = RequestMethod.POST)
    public R fileUpload(@RequestParam("file") MultipartFile file) throws IOException, MyException {
        String imgUrl=fileUrl;
        if(file!=null){
            System.out.println("multipartFile = " + file.getName()+"|"+file.getSize());

            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(configFile);
            TrackerClient trackerClient=new TrackerClient();
            TrackerServer trackerServer=trackerClient.getTrackerServer();
            StorageClient storageClient=new StorageClient(trackerServer,null);
            String filename=    file.getOriginalFilename();
            String extName = StringUtils.substringAfterLast(filename, ".");

            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            imgUrl=fileUrl ;
            imgUrl+=":8189";

            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                imgUrl+="/"+path;
            }

            //System.out.println(imgUrl);

        }

        return R.ok().put("imageUrl",imgUrl);
    }

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

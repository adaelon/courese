package com.zjx.courese.peerevaluation.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zjx.courese.peerevaluation.vo.AnomaliesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.peerevaluation.entity.AnomaliesEntity;
import com.zjx.courese.peerevaluation.service.AnomaliesService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;



/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:06:29
 */
@RestController
@RefreshScope
@RequestMapping("peerevaluation/anomalies")
public class AnomaliesController {
    @Autowired
    private AnomaliesService anomaliesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("peerevaluation:anomalies:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = anomaliesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{anomalyId}")
    //@RequiresPermissions("peerevaluation:anomalies:info")
    public R info(@PathVariable("anomalyId") Integer anomalyId){
		AnomaliesEntity anomalies = anomaliesService.getById(anomalyId);

        return R.ok().put("anomalies", anomalies);
    }

    /**
     * 保存
     */

    @RequestMapping("/save")
    //@RequiresPermissions("peerevaluation:anomalies:save")
    public R save(@RequestBody AnomaliesVO anomalies){
        AnomaliesEntity anomaliesEntity = new AnomaliesEntity();
        anomaliesEntity.setReviewerId(anomalies.getReviewerId());
        anomaliesEntity.setSubmissionId(anomalies.getSubmissionId());
		anomaliesService.save(anomaliesEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("peerevaluation:anomalies:update")
    public R update(@RequestBody AnomaliesEntity anomalies){
		anomaliesService.updateById(anomalies);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("peerevaluation:anomalies:delete")
    public R delete(@RequestBody Integer[] anomalyIds){
		anomaliesService.removeByIds(Arrays.asList(anomalyIds));

        return R.ok();
    }

}

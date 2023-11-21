package com.zjx.courese.peerevaluation.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.peerevaluation.entity.PeerReviewAssignmentsEntity;
import com.zjx.courese.peerevaluation.service.PeerReviewAssignmentsService;
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
@RequestMapping("peerevaluation/peerreviewassignments")
public class PeerReviewAssignmentsController {
    @Autowired
    private PeerReviewAssignmentsService peerReviewAssignmentsService;



    @RequestMapping("/userWork")
    public R userWork(@RequestBody Map<String,Object> params){
        System.out.println(params);
        PageUtils page = peerReviewAssignmentsService.queryUserWork(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("peerevaluation:peerreviewassignments:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = peerReviewAssignmentsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{reviewerId}")
    //@RequiresPermissions("peerevaluation:peerreviewassignments:info")
    public R info(@PathVariable("reviewerId") Integer reviewerId){
		PeerReviewAssignmentsEntity peerReviewAssignments = peerReviewAssignmentsService.getById(reviewerId);

        return R.ok().put("peerReviewAssignments", peerReviewAssignments);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("peerevaluation:peerreviewassignments:save")
    public R save(@RequestBody PeerReviewAssignmentsEntity peerReviewAssignments){
		peerReviewAssignmentsService.save(peerReviewAssignments);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("peerevaluation:peerreviewassignments:update")
    public R update(@RequestBody PeerReviewAssignmentsEntity peerReviewAssignments){
		peerReviewAssignmentsService.updateById(peerReviewAssignments);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("peerevaluation:peerreviewassignments:delete")
    public R delete(@RequestBody Integer[] reviewerIds){
		peerReviewAssignmentsService.removeByIds(Arrays.asList(reviewerIds));

        return R.ok();
    }

}

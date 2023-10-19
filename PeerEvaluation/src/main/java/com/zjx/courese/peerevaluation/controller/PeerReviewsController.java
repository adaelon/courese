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

import com.zjx.courese.peerevaluation.entity.PeerReviewsEntity;
import com.zjx.courese.peerevaluation.service.PeerReviewsService;
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
@RequestMapping("peerevaluation/peerreviews")
public class PeerReviewsController {
    @Autowired
    private PeerReviewsService peerReviewsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("peerevaluation:peerreviews:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = peerReviewsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{reviewId}")
    //@RequiresPermissions("peerevaluation:peerreviews:info")
    public R info(@PathVariable("reviewId") Integer reviewId){
		PeerReviewsEntity peerReviews = peerReviewsService.getById(reviewId);

        return R.ok().put("peerReviews", peerReviews);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("peerevaluation:peerreviews:save")
    public R save(@RequestBody PeerReviewsEntity peerReviews){
		peerReviewsService.save(peerReviews);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("peerevaluation:peerreviews:update")
    public R update(@RequestBody PeerReviewsEntity peerReviews){
		peerReviewsService.updateById(peerReviews);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("peerevaluation:peerreviews:delete")
    public R delete(@RequestBody Integer[] reviewIds){
		peerReviewsService.removeByIds(Arrays.asList(reviewIds));

        return R.ok();
    }

}

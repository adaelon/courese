package com.zjx.courese.score.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.score.entity.ReviewerRatingsEntity;
import com.zjx.courese.score.service.ReviewerRatingsService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;



/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 20:11:20
 */
@RestController
@RequestMapping("score/reviewerratings")
public class ReviewerRatingsController {
    @Autowired
    private ReviewerRatingsService reviewerRatingsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("score:reviewerratings:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = reviewerRatingsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ratingId}")
    //@RequiresPermissions("score:reviewerratings:info")
    public R info(@PathVariable("ratingId") Integer ratingId){
		ReviewerRatingsEntity reviewerRatings = reviewerRatingsService.getById(ratingId);

        return R.ok().put("reviewerRatings", reviewerRatings);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("score:reviewerratings:save")
    public R save(@RequestBody ReviewerRatingsEntity reviewerRatings){
		reviewerRatingsService.save(reviewerRatings);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("score:reviewerratings:update")
    public R update(@RequestBody ReviewerRatingsEntity reviewerRatings){
		reviewerRatingsService.updateById(reviewerRatings);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("score:reviewerratings:delete")
    public R delete(@RequestBody Integer[] ratingIds){
		reviewerRatingsService.removeByIds(Arrays.asList(ratingIds));

        return R.ok();
    }

}

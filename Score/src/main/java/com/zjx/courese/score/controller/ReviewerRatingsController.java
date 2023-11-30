package com.zjx.courese.score.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
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




    @RequestMapping("/createRating")
    public R createRating(@RequestParam Map<String,Object> params){
        //从params获取相关参数

        Integer reviewerId = Integer.parseInt((String) params.get("reviewerId"));
        Integer raterId = Integer.parseInt((String)params.get("raterId")) ;
        Integer ratingValue = Integer.parseInt((String)params.get("ratingValue")) ;


        //构建实体
        ReviewerRatingsEntity reviewerRatings = new ReviewerRatingsEntity();
        reviewerRatings.setReviewerId(reviewerId);
        reviewerRatings.setRaterId(raterId);
        reviewerRatings.setRatingValue(BigDecimal.valueOf(ratingValue));
        reviewerRatings.setRatingDate(new Date());


        //保存到数据库中
        reviewerRatingsService.save(reviewerRatings);

        //返回前端信息
        return R.ok("反馈成功！");




    }
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

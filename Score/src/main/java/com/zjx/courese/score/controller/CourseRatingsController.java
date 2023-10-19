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

import com.zjx.courese.score.entity.CourseRatingsEntity;
import com.zjx.courese.score.service.CourseRatingsService;
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
@RequestMapping("score/courseratings")
public class CourseRatingsController {
    @Autowired
    private CourseRatingsService courseRatingsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("score:courseratings:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseRatingsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ratingId}")
    //@RequiresPermissions("score:courseratings:info")
    public R info(@PathVariable("ratingId") Integer ratingId){
		CourseRatingsEntity courseRatings = courseRatingsService.getById(ratingId);

        return R.ok().put("courseRatings", courseRatings);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("score:courseratings:save")
    public R save(@RequestBody CourseRatingsEntity courseRatings){
		courseRatingsService.save(courseRatings);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("score:courseratings:update")
    public R update(@RequestBody CourseRatingsEntity courseRatings){
		courseRatingsService.updateById(courseRatings);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("score:courseratings:delete")
    public R delete(@RequestBody Integer[] ratingIds){
		courseRatingsService.removeByIds(Arrays.asList(ratingIds));

        return R.ok();
    }

}

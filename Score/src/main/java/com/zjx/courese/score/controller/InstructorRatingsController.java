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

import com.zjx.courese.score.entity.InstructorRatingsEntity;
import com.zjx.courese.score.service.InstructorRatingsService;
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
@RequestMapping("score/instructorratings")
public class InstructorRatingsController {
    @Autowired
    private InstructorRatingsService instructorRatingsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("score:instructorratings:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = instructorRatingsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ratingId}")
    //@RequiresPermissions("score:instructorratings:info")
    public R info(@PathVariable("ratingId") Integer ratingId){
		InstructorRatingsEntity instructorRatings = instructorRatingsService.getById(ratingId);

        return R.ok().put("instructorRatings", instructorRatings);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("score:instructorratings:save")
    public R save(@RequestBody InstructorRatingsEntity instructorRatings){
		instructorRatingsService.save(instructorRatings);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("score:instructorratings:update")
    public R update(@RequestBody InstructorRatingsEntity instructorRatings){
		instructorRatingsService.updateById(instructorRatings);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("score:instructorratings:delete")
    public R delete(@RequestBody Integer[] ratingIds){
		instructorRatingsService.removeByIds(Arrays.asList(ratingIds));

        return R.ok();
    }

}

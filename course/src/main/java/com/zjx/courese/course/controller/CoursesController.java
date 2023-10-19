package com.zjx.courese.course.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.course.entity.CoursesEntity;
import com.zjx.courese.course.service.CoursesService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;



/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 19:53:45
 */
@RestController
@RequestMapping("course/courses")
public class CoursesController {
    @Autowired
    private CoursesService coursesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("course:courses:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = coursesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    //@RequiresPermissions("course:courses:info")
    public R info(@PathVariable("courseId") Integer courseId){
		CoursesEntity courses = coursesService.getById(courseId);

        return R.ok().put("courses", courses);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("course:courses:save")
    public R save(@RequestBody CoursesEntity courses){
		coursesService.save(courses);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("course:courses:update")
    public R update(@RequestBody CoursesEntity courses){
		coursesService.updateById(courses);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("course:courses:delete")
    public R delete(@RequestBody Integer[] courseIds){
		coursesService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}

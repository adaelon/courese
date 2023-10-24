package com.zjx.courese.course.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.zjx.courese.course.entity.CoursesEntity;
import com.zjx.courese.course.service.CoursesService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 19:53:45
 */
@RestController
@RefreshScope
@RequestMapping("course/courses")
public class CoursesController {
    @Autowired
    private CoursesService coursesService;

    @Value("${course.user.name}")
    private String name;

    @Value("${course.user.age}")
    private String age;
    @RequestMapping("/test")
    public R test(){
        return R.ok().put("name",name).put("age",age);
    }

    @RequestMapping("/user/list")
    public R userCourse(){
        CoursesEntity  coursesEntity=new CoursesEntity();
        coursesEntity.setCourseName("math");
        return R.ok().put("course",Arrays.asList(coursesEntity));
    }

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

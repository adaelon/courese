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

import com.zjx.courese.course.entity.CourseSubscriptionsEntity;
import com.zjx.courese.course.service.CourseSubscriptionsService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;



/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-19 19:53:44
 */
@RestController
@RequestMapping("course/coursesubscriptions")
public class CourseSubscriptionsController {
    @Autowired
    private CourseSubscriptionsService courseSubscriptionsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("course:coursesubscriptions:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseSubscriptionsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("course:coursesubscriptions:info")
    public R info(@PathVariable("userId") Integer userId){
		CourseSubscriptionsEntity courseSubscriptions = courseSubscriptionsService.getById(userId);

        return R.ok().put("courseSubscriptions", courseSubscriptions);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("course:coursesubscriptions:save")
    public R save(@RequestBody CourseSubscriptionsEntity courseSubscriptions){
		courseSubscriptionsService.save(courseSubscriptions);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("course:coursesubscriptions:update")
    public R update(@RequestBody CourseSubscriptionsEntity courseSubscriptions){
		courseSubscriptionsService.updateById(courseSubscriptions);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("course:coursesubscriptions:delete")
    public R delete(@RequestBody Integer[] userIds){
		courseSubscriptionsService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}

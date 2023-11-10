package com.zjx.courese.course.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjx.common.utils.Query;
import com.zjx.courese.course.entity.CategoriesEntity;
import com.zjx.courese.course.entity.CoursesEntity;
import com.zjx.courese.course.feign.UserFeignService;
import com.zjx.courese.course.service.CategoriesService;
import com.zjx.courese.course.service.CoursesService;
import com.zjx.courese.user.entity.UsersEntity;
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


    @Autowired
    private CoursesService coursesService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private CategoriesService categoriesService;

    //查询用户所选课程
    @RequestMapping("/userCourse")
    public R userCourse(@RequestParam Map<String,Object> params){
        System.out.println(params);
        PageUtils page = courseSubscriptionsService.queryUserCourse(params);

        List<CourseSubscriptionsEntity> list = (List<CourseSubscriptionsEntity>) page.getList();

        //遍历
        for(CourseSubscriptionsEntity sub : list){
            Integer courseId = sub.getCourseId();
            //获得对应课程
            CoursesEntity course = coursesService.getById(courseId);
            //填充课程数据
            Integer categoryId = course.getCategoryId();
            CategoriesEntity  categoriesEntity= categoriesService.getById(categoryId);
            course.setCategory(categoriesEntity.getCategoryName());

            Integer creatorId = course.getCreatorId();

            R info = userFeignService.info(creatorId);

            if (info.get("code").equals(0)) {
                Map<String, Object> usersData = (Map<String, Object>) info.get("users");
                UsersEntity user = new UsersEntity();
                user.setUserId((Integer) usersData.get("userId"));
                user.setUsername((String) usersData.get("username"));
                course.setUsername(user.getUsername());
            } else {
                // 出现异常
            }
            sub.setCourse(course);
        }

        return R.ok().put("page",page);
    }


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

        return R.ok().put("page", courseSubscriptions);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("course:coursesubscriptions:save")
    public R save(@RequestBody CourseSubscriptionsEntity courseSubscriptions){

        System.out.println(courseSubscriptions);
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

package com.zjx.courese.course.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zjx.courese.course.entity.CategoriesEntity;
import com.zjx.courese.course.entity.CourseSubscriptionsEntity;
import com.zjx.courese.course.feign.UserFeignService;
import com.zjx.courese.course.service.CategoriesService;
import com.zjx.courese.user.entity.UsersEntity;
import org.checkerframework.checker.units.qual.C;
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
@CrossOrigin
public class CoursesController {
    @Autowired
    private CoursesService coursesService;
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private CategoriesService categoriesService;

    @Value("${course.user.name}")
    private String name;

    @Value("${course.user.age}")
    private String age;
    @RequestMapping("/test")
    public R test(){
        return R.ok().put("name",name).put("age",age);
    }




    //查询老师创建的课程
    @RequestMapping("/teacherCourse")
    public R teacherCourse(@RequestBody Map<String,Object> params){
        PageUtils page = coursesService.queryTeacherCourse(params);


        //获取查询到的课程列表
        List<CoursesEntity> courseList = (List<CoursesEntity>) page.getList();
        //遍历课程列表，将creatorId转换为username
        for(CoursesEntity course:courseList){
            Integer categoryId = course.getCategoryId();
            CategoriesEntity categoriesEntity= categoriesService.getById(categoryId);
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
        }

        return R.ok().put("page", page);


    }






    @RequestMapping("/user/list")
    public R userCourse(){
        CoursesEntity  coursesEntity=new CoursesEntity();
        coursesEntity.setCourseName("math");
        return R.ok().put("course",Arrays.asList(coursesEntity));
    }



    @RequestMapping("/limitList")
    public R limitList(@RequestParam Map<String, Object> params)
    {
        PageUtils page = coursesService.queryPageLimit(params);

        //获取查询到的课程列表
        List<CoursesEntity> courseList = (List<CoursesEntity>) page.getList();
        //遍历课程列表，将creatorId转换为username
        for(CoursesEntity course:courseList){
            Integer categoryId = course.getCategoryId();
            CategoriesEntity categoriesEntity= categoriesService.getById(categoryId);
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
        }

        return R.ok().put("page", page);
    }

    @RequestMapping("/list")
    //@RequiresPermissions("course:courses:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = coursesService.queryPage(params);
        //获取查询到的课程列表
        List<CoursesEntity> courseList = (List<CoursesEntity>) page.getList();
        //遍历课程列表，将creatorId转换为username
        for(CoursesEntity course:courseList){
            Integer categoryId = course.getCategoryId();
            CategoriesEntity categoriesEntity= categoriesService.getById(categoryId);
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
        }
        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    //@RequiresPermissions("course:courses:info")
    public R info(@PathVariable("courseId") Integer courseId){
		CoursesEntity courses = coursesService.getById(courseId);
        Integer creatorId = courses.getCreatorId();
        R info = userFeignService.info(creatorId);
        if (info.get("code").equals(0)) {
            Map<String, Object> usersData = (Map<String, Object>) info.get("users");
            UsersEntity user = new UsersEntity();
            user.setUserId((Integer) usersData.get("userId"));
            user.setUsername((String) usersData.get("username"));
            courses.setUsername(user.getUsername());
        } else {
            // 出现异常
        }

        return R.ok().put("courses", courses);
    }

//    newCourse:
//    {
//        coursename: '',
//                description: '',
//            creatorId:item.creatorId,
//            category:'',
//            img:'',
//            level:'',
//            time:'',
//    }

    @RequestMapping("/createCourse")
    public R createLession(@RequestParam Map<String,Object> params){
        //从params获取相关参数
        String courseName = (String)params.get("coursename");
        String description = (String)params.get("description");
        Integer creatorId = Integer.parseInt((String) params.get("creatorId"));
        Integer categoryId = Integer.parseInt((String)params.get("category")) ;
        String courseImg = (String)params.get("img");
        String courseLevel = (String)params.get("level");
        Integer courseTime = Integer.parseInt((String)params.get("time"));

        //判断是否有冲突课程（未完成）

        //构建课程实体
        CoursesEntity coursesEntity = new CoursesEntity();
        coursesEntity.setCourseName(courseName);
        coursesEntity.setDescription(description);
        coursesEntity.setCreatorId(creatorId);
        coursesEntity.setCategoryId(categoryId);
        coursesEntity.setCreationDate(new Date());
        coursesEntity.setCourseImg(courseImg);
        coursesEntity.setCourseLevel(courseLevel);
        coursesEntity.setCourseTime(courseTime);
        coursesEntity.setCoursePeople(0);

        //保存到数据库中
        coursesService.save(coursesEntity);

        //返回前端信息
        return R.ok("创建课程成功");




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

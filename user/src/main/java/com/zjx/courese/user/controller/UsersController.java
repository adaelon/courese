package com.zjx.courese.user.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zjx.courese.user.feign.CourseFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.user.entity.UsersEntity;
import com.zjx.courese.user.service.UsersService;
import com.zjx.common.utils.PageUtils;
import com.zjx.common.utils.R;



/**
 * 
 *
 * @author zjx
 * @email zjxlijo@gmail.com
 * @date 2023-10-20 10:18:17
 */
@RestController
@RefreshScope
@RequestMapping("user/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    CourseFeignService courseFeignService;
    @RequestMapping("/courses")
    public R test(){
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setUsername("ada");

        R usercourse= courseFeignService.userCourse();


        return R.ok().put("user",usersEntity).put("course",usercourse.get("course"));

    }


    @RequestMapping("/find/{username}")
    public R findUserByName(@PathVariable("username") String username){
        UsersEntity entity = usersService.getByName(username);
        return R.ok().put("user",entity);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:users:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = usersService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("user:users:info")
    public R info(@PathVariable("userId") Integer userId){
		UsersEntity users = usersService.getById(userId);

        return R.ok().put("users", users);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:users:save")
    public R save(@RequestBody UsersEntity users){
		usersService.save(users);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:users:update")
    public R update(@RequestBody UsersEntity users){
		usersService.updateById(users);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:users:delete")
    public R delete(@RequestBody Integer[] userIds){
		usersService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}

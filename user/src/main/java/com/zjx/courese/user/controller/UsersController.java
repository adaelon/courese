package com.zjx.courese.user.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zjx.common.utils.Query;
import com.zjx.courese.user.feign.CourseFeignService;
import com.zjx.courese.user.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("user/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    CourseFeignService courseFeignService;

    @Autowired
    private UserRolesService userRolesService;




    @RequestMapping("/register")
    public R register(@RequestParam Map<String,Object> params ){
        //从params中获取用户名和密码参数
        String username = (String) params.get("username");
        String password = (String) params.get("password");

        //判断该用户名是否已存在


        //构建用户实体
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(username);
        usersEntity.setPassword(password);
        usersEntity.setDateRegistered(new Date());
        usersEntity.setLastLogin(new Date());

        //保存到数据库中
        usersService.save(usersEntity);

        //从数据库中查询该注册信息
        PageUtils page = usersService.queryLogin(params);

        if (page != null && !page.getList().isEmpty()) {
            return R.ok().put("page", page);
        } else {
            return R.error(500,"注册失败").put("page",page);
        }
    }

    @RequestMapping("/login")
    public R login(@RequestParam Map<String, Object> params){
        PageUtils page = usersService.queryLogin(params);
        List<UsersEntity> usersEntity = (List<UsersEntity>) page.getList();
        Integer userId = usersEntity.get(0).getUserId();
        Integer roleId= userRolesService.getById(userId).getRoleId();
        usersEntity.get(0).setRoleId(roleId);
        if (page != null && !page.getList().isEmpty()) {
            return R.ok().put("page", page);
        } else {
            return R.error(500,"没有找到匹配的用户").put("page",page);
        }
    }


    @RequestMapping("/logout")
    public R logout(@RequestParam Map<String, Object> params){
       return R.ok("用户已退出");
    }

    @RequestMapping("/courses")
    public R test(){
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setUsername("ada");

        R usercourse= courseFeignService.userCourse();


        return R.ok().put("user",usersEntity).put("course",usercourse.get("course"));

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

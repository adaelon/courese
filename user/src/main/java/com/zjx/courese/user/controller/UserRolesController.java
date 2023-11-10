package com.zjx.courese.user.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zjx.courese.user.entity.RolesEntity;
import com.zjx.courese.user.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.user.entity.UserRolesEntity;
import com.zjx.courese.user.service.UserRolesService;
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
@RequestMapping("user/userroles")
public class UserRolesController {
    @Autowired
    private UserRolesService userRolesService;


    @Autowired
    private RolesService rolesService;

    @RequestMapping("/infoRoles/{userId}")
    //@RequiresPermissions("user:userroles:info")
    public R infoRoles(@PathVariable("userId") Integer userId){
        UserRolesEntity userRoles = userRolesService.getById(userId);
        RolesEntity roles = rolesService.getById(userRoles.getRoleId());
        return R.ok().put("roles", roles);

    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:userroles:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userRolesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("user:userroles:info")
    public R info(@PathVariable("userId") Integer userId){
		UserRolesEntity userRoles = userRolesService.getById(userId);

        return R.ok().put("userRoles", userRoles);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:userroles:save")
    public R save(@RequestBody UserRolesEntity userRoles){
		userRolesService.save(userRoles);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:userroles:update")
    public R update(@RequestBody UserRolesEntity userRoles){
		userRolesService.updateById(userRoles);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:userroles:delete")
    public R delete(@RequestBody Integer[] userIds){
		userRolesService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}

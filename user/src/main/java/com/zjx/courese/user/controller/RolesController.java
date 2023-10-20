package com.zjx.courese.user.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjx.courese.user.entity.RolesEntity;
import com.zjx.courese.user.service.RolesService;
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
@RequestMapping("user/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:roles:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rolesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roleId}")
    //@RequiresPermissions("user:roles:info")
    public R info(@PathVariable("roleId") Integer roleId){
		RolesEntity roles = rolesService.getById(roleId);

        return R.ok().put("roles", roles);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:roles:save")
    public R save(@RequestBody RolesEntity roles){
		rolesService.save(roles);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:roles:update")
    public R update(@RequestBody RolesEntity roles){
		rolesService.updateById(roles);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:roles:delete")
    public R delete(@RequestBody Integer[] roleIds){
		rolesService.removeByIds(Arrays.asList(roleIds));

        return R.ok();
    }

}

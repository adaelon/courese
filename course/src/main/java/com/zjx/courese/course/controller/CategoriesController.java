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

import com.zjx.courese.course.entity.CategoriesEntity;
import com.zjx.courese.course.service.CategoriesService;
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
@RequestMapping("course/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("course:categories:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoriesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{categoryId}")
    //@RequiresPermissions("course:categories:info")
    public R info(@PathVariable("categoryId") Integer categoryId){
		CategoriesEntity categories = categoriesService.getById(categoryId);

        return R.ok().put("categories", categories);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("course:categories:save")
    public R save(@RequestBody CategoriesEntity categories){
		categoriesService.save(categories);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("course:categories:update")
    public R update(@RequestBody CategoriesEntity categories){
		categoriesService.updateById(categories);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("course:categories:delete")
    public R delete(@RequestBody Integer[] categoryIds){
		categoriesService.removeByIds(Arrays.asList(categoryIds));

        return R.ok();
    }

}

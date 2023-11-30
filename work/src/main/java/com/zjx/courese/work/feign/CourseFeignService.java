package com.zjx.courese.work.feign;

import com.zjx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("course")
public interface CourseFeignService {
    @RequestMapping("course/coursesubscriptions/courseUser/{courseId}")
    public R courseUser(@PathVariable("courseId") Integer courseId,
                        @RequestParam Map<String,Object> params);
}

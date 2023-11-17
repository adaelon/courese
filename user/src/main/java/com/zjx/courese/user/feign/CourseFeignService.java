package com.zjx.courese.user.feign;

import com.zjx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("course")
public interface CourseFeignService {
    @RequestMapping("/course/courses/user/list")
    public R userCourse();
}

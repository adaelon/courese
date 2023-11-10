package com.zjx.courese.course.feign;
import com.zjx.common.utils.R;
import com.zjx.courese.user.entity.UsersEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user")
public interface UserFeignService {
    @RequestMapping("user/users/info/{userId}")
    public R info(@PathVariable("userId") Integer userId);

}

package com.zjx.courese.authserver.feign;

import com.zjx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user")
public interface UserFeignService {

    @RequestMapping("/user/users/find/{username}")
    public R findUserByName(@PathVariable String username);

    @RequestMapping("/user/userroles/info/{userId}")
    public R info(@PathVariable("userId") Integer userId);

}

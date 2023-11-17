package com.zjx.courese.authserver.sec;

import com.zjx.common.utils.R;
import com.zjx.courese.authserver.bean.ComUser;
import com.zjx.courese.authserver.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserFeignService userFeignService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        R response = userFeignService.findUserByName(username);
        if ((Integer)response.get("code") != 0 || response.get("user") == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Map<String, Object> userMap = (Map<String, Object>) response.get("user");

        ComUser comUser =new ComUser();
        comUser.setUsername((String) userMap.get("username"));
        comUser.setPassword((String) userMap.get("password"));
        comUser.setUserId((int) userMap.get("userId"));
        return comUser;






    }
}

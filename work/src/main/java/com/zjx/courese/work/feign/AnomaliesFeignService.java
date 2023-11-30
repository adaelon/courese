package com.zjx.courese.work.feign;

import com.zjx.common.utils.R;
import com.zjx.courese.work.vo.AnomaliesVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("peerevaluation")
public interface AnomaliesFeignService {



    @RequestMapping("peerevaluation/peerreviewassignments/save")
    //@RequiresPermissions("peerevaluation:anomalies:save")
    R save(@RequestBody AnomaliesVO anomalies);


}

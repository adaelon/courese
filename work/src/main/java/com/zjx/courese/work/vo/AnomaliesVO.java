package com.zjx.courese.work.vo;

import lombok.Data;

@Data
public class AnomaliesVO {

    /**
     *
     */
    private Integer reviewerId;
    /**
     *
     */
    private Integer submissionId;

    private Integer status;

    private  Integer assignmentId;
}

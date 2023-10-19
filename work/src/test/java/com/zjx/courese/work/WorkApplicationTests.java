package com.zjx.courese.work;

import com.zjx.courese.work.entity.AssignmentsEntity;
import com.zjx.courese.work.service.AssignmentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class WorkApplicationTests {

	@Autowired
	AssignmentsService assignmentsService;

	@Test
	void contextLoads() {
		AssignmentsEntity assignmentsEntity = new AssignmentsEntity();
		Date currentDate = new Date(System.currentTimeMillis());
		assignmentsEntity.setTitle("haha");
		assignmentsEntity.setDueDate(currentDate);
		assignmentsService.save(assignmentsEntity);
		System.out.println("保存成功");
	}

}

package edu.nju.desserthouse.service.impl;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//定时计划任务
@Service
@EnableScheduling
public class ScheduleTask {

	@Scheduled(fixedRate = 3 * 1000)
	public void autoConfirmBook() {

	}

	@Scheduled(fixedRate = 3 * 1000)
	public void autoCancleBook() {

	}
}

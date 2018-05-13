package net.xidlims.web.report;

import net.xidlims.service.report.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskJob {
	@Autowired
	private ReportService reportService;
	
	public void myJob(){
		System.out.println("---------------------");
		reportService.storeRates();  //计算8个绩效指标并存入数据库
	}
	
	public void myJobRank(){
		System.out.println("---------------------");
		reportService.storeRank();  //计算各个学院的综合指标和排名并存入数据库
	}
	
	
	/************************************************
	 * 设备学期使用情况表（计划任务）
	 * @author 贺子龙
	 * 2016-07-21
	 *************************************************/
	public void createSchoolDeviceUse(){
		System.out.println("---------------------");
		reportService.createSchoolDeviceUse();
	}
	
}

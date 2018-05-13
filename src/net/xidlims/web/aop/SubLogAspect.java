package net.xidlims.web.aop;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.Log;
import net.xidlims.domain.TDiscuss;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.log.LogService;
import net.xidlims.service.tcoursesite.TDiscussService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SubLogAspect extends SystemLogAspect{
	
	@Autowired
	private LogService logService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TDiscussService tDiscussService;
	
	@Before("serviceAspect()")
	public void doBefore(JoinPoint joinPoint) {

		//System.out.println("log...");
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// IP, MethodName, Description, Args
		String ip = request.getRemoteAddr();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String description = getServiceMthodDescription(joinPoint);
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (Object obj : joinPoint.getArgs()) {
				//System.out.println(obj);
			}
		}
		
		User user = shareService.getUser();
		
		String data = "tCourseSiteId=" + request.getParameter("tCourseSiteId");
		if(request.getParameter("folderId")!=null){
			data = "folderId=" + request.getParameter("folderId");
		}else if(request.getParameter("lessonId")!=null){
			data = "lessonId=" + request.getParameter("lessonId");
		}else if(request.getParameter("chapterId")!=null){
			data = "chapterId=" + request.getParameter("chapterId");
		}
		
		String module = "";
		
		if(methodName.equals("tCourseSite")){
			module = "";
		}else if(className.contains("TCourseSiteController")||className.contains("WkFolderController")||className.contains("WkUploadController")||className.contains("TAssignmentController")){
			if(request.getParameter("moduleType")!=null&&request.getParameter("moduleType").equals("1")){
				module = "知识";
			}else if(request.getParameter("moduleType")!=null&&request.getParameter("moduleType").equals("2")){
				module = "技能";
			}else if(request.getParameter("moduleType")!=null&&request.getParameter("moduleType").equals("3")){
				module = "体验";
			}
		}else if(className.contains("TCourseInfoController")){
			module = "课程信息";
			if(methodName.equals("editArtical")){
				data = "articalId=" + request.getParameter("articalId");
			}
		}else if(className.contains("TCourseStudentController")){
			module = "班级成员";
		}else if(className.contains("TDiscussController")){
			module = "讨论";
			if(methodName.equals("tDiscuss")){
				data = "discussId=" + request.getParameter("discussId");
			}else if(methodName.equals("saveReply")){
				data = "partentId=" + request.getParameter("partentId");
			}else if(methodName.equals("deleteReply")){
				TDiscuss tDiscuss = tDiscussService.findTDiscussByPrimaryKey(Integer.parseInt(request.getParameter("discussId")));
				int partentId = tDiscuss.getTDiscuss().getId();
				data = "partentId=" + partentId;
			}
		}else if(className.contains("TExerciseController")){
			module = "练习";
		}else if(className.contains("TGradeBookController")){
			module = "成绩";
		}else if(className.contains("TMessageController")){
			module = "通知";
		}else if(className.contains("TQuestionController")){
			module = "题库";
		}else if(className.contains("TLogController")){
			module = "学习行为";
		}
		
		
		
		
		Log log = new Log();
		log.setIp(ip);
		log.setAction(description);
		log.setCreateTime(Calendar.getInstance());
		log.setData(data);
		log.setModule(module);
		if(user!=null){
			log.setUserid(user.getUsername());
		}else{
			log.setUserid("visitor");
		}
		
		
		
		logService.saveLog(log);

	}

}

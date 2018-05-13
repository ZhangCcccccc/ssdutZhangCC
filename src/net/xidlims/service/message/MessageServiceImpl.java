package net.xidlims.service.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.TMessageUserDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.Message;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.TMessageUser;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.tcoursesite.TCourseSiteUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Message entities
 * 
 */

@Service("MessageService")
@Transactional
public class MessageServiceImpl implements MessageService {

	/**
	 * DAO injected by Spring that manages Message entities
	 * 
	 */
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private TMessageUserDAO tMessageUserDAO;


	/**
	 * Instantiates a new MessageServiceImpl.
	 *
	 */
	public MessageServiceImpl() {
	}

	/**
	 */
	@Transactional
	public Message findMessageByPrimaryKey(Integer id) {
		return messageDAO.findMessageByPrimaryKey(id);
	}

	/**
	 * Delete an existing Message entity
	 * 
	 */
	@Transactional
	public void deleteMessage(Message message) {
		messageDAO.remove(message);
		messageDAO.flush();
	}

	/**
	 * Return a count of all Message entity
	 * 
	 */
	@Transactional
	public Integer countMessages() {
		return ((Long) messageDAO.createQuerySingleResult("select count(o) from Message o").getSingleResult()).intValue();
	}

	/**
	 * Return all Message entity
	 * 
	 */
	@Transactional
	public List<Message> findAllMessages(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Message>(messageDAO.findAllMessages(startResult, maxRows));
	}

	/**
	 * Load an existing Message entity
	 * 
	 */
	@Transactional
	public Set<Message> loadMessages() {
		return messageDAO.findAllMessages();
	}

	/**
	 * Save an existing Message entity
	 * 
	 */
	@Transactional
	public void saveMessage(Message message) {
		Message existingMessage = messageDAO.findMessageByPrimaryKey(message.getId());

		if (existingMessage != null) {
			if (existingMessage != message) {
				existingMessage.setId(message.getId());
				existingMessage.setCond(message.getCond());
				existingMessage.setTitle(message.getTitle());
				existingMessage.setCreateTime(message.getCreateTime());
				existingMessage.setContent(message.getContent());
				existingMessage.setMessageState(message.getMessageState());
				existingMessage.setSendUser(message.getSendUser());
				existingMessage.setSendCparty(message.getSendCparty());
				existingMessage.setAuthId(message.getAuthId());
				existingMessage.setReceiveCpartyid(message.getReceiveCpartyid());
			}
			message = messageDAO.store(existingMessage);
		} else {
			message = messageDAO.store(message);
		}
		messageDAO.flush();
	}
	/*************************
	 * 功能：我的消息列表
	 * 作者：戴昊宇
	 * 时间：2017-08-31 
	 *************************/
	@Override
	public List<Message> findMessageBySome(Message message,int currpage, int pageSize,HttpServletRequest request) {
	List<Message> list =new ArrayList<Message>();
	String str="";
	String sql="";
	//获取当前时间，根据当前时间判断所属学期
	Calendar time = Calendar.getInstance();
	SchoolTerm term = shareService.getBelongsSchoolTerm(time);
	Calendar termStart = term.getTermStart();
	Calendar termEnd = term.getTermEnd();
	SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
	String terms = start.format(termStart.getTime());
	SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd");
	String terme = end.format(termEnd.getTime());
	Set<Authority> authorities = shareService.getUserDetail().getAuthorities();
	List<Authority> authoritiesList = new ArrayList<Authority>(authorities);
	if (authoritiesList.size()>0) {//找到当前登录用户的所有权限
		
		for (int i = 0; i < authoritiesList.size(); i++) {
			sql+="  m.authId="+authoritiesList.get(i).getId()+" or";
		}
	}
	
		
	
	String a="select m from Message m where 1=1 and "+sql;
	a=a.substring(0, a.length()-2);//-2  是为了去掉最后一个or
//	a+="and m.messageState=0";
	a+="or m.username= '"+shareService.getUserDetail().getUsername()+"'";//看是不是有当前登陆人的消息，根据username判断
	if (message.getReceiveCpartyid()!=null) {//不判断会报错
		
		a+=" and m.receiveCpartyid ="+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
	}
			
	if(message.getSendUser()!=null&&message.getSendUser()!=""){
		a+="and m.sendUser like'%"+message.getSendUser()+"%'";
	}
	if(message.getTitle()!=null&&message.getTitle()!=""){
		a+="and m.title like'%"+message.getTitle()+"%'";
	}
	String starttime= request.getParameter("starttime");
	String endtime=	request.getParameter("endtime");
	
	  if(starttime!=null && starttime.length()>0 && endtime!=null&& endtime.length()>0){
        	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 a += " and m.createTime between '"+starttime +"' and '"+endtime+"' "; 	
        }
	  a +=" and m.createTime between '"+terms +"' and'" + terme+"'";
	  a+="order by m.createTime desc, m.messageState asc";
	  list=messageDAO.executeQuery(a,currpage*pageSize,pageSize);
	  return list;
	}
	/*************************
	 * 功能：我的消息个数
	 * 作者：贺子龙
	 * 时间：2015-09-16 10:31:58
	 *************************/
	@Transactional
	public Integer countmessage(Message message,HttpServletRequest request) {
		List<Message> list =new ArrayList<Message>();
//		List<UserAuthority> d=userAuthorityDAO.executeQuery("select u from UserAuthority u where u.userId ="+baseApplicationService.getUserDetail().getId());
		//System.out.println(d);
		Set<Authority> authorities = shareService.getUserDetail().getAuthorities();
		List<Authority> authoritiesList = new ArrayList<Authority>(authorities);
		String str="";
		String sql="";
		if (authoritiesList.size()>0) {
			
			for (int i = 0; i < authoritiesList.size(); i++) {
				sql+="  m.authId="+authoritiesList.get(i).getId()+" or";
			}
		}
		String a="select count(m) from Message m where 1=1 and "+sql;
		a=a.substring(0, a.length()-2);
		a+="and m.messageState=0";
		a+="or m.username= '"+shareService.getUserDetail().getUsername()+"'";//看是不是有当前登陆人的消息，根据username判断
		
		if (message.getReceiveCpartyid()!=null) {//不判断会报错
		a+=" and m.receiveCpartyid ="+ shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		}
		if(message.getSendUser()!=null&&message.getSendUser()!=""){
			a+="and m.sendUser like'%"+message.getSendUser()+"%'";
		}
		if(message.getTitle()!=null&&message.getTitle()!=""){
			a+="and m.title like'%"+message.getTitle()+"%'";
		}
		String starttime=	request.getParameter("starttime");
		String endtime=	request.getParameter("endtime");
		
		  if(starttime!=null && starttime.length()>0 && endtime!=null&& endtime.length()>0){
	        	//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			 a += " and m.createTime between '"+starttime +"' and '"+endtime+"' "; 	
	        }
		 return ((Long) messageDAO.createQuerySingleResult(a.toString()).getSingleResult()).intValue();

	}
	
	/*************************
	 * 功能：我的消息个数
	 * 作者：贺子龙
	 * 时间：2015-09-16 10:31:58
	 *************************/
	@Transactional
	public Integer countmessage( ) { 
		String a="select count(m) from Message m where 1=1"; 
		a+="and m.messageState=0";
		a+="and m.username= '"+shareService.getUserDetail().getUsername()+"'";//看是不是有当前登陆人的消息，根据username判断 
		return ((Long) messageDAO.createQuerySingleResult(a.toString()).getSingleResult()).intValue();

	}
	
	/*************************
	 * 功能：根据tMessage保存相对应的Message
	 * 作者：张佳鸣
	 * 时间：2017-10-13
	 *************************/ 
	public void saveMessageByTMessage(TMessage tMessage,int tCourseSiteId){
		
		//通过课程号查找所有学生
		List<TCourseSiteUser> tCourseSiteUsers=tCourseSiteUserService.findAlltCourseSiteUsers(tCourseSiteId);
		//将通知与课程下的学生对应起来
		for(TCourseSiteUser t:tCourseSiteUsers){
			
			//查询之前是否已经存在该条数据（判断是新增还是更改）
			Message message = this.findMessageByTMessageAndTCourseSiteUser(tMessage,t);
			if(message == null){
				//new一个Message对象
			    message = new Message();
			}
			
			//对message设值
			message.setTitle(tMessage.getTitle());
			message.setCreateTime(tMessage.getReleaseTime());
			message.setMessageState(0);
			message.setContent(tMessage.getContent());
			message.setSendUser(tMessage.getUser().getCname());
			message.setSendCparty(tMessage.getUser().getSchoolAcademy().getAcademyName());
			message.setUsername(t.getUser().getUsername());
			message.setType(tMessage.getId().toString());
		    //保存message
			this.saveMessage(message);
		}
	}
	
	/*************************
	 * 功能：根据tMessage和tCourseSiteUser查找相对应的Message
	 * 作者：张佳鸣
	 * 时间：2017-10-16
	 *************************/ 
	public Message findMessageByTMessageAndTCourseSiteUser(TMessage tMessage,TCourseSiteUser tCourseSiteUser){
		
		//根据tMessage查询相应的Message数据是否存在
		return messageDAO.findMessageByTMessageAndTCourseSiteUser(tMessage,tCourseSiteUser);	
	}
}

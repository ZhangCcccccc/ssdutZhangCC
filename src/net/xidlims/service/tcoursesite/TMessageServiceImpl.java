package net.xidlims.service.tcoursesite;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.MessageDAO;
import net.xidlims.dao.SchoolClassesDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.TMessageAttachmentDAO;
import net.xidlims.dao.TMessageDAO;
import net.xidlims.dao.TMessageUserDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Message;
import net.xidlims.domain.SchoolClasses;
import net.xidlims.domain.TCourseSiteUser;
import net.xidlims.domain.TMessage;
import net.xidlims.domain.TMessageAttachment;
import net.xidlims.domain.TMessageUser;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;
import net.xidlims.service.message.MessageService;
import net.xidlims.util.mail.MailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



@Service("TMessageService")
public class TMessageServiceImpl implements TMessageService {

	@Autowired
	private TMessageDAO tMessageDAO;
	@Autowired
	private TMessageUserDAO tMessageUserDAO;
	@Autowired
	private SchoolClassesDAO schoolClassesDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TMessageAttachmentDAO tMessageAttachmentDAO;
	@Autowired
	private ShareService shareService;
	@Autowired 
	private TCourseSiteUserService tCourseSiteUserService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private MessageDAO messageDAO;
	@PersistenceContext 
	private EntityManager entityManager;
	
	
	/**************************************************************************
	 * Description:查询对应站点下的通知数量
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索 
	 * @author：黄崔俊
	 * @date ：2015-8-6 10:47:43
	 **************************************************************************/
	@Override
	public int getCountTMessageList(String tCourseSiteId, Integer queryType, String titleQuery) {
		//查询对应站点下的通知数量
		StringBuffer sql = new StringBuffer("select count(*) from TMessage c where c.TCourseSite.id = '"+ tCourseSiteId +"'");
		//模糊查询
		if ( 1 == queryType) {
			sql.append(" and c.title like '%"+ titleQuery +"%'");
		}
		//精确查询
		if ( 2 == queryType) {
			sql.append(" and c.title = '"+ titleQuery +"'");
		}
		return ((Long) tMessageDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}
	/*******************************************************
	 * Description:获取通知数量
	 * 
	 * @author：李军凯
	 * @date ：2016-08-30
	 *********************************************************/
	@Override
	public int getCountTMessageListBytCourseSiteId(String tCourseSiteId) {
		//获取通知数量
		StringBuffer sql = new StringBuffer("select count(*) from TMessage c where c.TCourseSite.id = '"+ tCourseSiteId +"'");
		return ((Long) tMessageDAO.createQuerySingleResult(sql.toString()).getSingleResult()).intValue();
	}

	/**************************************************************************
	 * Description:查询通知列表
	 * @param titleQuery 
	 * @param queryType 1表示模糊查询，2表示精确搜索 
	 * @author：黄崔俊
	 * @date ：2015-8-6 10:33:03
	 **************************************************************************/
	@Override
	public List<TMessage> findTMessageList(String tCourseSiteId,
			Integer queryType, String titleQuery,Integer currpage, int pageSize) {
		//查询通知列表
		StringBuffer sql = new StringBuffer("from TMessage c where c.TCourseSite.id = '"+ tCourseSiteId +"'");
		//模糊查询
		if (1 == queryType) {
			sql.append(" and c.title like '%"+ titleQuery +"%'");
		}
		//精确查询
		if (2 == queryType) {
			sql.append(" and c.title = '"+ titleQuery +"'");
		}
		sql.append(" order by c.releaseTime desc");
		return tMessageDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		
	}
	/*******************************************************
	 * Description:通过课程号获取通知列表
	 * 
	 * @author：李军凯
	 * @date ：2016-08-30
	 *********************************************************/
	@Override
	public List<TMessage> findTMessageListBytCourseSiteId(String tCourseSiteId,
			Integer currpage, int pageSize) {
		//获取通知列表
		StringBuffer sql = new StringBuffer("from TMessage c where c.TCourseSite.id = '"+ tCourseSiteId +"'");
		sql.append(" order by c.releaseTime desc");
		return tMessageDAO.executeQuery(sql.toString(), (currpage-1)*pageSize, pageSize);
		
	}
	/*******************************************************
	 * Description:通过登录人获取登录人的通知列表
	 * 
	 * @author：李军凯
	 * @date ：2016-09-30
	 *********************************************************/
	@Override
	public List<TMessage> findTMessageListByUsername(String userName,int tCourseSiteId) {
		//获取通知与用户名的对应关系
		String sql ="from TMessageUser c where c.username = '"+ userName +"'";
		List<TMessageUser> tMessageUsers= tMessageUserDAO.executeQuery(sql);
		List<TMessage> tMessages= new ArrayList<TMessage>();
		//获取通知
		if(tMessageUsers!=null){
		for(TMessageUser tMessageUser : tMessageUsers ){
			TMessage t= tMessageDAO.findTMessageByPrimaryKey(tMessageUser.getMessageId());
			if(t.getType()<300 && t.getTCourseSite().getId()==tCourseSiteId ){//不添加邮件类型的
			tMessages.add(t);}
		}
		}
		return tMessages;
		
	}
	/*******************************************************
	 * Description:通过登录人获取登录人的消息栏
	 * 
	 * @author：李军凯
	 * @date ：2016-10-8
	 *********************************************************/
	@Override
	public List findTMessageShowViewList(String userName,int tCourseSiteId) {
		//获取通知与用户名的对应关系
		String sql ="select * from view_t_message_show c where c.site_id = "+tCourseSiteId+" and c.username = '"+ userName +"'";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		return list;
		
	}
	/**************************************************************************
	 * Description:保存通知
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-6 14:33:44
	 **************************************************************************/
	@Override
	public void saveTMessage(TMessage tMessage) {
		//保存通知
		tMessage.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(tMessage.getTCourseSite().getId()));
		//没有创建人就用当前登陆人作为创建人
		tMessage.setUser(userDAO.findUserByPrimaryKey(tMessage.getUser().getUsername().trim())==null?shareService.getUser():userDAO.findUserByPrimaryKey(tMessage.getUser().getUsername().trim()));
		//0表示该通知尚未发布
		tMessage.setPublish(0);
		tMessage.setType(100);
		tMessage =tMessageDAO.store(tMessage);
		
		int tMessageId=tMessage.getId();
		int tCourseSiteId = tMessage.getTCourseSite().getId();

		//根据tMessage保存相对应的Message
		messageService.saveMessageByTMessage(tMessage,tCourseSiteId);
		
		//通过课程号查找所有学生
		List<TCourseSiteUser> tCourseSiteUsers=tCourseSiteUserService.findAlltCourseSiteUsers(tCourseSiteId);
		//将通知与课程下的学生对应起来
		for(TCourseSiteUser t:tCourseSiteUsers){
		String username = t.getUser().getUsername();
		saveTMessageuser(tMessageId, username);
		}
	}
	/**************************************************************************
	 * Description:消息-保存消息
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	@Override
	public TMessage saveTMessageInfo(TMessage tMessage) {
		//保存消息
		tMessage.setTCourseSite(tCourseSiteDAO.findTCourseSiteById(tMessage.getTCourseSite().getId()));
		//没有创建人就用当前登陆人作为创建人
		tMessage.setUser(userDAO.findUserByPrimaryKey(tMessage.getUser().getUsername().trim())==null?shareService.getUser():userDAO.findUserByPrimaryKey(tMessage.getUser().getUsername().trim()));
		//0表示该通知尚未发布
		tMessage.setPublish(0);
		tMessage=tMessageDAO.store(tMessage);
		
		int tMessageId=tMessage.getId();
		int tCourseSiteId = tMessage.getTCourseSite().getId();

		//根据tMessage保存相对应的Message
		messageService.saveMessageByTMessage(tMessage,tCourseSiteId);
		
		//通过课程号查找所有学生
		List<TCourseSiteUser> tCourseSiteUsers=tCourseSiteUserService.findAlltCourseSiteUsers(tCourseSiteId);
		//将通知与课程下的学生对应起来
		for(TCourseSiteUser t:tCourseSiteUsers){
		String username = t.getUser().getUsername();
		saveTMessageuser(tMessageId, username);
		}
		
		return tMessage;
	}
	/**************************************************************************
	 * Description:消息-保存消息发送对象
	 *  
	 * @author：李军凯
	 * @date ：2016-09-21
	 **************************************************************************/
	public void saveTMessageuser(int tMessageId,String username) {
		//查询之前是否已经存在该条数据（判断是新增还是更改）
		TMessageUser tMessageUser = tMessageUserDAO.findTMessageUserByMessageIdAndUsername(tMessageId, username);
		if(tMessageUser == null){
			tMessageUser = new TMessageUser();
			tMessageUser.setMessageId(tMessageId);
			tMessageUser.setUsername(username);
			tMessageUser.setCreateTime(Calendar.getInstance());
		}
		tMessageUser.setCreateBy(shareService.getUser().getUsername());
		tMessageUser.setIsRead(0);
		tMessageUserDAO.store(tMessageUser);
	}
	/**************************************************************************
	 * Description:根据主键查询通知
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-6 15:08:10
	 **************************************************************************/
	@Override
	public TMessage findTMessageByPrimaryKey(Integer id) {
		//根据主键查询通知
		return tMessageDAO.findTMessageByPrimaryKey(id);
	}

	/**************************************************************************
	 * Description:删除查询出的通知
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-6 15:10:50
	 **************************************************************************/
	@Override
	public void deleteTMessage(TMessage tMessage) {
		//删除通知与学生的对应关系
		List<TMessageUser>tMessageUsers= findAllTMessageUserByMessageId(tMessage.getId());
		for(TMessageUser tMessageUser:tMessageUsers){
		tMessageUserDAO.remove(tMessageUser);
		}
		//删除通知与Message的对应关系.
		List<Message> messages= findAllMessageByTMessageId(tMessage.getId());
		for(Message message:messages){
			messageDAO.remove(message);
		}
		//删除通知
		tMessageDAO.remove(tMessage);
	}

	/**************************************************************************
	 * Description:根据班级名称和学生姓名查询记录数
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-6 15:08:10
	 **************************************************************************/
	@Override
	public int getCountUserListByClassnameAndCname(String classname,
			String cname) {
		//根据班级名称和学生姓名查询记录数
		String sql = "select count(*) from User c where c.schoolClasses.className like '%"+ classname +"%' and c.cname like '%"+ cname +"%'";
		return ((Long) userDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}

	/**************************************************************************
	 * Description:根据关键字查询班级名称记录数
	 * 
	 * @author：裴继超
	 * @date ：2015-9-22
	 **************************************************************************/
	@Override
	public int getCountClassnameBykeyword(String classname) {
		//根据关键字查询班级名称记录数
		String sql = "select count(*) from SchoolClasses c where className like '%"+ classname +"%'";
		return ((Long) schoolClassesDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}

	/**************************************************************************
	 * Description:根据关键字查询班级名称列表
	 * 
	 * @author：裴继超
	 * @date ：2015-9-22
	 **************************************************************************/
	@Override
	public List<SchoolClasses> findClassnameBykeyword(String classname,
			 Integer page, int pageSize) {
		//根据关键字查询班级名称列表
		String sql = "from SchoolClasses c where c.className like '%"+ classname +"%' ";
		return schoolClassesDAO.executeQuery(sql, (page-1)*pageSize, pageSize);
	}

	/**************************************************************************
	 * Description:根据班级名称和学生姓名查询用户列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-10 10:13:05
	 **************************************************************************/
	@Override
	public List<User> findUserListByClassnameAndCname(String classname,
			String cname, Integer page, int pageSize) {
		//根据班级名称和学生姓名查询用户列表
		String sql = "from User c where c.schoolClasses.className like '%"+ classname +"%' and c.cname like '%"+ cname +"%'";
		return userDAO.executeQuery(sql, (page-1)*pageSize, pageSize);
	}

	/**************************************************************************
	 * Description:根据通知信息和用户信息保存通知发布的记录
	 * 
	 * @author：黄崔俊
	 * @date ：2015-8-6 15:08:10
	 **************************************************************************/
	/*@Override
	public void saveUserMessage(String[] messageIds, String[] usernames) {
		//循环所有通知
		for (String messageId : messageIds) {
			//根据id查询通知
			TMessage tMessage = tMessageDAO.findTMessageById(Integer.valueOf(messageId));
			//1表示该通知已发布
			tMessage.setPublish(1);
			//保存通知
			tMessageDAO.store(tMessage);
			//更新每个用户
			for(String username : usernames){
				User user = userDAO.findUserByPrimaryKey(username);
				Set<TMessage> tMessages = user.getReceivedMessages();
				tMessages.add(tMessage);
				user.getReceivedMessages().addAll(tMessages);
				userDAO.store(user);
			}
		}
	}*/
	/**************************************************************************
	 * Description:根据通知信息和用户信息保存通知发布的记录
	 * 
	 * @author：裴继超
	 * @date ：2015-9-22
	 **************************************************************************/
	/*@Override
	public void publishClassMessage(String[] messageIds, List<User> users) {
		//循环每个通知
		for (String messageId : messageIds) {
			//根据通知id查找通知
			TMessage tMessage = tMessageDAO.findTMessageById(Integer.valueOf(messageId));
			//1表示该通知已发布
			tMessage.setPublish(1);
			//保存通知
			tMessageDAO.store(tMessage);
			//更新每个用户
			for(User user : users){
				Set<TMessage> tMessages = user.getReceivedMessages();
				tMessages.add(tMessage);
				user.getReceivedMessages().addAll(tMessages);
				userDAO.store(user);
			}
		}
	}*/
	
	/**************************************************************************
	 * Description:根据通知信息和用户信息保存通知发布的记录
	 * 
	 * @author：裴继超
	 * @date ：2015-9-22
	 **************************************************************************/
	@Override
	public List<User> findUserListByClassnumber(String[] classnumbers) {
		//将所有班级编号存到字符串中
		String ss = "";
		for(String classnumber : classnumbers){
			
			ss+="'"+classnumber+"',";
		}
		//去掉最后一个,
		String str1 = ss.substring(0, ss.length() -1);
		//查找在这些班级中的学生
		String sql = "from User c where c.schoolClasses.classNumber in ("+str1+")";
		List<User> users = userDAO.executeQuery(sql,0,-1);
		return users;
		
	}

	/**************************************************************************
	 * Description:根据当前课程和登陆人查询通知列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-28 15:05:14
	 **************************************************************************/
	/*@Override
	public List<TMessage> findTMessageListByUserAndSite(Integer id, User user) {
		//新建通知列表
		List<TMessage> tMessages = new ArrayList<TMessage>();
		//新建通知集
		Set<TMessage> tMessagesSet = new HashSet<TMessage>();
		//教师身份
		if (user.getAuthorities().toString().contains("TEACHER")) {
			tMessagesSet = user.getSendMessages();
		}
		//学生身份
		if (user.getAuthorities().toString().contains("STUDENT")) {
			tMessagesSet = user.getReceivedMessages();
		}
		for (TMessage tMessage : tMessagesSet) {
			if (tMessage.getTCourseSite().getId().equals(id)) {
				tMessages.add(tMessage);
			}
		}
		return tMessages;
	}*/
	/**************************************************************************
	 * Description:消息-立即发送邮件
	 * 
	 * @author：李军凯
	 * @date ：2016-09-22
	 **************************************************************************/
	@Override
	public int sendMail(TMessage tMessage,String email,HttpServletRequest request) throws ParseException{	
		 MailSender sendmail = new MailSender();
		 if(email!=null){
		 String sql = "from TMessageAttachment t where t.messageId = "+tMessage.getId()+" ";
			List<TMessageAttachment> tMessageAttachments = tMessageAttachmentDAO.executeQuery(sql);
			for(TMessageAttachment t:tMessageAttachments){
				sendmail.attachfile(request.getSession().getServletContext().getRealPath("/")+t.getPath());
			}
        sendmail.setHost("smtp.163.com");   //邮箱服务器
        sendmail.setUserName("xidlims@163.com");  //发送者账户名
        sendmail.setPassWord("xidlims123");  //发送者账户密码
        sendmail.setTo(email);  //接收账户
        sendmail.setFrom("xidlims@163.com");  //发送账户
        sendmail.setSubject(tMessage.getTitle());  //主题
        sendmail.setContent(tMessage.getContent()); //内容
        sendmail.sendMailAttach();//附件
		 }
		return 0;
	}
	/*******************************************************
	 * Description:获取已发邮件列表
	 * 
	 * @author：李军凯
	 * @date ：2016-09-22
	 *********************************************************/
	@Override
	public List<TMessage> findAllTMessageListBytCourseSiteId(String tCourseSiteId) {
		//获取通知列表
		StringBuffer sql = new StringBuffer("from TMessage c where c.type != 100 and c.TCourseSite.id = '"+ tCourseSiteId +"'");
		sql.append(" order by c.releaseTime desc");
		return tMessageDAO.executeQuery(sql.toString());		
	}
	/*******************************************************
	 * Description:获取已发邮件对象列表
	 * 
	 * @author：李军凯
	 * @date ：2016-09-22
	 *********************************************************/
	@Override
	public List<TMessageUser> findAllTMessageUserListByMessageId( Integer messageId) {
		//获取通知列表
		String sql = "from TMessageUser c where c.messageId = '"+messageId+"' ";
		return tMessageUserDAO.executeQuery(sql);		
	}
	/**************************************************************************
	 * Description:消息-定时发送邮件
	 * 
	 * @author：李军凯
	 * @date ：2016-09-22
	 **************************************************************************/
	@Override
	public void sendMassageAtTiming(String curPath){
		String sql = "from TMessage c where c.type = 201 or type = 301 ";  
		List<TMessage> tMessages = tMessageDAO.executeQuery(sql,0,-1);
		if(tMessages.size()>0){
		for(TMessage t:tMessages){
			if(Calendar.getInstance().compareTo(t.getReleaseTime())>0)//当前时间比发送时间晚
			{
				String sql2 = "from TMessageUser c where c.messageId = "+t.getId()+" ";
				List<TMessageUser> tMessageUsers = tMessageUserDAO.executeQuery(sql2);
				for(TMessageUser tu:tMessageUsers){
					MailSender sendmail = new MailSender(); 
					sendmail.setHost("smtp.163.com"); //邮箱服务器
					sendmail.setUserName("xidlims@163.com"); //发送者账户名
					sendmail.setPassWord("xidlims123"); //密码
					sendmail.setFrom("xidlims@163.com");//发送账户
					String name = tu.getUsername();
					User user = tCourseSiteUserService.findUserByUsername(name);
					if(user!=null && user.getEmail()!=null){
					String email = tCourseSiteUserService.findUserByUsername(name).getEmail();
					sendmail.setTo(email);//接收账户
					sendmail.setSubject(t.getTitle()); //主题
					sendmail.setContent(t.getContent());//内容
					//查找附件
					String sql3 = "from TMessageAttachment t where t.messageId = "+t.getId()+" ";
					List<TMessageAttachment> tMessageAttachments = tMessageAttachmentDAO.executeQuery(sql3);
					for(TMessageAttachment ta:tMessageAttachments){
						sendmail.attachfile(curPath+ta.getPath());//添加附件
					}
			        sendmail.sendMailAttach();
					}
				}
				if(t.getType()==201){
					t.setType(202);//更改状态
					}
					else
					{
					t.setType(302);//更改状态
					}
					tMessageDAO.store(t);
			}
			
		   }
		}
	}
	/**************************************************************************
	 * Description:消息-上传附件
	 * 
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	@Override
	public int processUpload(Integer type,HttpServletRequest request) {
		//用于多文件上传的request
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//分隔符/
		String sep = System.getProperty("file.separator");
		//获取所有上传的文件
		Map<?, ?> files = multipartRequest.getFileMap();
		//多文件所有文件名称集合
		Iterator<?> fileNames = multipartRequest.getFileNames();
		//文件id列表，通过","分隔
		String resourceLis = "";
		//文件夹是否创建成功的标志位
		boolean flag = false;
		//设置时间格式
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		//拼接文件夹地址
		String fileDir = request.getSession().getServletContext().getRealPath("/") + "upload" + sep
				+ "folder" + sep + dateformat.format(new Date()) + sep;
		// 存放文件文件夹名称
		for (; fileNames.hasNext();) {
			String filename = (String) fileNames.next();
			//将文件转换成用于上传
			CommonsMultipartFile file = (CommonsMultipartFile) files.get(filename);
			//获得文件数据
			byte[] bytes = file.getBytes();
			if (bytes.length != 0) {
				// 说明申请有附件
				if (!flag) {
					//判断该文件夹是否存在
					File dirPath = new File(fileDir);
					if (!dirPath.exists()) {
						//该文件夹不存在，创建该文件夹
						flag = dirPath.mkdirs();
					}
				}
				//取得上传时的文件名称
				String fileTrueName = file.getOriginalFilename();
				//取得上传文件后缀
				String suffix = fileTrueName.substring(fileTrueName.lastIndexOf("."));
				// 使用UUID生成文件名称
				String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
				//拼成完整的文件保存路径加文件
				String fileName = request.getSession().getServletContext().getRealPath("/upload/folder/") + sep
						+ dateformat.format(new Date()) + sep + logImageName;
				//通过文件保存路径新建上传文件
				File uploadFile = new File(fileName);
				try {
					//拷贝文件内容，在相应路径创建该文件
					FileCopyUtils.copy(bytes, uploadFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//新建上传文件
				TMessageAttachment upload = new TMessageAttachment();
				//保存文件大小
				long a=uploadFile.length()/1024/1024;
				//文件大小的格式
				DecimalFormat fmt=new DecimalFormat("#.##");
				//文件大小
				String size="";
				//根据文件大小来添加单位M/KB
				if (a!=0) {
					size=fmt.format(a)+"M";
				}else{
					a = uploadFile.length()/1024;
					size = fmt.format(a)+"KB";
				};
				//设置上传文件大小
				upload.setSize(size);
				//设置上传文件路径
				upload.setPath("/upload/folder/" + dateformat.format(new Date()) + "/" + logImageName);
				//设置上传文件名称
				upload.setName(fileTrueName);
				//设置上传文件类型 0：视频，1：图片，2：文件
				upload.setType(type);
				//设置上传时间为当前时间
				upload.setUpTime(Calendar.getInstance());
				//保存上传文件 返回生成的文件id
				int id = saveUpload(upload);
				//资源列表
				resourceLis = resourceLis + String.valueOf(id) + ",";
				//返回生成的文件id
				return id;
			}
		}
		return 0;
	}
	/**************************************************************************
	 * Description:保存附件 
	 * 
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	@Override
	public int saveUpload(TMessageAttachment upload) {
		//保存文件
		TMessageAttachment up=tMessageAttachmentDAO.store(upload);
		//刷新数据库
		tMessageAttachmentDAO.flush();
		//返回上传文件的id
		return up.getId();
	}
	/**************************************************************************
	 * Description:查找附件 
	 * 
	 * @author：李军凯
	 * @date ：2016-09-27
	 **************************************************************************/
	@Override
	public TMessageAttachment findUpload(int id) {
		//通过主键查找
		TMessageAttachment up=tMessageAttachmentDAO.findTMessageAttachmentByPrimaryKey(id);
		//刷新数据库
		tMessageAttachmentDAO.flush();
		//返回上传文件的id
		return up;
	}
	/**************************************************************************
	 * Description:查找通知与学生的对应关系 
	 * 
	 * @author：李军凯
	 * @date ：2016-09-30
	 **************************************************************************/
	@Override
	public List<TMessageUser> findAllTMessageUserByMessageId(int tMessageId){
		   String sql = "from TMessageUser c where c.messageId = "+tMessageId+" ";
		   List<TMessageUser> tMessageUsers = tMessageUserDAO.executeQuery(sql);
		   return tMessageUsers;
	}
	/*******************************************************
	 * Description:通知设置为已读
	 * 
	 * @author：李军凯
	 * @date ：2016-09-30
	 *********************************************************/
	@Override
	public void setTMessageIsread(int messageId){
		//当前登录人
		User user = shareService.getUser();
		String sql = "from TMessageUser c where c.messageId = "+messageId+" and c.username = '"+user.getUsername()+"'";
		List<TMessageUser> tMessageUsers = tMessageUserDAO.executeQuery(sql);
		for(TMessageUser tMessageUser:tMessageUsers)
		{
			tMessageUser.setIsRead(1);//设置为已读
			tMessageUserDAO.store(tMessageUser);
		}
	}
	/**************************************************************************
	 * Description:查找通知与Message的对应关系 
	 * 
	 * @author：张佳鸣
	 * @date ：2017-10-17
	 **************************************************************************/
	public List<Message> findAllMessageByTMessageId(int tMessageId){
		
		String sql = "from Message m where m.type = "+tMessageId+" ";
		List<Message> messages = messageDAO.executeQuery(sql);
		return messages;
		   
	}
}
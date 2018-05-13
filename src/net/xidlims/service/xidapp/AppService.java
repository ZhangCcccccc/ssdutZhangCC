package net.xidlims.service.xidapp;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;
import net.xidlims.domain.AppClouddickDownloadFile;
import net.xidlims.domain.AppGroup;
import net.xidlims.domain.AppPostImages;
import net.xidlims.domain.AppPostReply;
import net.xidlims.domain.AppPostlist;
import net.xidlims.domain.AppQuestionchoose;
import net.xidlims.domain.AppQuestionnaire;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TCourseSite;
import app.xidlims.Album;
import app.xidlims.Annoucement;
import app.xidlims.AnnoucementList;
import app.xidlims.Appointment;
import app.xidlims.LearningChapter;
import app.xidlims.LearningVideoNew;
import app.xidlims.NewAlbum;
import app.xidlims.NewFolder;
import app.xidlims.NewPostList;
import app.xidlims.PostDetail;
import app.xidlims.Question;
import app.xidlims.QuestionNaireDetail;
import app.xidlims.QuestionPool;
import app.xidlims.ReturnDownloadFile;
import app.xidlims.ReturnDownloadFolder;
import app.xidlims.ShareAppPostReplyList;
import app.xidlims.ShareDetail;
import app.xidlims.ShareList;

public interface AppService {
	/****************************************************************************
	 * 功能：查询出所有的实验室
	 * 作者：张凯
	 * 时间：2017-03-28
	 ****************************************************************************/
	public String findAllLabRoom(String labRoomName);
	
	/****************************************************************************
	 * 功能：查询学生的课程
	 * 作者：张凯
	 * 时间：2017-03-29
	 ****************************************************************************/
	public String findCoursesByStudent(String username);
	
	/****************************************************************************
	 * 功能：查询学生的班级
	 * 作者：张凯
	 * 时间：2017-03-30
	 ****************************************************************************/
	public String findClassByStudent(String username);
	
	/****************************************************************************
	 * 功能：学生个人的成绩
	 * 作者：张凯
	 * 时间：2017-03-31
	 ****************************************************************************/
	public String getStudentGrades(String username);
	
	/****************************************************************************
	 * 功能：获取可用的实验室周次
	 * 作者：张凯
	 * 时间：2017-04-26
	 ****************************************************************************/
	public String getUnusedWeeks(String labroomID, String weekDay, String section);
	
	/****************************************************************************
	 * 功能：审核预约、保存实验室预约
	 * 作者：张凯
	 * 时间：2017-04-27
	 * @throws ParseException 
	 ****************************************************************************/
	public String  auditAndSaveLabReservation(String labroomID,String weekDay,String section,String week,String act, 
			String actName,String content,String ps,String students, String username) throws ParseException;
	
	/****************************************************************************
	 * 功能：获取课程下视频列表
	 * 作者：张凯
	 * 时间：2017-05-3
	 ****************************************************************************/
	public List<LearningVideoNew> getVideosList(String id);
	
	/****************************************************************************
	 * 功能：获取课程下章节
	 * 作者：张凯
	 * 时间：2017-05-04
	 ****************************************************************************/
	public List<LearningChapter> getChapters(String id);
	
	
	/*********************************************************************************
	 * @description:获取试题列表
	 * @author:张凯	
	 * @date：2017/05/20
	 ************************************************************************************/
	public List<Question> getQuestionList(Integer id);
	
//    /**************************************************************************
//     * Description:查询题库列表
//     * 
//     * @author：黄崔俊
//     * @date ：2015-10-23
//     **************************************************************************/
//    List<TAssignmentQuestionpool> findQuestionList(Integer cid);
    
//	TCourseSite findCourseSiteById(Integer id);

    
	
	/*********************************************************************************
	 * @description:保存新建讨论
	 * @author:张凯	
	 * @date:2017/06/29
	 ************************************************************************************/
	public AppPostlist savePost(String title, String sponsor, String content, String ids, String type,Integer groupID, Integer isStick);
	
	/*********************************************************************************
	 * @description:获取班级帖子列表
	 * @author:张凯	
	 * @date:2017/06/29
	 ************************************************************************************/
	public List<NewPostList> getPostlist(String username, HttpServletRequest request);
	
	/*********************************************************************************
	 * @description: 图片的保存
	 * @author:张凯	
	 * @date：2017/06/30
	 ************************************************************************************/
	public AppPostImages saveImage(String path, String fileNewName, Integer id);
	
	/*********************************************************************************
	 * @description: 图片的获取
	 * @author:张凯
	 * @date：2017/06/30
	 ************************************************************************************/
	public List<AppPostImages> getImageList(Integer id);
	
	/*********************************************************************************
	 * @description: 查看帖子
	 * @author:张凯	
	 * @date：2017/07/03
	 ************************************************************************************/
	public PostDetail viewPostDetails(String username, Integer ID, HttpServletRequest request);
	
	/*********************************************************************************
	 * @description: 查看帖子回复内容
	 * @author:张凯	
	 * @date：2017/07/03
	 ************************************************************************************/
	public String viewPostReplies(Integer ID);
	
	/*********************************************************************************
	 * @description: 保存帖子回复内容
	 * @author:张凯	
	 * @date：2017/07/03
	 ************************************************************************************/
	public void savePostReply(Integer ID, String Sponsor, String comment, Integer toResponseID);
	
	/*********************************************************************************
	 * @description: 帖子点赞
	 * @author:张凯	
	 * @date：2017/07/04
	 ************************************************************************************/
	public Integer saveUpvoteUserForPost(Integer ID, String sponsor, Integer flag);
	
	/*********************************************************************************
	 * @description: 帖子收藏
	 * @author:张凯	
	 * @date：2017/07/05
	 ************************************************************************************/
	public Integer saveCollectUserForPost(Integer ID, String sponsor, Integer flag);
	
	/*********************************************************************************
	 * @description: 获取公告列表
	 * @author:张凯	
	 * @date：2017/07/04
	 ************************************************************************************/
	public List<AnnoucementList> getAnnouncements(String username);
	
	/*********************************************************************************
	 * @description: 获取公告详情
	 * @author:张凯	
	 * @date：2017/07/04
	 ************************************************************************************/
	public Annoucement getAnnoucementDetails(Integer ID);
	
	/*********************************************************************************
	 * @description: 保存新建公告
	 * @author:张凯	
	 * @date：2017/07/04
	 ************************************************************************************/
	public AnnoucementList saveAnnoucement(String sponsor, String comment, String title);
	
	/*********************************************************************************
	 * @description: 新建分享
	 * @author:唐钦邦
	 * @date：2017/08/16
	 ************************************************************************************/
	public int NewShare(Integer ID, String username, String title,String postList);
	
	/*********************************************************************************
	 * @description: 上传分享
	 * @author:唐钦邦
	 * @date：2017/08/17
	 ************************************************************************************/
	public String uploadShareForLabRoom(HttpServletRequest request,
			HttpServletResponse response);
	
	/*********************************************************************************
	 * @description: 保存分享
	 * @author:张凯	
	 * @date：2017/07/07
	 ************************************************************************************/
	public AnnoucementList saveNewShare(Integer ID, String username, String title);
/*	
	*//*********************************************************************************
	 * @description: 上传文档
	 * @author:张凯	
	 * @date：2017/07/07
	 ************************************************************************************//*
	public AppPostImages uploadDocument(HttpServletRequest request,
			HttpServletResponse response);
	*/
	/*********************************************************************************
	 * @description: 分享列表
	 * @author:张凯	
	 * @date：2017/07/07
	 ************************************************************************************/
	public List<ShareList> getShareList(String username,HttpServletRequest request);
	
	/*********************************************************************************
	 * @description: 分享详情
	 * @author:张凯	
	 * @param request 
	 * @date：2017/07/07
	 ************************************************************************************/
	public ShareDetail shareDocument(Integer ID, HttpServletRequest request);
	
	/*********************************************************************************
	 * @description: 下载分享附件
	 * @author:张凯	
	 * @date：2017/07/07
	 ************************************************************************************/
	public String downloadDocument(Integer ID, HttpServletRequest request);
	
	/****************************************************************************
	 * 功能：查询学生的小组
	 * 作者：缪军
	 * 时间：2017-06-30
	 ****************************************************************************/
	public String getStudentGroup(String username);
	
	/****************************************************************************
	 * 功能：查询全部小组，和是否关注
	 * 作者：缪军
	 * 时间：2017-06-30
	 ****************************************************************************/
	public String getGroupList(String username);

	/****************************************************************************
	 * 功能：新建小组
	 * 作者：缪军
	 * 时间：2017-07-03
	 ****************************************************************************/
	public int newGroup(String username , String groupname);
	
	/****************************************************************************
	 * 功能：查找小组
	 * 作者：缪军
	 * 时间：2017-07-19
	 ****************************************************************************/
	public List<AppGroup> findGroup(String username , String groupname);
	
	/****************************************************************************
	 * 功能：关注小组
	 * 作者：缪军
	 * 时间：2017-07-03
	 ****************************************************************************/
	public int followStudentGroup(String username , Integer groupId , Integer param);
	
	/*********************************************************************************
	 * @description: 查看相册
	 * @author:缪军
	 * @date：2017/07/04
	 ************************************************************************************/
	public Album viewAlbum(Integer ID, HttpServletRequest request);
		
	/****************************************************************************
	 * 功能：新建相册
	 * 作者：缪军
	 * 时间：2017-07-04
	 ****************************************************************************/
	public int newAlbum(String title , String sponsor ,String imageList);
	
	/****************************************************************************
	 * 功能：下载相册
	 * 作者：缪军
	 * 时间：2017-07-05
	 ****************************************************************************/
	public void downloadAlbum(Integer id,HttpServletRequest request,HttpServletResponse response);
	
	/****************************************************************************
	 * 功能：根据路径下载文件
	 * 作者：缪军
	 * 时间：2017-07-05
	 ****************************************************************************/
	public void downloadFile(HttpServletRequest request,HttpServletResponse response, String filePath);
	
	/*********************************************************************************
	 * 功能：获取小组帖子列表
	 * 作者：缪军
	 * 时间：2017-07-06
	************************************************************************************/
	public List<NewPostList> getGroupPostlist(String username, Integer groupId, HttpServletRequest request);
	
	/****************************************************************************
	 * 功能：查询已经关注的小组
	 * 作者：缪军
	 * 时间：2017-07-07
	 ****************************************************************************/
	public List<AppGroup> getFollowedGroupList(String username);
	
		
	/****************************************************************************
	 * 功能：分组分别查询帖子列表
	 * 作者：缪军
	 * 时间：2017-07-07
	 ****************************************************************************/
	public List<NewPostList> getGroupListByGroup(String username,List<AppGroup> groups, HttpServletRequest request);
		
	/*********************************************************************************
	 * @description:获取班级帖子列表（随机）
	 * @author:缪军	2017/07/10
	 ************************************************************************************/
	public List<NewPostList> getPostlistRandom(Integer limit,String username,HttpServletRequest request);
		
	/*********************************************************************************
	 * @description: 上传图片
	 * @author:张凯	
	 * @date：2017/06/30
	 ************************************************************************************/
	public String uploadImageForLabRoom(HttpServletRequest request,
			HttpServletResponse response);
	
	/*********************************************************************************
	 * @description: 保存新建问卷
	 * @author:张凯	
	 * @date：2017/07/13
	 ************************************************************************************/
	public Integer saveNewQuestionnaire(String title, String description, List<String> text, Integer type);
	
	/*********************************************************************************
	 * @description: 保存问卷答案
	 * @author:唐钦邦
	 * @date：2017/08/09
	 ************************************************************************************/
	public Integer saveQuestionnaireAnswer(Integer ID,List<Integer> mAnswer);
	
	/*********************************************************************************
	 * @description: 获取问卷调查列表
	 * @author:张凯	
	 * @date：2017/07/13
	 ************************************************************************************/
	public String appQuestionNaireList();
	
	/*********************************************************************************
	 * @description: 获取问卷调查列表
	 * @author:张凯	
	 * @date：2017/07/13
	 ************************************************************************************/
	public Integer authorityPudge(String username);
	
	/*********************************************************************************
	 * @description: 获取问卷调查详情
	 * @author:张凯	
	 * @date：2017/07/14
	 ************************************************************************************/
	public QuestionNaireDetail getQuestionNaireDetail(Integer ID);

	/*********************************************************************************
	 * @description: 获取图片列表
	 * @author:赵昶	
	 * @date：2017/07/19
	 ************************************************************************************/
	public List<ShareAppPostReplyList> getAppPostReplyList(Integer iD);
/*	
	*//*********************************************************************************
	 * @description: 保存图片
	 * @author:唐钦邦
	 * @date：2017/08/16
	 ************************************************************************************//*
	public String saveImg(HttpServletRequest request,
			HttpServletResponse response);
*/
	
	/*********************************************************************************
	 * @description:获取实验室预约信息
	 * @author:孙广志	
	 * @date：2017/08/10
	 ************************************************************************************/
	public List<Appointment> getAppointmentList(String username,HttpServletRequest request);
	/*********************************************************************************
	 * @description:接收云盘文件夹信息(通过username)
	 * @author:孙广志	
	 * @date：2017/08/10
	 ************************************************************************************/
	public List<ReturnDownloadFolder> getDownloadFolderList(String username,HttpServletRequest request);
	/*********************************************************************************
	 * @description:接收云盘文件信息(username,文件夹id)
	 * @author:孙广志	
	 * @date：2017/08/10
	 ************************************************************************************/
	public List<ReturnDownloadFile> getDownloadFileList(String username,int id,HttpServletRequest request);
	/*********************************************************************************
	 * @description:获取新建文件夹信息
	 * @author:孙广志	
	 * @date：2017/08/14
	 ************************************************************************************/
	public List<NewFolder>getNewFolderList (String username, String folderName, HttpServletRequest request);
	/*********************************************************************************
	 * @description:保存上传文件
	 * @author:孙广志	
	 * @date：2017/08/16
	 ************************************************************************************/
	public String getSaveUploadFile (String username,int id,HttpServletRequest request,
			HttpServletResponse response);
	/*********************************************************************************
	 * @description:保存新建文件
	 * @author:孙广志	
	 * @date：2017/08/16
	 ************************************************************************************/
	public AppClouddickDownloadFile saveNewFile(String username,String path, int fromFolder,String fileNewName, int id);
	List<NewAlbum> getAlbumlist(String username, HttpServletRequest request);






}

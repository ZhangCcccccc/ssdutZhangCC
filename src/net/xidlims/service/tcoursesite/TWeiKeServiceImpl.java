package net.xidlims.service.tcoursesite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.dao.WkFolderDAO;
import net.xidlims.dao.WkCourseDAO;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.WkFolder;
import net.xidlims.domain.User;
import net.xidlims.domain.WkCourse;
import net.xidlims.domain.WkUpload;
import net.xidlims.service.common.ShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;




@Service("TWeiKeService")
public class TWeiKeServiceImpl implements  TWeiKeService {
	
	@Autowired
	private WkUploadService wkUploadService;
	@Autowired
	private WkFolderDAO folderDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private WkCourseDAO wkCourseDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	
	
	/*************************************************************************************
	 * Description:课程-我的课程与开放课程
	 * 
	 * @author： 裴继超
	 * @date：2016-11-24
	 *************************************************************************************/
	public List<TCourseSite> getSiteByUserAndIsOpen(TCourseSite tCourseSite, 
			Integer currpage, int pageSize){
		String sql = "select t from TCourseSite t where t.status = '1' ";
		if (tCourseSite!=null) {//判断查询条件是否为空
			if (tCourseSite.getTitle()!=null&&!"".equals(tCourseSite.getTitle())) {
				sql += " and t.title like '%"+tCourseSite.getTitle()+"%'";
			}
			if (tCourseSite.getSchoolTerm()!=null
					&&tCourseSite.getSchoolTerm().getId()!=null
					&&!"".equals(tCourseSite.getSchoolTerm().getId())) {//学期查询
				sql +=  " and t.schoolTerm.id ='"+tCourseSite.getSchoolTerm().getId()+"'";
			}
			if (tCourseSite.getUserByCreatedBy()!=null){//教师查询
				if(tCourseSite.getUserByCreatedBy().getCname()!=null&&tCourseSite.getUserByCreatedBy().getCname()!=""){
					sql += " and t.userByCreatedBy.cname like '%"+tCourseSite.getUserByCreatedBy().getCname()+"%'";
				}
			}
			sql += " and ( 1 != 1 ";
			//当前登录人
			User user = shareService.getUser();
			if(user!=null){
				sql += " or t.userByCreatedBy.username LIKE '"+user.getUsername()+"' ";
				sql += " or t.id in (select u.TCourseSite.id from TCourseSiteUser u WHERE u.user.username LIKE '"+user.getUsername()+"')";
			}
			if(tCourseSite.getIsOpen()!=null){
				sql += " or t.isOpen = " + tCourseSite.getIsOpen();
			}
			sql += ")";
			
	
		}
		sql += " order by t.id desc";
		return tCourseSiteDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
	}
	
}
package net.xidlims.service.device;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.domain.CommonDocument;
import net.xidlims.domain.LabRoomDeviceLending;
import net.xidlims.domain.LabRoomDeviceLendingResult;
import net.xidlims.domain.LabRoomDeviceRepair;
import net.xidlims.domain.LabRoomDeviceReservationResult;
import net.xidlims.domain.ResearchProject;
import net.xidlims.domain.SchoolDevice;
import net.xidlims.domain.LabRoomAdmin;
import net.xidlims.domain.LabRoomDevice;
import net.xidlims.domain.LabRoomDevicePermitUsers;
import net.xidlims.domain.LabRoomDeviceReservation;
import net.xidlims.domain.LabRoomDeviceTraining;
import net.xidlims.domain.LabRoomDeviceTrainingPeople;
import net.xidlims.domain.TimetableItemRelated;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableLabRelatedDevice;
import net.xidlims.domain.TimetableTeacherRelated;
import net.xidlims.domain.User;

 

public interface LabRoomDeviceService {

	/****************************************************************************
	 * 功能：保存实验分室设备
	 * 作者：李小龙
	 ****************************************************************************/
	public LabRoomDevice save(LabRoomDevice device);
	/****************************************************************************
	 * 功能：根据实验分室id查询实验分室的设备
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDevice> findLabRoomDeviceByRoomId(Integer id);
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备数量
	 * 作者：李小龙
	 ****************************************************************************/
	public int findAllLabRoomDeviceNew(LabRoomDevice device,Integer cid, Integer isReservation);
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备并分页
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDevice> findAllLabRoomDeviceNew(LabRoomDevice device,Integer cid, Integer page, int pageSize,Integer isReservation);
	
	/****************************************************************************
	 * 功能：删除实验室设备
	 * 作者：李小龙
	 ****************************************************************************/
	public void deleteLabRoomDevice(LabRoomDevice d);
	/****************************************************************************
	 * 功能：根据实验分室设备主键查询对象
	 * 作者：李小龙
	 ****************************************************************************/
	public LabRoomDevice findLabRoomDeviceByPrimaryKey(Integer id);
	
	/****************************************************************************
	 * 功能：根据实验室id查询管理员
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomAdmin> findLabRoomAdminByRoomId(Integer id,Integer typeId);
	
	/****************************************************************************
	 * 功能：根据实验室查询实验室的排课
	 * 作者：李小龙
	 ****************************************************************************/
	public List<TimetableLabRelated> findTimetableLabRelatedByRoomId(Integer id);
	
	/****************************************************************************
	 * 功能：查找实验室管理员
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<User> findAdminByLrid(Integer idKey);

	/****************************************************************************
	 * 功能：判断当前登录用户是否为实验室管理员
	 * 作者：李小龙
	 ****************************************************************************/
	public Boolean getLabRoomAdmin(Integer roomId, String username);
	
	
	/****************************************************************************
	 * 功能：上传设备图片
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:47:21
	 ****************************************************************************/
	public void deviceImageUpload(HttpServletRequest request,
			HttpServletResponse response, Integer id,int type);
	/****************************************************************************
	 * 功能：上传设备视频
	 * 作者：贺子龙
	 * 时间：2015-09-28 14:47:30
	 ****************************************************************************/
	public void deviceVideoUpload(HttpServletRequest request,
			HttpServletResponse response, Integer id);
	
	/****************************************************************************
	 * 功能：根据设备id查询设备的预约记录
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceReservation> findReservationListByDeviceId(
			Integer equinemtid);
	
	/****************************************************************************
	 * 功能：根据设备预约信息和审核状态查询设备预约并分页
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservation(
			LabRoomDeviceReservation reservation, int cId,Integer centerId, Integer page,
			int pageSize);
	
	/****************************************************************************
	 * 功能：根据设备预约信息和审核状态查询设备预约并分页(我的审核)
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservation(
			LabRoomDeviceReservation reservation, Integer cId,Integer centerId, Integer page,
			int pageSize,int tag);
	
	/****************************************************************************
	 * 功能：找出所有设备预约的实验室
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	public Map<Integer, String> findAllLabrooms(Integer centerId);
	
	/****************************************************************************
	 * 功能：根据设备预约信息和审核状态查询设备预约
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceReservation> findAllLabRoomDeviceReservation(
			LabRoomDeviceReservation reservation, Integer cId,Integer centerId);
	
	/****************************************************************************
	 * 功能：找出所有设备预约的申请人
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	public Map<String, String> findAllreserveUsers(Integer centerId);
	
	/****************************************************************************
	 * 功能：找出所有设备预约的指导老师
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	public Map<String, String> findAllTeachers(Integer centerId);
	
	/****************************************************************************
	 * 功能：找出所有设备预约的设备管理员
	 * 作者：贺子龙
	 * 时间：2015-10-10
	 ****************************************************************************/
	public Map<String, String> findAllManageUsers(Integer centerId);

	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备
	 * 作者：贺子龙
	 * 时间：2015-09-22 10:24:59
	 ****************************************************************************/
	public List<LabRoomDevice> findAllLabRoomDeviceNew(LabRoomDevice device,Integer cid) ;
	
	/**
	 * 查看所有设备名称
	 * @param cid
	 * @return
	 */
	public List<LabRoomDevice> findAllLabRoomDeviceNumbers(LabRoomDevice device,Integer cid);
	
	/****************************************************************************
	 * 功能：根据设备id查询培训
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceTraining> findLabRoomDeviceTrainingByDeviceId(LabRoomDeviceTraining train,
			Integer deviceId);
	
	/****************************************************************************
	 * 功能：根据培训id查询培训通过的人
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceTrainingPeople> findPassLabRoomDeviceTrainingPeopleByTrainId(
			Integer id) ;
	
	/****************************************************************************
	 * 功能：根据培训查询培训名单
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceTrainingPeople> findTrainingPeoplesByTrainingId(
			int id) ;
	
	/****************************************************************************
	 * 功能：保存设备培训
	 * 作者：李小龙
	 ****************************************************************************/
	public LabRoomDeviceTraining saveLabRoomDeviceTraining(
			LabRoomDeviceTraining train) ;
	
	/****************************************************************************
	 * 功能：根据培训查询培训名单--已通过的学生
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabRoomDeviceTrainingPeople> findTrainingPassPeoplesByTrainingId(
			int id) ;
	
	/****************************************************************************
	 * 功能：当前用户取消已经预约的培训
	 * 作者：贺子龙
	 ****************************************************************************/
	public void cancleTraining(int trainingId);
	
	/****************************************************************************
	 * 功能：判断用户是否通过安全准入
	 * 作者：李小龙
	 ****************************************************************************/
	public String securityAccess(String username, Integer id) ;
	
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备，添加所在实验室条件
	 * 作者：李小龙
	 ****************************************************************************/
	public int findAllLabRoomDevice(LabRoomDevice device,Integer cid, int roomId);
	
	/****************************************************************************
	 * 功能：根据user对象和学院编号查询所有学生
	 * 作者：贺子龙
	 * 时间：2015-11-05
	 ****************************************************************************/
	public int findStudentByCnameAndUsername(User user, String academyNumber);

	/****************************************************************************
	 * 功能：根据user对象和学院编号查询所有学生
	 * 作者：贺子龙
	 * 时间：2015-11-05
	 ****************************************************************************/
	public List<User> findStudentByCnameAndUsername(User user, String academyNumber, Integer page, int pageSize);
	
	/****************************************************************************
	 * 功能：根据设备主键查询设备被预约记录
	 * 作者：黄崔俊
	 * 时间：2016-5-18 10:07:45
	 ****************************************************************************/
	public List<LabRoomDeviceReservation> findLabRoomDeviceReservationInfoByDeviceId(
			Integer labRoomDeviceId, Integer currpage, int pageSize);
	
	/****************************************************************************
	 * 功能：根据设备主键统计设备被预约次数
	 * 作者：黄崔俊
	 * 时间：2016-5-18 10:07:52
	 ****************************************************************************/
	public int countLabRoomDeviceReservationInfoByDeviceId(
			Integer labRoomDeviceId);
	
	/****************************************************************************
	 * 功能：根据设备主键查询设备培训通过名单
	 * 作者：黄崔俊
	 * 时间：2016-5-18 10:07:52
	 ****************************************************************************/
	public List<LabRoomDevicePermitUsers> findPermitUserByDeivceId(
			Integer labRoomDeviceId, int currpage, int pageSize);
	
	/****************************************************************************
	 * 功能：根据设备id查询培训记录
	 * 作者：黄崔俊
	 * 时间：2016-5-19 16:46:40
	 ****************************************************************************/
	public List<LabRoomDeviceTraining> findLabRoomDeviceTrainingListByDeviceId(
			Integer labRoomDeviceId, Integer currpage, int pageSize);

	/****************************************************************************
	 * 功能：根据设备id查询培训记录数
	 * 作者：黄崔俊
	 * 时间：2016-5-19 16:46:40
	 ****************************************************************************/
	public int countLabRoomDeviceTrainingListByDeviceId(Integer labRoomDeviceId);
	
	/****************************************************************************
	 * 功能：保存设备的文档
	 * 作者：李小龙
	 ****************************************************************************/
	public void saveDeviceDocument(String fileTrueName, Integer id,int type);
	
	
	
	
	/****************************************************************************
	 * 功能：根据查询条件查询出所有的实验分室设备并分页，添加所在实验室条件
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabRoomDevice> findAllLabRoomDevice(LabRoomDevice device,Integer cid,
			Integer page, int pageSize,int roomId);
	/****************************************************************************
	 * 功能：根据设备id查询设备的预约记录
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceReservation> findLabRoomDeviceReservationByDeviceNumber(String deviceNumber);
	
	/****************************************************************************
	 * 功能：上传设备文档
	 * 作者：李小龙
	 ****************************************************************************/
	public void deviceDocumentUpload(HttpServletRequest request,
			HttpServletResponse response, Integer id,int type);
	/****************************************************************************
	 * 功能：根据学期查询培训
	 * 作者：李小龙
	 ****************************************************************************/
	public List<LabRoomDeviceTraining> findLabRoomDeviceTrainingByTermId(
			Integer termId,Integer id);
	/****************************************************************************
	 * 功能：根据实验室设备查询设备预约
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabRoomDeviceReservation> findLabRoomDeviceReservationByDeviceNumberAndCid(LabRoomDeviceReservation reservation, HttpServletRequest request,
			String deviceNumber, int page, int pageSize, int status, Integer cid);
	
	/****************************************************************************
	 * 功能：根据实验室设备查询设备预约--数量
	 * 作者：贺子龙
	 ****************************************************************************/
	public int countLabRoomDeviceReservationByDeviceNumberAndCid(LabRoomDeviceReservation reservation, HttpServletRequest request,
			String deviceNumber, int status, Integer cid);
	
	/****************************************************************************
	 * 功能：获取一个设备预约的使用机时数
	 * 作者：贺子龙
	 ****************************************************************************/
	public BigDecimal getReserveHoursOfReservation(LabRoomDeviceReservation labRoomDeviceReservation);
	
	
	/****************************************************************************
	 * 功能：根据设备预约信息和审核状态查询设备预约--数量
	 * 作者：贺子龙
	 ****************************************************************************/
	public int countAllLabRoomDeviceReservation(LabRoomDeviceReservation reservation, int cId,Integer centerId);
	
	
	/****************************************************************************
	 * 功能：保存新建的出借申请单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public void saveDeviceLendApply(LabRoomDeviceLending lrdl);
	/****************************************************************************
	 * 功能：查找所有设备借用单的记录总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getLabRoomLendsTotals(LabRoomDeviceLending lrdl);
	/****************************************************************************
	 * 功能：查找所有设备借用单的记录总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getLabRoomKeepsTotals(LabRoomDeviceLending lrdl);
	/****************************************************************************
	 * 功能：查找所有设备借用单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLending> findAllLabRoomLends(LabRoomDeviceLending lrdl,
			Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有设备领用单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLending> findAllLabRoomKeeps(LabRoomDeviceLending lrdl,
			Integer page, int pageSize);
	/****************************************************************************
	 * 功能：根据id查找设备借用单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public LabRoomDeviceLending findDeviceLendingById(Integer idKey);
	/****************************************************************************
	 * 功能：查找审核结果map
	 * 作者：李鹏翔
	 ****************************************************************************/
	public Map<Integer,String> getAuditResultMap();
	/****************************************************************************
	 * 功能：保存审核之后的借用单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public void saveAuditDeviceLending(LabRoomDeviceLendingResult lrdlr);
	/****************************************************************************
	 * 功能：查找所有审核通过的设备借用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getPassLendingTotals(LabRoomDeviceLendingResult lrdlr);
	/****************************************************************************
	 * 功能：查找所有审核通过的设备领用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getPassKeepingTotals(LabRoomDeviceLendingResult lrdlr);
	/****************************************************************************
	 * 功能：查找所有审核通过的设备借用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllPassLending(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有审核通过的设备领用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllPassKeeping(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有审核被拒绝的设备借用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getRejectedLendingTotals(LabRoomDeviceLendingResult lrdlr);
	/****************************************************************************
	 * 功能：查找所有审核被拒绝的设备领用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getRejectedKeepingTotals(LabRoomDeviceLendingResult lrdlr);
	/****************************************************************************
	 * 功能：查找所有审核通过的设备借用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllRejectedLending(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有审核通过的设备领用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllRejectedKeeping(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有审核被拒绝的设备借用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getReturnedLendingTotals(LabRoomDeviceLendingResult lrdlr);
	/****************************************************************************
	 * 功能：查找所有审核被拒绝的设备领用审核单总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getReturnedKeepingTotals(LabRoomDeviceLendingResult lrdlr);
	/****************************************************************************
	 * 功能：查找所有审核通过的设备借用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllReturnedLending(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有审核通过的设备领用审核单
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceLendingResult> findAllReturnedKeeping(
			LabRoomDeviceLendingResult lrdlr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：归还借出的设备
	 * 作者：李鹏翔
	 ****************************************************************************/
	public void returnDeviceLending(Integer idKey);
	/****************************************************************************
	 * 功能：查找所有维修列表总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getAllRepairTotals(LabRoomDeviceRepair lrdr);
	/****************************************************************************
	 * 功能：查找所有维修列表
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findAllRepairs(
			LabRoomDeviceRepair lrdr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找当前用户的设备报修或审核
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findAllRepairsByUser(String username);
	/****************************************************************************
	 * 功能：保存设备报修
	 * 作者：李鹏翔
	 ****************************************************************************/
	public void saveNewDeviceRepair(LabRoomDeviceRepair lrdr);
	/****************************************************************************
	 * 功能：查找所有报修列表总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getApplyRepairTotals(LabRoomDeviceRepair lrdr);
	/****************************************************************************
	 * 功能：查找所有报修列表
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findApplyRepairs(
			LabRoomDeviceRepair lrdr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有已修复列表总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getPassRepairTotals(LabRoomDeviceRepair lrdr);
	/****************************************************************************
	 * 功能：查找所有已修复列表
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findPassRepairs(
			LabRoomDeviceRepair lrdr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有未修复列表总数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getRejectedRepairTotals(LabRoomDeviceRepair lrdr);
	/****************************************************************************
	 * 功能：查找所有未修复列表
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<LabRoomDeviceRepair> findRejectedRepairs(
			LabRoomDeviceRepair lrdr, Integer page, int pageSize);
	/****************************************************************************
	 * 功能：查找所有借用记录条数
	 * 作者：李鹏翔
	 ****************************************************************************/
	public int getAllLendTotals();
	/****************************************************************************
	 * 功能：查找所有借用申请和审核
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<SchoolDevice> findAllLendingList(int curr , int size);
	/****************************************************************************
	 * 功能：查找当前用户的借用申请或审核
	 * 作者：李鹏翔
	 ****************************************************************************/
	public List<SchoolDevice> findLendingListByUser(String username);
	/****************************************************************************
	 * 功能：根据主键查询实验室设备预约
	 * 作者：李小龙
	 ****************************************************************************/
	public LabRoomDeviceReservation findlabRoomDeviceReservationByPrimaryKey(int id);
	/****************************************************************************
	 * 功能：根据设备预约id和username查询审核结果
	 * 作者：李小龙
	 ****************************************************************************/
	public LabRoomDeviceReservationResult findLabRoomDeviceReservationResult(int id, String username);
	/****************************************************************************
	 * 功能：根据预约id和导师查询非导师的审核结果(即查询实验室管理员的审核)
	 * 作者：李小龙
	 ****************************************************************************/
	public LabRoomDeviceReservationResult findManageAudit(int id, String username);
	/****************************************************************************
	 * 功能：下载设备文档（化工学院）
	 * 作者：李小龙
	 ****************************************************************************/
	public void downloadFile(CommonDocument doc, HttpServletRequest request,
			HttpServletResponse response);

	/****************************************************************************
	 * 功能：根据user对象和学院编号查询所有学生
	 * 作者：贺子龙
	 * 时间：2015-11-05
	 ****************************************************************************/
	public int findStudentByCnameAndUsername(User user, String academyNumber, Integer deviceId);
	/****************************************************************************
	 * 功能：根据user对象和学院编号查询所有学生
	 * 作者：贺子龙
	 * 时间：2015-11-05
	 ****************************************************************************/
	public List<User> findStudentByCnameAndUsername(User user, String academyNumber, Integer deviceId, Integer page, int pageSize);
	
	/****************************************************************************
	 * 功能：通过username和deviceId查询labRoomDevicePermitUser
	 * 作者：贺子龙
	 ****************************************************************************/
	public LabRoomDevicePermitUsers findPermitUserByUsernameAndDeivce(String username, int deviceId);
	
	/****************************************************************************
	 * 功能：通过deviceId查询labRoomDevicePermitUser
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabRoomDevicePermitUsers> findPermitUserByDeivceId(int deviceId, int page, int pageSize);
	
	/****************************************************************************
	 * 功能：删除labRoomDevicePermitUser
	 * 作者：贺子龙
	 ****************************************************************************/
	public void deletePermitUser(LabRoomDevicePermitUsers user);
	
	/****************************************************************************
	 * 功能：通过主键查询labRoomDevicePermitUser
	 * 作者：贺子龙
	 ****************************************************************************/
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersByPrimaryKey(int id);
	
	/****************************************************************************
	 * 功能：更新某一设备下所有预约的审核结果
	 * 作者：贺子龙
	 ****************************************************************************/
	public void updateReservationResult(int deviceId) throws ParseException;
	
	/****************************************************************************
	 * 功能：设备预约审核拒绝后放开被预约掉的时间段，使其设备可以重新预约该时间段
	 * 作者：贺子龙
	 ****************************************************************************/
	public void alterTimeAfterRefused(LabRoomDeviceReservation reservation, int flag) throws ParseException;
	
	/****************************************************************************
	 * 功能：查出某一中心下的所有设备管理员
	 * 作者：贺子龙
	 ****************************************************************************/
	public Map<String, String> findDeviceManagerByCid(int cid);
	
	/****************************************************************************
	 * 功能：根据设备编号查询实验室设备
	 * 作者：贺子龙
	 ****************************************************************************/
	public LabRoomDevice findLabRoomDeviceByDeviceNumber(String deviceNumber);
	
	/****************************************************************************
	 * 功能：找到当前用户的所有培训预约
	 * 作者：贺子龙
	 ****************************************************************************/
	public List<LabRoomDeviceTraining> findLabRoomDeviceTrainingByUser(Integer deviceId, Integer termId, int page, int pageSize);

	/****************************************************************************
	 * description：设备教学使用报表
	 * author：郑昕茹
	 ****************************************************************************/
	public List getListLabRoomDeviceUsageInAppointment(HttpServletRequest request, int page, int pageSize);
	
	/****************************************************************************
	 * description：设备教学使用报表-找到所有被排课用到的设备
	 * author：郑昕茹
	 ****************************************************************************/ 
		public List<TimetableLabRelatedDevice> getAllLabRoomDeviceUsageInAppointment();
		
		/****************************************************************************
		 * @description：设备教学使用报表-找到所有上课教师
		 * @author：郑昕茹
		****************************************************************************/ 
		public List<TimetableTeacherRelated> getAllTimetableRelatedTeachers( );
		
		/****************************************************************************
		 * @description：设备教学使用报表-找到所有排课相关的实验项目
		 * @author：郑昕茹
		****************************************************************************/ 
		public List<TimetableItemRelated> getAllTimetableRelatedItems( );
		
		/****************************************************************************
		 * description：设备教学使用报表-找到所有的课程名称
		 * author：郑昕茹
		 ****************************************************************************/ 
		public List getAllCoursesInAppointment(HttpServletRequest request);
		
		/*************************************************************************************
		 * @description：设备教学使用报表
		 * @author：郑昕茹
		 * @date：2016-10-25
		 *************************************************************************************/
		public void exportLabRoomDeviceUsageInAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception ;
		
		/*************************************************************************************
		 * @description：设备使用报表
		 * @author：郑昕茹
		 * @date：2016-10-25
		 *************************************************************************************/
		public void exportLabRoomDeviceUsage(HttpServletRequest request, HttpServletResponse response, LabRoomDeviceReservation reservation, Integer cid) throws Exception;
		
		/****************************************************************************
		 * @description：课题组管理-查询到所有的课题组
		 * @author：郑昕茹
		 * @date:2016-11-06
		 ****************************************************************************/
		public List<ResearchProject> findAllResearchProjects(ResearchProject researchProject, int currpage, int pageSize);
		
		/****************************************************************************
		 * @description：课题组管理-保存新建的课题组
		 * @author：郑昕茹
		 * @date:2016-11-06
		 ****************************************************************************/
		public ResearchProject saveResearchProject(ResearchProject researchProject);
		
		/****************************************************************************
		 * @description：课题组管理-删除课题组
		 * @author：郑昕茹
		 * @date:2016-11-06
		 ****************************************************************************/
		public boolean deleteResearchProject(int id);
		
		/****************************************************************************
		 * @description：课题组管理-根据主键找到课题组
		 * @author：郑昕茹
		 * @date:2016-11-06
		 ****************************************************************************/
		public ResearchProject findResearchProjectByPrimaryKey(int id);
		
		/****************************************************************************
		 * 功能：查出某一中心下的所有设备管理员
		 * 作者：贺子龙
		 ****************************************************************************/
		public Map<String, String> findDeviceManageCnamerByCid(int cid);
		
		/****************************************************************************
		 * 得到设备的使用时间、使用次数、收取费用
		 * @author 贺子龙
		 * 2016-07-18
		 ***************************************************************************/
		public void calculateHoursForSchoolDevice (String deviceNumber, String terms);
}
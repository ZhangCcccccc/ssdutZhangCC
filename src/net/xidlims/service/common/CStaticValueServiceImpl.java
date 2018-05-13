package net.xidlims.service.common;

import java.util.List;

import net.xidlims.dao.CStaticValueDAO;
import net.xidlims.dao.LabCenterDAO;
import net.xidlims.domain.CStaticValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CStaticValueService")
public class CStaticValueServiceImpl implements CStaticValueService {

	@Autowired
	private CStaticValueDAO cStaticValueDAO;
	@Autowired
	private LabCenterDAO labCenterDAO;
	@Autowired
	private ShareService shareService;
	
	/*
	 * Instantiates a new ShareServiceImpl.
	 */
	public CStaticValueServiceImpl() {
	}

	/***********************************************************************************************
	 * @功能：根据所选实验中心，获取该学院的实验室设备资源在排课中的对象
	 * @作者：魏诚
	 * @日期：2015-11-03
	 ***********************************************************************************************/
	@Override
	public CStaticValue getCStaticValueByTimetableLabDevice(int cid) {
		// 获取所选或所属的学院编号
		String academyNumber = "";
		if (cid != -1) {
			// 获取选择的实验中心
			academyNumber = labCenterDAO.findLabCenterById(cid).getSchoolAcademy().getAcademyNumber();
		} else {
			academyNumber = shareService.getUserDetail().getSchoolAcademy().getAcademyNumber();
		}

		// 获取实验室排课的通用配置对象；
		List<CStaticValue> cStaticValues = cStaticValueDAO
				.executeQuery("select c from CStaticValue c where c.academyNumber like '" + academyNumber
						+ "' and c.code ='timetable_lab_device'");
		if(cStaticValues.size()>0){
			return cStaticValues.get(0);
		}else{
			return null;
		}

	}

}
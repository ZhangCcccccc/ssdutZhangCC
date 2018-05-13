package net.xidlims.service.teaching;

import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentSectionDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.TAssignmentSection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TAssignmentSectionService")
public class TAssignmentSectionServiceImpl implements TAssignmentSectionService {

	@Autowired
	private TAssignmentSectionDAO tAssignmentSectionDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public TAssignmentSection findTAssignmentSectionById(Integer sectionId) {
		// TODO Auto-generated method stub
		TAssignmentSection tAssignmentSection = tAssignmentSectionDAO.findTAssignmentSectionById(sectionId);
		
		return tAssignmentSection;
	}

	@Override
	public TAssignmentSection saveExamSection(
			TAssignmentSection tAssignmentSection) {
		// TODO Auto-generated method stub
		TAssignmentSection newTAssignmentSection = null;
		if (tAssignmentSection.getId()==null) {
			tAssignmentSection.setTAssignment(tAssignmentDAO.findTAssignmentById(tAssignmentSection.getTAssignment().getId()));
			tAssignmentSection.setUser(userDAO.findUserByPrimaryKey(tAssignmentSection.getUser().getUsername()));
			newTAssignmentSection = tAssignmentSectionDAO.store(tAssignmentSection);
		}else {
			TAssignmentSection oldTAssignmentSection = tAssignmentSectionDAO.findTAssignmentSectionById(tAssignmentSection.getId());
			oldTAssignmentSection.setCreatedTime(tAssignmentSection.getCreatedTime());
			oldTAssignmentSection.setDescription(tAssignmentSection.getDescription());
			oldTAssignmentSection.setSequence(tAssignmentSection.getSequence());
			newTAssignmentSection = tAssignmentSectionDAO.store(oldTAssignmentSection);
		}
		return newTAssignmentSection;
	}

}

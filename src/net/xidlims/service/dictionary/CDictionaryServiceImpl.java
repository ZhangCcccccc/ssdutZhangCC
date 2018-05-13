package net.xidlims.service.dictionary;

import java.util.List;

import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.LabConstructionFundingAuditDAO;
import net.xidlims.dao.LabConstructionProjectAuditDAO;
import net.xidlims.dao.LabConstructionPurchaseAuditDAO;
import net.xidlims.dao.LabConstructionPurchaseDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabConstructionFundingAudit;
import net.xidlims.domain.LabConstructionProjectAudit;
import net.xidlims.domain.LabConstructionPurchase;
import net.xidlims.domain.LabConstructionPurchaseAudit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.inject.persist.Transactional;

@Service("CDictionaryService")
public class CDictionaryServiceImpl implements CDictionaryService{

	
	@Autowired
	CDictionaryDAO cDictionaryDAO;
	
	/**
	 * DAO injected by Spring that manages LabConstructionFundingAudit entities
	 * 
	 */
	@Autowired
	private LabConstructionFundingAuditDAO labConstructionFundingAuditDAO;

	/**
	 * DAO injected by Spring that manages LabConstructionProjectAudit entities
	 * 
	 */
	@Autowired
	private LabConstructionProjectAuditDAO labConstructionProjectAuditDAO;

	/**
	 * DAO injected by Spring that manages LabConstructionPurchaseAudit entities
	 * 
	 */
	@Autowired
	private LabConstructionPurchaseAuditDAO labConstructionPurchaseAuditDAO;

	/**
	 * DAO injected by Spring that manages LabConstructionPurchase entities
	 * 
	 */
	@Autowired
	private LabConstructionPurchaseDAO labConstructionPurchaseDAO;
	
	
	@Override
	public List<CDictionary> findallCType() {
		String s = "select u from CDictionary u where u.CCategory='c_agent_type'";
		return cDictionaryDAO.executeQuery(s,0,-1);
	}
	
	
	
	//新增
	/**
	 * Delete an existing LabConstructionFundingAudit entity
	 * 
	 */
	@Transactional
	public CDictionary deleteCDictionaryLabConstructionFundingAudits(Integer cdictionary_id, Integer related_labconstructionfundingaudits_id) {
		LabConstructionFundingAudit related_labconstructionfundingaudits = labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(related_labconstructionfundingaudits_id, -1, -1);

		CDictionary cdictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(cdictionary_id, -1, -1);

		related_labconstructionfundingaudits.setCDictionary(null);
		cdictionary.getLabConstructionFundingAudits().remove(related_labconstructionfundingaudits);

		labConstructionFundingAuditDAO.remove(related_labconstructionfundingaudits);
		labConstructionFundingAuditDAO.flush();

		return cdictionary;
	}
	
	/**
	 * Delete an existing LabConstructionProjectAudit entity
	 * 
	 */
	@Transactional
	public CDictionary deleteCDictionaryLabConstructionProjectAudits(Integer cdictionary_id, Integer related_labconstructionprojectaudits_id) {
		LabConstructionProjectAudit related_labconstructionprojectaudits = labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(related_labconstructionprojectaudits_id, -1, -1);

		CDictionary cdictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(cdictionary_id, -1, -1);

		related_labconstructionprojectaudits.setCDictionary(null);
		cdictionary.getLabConstructionProjectAudits().remove(related_labconstructionprojectaudits);

		labConstructionProjectAuditDAO.remove(related_labconstructionprojectaudits);
		labConstructionProjectAuditDAO.flush();

		return cdictionary;
	}
	
	/**
	 * Delete an existing LabConstructionPurchaseAudit entity
	 * 
	 */
	@Transactional
	public CDictionary deleteCDictionaryLabConstructionPurchaseAudits(Integer cdictionary_id, Integer related_labconstructionpurchaseaudits_id) {
		LabConstructionPurchaseAudit related_labconstructionpurchaseaudits = labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(related_labconstructionpurchaseaudits_id, -1, -1);

		CDictionary cdictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(cdictionary_id, -1, -1);

		related_labconstructionpurchaseaudits.setCDictionary(null);
		cdictionary.getLabConstructionPurchaseAudits().remove(related_labconstructionpurchaseaudits);

		labConstructionPurchaseAuditDAO.remove(related_labconstructionpurchaseaudits);
		labConstructionPurchaseAuditDAO.flush();

		return cdictionary;
	}
	
	/**
	 * Delete an existing LabConstructionPurchase entity
	 * 
	 */
	@Transactional
	public CDictionary deleteCDictionaryLabConstructionPurchases(Integer cdictionary_id, Integer related_labconstructionpurchases_id) {
		LabConstructionPurchase related_labconstructionpurchases = labConstructionPurchaseDAO.findLabConstructionPurchaseByPrimaryKey(related_labconstructionpurchases_id, -1, -1);

		CDictionary cdictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(cdictionary_id, -1, -1);

		related_labconstructionpurchases.setCDictionary(null);
		cdictionary.getLabConstructionPurchases().remove(related_labconstructionpurchases);

		labConstructionPurchaseDAO.remove(related_labconstructionpurchases);
		labConstructionPurchaseDAO.flush();

		return cdictionary;
	}
/**
	 * Save an existing LabConstructionFundingAudit entity
	 * 
	 */
	@Transactional
	public CDictionary saveCDictionaryLabConstructionFundingAudits(Integer id, LabConstructionFundingAudit related_labconstructionfundingaudits) {
		CDictionary cdictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(id, -1, -1);
		LabConstructionFundingAudit existinglabConstructionFundingAudits = labConstructionFundingAuditDAO.findLabConstructionFundingAuditByPrimaryKey(related_labconstructionfundingaudits.getId());

		// copy into the existing record to preserve existing relationships
		if (existinglabConstructionFundingAudits != null) {
			existinglabConstructionFundingAudits.setId(related_labconstructionfundingaudits.getId());
			existinglabConstructionFundingAudits.setUser(related_labconstructionfundingaudits.getUser());
			existinglabConstructionFundingAudits.setComments(related_labconstructionfundingaudits.getComments());
			related_labconstructionfundingaudits = existinglabConstructionFundingAudits;
		} else {
			related_labconstructionfundingaudits = labConstructionFundingAuditDAO.store(related_labconstructionfundingaudits);
			labConstructionFundingAuditDAO.flush();
		}

		related_labconstructionfundingaudits.setCDictionary(cdictionary);
		cdictionary.getLabConstructionFundingAudits().add(related_labconstructionfundingaudits);
		related_labconstructionfundingaudits = labConstructionFundingAuditDAO.store(related_labconstructionfundingaudits);
		labConstructionFundingAuditDAO.flush();

		cdictionary = cDictionaryDAO.store(cdictionary);
		cDictionaryDAO.flush();

		return cdictionary;
	}
	
	
	/**
	 * Save an existing LabConstructionProjectAudit entity
	 * 
	 */
	@Transactional
	public CDictionary saveCDictionaryLabConstructionProjectAudits(Integer id, LabConstructionProjectAudit related_labconstructionprojectaudits) {
		CDictionary cdictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(id, -1, -1);
		LabConstructionProjectAudit existinglabConstructionProjectAudits = labConstructionProjectAuditDAO.findLabConstructionProjectAuditByPrimaryKey(related_labconstructionprojectaudits.getId());

		// copy into the existing record to preserve existing relationships
		if (existinglabConstructionProjectAudits != null) {
			existinglabConstructionProjectAudits.setId(related_labconstructionprojectaudits.getId());
			existinglabConstructionProjectAudits.setComments(related_labconstructionprojectaudits.getComments());
			related_labconstructionprojectaudits = existinglabConstructionProjectAudits;
		} else {
			related_labconstructionprojectaudits = labConstructionProjectAuditDAO.store(related_labconstructionprojectaudits);
			labConstructionProjectAuditDAO.flush();
		}

		related_labconstructionprojectaudits.setCDictionary(cdictionary);
		cdictionary.getLabConstructionProjectAudits().add(related_labconstructionprojectaudits);
		related_labconstructionprojectaudits = labConstructionProjectAuditDAO.store(related_labconstructionprojectaudits);
		labConstructionProjectAuditDAO.flush();

		cdictionary = cDictionaryDAO.store(cdictionary);
		cDictionaryDAO.flush();

		return cdictionary;
	}
	
	
	/**
	 * Save an existing LabConstructionPurchaseAudit entity
	 * 
	 */
	@Transactional
	public CDictionary saveCDictionaryLabConstructionPurchaseAudits(Integer id, LabConstructionPurchaseAudit related_labconstructionpurchaseaudits) {
		CDictionary cdictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(id, -1, -1);
		LabConstructionPurchaseAudit existinglabConstructionPurchaseAudits = labConstructionPurchaseAuditDAO.findLabConstructionPurchaseAuditByPrimaryKey(related_labconstructionpurchaseaudits.getId());

		// copy into the existing record to preserve existing relationships
		if (existinglabConstructionPurchaseAudits != null) {
			existinglabConstructionPurchaseAudits.setId(related_labconstructionpurchaseaudits.getId());
			existinglabConstructionPurchaseAudits.setComments(related_labconstructionpurchaseaudits.getComments());
			related_labconstructionpurchaseaudits = existinglabConstructionPurchaseAudits;
		} else {
			related_labconstructionpurchaseaudits = labConstructionPurchaseAuditDAO.store(related_labconstructionpurchaseaudits);
			labConstructionPurchaseAuditDAO.flush();
		}

		related_labconstructionpurchaseaudits.setCDictionary(cdictionary);
		cdictionary.getLabConstructionPurchaseAudits().add(related_labconstructionpurchaseaudits);
		related_labconstructionpurchaseaudits = labConstructionPurchaseAuditDAO.store(related_labconstructionpurchaseaudits);
		labConstructionPurchaseAuditDAO.flush();

		cdictionary = cDictionaryDAO.store(cdictionary);
		cDictionaryDAO.flush();

		return cdictionary;
	}
/**
	 * Save an existing LabConstructionPurchase entity
	 * 
	 */
	@Transactional
	public CDictionary saveCDictionaryLabConstructionPurchases(Integer id, LabConstructionPurchase related_labconstructionpurchases) {
		CDictionary cdictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(id, -1, -1);
		LabConstructionPurchase existinglabConstructionPurchases = labConstructionPurchaseDAO.findLabConstructionPurchaseByPrimaryKey(related_labconstructionpurchases.getId());

		// copy into the existing record to preserve existing relationships
		if (existinglabConstructionPurchases != null) {
			existinglabConstructionPurchases.setId(related_labconstructionpurchases.getId());
			existinglabConstructionPurchases.setPurchaseReason(related_labconstructionpurchases.getPurchaseReason());
			existinglabConstructionPurchases.setUseLocation(related_labconstructionpurchases.getUseLocation());
			existinglabConstructionPurchases.setPurchaseTime(related_labconstructionpurchases.getPurchaseTime());
			existinglabConstructionPurchases.setAuditResults(related_labconstructionpurchases.getAuditResults());
			existinglabConstructionPurchases.setStage(related_labconstructionpurchases.getStage());
			related_labconstructionpurchases = existinglabConstructionPurchases;
		} else {
			related_labconstructionpurchases = labConstructionPurchaseDAO.store(related_labconstructionpurchases);
			labConstructionPurchaseDAO.flush();
		}

		related_labconstructionpurchases.setCDictionary(cdictionary);
		cdictionary.getLabConstructionPurchases().add(related_labconstructionpurchases);
		related_labconstructionpurchases = labConstructionPurchaseDAO.store(related_labconstructionpurchases);
		labConstructionPurchaseDAO.flush();

		cdictionary = cDictionaryDAO.store(cdictionary);
		cDictionaryDAO.flush();

		return cdictionary;
	}



	@Override
	public CDictionary finCDictionaryByPrimarykey(Integer id) {
		// TODO Auto-generated method stub
		CDictionary cDictionary = cDictionaryDAO.findCDictionaryByPrimaryKey(id);
		return cDictionary;
	}

	
	
	
}

package net.xidlims.service.dictionary;

import java.util.List;

import net.xidlims.domain.CDictionary;
import net.xidlims.domain.LabConstructionFundingAudit;
import net.xidlims.domain.LabConstructionProjectAudit;
import net.xidlims.domain.LabConstructionPurchase;
import net.xidlims.domain.LabConstructionPurchaseAudit;

public interface CDictionaryService {

	public List<CDictionary> findallCType();
	
	//新增
	/**
	 * Delete an existing LabConstructionFundingAudit entity
	 * 
	 */
	public CDictionary deleteCDictionaryLabConstructionFundingAudits(Integer cdictionary_id_3, Integer related_labconstructionfundingaudits_id);
/**
	 * Delete an existing LabConstructionProjectAudit entity
	 * 
	 */
	public CDictionary deleteCDictionaryLabConstructionProjectAudits(Integer cdictionary_id_2, Integer related_labconstructionprojectaudits_id);
/**
	 * Delete an existing LabConstructionPurchaseAudit entity
	 * 
	 */
	public CDictionary deleteCDictionaryLabConstructionPurchaseAudits(Integer cdictionary_id, Integer related_labconstructionpurchaseaudits_id);
/**
	 * Delete an existing LabConstructionPurchase entity
	 * 
	 */
	public CDictionary deleteCDictionaryLabConstructionPurchases(Integer cdictionary_id_1, Integer related_labconstructionpurchases_id);
/**
	 * Save an existing LabConstructionFundingAudit entity
	 * 
	 */
	public CDictionary saveCDictionaryLabConstructionFundingAudits(Integer id_2, LabConstructionFundingAudit related_labconstructionfundingaudits);

	/**
	 * Save an existing LabConstructionProjectAudit entity
	 * 
	 */
	public CDictionary saveCDictionaryLabConstructionProjectAudits(Integer id_3, LabConstructionProjectAudit related_labconstructionprojectaudits);

	/**
	 * Save an existing LabConstructionPurchase entity
	 * 
	 */
	public CDictionary saveCDictionaryLabConstructionPurchases(Integer id_4, LabConstructionPurchase related_labconstructionpurchases);
	/**
	 * Save an existing LabConstructionPurchaseAudit entity
	 * 
	 */
	public CDictionary saveCDictionaryLabConstructionPurchaseAudits(Integer id, LabConstructionPurchaseAudit related_labconstructionpurchaseaudits);

	
	public CDictionary finCDictionaryByPrimarykey(Integer id);
}

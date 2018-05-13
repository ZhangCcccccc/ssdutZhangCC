package app.xidlims;

/*********************************************************************************
 * @description:实验室预约信息
 * @author:孙广志	2017/08/10
 ************************************************************************************/
public class Appointment{
	private String labRoomName;//实验室名称
	private int auditResults;//审核信息

	public Appointment(String labRoomName, int auditResults) {
		super();
		this.labRoomName = labRoomName;
		this.auditResults = auditResults;
		
	}
	public String getLabRoomName() {
		return labRoomName;
	}
	public void setLabRoomName(String labRoomName) {
		this.labRoomName = labRoomName;
	}
	public int getAuditResults() {
		return auditResults;
	}
	public void setAuditResults(int auditResults) {
		this.auditResults = auditResults;
	}
	
}
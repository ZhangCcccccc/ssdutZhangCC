package app.xidlims;


/*********************************************************************************
 * @description:文件夹信息
 * @author:孙广志	2017/08/11
 ************************************************************************************/
public class ReturnDownloadFolder {
	private String folderName;//文件夹名称
	private int id;//文件夹id
	private String location;//文件夹地址
	
	
	
	
	public ReturnDownloadFolder(String folderName, int id, String location) {
		super();
		this.folderName = folderName;
		this.id = id;
		this.location = location;
		
		
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}

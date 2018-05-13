package app.xidlims;
/*********************************************************************************
 * @description:新建文件夹信息
 * @author:孙广志	2017/08/14
 ************************************************************************************/
public class NewFolder {
	private int id;//新建文件夹id
	private String folderName;//新建文件夹名字
	private String location;//新建文件夹地址路径
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public NewFolder(int id, String folderName, String location) {
		super();
		this.id = id;
		this.folderName = folderName;
		this.location = location;
	}
	
	

}

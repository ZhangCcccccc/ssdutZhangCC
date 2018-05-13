package app.xidlims;
/*********************************************************************************
 * @description:文件信息
 * @author:孙广志	2017/08/11
 ************************************************************************************/
public class ReturnDownloadFile {
	private String FileName;//文件名称
	private String location;//文件地址
	
	
	
	public ReturnDownloadFile(String fileName, String location) {
		super();
		FileName = fileName;
		this.location = location;
	}



	public String getFileName() {
		return FileName;
	}



	public void setFileName(String fileName) {
		FileName = fileName;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}
	

}

package app.xidlims;

import java.util.List;


/*********************************************************************************
 * @description:相册
 * @author:赵昶	2017/07/20
 ************************************************************************************/
public class NewAlbum {
	int ID;//相册id
	String title;//相册标题
	String imageList;//首张相册图片
	String sponsor;//相册分享者
	String time;//相册建立时间
	public NewAlbum(int ID, String title,String imageList, String sponsor, String time) {
	        this.ID = ID;
	        this.title = title;
	        this.sponsor = sponsor;
	        this.time = time;
	        this.imageList = imageList;
	      
	    }
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageList() {
		return imageList;
	}
	public void setImageList(String imageList) {
		this.imageList = imageList;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	

}

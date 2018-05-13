package app.xidlims;

import java.util.List;


/*********************************************************************************
 * @description:相册
 * @author:缪军	2017/07/04
 ************************************************************************************/
public class Album {
	int ID;//相册id
	String title;//相册标题
	List<String> imageList;//相册图片，格式有待商量
	String sponsor;//相册分享者
	int sum;//图片数量
	String time;//相册建立时间
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
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
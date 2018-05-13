package app.xidlims;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 张凯 on 2017/07/05.
 */
public class NewPostList{
	private int ID;//帖子id
	private String title;//帖子标题
	private int state;//帖子状态，0表示未读，1表示已读，2表示有更新，3表示正在审查（审查先不做）
	private int isStick;//是否置顶，置顶帖不显示图片
	private String sponsor;//帖子发起者
	private String time;//帖子时间
	private String imageList;//帖子第一张图片，格式有待商量



    public NewPostList(int ID, String title,int state, int isStick, String sponsor, String time, String imageList) {
        this.ID = ID;
        this.title = title;
        this.state = state;
        this.isStick = isStick;
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



	public int getState() {
		return state;
	}



	public void setState(int state) {
		this.state = state;
	}



	public int getIsStick() {
		return isStick;
	}



	public void setIsStick(int isStick) {
		this.isStick = isStick;
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



	public String getImageList() {
		return imageList;
	}



	public void setImageList(String imageList) {
		this.imageList = imageList;
	}


}
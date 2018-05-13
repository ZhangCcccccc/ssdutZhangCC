package app.xidlims;

import java.util.Calendar;
import java.util.List;

/**
 * Created by 张凯 on 2017/07/06.
 */
public class PostDetail{
	private String title;//帖子标题
	private String content;//帖子内容
	private String sponsor;//帖子发起者
	private String time;//帖子时间
	private List<String> imageList;//帖子第一张图片，格式有待商量
	private int isLike;//是否喜欢
	private int isCollect;//是否收藏



    public PostDetail( String title, String content, String sponsor, String time, List<String> imageList, int isLike, int isCollect) {
        this.title = title;
        this.content = content;
        this.sponsor = sponsor;
        this.time = time;
        this.imageList = imageList;
        this.isLike = isLike;
        this.isCollect = isCollect;
      
    }




	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public int getIsLike() {
		return isLike;
	}



	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}



	public int getIsCollect() {
		return isCollect;
	}



	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
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



	public List<String> getImageList() {
		return imageList;
	}



	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}


}
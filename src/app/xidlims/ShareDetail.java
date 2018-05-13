package app.xidlims;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 张凯 on 2017/07/05.
 */
public class ShareDetail{
	private int ID;//分享id
	private String title;//分享标题
	private String sharer;//分享人
	private String time;//分享时间时间
	private List<String> docName;//分享附件名称
	private List<String> docUrl;//分享附件路径
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
	public String getSharer() {
		return sharer;
	}
	public void setSharer(String sharer) {
		this.sharer = sharer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<String> getDocName() {
		return docName;
	}
	public void setDocName(List<String> docName) {
		this.docName = docName;
	}
	public List<String> getDocUrl() {
		return docUrl;
	}
	public void setDocUrl(List<String> docUrl) {
		this.docUrl = docUrl;
	}
	public ShareDetail(int iD, String title, String sharer, String time,
			List<String> docName, List<String> docUrl) {
		super();
		ID = iD;
		this.title = title;
		this.sharer = sharer;
		this.time = time;
		this.docName = docName;
		this.docUrl = docUrl;
	}


    

}
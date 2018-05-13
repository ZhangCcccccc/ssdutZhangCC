package app.xidlims;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张凯 on 2017/07/14.
 */
public class AnnoucementList{
	private Integer id;
    private String title;//标题
    private String content;//内容
    private String time;//时间
    private String sponsor;//发起人


    public AnnoucementList(Integer id, String title, String content, String time,String sponsor) {
       this.id = id;
       this.content = content;
       this.sponsor = sponsor;
       this.time = time;
       this.title = title;
      
    }
    public AnnoucementList() {
    
       
     }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getSponsor() {
		return sponsor;
	}


	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

    
}
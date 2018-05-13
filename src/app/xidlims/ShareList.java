package app.xidlims;

import java.util.List;

/**
 * Created by 赵昶 on 2017/07/19.
 */
public class ShareList{
	private Integer id;
    private String title;//标题
    private String time;//时间
    private String sponsor;//发起人
    private List<String> Enclosure;


    public List<String> getEnclosure() {
		return Enclosure;
	}


	public void setEnclosure(List<String> enclosure) {
		Enclosure = enclosure;
	}


	public ShareList(Integer id, String title, String time,String sponsor,List<String> Enclosure) {
       this.id = id;
       this.sponsor = sponsor;
       this.time = time;
       this.title = title;
       this.Enclosure=Enclosure;
      
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
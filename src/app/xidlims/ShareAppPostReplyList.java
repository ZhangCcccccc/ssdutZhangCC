package app.xidlims;
/**
 * Created by 赵昶  on 2017/07/19.
 */

//SELECT r.id,r.to_discussion_in_id,r.sponsor,r.time,r.`comment`,r.to_response_id "
public class ShareAppPostReplyList{
	private Integer id;
	private Integer discussionId;//帖子id
	private String sponsor;//发起人	
	private String time;//时间
	private String comment;//回复内容	
    private Integer responseId;//回应id
   



    public ShareAppPostReplyList(Integer id,Integer discussionId,String sponsor,String time,String comment,Integer responseId ) {
       this.id = id;
       this.discussionId=discussionId;
       this.sponsor = sponsor;
       this.time = time;
       this.comment=comment;
       this.responseId=responseId;
      
    }




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Integer getDiscussionId() {
		return discussionId;
	}




	public void setDiscussionId(Integer discussionId) {
		this.discussionId = discussionId;
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




	public String getComment() {
		return comment;
	}




	public void setComment(String comment) {
		this.comment = comment;
	}




	public Integer getResponseId() {
		return responseId;
	}




	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}


	

    
}
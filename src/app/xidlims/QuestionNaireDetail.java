package app.xidlims;

import java.util.Calendar;
import java.util.List;

/**
 * Created by 张凯 on 2017/07/06.
 */
public class QuestionNaireDetail{
	private String title;//问卷题目
	private List<String> text;//问卷选项
	private int type;//题目类型



    public QuestionNaireDetail( String title, List<String> text, int type) {
        this.title = title;
        this.text = text;
        this.type = type;
      
    }


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}


	public List<String> getText() {
		return text;
	}


	public void setText(List<String> text) {
		this.text = text;
	}

}
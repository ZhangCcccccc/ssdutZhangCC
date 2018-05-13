package app.xidlims;

import java.util.List;

public class NewQuestionnaire {
	String title;
	String description;
	int type;
	List<String> text;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<String> getText() {
		return text;
	}
	public void setText(List<String> text) {
		this.text = text;
	}
	public NewQuestionnaire(String title, String description, int type,
			List<String> text) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.text = text;
	}
	public NewQuestionnaire() {
		super();
	}

	
	
	

}

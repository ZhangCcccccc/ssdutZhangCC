package app.xidlims;

import java.util.List;

public class SaveQuestionnaire {
	int ID;
	List<Integer> mAnswer;
	
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public List<Integer> getMAnswer() {
		return mAnswer;
	}
	public void setMAnswer(List<Integer> mAnswer) {
		this.mAnswer = mAnswer;
	}
	public SaveQuestionnaire( int ID,
			List<Integer> mAnswer) {
		super();
		this.ID = ID;
		this.mAnswer = mAnswer;
	}
	public SaveQuestionnaire() {
		super();
	}

	
	
	

}

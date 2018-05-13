package app.xidlims;

/**
 * Created by 典雅 on 2016/11/10.
 */
public class LearningChapter {
    private int ID;
    private String name;//章节名
    public LearningChapter(int ID,String name){
        this.ID=ID;
        this.name=name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}

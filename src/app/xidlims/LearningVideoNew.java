package app.xidlims;

/**
 * Created by 典雅 on 2016/11/29.
 */
public class LearningVideoNew {
    private int ID;
    private String name;//视频名称
    private int IDOfChapter;//属于哪个章节
    private String nameOfChapter;//属于哪个章节
    public LearningVideoNew(int ID,String name, int IDOfChapter,String nameOfChapter){
        this.ID=ID;
        this.name=name;
        this.IDOfChapter=IDOfChapter;
        this.nameOfChapter=nameOfChapter;

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setIDOfChapter(int IDOfChapter) {
        this.IDOfChapter = IDOfChapter;
    }

   

    public void setName(String name) {
        this.name = name;
    }

    public void setNameOfChapter(String nameOfChapter) {
        this.nameOfChapter = nameOfChapter;
    }

    public int getID() {
        return ID;
    }

    public int getIDOfChapter() {
        return IDOfChapter;
    }

    public String getName() {
        return name;
    }

    public String getNameOfChapter() {
        return nameOfChapter;
    }
}

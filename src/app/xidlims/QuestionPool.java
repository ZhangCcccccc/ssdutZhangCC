package app.xidlims;

/**
 * Created by 张凯  on 2017/07/11.
 */
public class QuestionPool {
    private int ID;
    private  String title;

    public  QuestionPool(int ID,String title){
        this.ID=ID;
        this.title=title;
       
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

}

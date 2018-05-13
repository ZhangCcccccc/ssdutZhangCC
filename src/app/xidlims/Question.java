package app.xidlims;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 典雅 on 2016/11/14.
 */
public class Question{
    private int id;
    private String description;//题干
    private List<String> text = new ArrayList();//选项
    private List<Integer> answer = new ArrayList();//答案
    private int type;//题目类型，1为多选，4为单选


    public Question(int id, String description, List<String> text, List<Integer> answer, int type) {
        this.id = id;
        this.description = description;
        this.text = text;
        this.answer = answer;
        this.type = type;
      
    }


    public String getDescription() {
        return description;
    }

   
    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public List<String> getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public void setType(int type) {
        this.type = type;
    }
}
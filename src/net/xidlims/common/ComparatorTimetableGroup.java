package net.xidlims.common;
//具体的比较类，实现Comparator接口

import java.util.Comparator;

import net.xidlims.domain.TimetableGroup;

@SuppressWarnings("rawtypes")
public class ComparatorTimetableGroup implements Comparator{

 public int compare(Object arg0, Object arg1) {
	 TimetableGroup group0=(TimetableGroup)arg0;
	 TimetableGroup group1=(TimetableGroup)arg1;
	 //首先比较分批号，如果分批号相同，则比较名字
	 int flag=group0.getTimetableBatch().getId().compareTo(group1.getTimetableBatch().getId());
	 if(flag==0){
		 return group0.getGroupName().compareTo(group1.getGroupName());
	 }else{
		 return flag;
	 }  
 	}
 
}
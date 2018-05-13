package net.xidlims.service.system;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import net.xidlims.domain.CDictionary;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;

public interface TermDetailService {

	/*************************************************************************************
	 * @內容：获取学期的总记录数
	 * @作者： 叶明盾
	 * @日期：2014-07-28
	 *************************************************************************************/
	public int getTermTotalRecords();
	
	/*************************************************************************************
	 * @內容：查找所有的学期信息
	 * @作者： 叶明盾
	 * @日期：2014-07-28
	 *************************************************************************************/
	public Set<SchoolTerm> findAllTerms(int curr, int size);
	
	/*************************************************************************************
	 * @內容：根据学期生成周次
	 * @作者： 叶明盾
	 * @日期：2014-07-29
	 *************************************************************************************/
	public void createWeek(SchoolTerm schoolTerm);
	
	/*************************************************************************************
	 * @內容：根据学期的名称查找学期
	 * @作者： 叶明盾
	 * @日期：2014-07-29
	 *************************************************************************************/
	public Set<SchoolTerm> findTermsByTermName(String termName);
	
	/*************************************************************************************
	 * @內容：根据学期的id查找学期
	 * @作者： 叶明盾
	 * @日期：2014-08-06
	 *************************************************************************************/
	public SchoolTerm findTermById(Integer id);
	/************************************************************ 
	 * @内容：删除学期
	 * @作者: 李鹏翔
	 ************************************************************/
	public void deleteTerm(SchoolTerm st);
	/************************************************************ 
	 * @内容：保存学期
	 * @作者: 李鹏翔
	 ************************************************************/
	public SchoolTerm saveTerm(SchoolTerm st);
	
	
	/************************************************************ 
	 * description:找到字典表中关于特殊周次的描述
	 * @author:郑昕茹
	 * @date: 2017-04-06
	 ************************************************************/
    public List<CDictionary> findCTeachingDateType();

    
    /************************************************************ 
	 * description:根据时间找到唯一SchoolWeek
	 * @author:郑昕茹
	 * @date: 2017-04-06
	 ************************************************************/
    public SchoolWeek findSchoolWeekByDate(String date);
    
    /************************************************************ 
	 * description:根据日期和放假类型和学期新建一条SchoolWeek记录(参数type为CDictionary的cTeachingDateType)
	 * @author:郑昕茹
	 * @date: 2017-04-06
	 ************************************************************/
    public void createSpecialWeekByDateAndTypeAndTerm(String startDate,String endDate, Integer type, SchoolTerm schoolTerm)throws ParseException;
    
    /*************************************************************
     * description:根据日期和放假类型和学期新建一条SchoolWeek记录(参数type为CDictionary的id)
     * 
     ************************************************************/
    public void createSpecialWeekByDateAndTypeAndTerm1(String startDate,String endDate, Integer type, SchoolTerm schoolTerm)throws ParseException;
    
    /************************************************************ 
   	 * description:根据cTeachingDateType找到对应的Cdictionary
   	 * @author:郑昕茹
   	 * @date: 2017-04-06
   	 ************************************************************/
    public CDictionary findCDictionaryByTypeId(Integer type);
    
    /************************************************************ 
   	 * description:根据学期找到其下的特殊日期的起止时间
   	 * @author:郑昕茹
   	 * @date: 2017-04-06
   	 ************************************************************/
    public List<Object[]> findSchoolWeeksStartAndEnd(Integer termId);
    

    /************************************************************ 
	 * description:根据学期找到其下的所有schoolWeek并删除
	 * @author:郑昕茹
	 * @date: 2017-04-06
	 ************************************************************/
    public void findSchoolWeekByTermAndDelete(Integer termId);
    
    /************************************************************ 
   	 * description:根据学期找到其下的所有SchoolTermActives并删除
   	 * @author:郭明杰
   	 * @date: 2017-08-015
   	 ************************************************************/
    public void deleteAllSchoolTermActives(Integer termId);
    
    /************************************************************ 
  	 * description:根据学期找到该学期的最大最小周次，最大最小年份，最大最小月份
  	 * @author:郑昕茹
  	 * @date: 2017-04-07
  	 ************************************************************/
   public List<Object[]> findViewSchoolTermWeek(Integer termId);
   
   

   /************************************************************ 
  	 * description:根据学期找到其下的所有特殊的schoolWeek
  	 * @author:郑昕茹
  	 * @date: 2017-04-07
  	 ************************************************************/
      public List<SchoolWeek> findSpecialSchoolWeekByTerm(Integer termId);
}
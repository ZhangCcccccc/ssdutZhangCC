package net.xidlims.service.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.xidlims.dao.CDictionaryDAO;
import net.xidlims.dao.SchoolTermActiveDAO;
import net.xidlims.dao.SchoolTermDAO;
import net.xidlims.dao.SchoolWeekDAO;
import net.xidlims.domain.CDictionary;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.SchoolWeek;
import net.xidlims.domain.SchoolTermActive;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;
@Service("TermDetailService")
public class TermDetailServiceImpl implements TermDetailService {
	@Autowired
	private SchoolTermDAO schoolTermDAO;
	
	@Autowired
	private SchoolWeekDAO schoolWeekDAO;
	@Autowired
	private CDictionaryDAO cDictionaryDAO;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private SchoolTermActiveDAO schoolTermActiveDAO; 
	/*************************************************************************************
	 * @內容：获取学期的总记录数
	 * @作者： 叶明盾
	 * @日期：2014-07-28
	 *************************************************************************************/
	@Transactional
	public int getTermTotalRecords(){
		Set<SchoolTerm> terms = schoolTermDAO.findAllSchoolTerms();
		return terms.size();
	}
	
	/*************************************************************************************
	 * @內容：查找所有的学期信息
	 * @作者： 叶明盾
	 * @日期：2014-07-28
	 *************************************************************************************/
	public Set<SchoolTerm> findAllTerms(int curr, int size){
		return schoolTermDAO.findAllSchoolTerms(curr*size, size);
	}
	
	/*************************************************************************************
	 * @內容：根据学期生成周次
	 * @作者： 叶明盾
	 * @日期：2014-07-29
	 *************************************************************************************/
	@Transactional
	public void createWeek(SchoolTerm schoolTerm){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//判断学期创建时间是否为空，如果为空则设置当前时间
		if(schoolTerm.getCreatedAt() == null )
			schoolTerm.setCreatedAt(Calendar.getInstance());
		//设置更新时间为当前时间
			schoolTerm.setUpdatedAt(Calendar.getInstance());
			schoolTerm.setFlag(1);
		//保存schoolTerm
			 SchoolTerm term = schoolTermDAO.store(schoolTerm);
		//获取term的schoolWeek的集合
			 Set<SchoolWeek> schoolWeeks = term.getSchoolWeeks();
		//新建一个SchoolWeek的list对象
			 List<SchoolWeek> weeks=new ArrayList<SchoolWeek>();
		//将schoolWeeks添加到weeks中
			 weeks.addAll(schoolWeeks);
		//如果schoolWeeks的大小不为0则将这记录删除；
			 if(weeks.size() > 0) {
				 //for循环weeks删除schoolWeek
				 for(SchoolWeek schoolWeek:weeks){
					 if(schoolWeek.getCDictionary() == null)
					 schoolWeekDAO.remove(schoolWeek);
				 }
			 }
		//得到学期的开始时间
			 Calendar termStart = term.getTermStart();
		//得到学期的结束时间
			 Calendar termEnd = term.getTermEnd();
		//获取当天周内天数
			 int current = termStart.get(Calendar.DAY_OF_WEEK);
		//获取周开始基准
			 int min = termStart.getActualMinimum(Calendar.DAY_OF_WEEK);
		//将termStart转成Date型
			 Date date2 =  termStart.getTime();
		//设置一个日期变量
			 Calendar now = termStart;
		//当天-基准，获取周开始日期
			 now.add(Calendar.DAY_OF_WEEK, min-current+1);
		//得到两个日期相隔的时间数；
			 long betTimes=termEnd.getTime().getTime()-now.getTime().getTime();
		//得到两个日期相隔的天数；
			 int betDays = (int)(betTimes/86400000);
		//算出该学期有多少周；
			 int week = betDays/7+1;
		//循环保存该学期的周次；
			 for(int i=1; i<week+1; i++){
				 for(int j=1; j<8; j++){
					//获取所需要加的天数；
					 int weekSum=(i-1)*7+(j-1);
					//新建一个week对象；
					SchoolWeek schoolWeek=new SchoolWeek();					
					 //设置schoolWeek的schoolTerm为term；
					schoolWeek.setSchoolTerm(term);
					//设置schoolWeek的week为i；
					schoolWeek.setWeek(i);
					Calendar cal= Calendar.getInstance();
					cal.setTime(date2);
					cal.add(Calendar.DATE, weekSum);
					 //设置schoolWeek的Date；
					schoolWeek.setDate(cal);
					//获取cal在周内的天数；
					int dayWeek=cal.get(Calendar.DAY_OF_WEEK);
					//如果dayWeek不等于1;则设置schoolWeek的weekDay为dayWeek减1；
					if(dayWeek!=1){
			        	   schoolWeek.setWeekday(dayWeek-1);
			           }else{
			        	   schoolWeek.setWeekday(7);
			           }				  
					   //如果创建时间为空，则设置为当前时间；
							if(schoolWeek.getCreatedAt()==null)
								schoolWeek.setCreatedAt(Calendar.getInstance());
							//设置更新时间为当前时间；
								schoolWeek.setUpdatedAt(Calendar.getInstance());
						if(this.findSchoolWeekByDate(sdf.format(cal.getTime())) == null)
						{
							schoolWeekDAO.store(schoolWeek);						 
						}
				 }			 
			 }
	}
	
	/*************************************************************************************
	 * @內容：根据学期的名称查找学期
	 * @作者： 叶明盾
	 * @日期：2014-07-29
	 *************************************************************************************/
	public Set<SchoolTerm> findTermsByTermName(String termName)
	{
		return schoolTermDAO.findSchoolTermByTermName(termName);
	}
	
	/*************************************************************************************
	 * @內容：根据学期的id查找学期
	 * @作者： 叶明盾
	 * @日期：2014-08-06
	 *************************************************************************************/
	public SchoolTerm findTermById(Integer id)
	{
		SchoolTerm schoolTerm = schoolTermDAO.findSchoolTermById(id);
		return schoolTerm;		
	}

	/************************************************************ 
	 * @内容：删除学期
	 * @作者: 李鹏翔
	 ************************************************************/
	public void deleteTerm(SchoolTerm st){
		schoolTermDAO.remove(st);
		schoolTermDAO.flush();
	}
	/************************************************************ 
	 * @内容：删除学期
	 * @作者: 李鹏翔
	 ************************************************************/
	public SchoolTerm saveTerm(SchoolTerm st){
		if(st.getCreatedAt()==null){
			st.setCreatedAt(Calendar.getInstance());
		}
		st.setUpdatedAt(Calendar.getInstance());
		st = schoolTermDAO.store(st);
		schoolTermDAO.flush();
		return st;
	}
	
	/************************************************************ 
	 * description:找到字典表中关于特殊周次的描述
	 * @author:郑昕茹
	 * @date: 2017-04-06
	 ************************************************************/
    public List<CDictionary> findCTeachingDateType(){
    	String sql = "select c from CDictionary c where 1=1 and c.cTeachingDateType is not null";
    	return cDictionaryDAO.executeQuery(sql, 0,-1);
    }
    
	/************************************************************ 
	 * description:根据时间找到唯一SchoolWeek
	 * @author:郑昕茹
	 * @date: 2017-04-06
	 ************************************************************/
    public SchoolWeek findSchoolWeekByDate(String date){
    	String sql = "select s from SchoolWeek s where 1=1 and date ='"+date+"'";
    	List<SchoolWeek> schoolWeeks = schoolWeekDAO.executeQuery(sql,0,-1);
    	if(schoolWeeks != null && schoolWeeks.size() != 0){
    		return schoolWeeks.get(0);
    	}
    	return null;
    }
    
    /************************************************************ 
   	 * description:根据日期和放假类型和学期新建一条SchoolWeek记录和保存schoolTermActive。
   	 * @author:郑昕茹
     * @throws ParseException 
   	 * @date: 2017-04-06
   	 ************************************************************/
       public void createSpecialWeekByDateAndTypeAndTerm(String startDate,String endDate, Integer type, SchoolTerm term) throws ParseException{
    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	   Calendar startCal = Calendar.getInstance();
    	   startCal.setTime(sdf.parse(startDate));
    	   Calendar endCal = Calendar.getInstance();
    	   endCal.setTime(sdf.parse(endDate));
     	  
    	   SchoolWeek schoolWeek = new SchoolWeek();
    	  // term.setSchoolTermActives(schoolTermActives);
    	   schoolWeek.setCDictionary(this.findCDictionaryByTypeId(type));
    	   SchoolTermActive schoolTermActive= new SchoolTermActive();
    	   schoolTermActive.setSchoolTerm(schoolTermDAO.findSchoolTermById(term.getId()));
    	   schoolTermActive.setCDictionary(this.findCDictionaryByTypeId(type));
    	   schoolTermActive.setActiveStarttime(startCal);
    	   schoolTermActive.setActiveFinishtime(endCal);
    	   schoolTermActiveDAO.store(schoolTermActive);
    	   Calendar weekCal = startCal;
    	   while(weekCal.before(endCal) || weekCal.equals(endCal)){
    		   Calendar termCal = term.getTermStart();
    		   

        	   if(this.findSchoolWeekByDate(sdf.format(weekCal.getTime())) != null){
        		   
        		   schoolWeek = this.findSchoolWeekByDate(sdf.format(weekCal.getTime()));
        		   schoolWeek.setCDictionary(this.findCDictionaryByTypeId(type));
        	   }
        	   schoolWeek.setDate(weekCal);
        	   if(weekCal.get(Calendar.DAY_OF_WEEK) != 1)
        	   {
        		   schoolWeek.setWeekday(weekCal.get(Calendar.DAY_OF_WEEK)-1);
        	   }
        	   if(weekCal.get(Calendar.DAY_OF_WEEK) == 1)
        	   {
        		   schoolWeek.setWeekday(7);
        	   }
        	   schoolWeek.setCreatedAt(Calendar.getInstance());
        	   schoolWeek.setUpdatedAt(Calendar.getInstance());
        	   schoolWeek.setSchoolTerm(term);
        	  // schoolWeek.setCDictionary(this.findCDictionaryByTypeId(type));
        	   Integer between_days = Integer.parseInt(String.valueOf((weekCal.getTimeInMillis() - termCal.getTimeInMillis())/(1000*3600*24)));
        	   Integer between_weeks = between_days/7;
        	   Integer remain_days = between_days%7;
        	   System.out.print(termCal.get(Calendar.DAY_OF_WEEK));
        	   schoolWeek.setWeek(between_weeks+1);
        	   schoolWeekDAO.store(schoolWeek);
        	   weekCal.add(Calendar.DAY_OF_YEAR,1);
    	   }
    	   
       }
       
       /************************************************************
       description:根据日期和放假类型和学期新建一条SchoolWeek记录和新建保存schoolTermActive。
   	 * @author:郭明杰
     * @throws ParseException 
   	 * @date: 2017-08-15
        *************************************************************/
       
       public void createSpecialWeekByDateAndTypeAndTerm1(String startDate,String endDate, Integer type, SchoolTerm term) throws ParseException{
    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	   SchoolTermActive schoolTermActive= new SchoolTermActive();
    	   schoolTermActive.setSchoolTerm(schoolTermDAO.findSchoolTermById(term.getId()));
    	   Calendar startCal = Calendar.getInstance();
    	   startCal.setTime(sdf.parse(startDate));
    	   schoolTermActive.setActiveStarttime(startCal);
    	   
    	   Calendar endCal = Calendar.getInstance();
    	   endCal.setTime(sdf.parse(endDate));
    	   schoolTermActive.setActiveFinishtime(endCal);
    	   SchoolWeek schoolWeek = new SchoolWeek();
    	   schoolWeek.setCDictionary(cDictionaryDAO.findCDictionaryById(type));
    	   schoolTermActive.setCDictionary(cDictionaryDAO.findCDictionaryById(type));
    	   schoolTermActiveDAO.store(schoolTermActive);
    	   Calendar weekCal = startCal;
    	   //保存特殊日期date
    	   while(weekCal.before(endCal) || weekCal.equals(endCal)){
    		   Calendar termCal = term.getTermStart();
        	   
        	   if(this.findSchoolWeekByDate(sdf.format(weekCal.getTime())) != null){
        		   schoolWeek = this.findSchoolWeekByDate(sdf.format(weekCal.getTime()));
        		   schoolWeek.setCDictionary(cDictionaryDAO.findCDictionaryById(type));
        		   schoolTermActive.setCDictionary(cDictionaryDAO.findCDictionaryById(type));
        	   }
        	   schoolWeek.setDate(weekCal);
        	   if(weekCal.get(Calendar.DAY_OF_WEEK) != 1)
        	   {
        		   schoolWeek.setWeekday(weekCal.get(Calendar.DAY_OF_WEEK)-1);
        	   }
        	   if(weekCal.get(Calendar.DAY_OF_WEEK) == 1)
        	   {
        		   schoolWeek.setWeekday(7);
        	   }
        	   schoolWeek.setCreatedAt(Calendar.getInstance());
        	   //schoolWeek.setCreatedAt(startCal);
        	  schoolWeek.setUpdatedAt(Calendar.getInstance());
        	   schoolWeek.setSchoolTerm(term);
        	   //schoolWeek.setCDictionary(this.findCDictionaryByTypeId(type));
        	   //计算特殊日期在第几周
        	   Integer between_days = Integer.parseInt(String.valueOf((weekCal.getTimeInMillis() - termCal.getTimeInMillis())/(1000*3600*24)));
        	   Integer between_weeks = between_days/7;
        	   Integer remain_days = between_days%7;
        	   System.out.print(termCal.get(Calendar.DAY_OF_WEEK));
        	   schoolWeek.setWeek(between_weeks+1);
        	   schoolWeekDAO.store(schoolWeek);
        	   weekCal.add(Calendar.DAY_OF_YEAR,1);
    	   }
    	   
       }
       /************************************************************ 
      	 * description:根据cTeachingDateType找到对应的Cdictionary
      	 * @author:郑昕茹
      	 * @date: 2017-04-06
      	 ************************************************************/
       public CDictionary findCDictionaryByTypeId(Integer type){
    	   String sql = "select c from CDictionary c where 1=1 and c.cTeachingDateType ="+type;
	       	List<CDictionary> cDictionarys = cDictionaryDAO.executeQuery(sql,0,-1);
	       	if(cDictionarys != null && cDictionarys.size() != 0){
	       		return cDictionarys.get(0);
	       	}
	       	return null; 
       }
       
       
       /************************************************************ 
      	 * description:根据学期找到其下的特殊日期的起止时间
      	 * @author:郑昕茹
      	 * @date: 2017-04-06
      	 ************************************************************/
       public List<Object[]> findSchoolWeeksStartAndEnd(Integer termId){
   		String sql = "select * from view_school_special_week v where term_id = "+termId;
   		List<Object[]> objects = entityManager.createNativeQuery(sql).getResultList();
   		return objects;
       }
       
       
       /************************************************************ 
   	 * description:根据学期找到其下的所有schoolWeek并删除
   	 * @author:郑昕茹
   	 * @date: 2017-04-06
   	 ************************************************************/
       public void findSchoolWeekByTermAndDelete(Integer termId){
       	String sql = "select s from SchoolWeek s where 1=1 and term_id="+termId;
       	List<SchoolWeek> schoolWeeks = schoolWeekDAO.executeQuery(sql,0,-1);
       	for(SchoolWeek s:schoolWeeks){
       		schoolWeekDAO.remove(s);
       		schoolWeekDAO.flush();
       	}
       }
       /************************************************************ 
   	 * description:根据学期找到其下的所有schoolTermActive并删除
   	 * @author:郭明杰
   	 * @date: 2017-08-15
   	 ************************************************************/     
   	public void deleteAllSchoolTermActives(Integer termId) {
		// TODO Auto-generated method stub
       	String sql = "select s from SchoolTermActive s where 1=1 and school_term_id="+termId;
       	List<SchoolTermActive> schoolTermActive = schoolTermActiveDAO.executeQuery(sql,0,-1);
       	for(SchoolTermActive s:schoolTermActive){
       		schoolTermActiveDAO.remove(s);
       		schoolTermActiveDAO.flush();
       	}	
	} 
       /************************************************************ 
      	 * description:根据学期找到该学期的最大最小周次，最大最小年份，最大最小月份
      	 * @author:郑昕茹
      	 * @date: 2017-04-07
      	 ************************************************************/
       public List<Object[]> findViewSchoolTermWeek(Integer termId){
   		String sql = "select * from view_school_term_week v where term_id = "+termId;
   		sql += " group by term_id,week";
   		List<Object[]> objects = entityManager.createNativeQuery(sql).getResultList();
   		return objects;
       }
       
       
       /************************************************************ 
      	 * description:根据学期找到其下的所有特殊的schoolWeek
      	 * @author:郑昕茹
      	 * @date: 2017-04-07
      	 ************************************************************/
          public List<SchoolWeek> findSpecialSchoolWeekByTerm(Integer termId){
          	String sql = "select s from SchoolWeek s where 1=1 and term_id="+termId;
          	sql += " and s.CDictionary is not null";
          	List<SchoolWeek> schoolWeeks = schoolWeekDAO.executeQuery(sql,0,-1);
          	return schoolWeeks;
          }
}
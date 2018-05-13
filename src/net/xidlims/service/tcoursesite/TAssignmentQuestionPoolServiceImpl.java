package net.xidlims.service.tcoursesite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xidlims.dao.SchoolCourseInfoDAO;
import net.xidlims.dao.TAssignmentAnswerDAO;
import net.xidlims.dao.TAssignmentDAO;
import net.xidlims.dao.TAssignmentItemDAO;
import net.xidlims.dao.TAssignmentQuestionpoolDAO;
import net.xidlims.dao.TCourseSiteDAO;
import net.xidlims.domain.Authority;
import net.xidlims.domain.SchoolCourseDetail;
import net.xidlims.domain.SchoolCourseInfo;
import net.xidlims.domain.SchoolTerm;
import net.xidlims.domain.TAssignment;
import net.xidlims.domain.TAssignmentAnswer;
import net.xidlims.domain.TAssignmentItem;
import net.xidlims.domain.TAssignmentQuestionpool;
import net.xidlims.domain.TAssignmentSection;
import net.xidlims.domain.TCourseSite;
import net.xidlims.domain.TimetableAppointment;
import net.xidlims.domain.TimetableCourseStudent;
import net.xidlims.domain.TimetableLabRelated;
import net.xidlims.domain.TimetableSelfCourse;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

import excelTools.ExcelUtils;
import excelTools.JsGridReportBase;
import excelTools.TableData;
import net.xidlims.service.tcoursesite.TCourseSiteService;
@Service("TAssignmentQuestionService")
public class TAssignmentQuestionPoolServiceImpl implements TAssignmentQuestionPoolService {

	@Autowired
	private TAssignmentQuestionpoolDAO tAssignmentQuestionpoolDAO;
	@Autowired
	private TCourseSiteDAO tCourseSiteDAO;
	@Autowired
	private ShareService shareService;
	@Autowired
	private TAssignmentItemDAO tAssignmentItemDAO;
	@Autowired
	private TAssignmentAnswerDAO tAssignmentAnswerDAO;
	@Autowired
	private TAssignmentDAO tAssignmentDAO;
	@Autowired
	private SchoolCourseInfoDAO schoolCourseInfoDAO;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired 
	private TCourseSiteService tCourseSiteService;
	/**************************************************************************
	 * Description:查询题库列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-23
	 **************************************************************************/
	@Override
	public List<TAssignmentQuestionpool> findQuestionList(Integer cid) {
		// TODO Auto-generated method stub
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = new ArrayList<TAssignmentQuestionpool>();
		//查询公共题库
		String sql = "from TAssignmentQuestionpool c where c.type = '1'";
		List<TAssignmentQuestionpool> tAssignmentQuestionpoolsList = tAssignmentQuestionpoolDAO.executeQuery(sql, 0, -1);
		//查询非公共题库
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);//查询当前站点
		Set<TAssignmentQuestionpool> tAssignmentQuestionpoolsSet = tCourseSite.getTAssignmentQuestionpools();
		tAssignmentQuestionpools.addAll(tAssignmentQuestionpoolsList);
		tAssignmentQuestionpools.addAll(tAssignmentQuestionpoolsSet);
		
		return tAssignmentQuestionpools;
	}

	/**************************************************************************
	 * Description:当前课程及登陆用户查询题库列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-23
	 **************************************************************************/
	@Override
	public Map<Integer, String> findQuestionListByTypeAndUser(Integer type,
			Integer cid, User user) {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (type==1) {
			String sql = "from TAssignmentQuestionpool c where c.type = '1'";
			List<TAssignmentQuestionpool> assignmentQuestionpoolList = tAssignmentQuestionpoolDAO.executeQuery(sql, 0, -1);
			for (TAssignmentQuestionpool tAssignmentQuestionpool : assignmentQuestionpoolList) {
				map.put(tAssignmentQuestionpool.getQuestionpoolId(), tAssignmentQuestionpool.getTitle()+"("+tAssignmentQuestionpool.getTAssignmentItems().size()+")");
			}
		}
		if (type != 1) {
			TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
			Set<TAssignmentQuestionpool> tAssignmentQuestionpoolsSet = tCourseSite.getTAssignmentQuestionpools();
			for (TAssignmentQuestionpool tAssignmentQuestionpool : tAssignmentQuestionpoolsSet) {
				map.put(tAssignmentQuestionpool.getQuestionpoolId(), tAssignmentQuestionpool.getTitle()+"("+tAssignmentQuestionpool.getTAssignmentItems().size()+")");
			}
		}
		return map;
	}

	/**************************************************************************
	 * Description:保存题库类别
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-23
	 **************************************************************************/
	@Override
	public TAssignmentQuestionpool saveTAssignmentQuestionPool(Integer cid,
			TAssignmentQuestionpool tAssignmentQuestionpool) {
		// TODO Auto-generated method stub
		TAssignmentQuestionpool questionpool = null;
		if (tAssignmentQuestionpool.getQuestionpoolId()==null) {//新增
			tAssignmentQuestionpool.setCreatedTime(Calendar.getInstance());
			tAssignmentQuestionpool.setModifyTime(Calendar.getInstance());
			tAssignmentQuestionpool.setUser(shareService.getUser());
			tAssignmentQuestionpool.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(tAssignmentQuestionpool.getTAssignmentQuestionpool().getQuestionpoolId()));
			if (tAssignmentQuestionpool.getType()==1) {//公共题库
				questionpool = tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
			}
			if (tAssignmentQuestionpool.getType()!=1) {//非公共题库
				questionpool = tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
				TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
				questionpool.getTCourseSites().add(tCourseSite);
				questionpool = tAssignmentQuestionpoolDAO.store(questionpool);
			}
		}else {//修改
			TAssignmentQuestionpool oldTAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(tAssignmentQuestionpool.getQuestionpoolId());
			oldTAssignmentQuestionpool.setModifyTime(Calendar.getInstance());
			oldTAssignmentQuestionpool.setUser(shareService.getUser());
			oldTAssignmentQuestionpool.setTitle(tAssignmentQuestionpool.getTitle());
			oldTAssignmentQuestionpool.setType(tAssignmentQuestionpool.getType());
			//oldTAssignmentQuestionpool.setSort(tAssignmentQuestionpool.getSort());
			oldTAssignmentQuestionpool.setTAssignmentQuestionpool(tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(tAssignmentQuestionpool.getTAssignmentQuestionpool().getQuestionpoolId()));
			oldTAssignmentQuestionpool.setDescription(tAssignmentQuestionpool.getDescription());
			if (tAssignmentQuestionpool.getType()==1) {//公共题库
				oldTAssignmentQuestionpool.setTCourseSites(new HashSet<TCourseSite>());
				questionpool = tAssignmentQuestionpoolDAO.store(oldTAssignmentQuestionpool);
			}
			if (tAssignmentQuestionpool.getType()!=1) {//非公共题库
				questionpool = tAssignmentQuestionpoolDAO.store(oldTAssignmentQuestionpool);
				TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
				questionpool.getTCourseSites().add(tCourseSite);
				questionpool = tAssignmentQuestionpoolDAO.store(questionpool);
			}
		}
		return questionpool;
		
	}
	/**************************************************************************
	 * Description:根据id查询题库类别
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-26
	 **************************************************************************/
	@Override
	public TAssignmentQuestionpool findTAssignmentQuestionpoolById(Integer id) {
		// TODO Auto-generated method stub
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(id);
		return tAssignmentQuestionpool;
	}
	/**************************************************************************
	 * Description:根据题库id查询题库下题目
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-27
	 **************************************************************************/
	@Override
	public List<TAssignmentItem> findTAssignmentItemsByQuestionId(Integer id, Integer currpage, Integer pageSize) {
		// TODO Auto-generated method stub
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(id);
		String ids = "";
		for (TAssignmentItem tAssignmentItem : tAssignmentQuestionpool.getTAssignmentItems()) {
			ids += "'"+tAssignmentItem.getId()+"',";
		}
		if(!ids.equals("")){
			ids= ids.substring(0, ids.length()-1);
		}else {
			ids="''";
		}
		String sql = "from TAssignmentItem c where c.id in ("+ids+")";
		List<TAssignmentItem> tAssignmentItems = tAssignmentItemDAO.executeQuery(sql, (currpage-1)*pageSize, pageSize);
		return tAssignmentItems;
	}
	/**************************************************************************
	 * Description:根据题库id导入测验题目（xls格式）
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-27
	 **************************************************************************/
	@Override
	public int importTAssignmentItemsXls(String filePath, Integer questionId) {
		// TODO Auto-generated method stub
		int result = 0;//用于判断结果
		boolean isE2007 = false; // 判断是否是excel2007格式
		if (filePath.endsWith("xlsx")) {
			isE2007 = true;
		}
		try {
			InputStream input = new FileInputStream(filePath); // 建立输入流
			Workbook wb = null;
			// 根据文件格式(2003或者2007)来初始化
			if (isE2007){
				wb = new XSSFWorkbook(input);
			}else{
				wb = new HSSFWorkbook(input);
			}
		    Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
		    Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
		    TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId);
		    Set<TAssignmentItem> tAssignmentItems = new HashSet<TAssignmentItem>();
		    String[] labelStrings = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};
		    while (rows.hasNext()) {
				Row row = rows.next(); // 获得行数据
				System.out.println("-----------"+row.getRowNum());
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				String type = row.getCell(0).getStringCellValue();// 试题类型
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				String description = row.getCell(1).getStringCellValue();// 题干
				//row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				//String score = row.getCell(2).getStringCellValue();// 分值
				TAssignmentItem tAssignmentItem = new TAssignmentItem();
				tAssignmentItem.setDescription(description);
				/*if(score!=null&&score!=""){
					tAssignmentItem.setScore(new BigDecimal(score));
				}else{
					tAssignmentItem.setScore(new BigDecimal(0));
				}*/
				System.out.println(description);
				tAssignmentItem.setType(Integer.valueOf(type));
				tAssignmentItem.setCreatedTime(Calendar.getInstance());
				tAssignmentItem.setUser(shareService.getUser());
				tAssignmentItem = tAssignmentItemDAO.store(tAssignmentItem);
				tAssignmentItems.add(tAssignmentItem);
				
				int labelCount = 0;
				if (type.equals("1")) {//多选题
					int i = 2;
					if (row.getCell(2)!=null) {
						row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					}
					while (row.getCell(i)!=null&&!row.getCell(i).getStringCellValue().trim().equals("")) {
						TAssignmentAnswer answer = new TAssignmentAnswer();
						answer.setLabel(labelStrings[labelCount]);
						answer.setTAssignmentItem(tAssignmentItem);
						answer.setText(row.getCell(i).getStringCellValue().trim());
						i++;
						row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						answer.setIscorrect(Integer.valueOf(row.getCell(i).getStringCellValue().trim()));
						i++;
						tAssignmentAnswerDAO.store(answer);
						if (row.getCell(i)!=null) {
							row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						}
						labelCount++;
					}
				}
				if(type.equals("2")){//对错题
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					String isCorrect = row.getCell(2).getStringCellValue();// 对错
					if (isCorrect.equals("1")) {
						TAssignmentAnswer answer = new TAssignmentAnswer();
						answer.setLabel("0");
						answer.setText("对");
						answer.setIscorrect(1);
						answer.setTAssignmentItem(tAssignmentItem);
						tAssignmentAnswerDAO.store(answer);
						answer.setLabel("1");
						answer.setText("错");
						answer.setIscorrect(0);
						tAssignmentAnswerDAO.store(answer);
						
					}else{
						TAssignmentAnswer answer = new TAssignmentAnswer();
						answer.setLabel("0");
						answer.setText("对");
						answer.setIscorrect(0);
						answer.setTAssignmentItem(tAssignmentItem);
						tAssignmentAnswerDAO.store(answer);
						answer.setLabel("1");
						answer.setText("错");
						answer.setIscorrect(1);
						tAssignmentAnswerDAO.store(answer);
					}
				}
				if (type.equals("4")) {//单选题
					int i = 2;
					if (row.getCell(2)!=null) {
						row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					}
					while (row.getCell(i)!=null&&!row.getCell(i).getStringCellValue().trim().equals("")) {
						TAssignmentAnswer answer = new TAssignmentAnswer();
						answer.setLabel(labelStrings[labelCount]);
						answer.setTAssignmentItem(tAssignmentItem);
						answer.setText(row.getCell(i).getStringCellValue().trim());
						i++;
						row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						answer.setIscorrect(Integer.valueOf(row.getCell(i).getStringCellValue().trim()));
						i++;
						tAssignmentAnswerDAO.store(answer);
						if (row.getCell(i)!=null) {
							row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						}
						labelCount++;
					}
				}
				if (type.equals("8")) {//填空
					/*String[] ss = description.split("\\{");
					for (int i = 1;i<ss.length; i++) {
						TAssignmentAnswer answer = new TAssignmentAnswer();
						answer.setText(ss[i].substring(0, ss[i].indexOf("}")));
						description = description.replace("{"+answer.getText()+"}", "_________");
						answer.setIscorrect(1);
						answer.setTAssignmentItem(tAssignmentItem);
						tAssignmentAnswerDAO.store(answer);
					}*/
					TAssignmentAnswer answer = new TAssignmentAnswer();
					answer.setText(row.getCell(2).getStringCellValue().trim());
					description=row.getCell(1).getStringCellValue().trim();
					answer.setIscorrect(1);
					answer.setTAssignmentItem(tAssignmentItem);
					tAssignmentAnswerDAO.store(answer);

					tAssignmentItem.setDescriptionTemp(description);
					tAssignmentItemDAO.store(tAssignmentItem);
					tAssignmentItems.add(tAssignmentItem);
				}
				
			}
		    
		    tAssignmentQuestionpool.getTAssignmentItems().addAll(tAssignmentItems);
			tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**************************************************************************
	 * Description:根据题库id删除题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-29
	 **************************************************************************/
	@Override
	public void deleteTAssignmentQuestionPoolById(Integer questionId) {
		// TODO Auto-generated method stub
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId);
		/**
		 * 将数据库表t_assignment_questionpool的外键parentpool_id设置为删除时SET null,不级联删除
		 * 若需级联删除，修改外键设置
		 */
		
		//解除与子级题库的关联,如果需级联删除，则不需要解除关联
		Set<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionpool.getTAssignmentQuestionpools();
		for (TAssignmentQuestionpool assignmentQuestionpool : tAssignmentQuestionpools) {
			assignmentQuestionpool.setTAssignmentQuestionpool(null);
			tAssignmentQuestionpoolDAO.store(assignmentQuestionpool);
		}
		tAssignmentQuestionpool.setTAssignmentQuestionpools(null);
		tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
		
		Set<TAssignmentItem> tAssignmentItems = new HashSet<TAssignmentItem>();
		tAssignmentItems.addAll(tAssignmentQuestionpool.getTAssignmentItems());
		
		tAssignmentQuestionpoolDAO.remove(tAssignmentQuestionpool);
		for (TAssignmentItem tAssignmentItem : tAssignmentItems) {
			if (tAssignmentItem.getTAssignmentSection()==null) {//如果题库中的试题不被测验所使用，则删除该数据，否则保留
				tAssignmentItemDAO.remove(tAssignmentItem);
			}
		}
	}
	/**************************************************************************
	 * Description:根据试题id删除试题
	 * 
	 * @author：黄崔俊
	 * @date ：2015-10-30
	 **************************************************************************/
	@Override
	public void deleteTAssignmentItemById(Integer itemId,Integer questionId) {
		// TODO Auto-generated method stub
		TAssignmentItem tAssignmentItem = tAssignmentItemDAO.findTAssignmentItemById(itemId);
		//删除该试题与题库之间的关联
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId);
		tAssignmentQuestionpool.getTAssignmentItems().remove(tAssignmentItem);
		tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
		//如果该试题未被测验所使用，则直接删除
		if (tAssignmentItem.getTAssignmentSection()==null) {
			tAssignmentItemDAO.remove(tAssignmentItem);
		}
	}
	/**************************************************************************
	 * Description:根据题库id将试题导入题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-2
	 **************************************************************************/
	@Override
	public void importTAssignmentItemsByQuestionId(Integer questionId,
			Integer examId) {
		// TODO Auto-generated method stub
		TAssignment exam = tAssignmentDAO.findTAssignmentById(examId);
		Set<TAssignmentItem> tAssignmentItems = new HashSet<TAssignmentItem>();
		for (TAssignmentSection  section : exam.getTAssignmentSections()) {
			for (TAssignmentItem tAssignmentItem : section.getTAssignmentItems()) {
				tAssignmentItems.add(tAssignmentItem);
			}
		}
		
		TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId);
		tAssignmentQuestionpool.getTAssignmentItems().addAll(tAssignmentItems);
		tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
	}
	/**************************************************************************
	 * Description:根据课程id查询可导入的题库
	 * 
	 * @author：黄崔俊
	 * @date ：2015-11-25
	 **************************************************************************/
	@Override
	public List<TAssignmentQuestionpool> findCheckQuestionList(Integer cid) {
		// TODO Auto-generated method stub
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = new ArrayList<TAssignmentQuestionpool>();
			//查询公共题库
			String sql = "from TAssignmentQuestionpool c where c.type = '1'";
			List<TAssignmentQuestionpool> tAssignmentQuestionpoolsList = tAssignmentQuestionpoolDAO.executeQuery(sql, 0, -1);
			tAssignmentQuestionpools.addAll(tAssignmentQuestionpoolsList);
			//查询非公共题库
			TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);//查询当前站点
			Set<TAssignmentQuestionpool> tAssignmentQuestionpoolsSet = tCourseSite.getTAssignmentQuestionpools();
			
			tAssignmentQuestionpools.addAll(tAssignmentQuestionpoolsSet);
		
		return tAssignmentQuestionpools;
	}
	/*********************************************************************************
	 * description 题库-课程题库题库{导入txt文档格式的试题}
	 * 
	 * @author 李军凯
	 * @date 2016-08-23
	 ************************************************************************************/
	@Override
	public String importTAssignmentItemsTxt(String filePath, Integer questionId) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			File text = new File(filePath); // 文件
			String code = getFileCode(filePath);
			// if (code.equals("gb2312-8")) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(text), "UTF-8"));
			if (code.equals("UTF-8")) {
				br.skip(1);// Java读取BOM（Byte Order　Mark）的问题,这是java的bug，必须跳过bom
			}

			String[] labelStrings = new String[]{"A","B","C","D","E","F","G","H","I","J"};
			String line = "";
			int lineCount = 0;
			//BigDecimal score =new BigDecimal(0);
			String description = "";// 得到题目内容

			List<TAssignmentItem> tAssignmentItems = new ArrayList<TAssignmentItem>();
			// 读取直到最后一行
			while ((line = br.readLine()) != null) {
				lineCount++;//获取当前所读的行的序号
				line = line.trim();
				
				if (line.startsWith("::")) {// 题目以：：开始
					String[] lineContents = line.split("::");
					String[] lineType = line.split("~");
				//	int a = line.lastIndexOf("::");// 得到题目的索引
					description = lineContents[lineContents.length-1];// 得到题目内容
					String type ="";
					for(int c=lineCount;c<lineCount+1;c++)
					{
						line = br.readLine();
						line = line.trim();
						lineContents = line.split("=");
						lineType = line.split("~");
					}
					if(lineType.length>2){
						if(lineContents.length>2)
						{
							type = "多选";
						}
						if(lineContents.length==2)
						{
							type = "单选";
						}
					}
					else{
						if(lineContents.length==2)
						{
							type = "填空";
						}
						if(lineContents.length==1)
						{
							type = "判断";
						}
					}
					//String type = lineContents[1];// 类型
					//score =new BigDecimal(lineContents[2]);
					//description = lineContents[lineContents.length-1];// 得到题目内容
					int labelCount = 0;
					if (type.equals("单选")) {// 判断题目类型
						int b = line.indexOf("{");// 获取答案的索引
					//	description = line.substring(a + 2, b);// 重新处理问题内容
						TAssignmentItem tAssignmentItem = new TAssignmentItem();
						tAssignmentItem.setDescription(description);
					//	tAssignmentItem.setScore(score);
						tAssignmentItem.setType(4);
						tAssignmentItem.setCreatedTime(Calendar.getInstance());
						tAssignmentItem.setUser(shareService.getUser());
						tAssignmentItem = tAssignmentItemDAO.store(tAssignmentItem);
						tAssignmentItems.add(tAssignmentItem);
						
						String answers = line.substring(b + 1, line.length() - 1);// 得到答案
						// int a = line.lastIndexOf("}"); // 获取答案字符串的索引
						String[] answerArray = answers.split(" ");
						
						for (String answertext : answerArray) {
							if (!answertext.equals("")) {//答案不为空则加入
								TAssignmentAnswer answer = new TAssignmentAnswer();
								if (answertext.startsWith("=")) {//以等号打头是正确答案
									answer.setIscorrect(1);
								}else {
									answer.setIscorrect(0);//否则是错误答案
								}
								answer.setText(answertext.substring(1).trim());//去除初始标记位
								answer.setTAssignmentItem(tAssignmentItem);
								answer.setLabel(labelStrings[labelCount]);
								labelCount++;
								tAssignmentAnswerDAO.store(answer);
							}
						}
						tAssignmentItem.setDescriptionTemp(description);
						tAssignmentItemDAO.store(tAssignmentItem);
						tAssignmentItems.add(tAssignmentItem);
					}
					if (type.equals("多选")) {
						int b = line.indexOf("{");// 获取答案的索引
					//	description = line.substring(a + 2, b);// 重新处理问题内容
						
						TAssignmentItem tAssignmentItem = new TAssignmentItem();
						tAssignmentItem.setDescription(description);
					//	tAssignmentItem.setScore(score);
						tAssignmentItem.setType(1);
						tAssignmentItem.setCreatedTime(Calendar.getInstance());
						tAssignmentItem.setUser(shareService.getUser());
						tAssignmentItem = tAssignmentItemDAO.store(tAssignmentItem);
						tAssignmentItems.add(tAssignmentItem);
						
						
						String answers = line.substring(b + 1, line.length() - 1);// 得到答案
						// int a = line.lastIndexOf("}"); // 获取答案字符串的索引
						String[] answerArray = answers.split(" ");
						
						for (String answertext : answerArray) {
							if (!answertext.equals("")) {//答案不为空则加入
								TAssignmentAnswer answer = new TAssignmentAnswer();
								if (answertext.startsWith("=")) {//以等号打头是正确答案
									answer.setIscorrect(1);
								}else {
									answer.setIscorrect(0);//否则是错误答案
								}
								answer.setText(answertext.substring(1).trim());//去除初始标记位
								answer.setTAssignmentItem(tAssignmentItem);
								answer.setLabel(labelStrings[labelCount]);
								labelCount++;
								tAssignmentAnswerDAO.store(answer);
							}
						}
						tAssignmentItem.setDescriptionTemp(description);
						tAssignmentItemDAO.store(tAssignmentItem);
						tAssignmentItems.add(tAssignmentItem);
					}
					if (type.equals("判断")) {// 判断题处理机制不一样
						int b = line.indexOf("{");// 获取答案的索引
					//	description = line.substring(a + 2, b);// 重新处理问题内容
						TAssignmentItem tAssignmentItem = new TAssignmentItem();
						tAssignmentItem.setDescription(description);
					//	tAssignmentItem.setScore(score);
						tAssignmentItem.setType(2);
						tAssignmentItem.setCreatedTime(Calendar.getInstance());
						tAssignmentItem.setUser(shareService.getUser());
						tAssignmentItem = tAssignmentItemDAO.store(tAssignmentItem);
						tAssignmentItems.add(tAssignmentItem);
						
						
						String answers = line.substring(b + 1, line.length() - 1);// 得到答案
						if (answers.startsWith("T")||answers.equals("1")) {//答案为正确
							
							TAssignmentAnswer answer = new TAssignmentAnswer();
							answer.setLabel("0");
							answer.setText("对");
							answer.setIscorrect(1);
							answer.setTAssignmentItem(tAssignmentItem);
							tAssignmentAnswerDAO.store(answer);
							answer.setLabel("1");
							answer.setText("错");
							answer.setIscorrect(0);
							tAssignmentAnswerDAO.store(answer);
						}
						if (answers.startsWith("F")||answers.equals("0")) {//答案为错误
							
							TAssignmentAnswer answer = new TAssignmentAnswer();
							answer.setLabel("0");
							answer.setText("对");
							answer.setIscorrect(0);
							answer.setTAssignmentItem(tAssignmentItem);
							tAssignmentAnswerDAO.store(answer);
							answer.setLabel("1");
							answer.setText("错");
							answer.setIscorrect(1);
							tAssignmentAnswerDAO.store(answer);
						}
					}
					   if (type.equals("填空")) {// 填空题处理机制不一样
						int b = line.indexOf("{");// 获取答案的索引
					//	description = line.substring(a + 2, b);// 重新处理问题内容						
						TAssignmentItem tAssignmentItem = new TAssignmentItem();
						tAssignmentItem.setDescription(description);
					//	tAssignmentItem.setScore(score);
						tAssignmentItem.setType(8);
						tAssignmentItem.setCreatedTime(Calendar.getInstance());
						tAssignmentItem.setUser(shareService.getUser());
						tAssignmentItem = tAssignmentItemDAO.store(tAssignmentItem);
						
						/*String[] ss = description.split("\\{");
						for (int i = 1;i<ss.length; i++) {
							TAssignmentAnswer answer = new TAssignmentAnswer();
							int end = ss[i].indexOf("}");
							if (end==-1) {
								result = "导入失败，请检查文本，第"+lineCount+"行填空题答案设置格式有误，大括号不完整！";
							}
							answer.setText(ss[i].substring(0, end));
							description = description.replace("{"+answer.getText()+"}", "_________");
							answer.setIscorrect(1);
							answer.setTAssignmentItem(tAssignmentItem);
							tAssignmentAnswerDAO.store(answer);
						}*/
						String answers = line.substring(b + 2, line.length() - 1);// 得到答案
						TAssignmentAnswer answer = new TAssignmentAnswer();
						answer.setText(answers);
						answer.setIscorrect(1);
						answer.setTAssignmentItem(tAssignmentItem);
						tAssignmentAnswerDAO.store(answer);
						tAssignmentItem.setDescriptionTemp(description);
						tAssignmentItemDAO.store(tAssignmentItem);
						tAssignmentItems.add(tAssignmentItem);
					   }
					}
					
				}
	//		}
			TAssignmentQuestionpool tAssignmentQuestionpool = tAssignmentQuestionpoolDAO.findTAssignmentQuestionpoolByPrimaryKey(questionId);
			tAssignmentQuestionpool.getTAssignmentItems().addAll(tAssignmentItems);
			tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();// 捕获File对象生成时的异常
		} catch (IOException e) {
			e.printStackTrace();// 捕获BufferedReader对象关闭时的异常
		} catch (StringIndexOutOfBoundsException e) {
			
		} 
		return result;
	}
	
	/**
	 * 判断文件编码 输入：文件路径 输出：编码，比如utf-8
	 */
	public String getFileCode(String filePath) {
		InputStream inputStream = null;
		String code = "";
		try {
			inputStream = new FileInputStream(filePath);
			byte[] head = new byte[3];
			inputStream.read(head);
			code = "gb2312";
			if (head[0] == -1 && head[1] == -2)
				code = "UTF-16";
			if (head[0] == -2 && head[1] == -1)
				code = "Unicode";
			if (head[0] == -17 && head[1] == -69 && head[2] == -65)
				code = "UTF-8";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return code;
	}
	/**************************************************************************
	 * Description:分页查询课程下的题库列表
	 * 
	 * @author：黄崔俊
	 * @date ：2015-12-25
	 **************************************************************************/
	@Override
	public List<TAssignmentQuestionpool> findQuestionListBySiteId(Integer cid,
			Integer currpage, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "select distinct q from TCourseSite t,TAssignmentQuestionpool q where q.questionpoolId in elements(t.TAssignmentQuestionpools) and t.id = "+cid+" order by q.id asc";
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = tAssignmentQuestionpoolDAO.executeQuery(hql, (currpage-1)*pageSize, pageSize);
		return tAssignmentQuestionpools;

	}
	/**************************************************************************
	 * Description:查询题库id，title，数量
	 * 
	 * @author：黄崔俊
	 * @date ：2016-3-20
	 **************************************************************************/
	@Override
	public List<Object[]> findQuestionInfoListBySiteId(Integer cid,Integer itemType) {
		// TODO Auto-generated method stub
		TCourseSite tCourseSite = tCourseSiteDAO.findTCourseSiteById(cid);
		String ids = "(";
		for (TCourseSite courseSite : tCourseSite.getSchoolCourseInfo().getTCourseSites()) {
			ids += courseSite.getId()+",";
		}
		ids = ids.substring(0,ids.length()-1)+")";
		String sql = "select q.questionpool_id,q.title,count(*) from t_assignment_questionpool q" +
				" left join t_course_questionpool cq" +
				" on q.questionpool_id = cq.t_questionpool_id" +
				" left join t_assignment_questionpool_item qi" +
				" on q.questionpool_id = qi.questionpool_id" +
				" left join t_assignment_item i on" +
				" i.id = qi.item_id" +
				" where cq.t_course_site_id in "+ids+" " +
				" and i.type = '"+itemType+"'" +
				" group by q.questionpool_id,q.sort order by q.sort,q.questionpool_id asc";
		List<Object> objects = entityManager.createNativeQuery(sql).getResultList();
		List<Object[]> list = new ArrayList<Object[]>();
		for (Object object : objects) {
			Object[] obj = (Object[])object;
			list.add(obj);
		}
		return list;
	}
	/**************************************************************************
	 * Description:根据课程编号查询题库列表
	 * 
	 * @author：黄崔俊
	 * @date ：2016-3-20
	 **************************************************************************/
	@Override
	public List<TAssignmentQuestionpool> findSchoolCourseQuestionList(
			String courseNumber) {
		// TODO Auto-generated method stub
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = new ArrayList<TAssignmentQuestionpool>();
		SchoolCourseInfo schoolCourseInfo = schoolCourseInfoDAO.findSchoolCourseInfoByCourseNumber(courseNumber);
		String ids = "(";
		for (TCourseSite tCourseSite : schoolCourseInfo.getTCourseSites()) {
			ids += tCourseSite.getId()+",";
		}
		ids = ids.substring(0,ids.length()-1)+")";
		String sql = "select c from TAssignmentQuestionpool c join c.TCourseSites t where t.id in "+ids+" order by c.sort asc";
		tAssignmentQuestionpools = tAssignmentQuestionpoolDAO.executeQuery(sql, 0, -1);
		
		return tAssignmentQuestionpools;
	}
	/**************************************************************************
	 * Description:根据站点号查询题库数量
	 * 
	 * @author：黄崔俊
	 * @date ：2016-3-20
	 **************************************************************************/
	@Override
	public int countQuestionBySiteId(Integer cid) {
		// TODO Auto-generated method stub
		String hql = "select count(q) from TCourseSite t join t.TAssignmentQuestionpools q where t.id = "+cid+"";
		int result = ((Long)tAssignmentQuestionpoolDAO.createQuerySingleResult(hql).getSingleResult()).intValue();
		return result;
	}
	/**************************************************************************
	 * Description:题库-题库复制-获取题库
	 * 
	 * @author：李军凯
	 * @date ：2016-09-05
	 **************************************************************************/
	@Override
	public List<TAssignmentQuestionpool> findQuestionListByUser(String username,Integer tCourseSiteId) {
		// TODO Auto-generated method stub
		List<TAssignmentQuestionpool> tAssignmentQuestionpools = new ArrayList<TAssignmentQuestionpool>();
		//根据用户名查询题库	
		String sql = "select c from TAssignmentQuestionpool c where c.user.username like '"+username+"' ";		
		List<TAssignmentQuestionpool> tAssignmentQuestionpoolsList = tAssignmentQuestionpoolDAO.executeQuery(sql, 0, -1);
		//根据CourseSiteId获取本门课程之外的题库
		for(TAssignmentQuestionpool tAssignmentQuestionpool:tAssignmentQuestionpoolsList){
			int flag=0;
			for(TCourseSite tCourseSite:tAssignmentQuestionpool.getTCourseSites()){
				if(tCourseSite.getId().equals(tCourseSiteId)){
					flag=1;
				}
			}
			if(flag==0){
				tAssignmentQuestionpools.add(tAssignmentQuestionpool);
			}
		}
		return tAssignmentQuestionpools;
	}
	/**************************************************************************
	 * Description:题库-题库复制-获取题库列表
	 * 
	 * @author：李军凯
	 * @date ：2016-09-05
	 **************************************************************************/
	@Override
	public Set<TAssignmentQuestionpool> findCopyQuestionListByUser(String[] poolIds) {
		Set<TAssignmentQuestionpool> tAssignmentQuestionpools = new HashSet<TAssignmentQuestionpool>();
		//查询题库	
		for(String id: poolIds){
		String sql = "select c from TAssignmentQuestionpool c where c.questionpoolId like '"+id+"'";
		List<TAssignmentQuestionpool> tAssignmentQuestionpoolsList = tAssignmentQuestionpoolDAO.executeQuery(sql, 0, -1);				
		tAssignmentQuestionpools.addAll(tAssignmentQuestionpoolsList);
		}					
		return tAssignmentQuestionpools;
	}
	/**************************************************************************
	 * Description:题库-题库复制-保存复制的题库
	 * 
	 * @author：李军凯
	 * @date ：2016-09-05
	 **************************************************************************/
	public void saveCopyQuestionList(TAssignmentQuestionpool tAssignmentQuestionpool)
	{
		tAssignmentQuestionpoolDAO.store(tAssignmentQuestionpool);
		tAssignmentQuestionpoolDAO.flush();

	}
	/**************************************************************************************
	 * Description:课程-题库-题库导出
	 * 
	 * @author：李军凯
	 * @date ：2016-09-07
	 *************************************************************************************/
	public void exportTAssignmentItemById(Integer questionId,HttpServletRequest request,HttpServletResponse response)
	{
		List<TAssignmentItem> tAssignmentItemList = new ArrayList<TAssignmentItem>();
		//根据id获取题库
		TAssignmentQuestionpool tAssignmentQuestionpool = findTAssignmentQuestionpoolById(questionId);
		//获取题库标题
		String title =  tAssignmentQuestionpool.getTitle();
		//获取题目集合
		for(TAssignmentItem tAssignmentItem:tAssignmentQuestionpool.getTAssignmentItems()){
			tAssignmentItemList.add(tAssignmentItem);
		}
		String[] question = new String[2*tAssignmentItemList.size()];				
		int i=0;
		for(TAssignmentItem tAssignmentItem:tAssignmentItemList)
		{ 	int type=tAssignmentItem.getType();		
			question[i] = "::"+tAssignmentItem.getDescription();
			question[i+1] = "{";
			//遍历答案
			//单选，多选，填空
			if(type==1||type==4||type==8){
			for(TAssignmentAnswer tAssignmentAnswer:tAssignmentItem.getTAssignmentAnswers()){
				if(tAssignmentAnswer.getIscorrect().equals(1)){
					question[i+1] += "="+tAssignmentAnswer.getText()+"    ";
				}
				else{
					question[i+1] += "~"+tAssignmentAnswer.getText()+"    ";
				}
			}
			question[i+1] += " }";
			}
			//判断题
			if(type==2){
				for(TAssignmentAnswer tAssignmentAnswer:tAssignmentItem.getTAssignmentAnswers()){
					if(tAssignmentAnswer.getIscorrect().equals(1)){	
						if(tAssignmentAnswer.getText().equals("对")){
						question[i+1] += "TRUE";
						}
						else{
						question[i+1] += "FALSE";
						}
					}
				}
				question[i+1] += "}";
				}
			i=i+2;
		}
	     try {
	    	 	
	    	 	String fileName = title;
	    	 	String filePath = "D:"; 
	    	 	//定义文件名,路径
	    	 	File file = new File(filePath,fileName+".txt");
	    	    if(!file.exists()) //如果文件存在则覆盖  
	            {   
	    	    	file.createNewFile();   
	            }   
	    	 	OutputStreamWriter fw=null;//定义一个流
	    	 	fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");//确认流的输出文件和编码格式
	    	 	for(String s:question){
	    	    fw.write(s+"\r\n");//将要写入文件的内容
	    	 	}
	    	    fw.close();//关闭流
	    	 } catch (IOException e) {
	    	  // TODO Auto-generated catch block
	    	  e.printStackTrace();
	    	 }
	     	
	}
	/**************************************************************************************
	 * Description:课程-题库-题库导出
	 * 
	 * @author：李军凯
	 * @date ：2016-09-08
	 *************************************************************************************/
	public void exportExcelQuestionPoolById(Integer questionId,HttpServletRequest request,HttpServletResponse response)
			throws Exception {		
				List<TAssignmentItem> tAssignmentItemList = new ArrayList<TAssignmentItem>();
				//根据id获取题库
				TAssignmentQuestionpool tAssignmentQuestionpool = findTAssignmentQuestionpoolById(questionId);
				//获取题库标题
				String title =  tAssignmentQuestionpool.getTitle();
				//获取题目集合
				for(TAssignmentItem tAssignmentItem:tAssignmentQuestionpool.getTAssignmentItems()){
							tAssignmentItemList.add(tAssignmentItem);
				}
				//创建HSSFWorkbook对象(excel的文档对象)
			      HSSFWorkbook wb = new HSSFWorkbook();
			      //建立新的sheet对象（excel的表单）
			      HSSFSheet sheet= wb.createSheet(title);
			      HSSFRow row = sheet.createRow((int) 0);
			     int i=0;
				for(TAssignmentItem tAssignmentItem:tAssignmentItemList) //遍历题目
				{	
					int j=2;
					int type=tAssignmentItem.getType();					
					row = sheet.createRow((int) i );
					row.createCell(0).setCellValue(type);
					row.createCell(1).setCellValue(tAssignmentItem.getDescription());
					if(type==2){//判断
						for(TAssignmentAnswer tAssignmentAnswer:tAssignmentItem.getTAssignmentAnswers()){
							if(tAssignmentAnswer.getIscorrect().equals(1)){	
								if(tAssignmentAnswer.getText().equals("对")){
									row.createCell(2).setCellValue(1);
									}
								else{
									row.createCell(2).setCellValue(0);
									}
								}
							}
						}
					if(type == 1||type == 4){//多选题,单选题
						for(TAssignmentAnswer tAssignmentAnswer:tAssignmentItem.getTAssignmentAnswers()){
							if(tAssignmentAnswer.getIscorrect().equals(1)){
							row.createCell(j).setCellValue(tAssignmentAnswer.getText());
							row.createCell(j+1).setCellValue(1);
							j=j+2;//列标
						}
						else{
							row.createCell(j).setCellValue(tAssignmentAnswer.getText());
							row.createCell(j+1).setCellValue(0);
							j=j+2;
						}
						}								
					}
					if(type == 8){//填空
						for(TAssignmentAnswer tAssignmentAnswer:tAssignmentItem.getTAssignmentAnswers()){
							if(tAssignmentAnswer.getIscorrect().equals(1)){
							row.createCell(j).setCellValue(tAssignmentAnswer.getText());
							j=j+1;//列标
						}
						}								
					}
				i++;	//行标
				}
				 try  
			        {  
					 	String fileName = title;
			    	 	String filePath = "D:"; 
			    	 	//定义文件名,路径
			    	 	File file = new File(filePath,fileName+".xls");
			            FileOutputStream fout = new FileOutputStream(file);  
			            wb.write(fout);  
			            fout.close();  
			        }  
			        catch (Exception e)  
			        {  
			            e.printStackTrace();  
			        }  
				}
	
	/**************************************************************************
	 * Description:根据题库id，题目数量和题目类型来判断是否超出题库该类题目总数
	 * 
	 * @author：于侃
	 * @date ：2016年10月27日 14:32:19
	 **************************************************************************/
	public String checkTestItemCount(Integer questionpoolId,Integer quantity,Integer type){
		String sql = "select count(t) from TAssignmentQuestionpool q join q.TAssignmentItems t where q.questionpoolId = " + questionpoolId + 
				" and t.type = " + type;
		int questionpoolQuantity = ((Long)tAssignmentQuestionpoolDAO .createQuerySingleResult(sql).getSingleResult()).intValue();
		if(questionpoolQuantity >= quantity){
			return "success";
		}else{
			return "failure";
		}
	}
	
	/**************************************************************************
	 * Description:根据题库id，题目类型来获取题库该类题目总数
	 * 
	 * @author：于侃
	 * @date ：2016年11月20日 13:51:14
	 **************************************************************************/
	public int getItemCount(Integer questionpoolId,Integer type){
		String sql = "select count(t) from TAssignmentQuestionpool q join q.TAssignmentItems t where q.questionpoolId = " + questionpoolId + 
				" and t.type = " + type;
		int questionpoolQuantity = ((Long)tAssignmentQuestionpoolDAO .createQuerySingleResult(sql).getSingleResult()).intValue();
		return questionpoolQuantity;
	}
}

package net.xidlims.service.expendable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.dao.ExpendableDAO;
import net.xidlims.dao.UserDAO;
import net.xidlims.domain.Expendable;
import net.xidlims.domain.User;
import net.xidlims.service.common.ShareService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Service("ExpendableImportService")
public class ExpendableImportServiceImpl implements  ExpendableImportService {
	
	@Autowired ShareService shareService;
	@Autowired UserDAO userDAO;
	@Autowired ExpendableDAO expendableDAO;
	/***********************************************************************************************
	 * @功能：通用模塊service層定義-获取上传文件的保存路径
	 * @作者：李小龙 
	 * @日期：2014-07-27
	 ***********************************************************************************************/
	@Override
	public String getUpdateFilePath(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 日期格式 **/
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		/** 构建文件保存的目录* */
		String PathDir = "/upload/" + dateformat.format(new Date());
		/** 得到文件保存目录的真实路径* */
		String RealPathDir = request.getSession().getServletContext().getRealPath(PathDir);
		// System.out.println("文件保存目录的真实路径:"+logoRealPathDir);
		/** 根据真实路径创建目录* */
		File SaveFile = new File(RealPathDir);
		if (!SaveFile.exists()) {
			SaveFile.mkdirs();
		}
		/** 页面控件的文件流* */
		System.out.println("准备获取文件---");
		MultipartFile multipartFile = multipartRequest.getFile("file");
		/** 获取文件的后缀* */
		System.out.println("上传的文件名称" + multipartFile.getOriginalFilename());
		/** 判断文件不为空 */
		if (!multipartFile.isEmpty()) {
			String suffix = multipartFile.getOriginalFilename().substring(
					multipartFile.getOriginalFilename().lastIndexOf("."));
			/** 使用UUID生成文件名称* */
			String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
			/** 拼成完整的文件保存路径加文件* */
			String fileName = RealPathDir + File.separator + logImageName;
			System.out.println(fileName);
			File file = new File(fileName);
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/** 上传到服务器的文件的绝对路径* */
			String saveUrl = PathDir + "/" + logImageName;
			return saveUrl;
		}
		return "";
	}
	/**************************************************************************************
     * 功能：导入耗材记录
     * 作者：郑昕茹
     * 日期：2016-07-27
     **************************************************************************************/
	public void importExpendable(String File){
		Boolean isE2007=false;
		if(File.endsWith("xlsx")){
			isE2007=true;
		}
		//建立输入流
		try {
			//建立输入流
			InputStream input = new FileInputStream(File);
			Workbook wb =null;
			if(isE2007){
				wb=new XSSFWorkbook(input);
			}else{
				wb=new HSSFWorkbook(input);
			}
			//获取第一个表单数据
			Sheet sheet= wb.getSheetAt(0);
			//获取第一个表单迭代器
			Iterator<Row>rows=sheet.rowIterator();
			Row rowContent=null;// 表头
			String orderNumber ="";//订单号
			String expendableType ="";//类型
			String supplier="";//经销商名称
			String purchaseDate="";//申购时间
			String purchaseUser ="";//申购人
			String fundAccount ="";//经费账号
			String expendableName ="";//商品名称
			String expendableSource="";//类别
			String expendableSpecification="";//规格
			String brand ="";//品牌
			String expendableUnit="";//包装单位
			String unitPrice="";//单价（元）
			String quantity="";//数量
			String arriveQuantity ="";//到货数量
			String arriveTotalPrice="";//到货总价
			String expendableStatus="";//状态
			String ifDangereous ="";//危化品标记
			String dangerousType="";//危化品类型
			int a=0;
			while(rows.hasNext()){
				
				if(a==0){
					rowContent=rows.next();
					a=1;
				}
				Row row =rows.next();
				int column=sheet.getRow(0).getPhysicalNumberOfCells();
				
				for(int k=0;k<column;k++){
					if(row.getCell(k)!=null){
						row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
						String columnName = rowContent.getCell(k).getStringCellValue();
						String content = row.getCell(k).getStringCellValue();
						if(columnName.equals("订单号")){
							orderNumber = content;
						}
						if(columnName.equals("类型")){
							expendableType = content;
						}
						if(columnName.equals("经销商名称")){
							supplier = content;
						}
						if (columnName.equals("申购时间")) {
							purchaseDate = content;
						}
						if (columnName.equals("申购人")) {
							purchaseUser = content;
						}
						if (columnName.equals("经费账号")) {
							fundAccount = content;
						}
						if(columnName.equals("商品名称")){
							expendableName = content;
						}
						if(columnName.equals("类别")){
							expendableSource = content;
						}
						if (columnName.equals("规格")) {
							expendableSpecification = content;
						}
						if (columnName.equals("品牌")) {
							brand = content;
						}
						if (columnName.equals("包装单位")) {
							expendableUnit = content;
						}
						if(columnName.equals("单价（元）")){
							unitPrice = content;
						}
						if(columnName.equals("数量")){
							quantity = content.replaceAll("[^\\d]", "");// 替换所有的非数字
						}
						if (columnName.equals("到货数量")) {
							arriveQuantity = content.replaceAll("[^\\d]", "");// 替换所有的非数字
						}
						if (columnName.equals("到货总价（元）")) {
							arriveTotalPrice = content.replaceAll("[^\\d]", "");// 替换所有的非数字
						}
						if (columnName.equals("状态")) {
							expendableStatus = content;
						}
						if (columnName.equals("危化品标记")) {
							ifDangereous = content;
						}
						if (columnName.equals("危化品类型")) {
							dangerousType = content;
						}
					}
				}
				Expendable expendable = new Expendable();
				//订单号
				if(!orderNumber.equals("")){
					expendable.setOrderNumber(orderNumber);
				}
				if(!expendableType.equals("")){
					expendable.setExpendableType(expendableType);//类型
				}
				if(!supplier.equals("")){
					expendable.setSupplier(supplier);//经销商名称
				}
				//String转化为Calendar格式
				if(!purchaseDate.equals("")){
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = sdf.parse(purchaseDate);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					expendable.setPurchaseDate(calendar);//申购时间
				}
				//根据申购人工号找到申购人
				if(!purchaseUser.equals("")){
					User user = userDAO.findUserByPrimaryKey(purchaseUser);
					if(user!=null){
						expendable.setUser(user);//申购人
					}
				}
				if(!fundAccount.equals("")){
					expendable.setFundAccount(fundAccount);//经费账号
				}
				if(!expendableName.equals("")){
					expendable.setExpendableName(expendableName);//商品名称
				}
				if(!expendableSource.equals("")){
					expendable.setExpendableSource(expendableSource);//商品来源类别
				}
				if(!expendableSpecification.equals("")){
					expendable.setExpendableSpecification(expendableSpecification);//商品规格
				}
				if(!brand.equals("")){
					expendable.setBrand(brand);//品牌
				}
				if(!expendableUnit.equals("")){
					expendable.setExpendableUnit(expendableUnit);//包装单位
				}
				//单价
				if(!unitPrice.equals("")){
					BigDecimal bgUnitPrice = new BigDecimal(unitPrice);
					expendable.setUnitPrice(bgUnitPrice);
				}
				if(!quantity.equals("")){
					expendable.setQuantity(Integer.parseInt(quantity));//数量
				}
				if(!arriveQuantity.equals("")){
					expendable.setArriveQuantity(Integer.parseInt(arriveQuantity));//到货数量
				}
				//到货总价
				if(!arriveTotalPrice.equals("")){
					BigDecimal bgArriveTotalPrice = new BigDecimal(arriveTotalPrice);
					expendable.setArriveTotalPrice(bgArriveTotalPrice);
				}
				if(!expendableStatus.equals("")){
					expendable.setExpendableStatus(expendableStatus);//状态
				}
				if(!ifDangereous.equals("")){
					if(ifDangereous.equals("是"))expendable.setIfDangerous(1);//危化品标记
					if(ifDangereous.equals("否"))expendable.setIfDangerous(0);
				}
				if(!dangerousType.equals("")){
					expendable.setDangerousType(dangerousType);//危化品类型
				}
				expendable.setFlag(0);//标志位设为0，表示为导入数据
				expendableDAO.store(expendable);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/**************************************************************************************
     * 功能：根据username找到User
     * 作者：郑昕茹
     * 日期：2016-07-27
     **************************************************************************************/
	public User findUser(String name){
		String sql = "select u from User u where 1=1 and u.username = '"+name+"'";
		List<User> users = userDAO.executeQuery(sql, 0, -1);
		if (users.size()>0) {
			return users.get(0);
			// do nothing
		}else {
			return null;
		}
	}
	
}
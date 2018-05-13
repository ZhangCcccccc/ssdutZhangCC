package net.xidlims.service.asset;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.xidlims.dao.AssetAppRecordDAO;
import net.xidlims.dao.AssetDAO;
import net.xidlims.domain.Asset;
import net.xidlims.domain.AssetAppRecord;
import net.xidlims.domain.AssetCabinetWarehouseAccess;
import net.xidlims.domain.AssetCabinetWarehouseAccessRecord;
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



@Service("AssetService")
public class AssetServiceImpl implements  AssetService {
	
	@Autowired ShareService shareService;
	@Autowired AssetDAO assetDAO;
	@Autowired AssetAppRecordDAO assetAppRecordDAO;
	 
	/***********************************************************************************
	 * @功能：获取全部药品字典
	 * @author 郑昕茹
	 * @日期：2016-08-03
	 * **********************************************************************************/
	public List<Asset> findAllAssetsByCategory(int currpage,int pageSize, Asset asset,Integer category){
		String sql = "select a from Asset a where 1=1";
		sql += " and category ="+ category.toString();
		if(asset != null && asset.getId() != null){
			sql += " and id = " + asset.getId().toString();
		}
		return assetDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
	}
	/***********************************************************************************
	 * @功能：获取全部药品名称
	 * @author 郑昕茹
	 * @日期：2016-08-03
	 * **********************************************************************************/
	public List<Asset> findAllAssetNames(){
		String sql = "select a from Asset a where 1=1";
		return assetDAO.executeQuery(sql,0,-1);
	}
	/***********************************************************************************
	 * @功能：获取全部药品名称
	 * @author 郑昕茹
	 * @日期：2016-08-03
	 * **********************************************************************************/
	public List<Asset> findAllAssetNamesByCategory(Integer category){
		String sql = "select a from Asset a where 1=1";
		sql += " and category ="+ category.toString();
		return assetDAO.executeQuery(sql,0,-1);
	}
	/***********************************************************************************
	 * @功能：通过主键找到药品字典记录
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	public Asset findAssetByPrimaryKey(Integer id){
		return assetDAO.findAssetByPrimaryKey(id);
	}
	/***********************************************************************************
	 * @功能：删除药品字典记录
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	public boolean deleteAsset(Integer id){
		Asset asset = assetDAO.findAssetByPrimaryKey(id);
		if(asset != null){
			assetDAO.remove(asset);
			assetDAO.flush();
			return true;
		}
		return false;
	}
	/***********************************************************************************
	 * @功能：保存药品字典记录
	 * @author 郑昕茹
	 * @日期：2016-08-04
	 * **********************************************************************************/
	public Asset saveAsset(Asset asset){
		return assetDAO.store(asset);
	}
	/**************************************************************************************
     * 功能：导入药材字典记录
     * 作者：郑昕茹
     * 日期：2016-08-04
     **************************************************************************************/
	public void importAsset(String File, Integer category){
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
			String chName ="";//品名
			String specifications="";//规格
			String unit ="";//计量单位
			String cas ="";//cas号
			String flag="";//是否需要库存提醒
			String assetLimit="";//库存提醒上线 
			int a=0;
			while(rows.hasNext()){
				
				if(a==0){
					rowContent=rows.next();
					a=1;
				}
				Row row =rows.next();
				int column=sheet.getRow(0).getPhysicalNumberOfCells();
				chName ="";//品名
				specifications="";//规格
				unit ="";//计量单位
				cas ="";//cas号
				flag="";//是否需要库存提醒
				assetLimit="";//库存提醒上线 
				for(int k=0;k<column;k++){
					if(row.getCell(k)!=null){
						row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
						String columnName = rowContent.getCell(k).getStringCellValue();
						String content = row.getCell(k).getStringCellValue();
						if(columnName.equals("药品名称")){
							chName = content;
						}
						if(columnName.equals("规格")){
							specifications = content;
						}
						if(columnName.equals("单位")){
							unit = content;
						}
						if (columnName.equals("CAS号")) {
							cas = content;
						}
						if (columnName.equals("库存提醒")) {
							flag = content;
						}
						if (columnName.equals("提醒下限")) {
							assetLimit = content.replaceAll("[^\\d]", "");// 替换所有的非数字
						}
					}
				}
				if(!this.isDuplicate(chName,specifications)){
					Asset asset = new Asset();
					//品名
					if(!chName.equals("")){
						asset.setChName(chName);
					}
					//规格
					if(!specifications.equals("")){
						asset.setSpecifications(specifications);
					}
					//计量单位
					if(!unit.equals("")){
						asset.setUnit(unit);
					}
					//cas号
					if(!cas.equals("")){
						asset.setCas(cas);
					}
					//是否需要库存提醒
					if(flag.equals("")){
						asset.setFlag(0);
					}
					if(flag.equals("是")){
						asset.setFlag(1);
					}
					if(flag.equals("否")){
						asset.setFlag(0);
					}
					if(assetLimit.equals("")){
						asset.setFlag(0);
					}
					//库存提醒上限
					if(!assetLimit.equals("") && flag.equals("是")){
						asset.setAssetLimit(Integer.parseInt(assetLimit));
					}
					if(category.equals("试剂")){
						asset.setCategory(0);
					}
					if(category.equals("耗材")){
						asset.setCategory(1);
					}
					asset.setStatus(0);
					asset.setCategory(category);
					assetDAO.store(asset);
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	
	}
	/**************************************************************************************
     * 功能：药材字典记录根据名称和规格判重
     * 作者：郑昕茹
     * 日期：2016-08-05
     **************************************************************************************/
	public boolean isDuplicate(String chName, String specifications){
		//根据品名和规格查询到字典表中的数据 
		String sql = " select a from Asset a where 1=1 and chName = '" + chName+"'"; 
		if(!specifications.equals("")){
			sql +=" and specifications = '" + specifications + "'";
		}
		List<Asset> assets = assetDAO.executeQuery(sql,0,-1);
		//若查询到的数据>=1条说明存在
		if(assets.size() > 0)return true;
		else return false;
	}
	/***********************************************************************
	 * 功能：导入文件前的日期格式、数字格式检查
     * 作者：郑昕茹
     * 日期：2016-08-05
	 ************************************************************************/
	public String checkRegex(String filePath) throws ParseException{
		String checkResult = "";
		if (filePath!=null && !filePath.equals("")) {// 判空
			if(filePath.endsWith("xls")||filePath.endsWith("xlsx")){// 文件格式
				//判断是不是2007
		        boolean isE2007=false;
		        if(filePath.endsWith("xlsx")){
		            isE2007=true;
		        }
		        try {
		        	//建立输入流
		            InputStream input = new FileInputStream(filePath);
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
		            Row rowContent=null;
		            String chName ="";//品名
					String unit ="";//计量单位
					String flag="";//是否需要库存提醒
		            int a=0;
		            boolean isBreak = false;
		            while(rows.hasNext() && !isBreak){
		            	chName ="";//品名
						unit ="";//计量单位
						flag="";//是否需要库存提醒
			            if(a==0){
			                rowContent=rows.next();
			                a=1;
			            }
			            Row row =rows.next();
			            int column=sheet.getRow(0).getPhysicalNumberOfCells();
			            for(int k=0;k<column;k++){
			            	if(row.getCell(k)==null){
			            		if(rowContent.getCell(k)!=null){
			            			
			            		}
			            		String columnName = rowContent.getCell(k).getStringCellValue();
			            		if(columnName.equals("药品名称"))
			                    {
			                        checkResult = "nullError";//品名为空错误
			                        isBreak = true;// 终止外层循环
			                        break;
			                    }
			                    if(columnName.equals("单位"))
			                    {
			                    	checkResult = "nullError";//品名为空错误
			                        isBreak = true;// 终止外层循环
			                        break;
			                    }
			                    else checkResult = "success";
			            	}
			                if(row.getCell(k)!=null){
			                    row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
			                    String columnName = rowContent.getCell(k).getStringCellValue();
			                    if(columnName.equals("药品名称"))
			                    {
			                        chName = row.getCell(k).getStringCellValue();
			                        if (chName.equals("")) {
			                        	checkResult = "nullError";//品名为空错误
			                        	isBreak = true;// 终止外层循环
			                        	break;
									}
			                        else{
			                        	checkResult = "success";
			                        }
			                    }
			                    if(columnName.equals("单位"))
			                    {
			                        unit = row.getCell(k).getStringCellValue();
			                        if (unit.equals("")) {
			                        	checkResult = "nullError";//品名为空错误
			                        	isBreak = true;// 终止外层循环
			                        	break;
									}
			                        else{
			                        	checkResult = "success";
			                        }
			                    }
			                    if(columnName.equals("库存提醒"))
			                    {
			                        flag = row.getCell(k).getStringCellValue();
			                        if (!flag.equals("是")&&!flag.equals("否")&&!flag.equals("")) {
			                        	checkResult = "flagError";//是否需要库存提醒格式错误
			                        	isBreak = true;// 终止外层循环
			                        	break;
									}
			                        else{
			                        	checkResult = "success";
			                        }
			                    }
			                }
			            }
		            }
		            
		        } catch (FileNotFoundException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
			}else {
				checkResult = "fileError";// 文件格式错误
			}
		}
		return checkResult;
	}
	/***********************************************************************************
	 * @功能：获取药品字典表中药品在在用物资表中的药品及其总数量
	 * @author 郑昕茹
	 * @日期：2016-08-03
	 * **********************************************************************************/
	public Map<Asset,Double> findEveryTotalQuantitiesInAccess(int currpage,int pageSize, Asset asset,int flag){
		//找到药品字典中的所有药品记录
		String sql = "select a from Asset a where 1=1";
		//根据名称查询
		if(asset != null && asset.getChName() != null && !asset.getChName().equals("")){
			sql += " and a.chName like '%" + asset.getChName() + "%'";
		}
		List<Asset> listAssets = assetDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
		//存储每个药品记录，及其总数量
		Map<Asset, Double> map = new HashMap<Asset, Double>();
		/*
		 * 公用
		 */
		//遍历药品字典的每条记录，获得其在物资表中的对应的所有记录，并求得总数量
		for(Asset a:listAssets){
			//获得物资表中的多条记录
			Set<AssetCabinetWarehouseAccessRecord> accessRecords = a.getAssetCabinetWarehouseAccessRecords();
			//统计每种药品的数量
			double num = 0;
			for(AssetCabinetWarehouseAccessRecord accessRecord:accessRecords){
				if(accessRecord.getStatus() == 1 && accessRecord.getAssetCabinetWarehouseAccess().getIfPublic()==1)
				num += accessRecord.getCabinetQuantity().doubleValue();	
			}
			map.put(a, num);
		}
		return map;
	} 
	/***********************************************************************************
	 * @功能：药品溶液管理-药品申购(获取全部药品申购记录)
	 * @author 郑昕茹
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public List<AssetAppRecord> findAllAssetAppRecords(){
		User user = shareService.getUser();
		String sql = "select a from AssetAppRecord a where 1=1 and assetApp.user.cname='"+user.getCname()+"'";
		return assetAppRecordDAO.executeQuery(sql,0,-1);
	}
	
	/***********************************************************************************
	 * @功能：通过药品名称找到药品字典记录
	 * @author 徐文
	 * @日期：2016-08-09
	 * **********************************************************************************/
	public List<Asset> findAssetByChName(String chName,String specifications){
		String sql = "select a from Asset a where 1=1";
		sql += " and a.chName like '%" + chName + "%'";
		sql += " and a.specifications like '%" + specifications + "%'";
		return assetDAO.executeQuery(sql,0,-1);
	}
	
	/***********************************************************************************
	 * @功能：获取入过库的药品字典
	 * @author 郑昕茹
	 * @日期：2016-08-19
	 * **********************************************************************************/
	public List<Asset> findAllAssetsInStock(int currpage,int pageSize, Asset asset){
		String sql = "select a from Asset a where 1=1 and status = 1";
		if(asset != null && asset.getId() != null){
			sql += " and id = " + asset.getId().toString();
		}
		return assetDAO.executeQuery(sql, pageSize*(currpage-1), pageSize);
	}
	
	/***********************************************************************************
	 * @description：获取全部药品试剂名称
	 * @author 郑昕茹
	 * @date：2016-09-18
	 * **********************************************************************************/
	public List<Asset> findAllReagentAssetNames(){
		String sql = "select a from Asset a where 1=1 and category = 0";
		return assetDAO.executeQuery(sql,0,-1);
	}
}
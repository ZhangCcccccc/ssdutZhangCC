package net.xidlims.service.expendable;

import javax.servlet.http.HttpServletRequest;

import net.xidlims.domain.User;


public interface ExpendableImportService {
	/***********************************************************************************************
	 * @功能：通用模塊service層定義-获取上传文件的保存路径
	 * @作者：李小龙 
	 * @日期：2014-07-27
	 ***********************************************************************************************/
	public String getUpdateFilePath(HttpServletRequest request);
	/**************************************************************************************
     * 功能：导入耗材记录
     * 作者：郑昕茹
     * 日期：2016-07-27
     **************************************************************************************/
	public void importExpendable(String File);
	/**************************************************************************************
     * 功能：根据username找到User
     * 作者：郑昕茹
     * 日期：2016-07-27
     **************************************************************************************/
	public User findUser(String name);
	
}
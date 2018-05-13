package net.xidlims.constant;

public interface CommonConstantInterface {

	/************************************************************
	 *  常量範圍：通用常量管理，包含系统全局范围内所用常量定义、标志位定义以及全局量定义
	 *  魏诚 2014-07-15
	 *  命名规约：字符类型_常量应用定义_值的意义 
	 *  例： String STR_FLAG_AGREE = "审批同意";
	 *  int INT_FLAG_APPROV_ZERO = 0; //标记为为0，审批不同意
	 ************************************************************/
	
	/*
	 * 分页处理的常量定义
	 */
	//基本标记位0代表所有
	Integer INT_Flag_ZERO = 0;
	
	//基本标记位-1代表所有无匹配对象
	Integer INT_Flag_NEGATIVE = -1;
	// 为0时表示不分页
	Integer INT_CURRPAGE_ZERO = 0;
	// 当前分页为第一页
	Integer INT_CURRPAGE_FIRST = 1;
	// 默认每页分页的记录数
	Integer INT_PAGESIZE = 20;
	
	//Message 对应的权限
		int INT_AUTHID_FIRST=1;
		int INT_AUTHID_TWO=2;
		int INT_AUTHID_THREE=3;
		int INT_AUTHID_FOUR=4;
		int INT_AUTHID_FIVE=5;
		int INT_AUTHID_SIX=6;
		int INT_AUTHID_SEVEN=7;
		int INT_AUTHID_EIGHT=8;
		int INT_AUTHID_NINE=9;
		int INT_AUTHID_TEN=10;
		int INT_AUTHID_ELEVEN=11;
		int INT_AUTHID_TWELVE=12;
	
	
	
		//实验室项目卡申请
		String STR_OPERATIONITEM_TITLE="实验室项目卡申请";
		//审批同意
    	String STR_FLAG_AGREE = "审核通过";
    	//审批不同意
    	String STR_FLAG_DISAGREE = "<font color=red>审核未通过</font>";
    
    
    	//实验室预约申请
  		String STR_LABROOM_TITLE="实验室预约";
  		//审批同意
  		String STR_FLAG_SUCCESS = "预约成功";
  		//审批不同意
  		String STR_FLAG_FAIL = "<font color=red>预约失败</font>";
      
      
      
      
  		//项目立项申请
  		String STR_LABCONSTRUCTIONPROJECT_TITLE="项目立项申请";
  		//一级审批同意
  		String STR_LABCONSTRUCTIONPROJECT_SUCCESS_ONE="项目立项申请，学院领导审核同意，请教务处审核";
  		//一级审批不同意
  		String STR_LABCONSTRUCTIONPROJECT_FAIL_ONE="项目立项申请，学院领导审核不同意";
  		
  		//二级审批同意
  		String STR_LABCONSTRUCTIONPROJECT_SUCCESS_TWO="项目立项申请，教务处审核同意，请校领导审核";
  		//二级审批不同意
  		String STR_LABCONSTRUCTIONPROJECT_FAIL_TWO="项目立项申请，教务处审核不同意";
  		
  		//三级审批同意
  		String STR_LABCONSTRUCTIONPROJECT_SUCCESS_THREE="项目立项申请，校领导审核同意，审核通过";
  		//三级审批不同意
  		String STR_LABCONSTRUCTIONPROJECT_FAIL_THREE="项目立项申请，校领导审核不同意";
  		
  		
  		
  		//项目建设申请
  		String STR_LABCONSTRUCTIONPURCHASE_TITLE="项目建设申请";
  		//一级审批同意
  		String STR_LABCONSTRUCTIONPURCHASE_SUCCESS_ONE="项目建设申请，学院领导审核同意，请资产管理处审核";
  		//一级审批不同意
  		String STR_LABCONSTRUCTIONPURCHASE_FAIL_ONE="项目建设申请，学院领导审核不同意";
  		
  		//二级审批同意
  		String STR_LABCONSTRUCTIONPURCHASE_SUCCESS_TWO="项目建设申请，资产管理处审核同意，请校领导审核";
  		//二级审批不同意
  		String STR_LABCONSTRUCTIONPURCHASE_FAIL_TWO="项目建设申请，资产管理处审核不同意";
  		
  		//三级审批同意
  		String STR_LABCONSTRUCTIONPURCHASE_SUCCESS_THREE="项目建设申请，校领导审核同意，请财务总监审核";
  		//三级审批不同意
  		String STR_LABCONSTRUCTIONPURCHASE_FAIL_THREE="项目建设申请，校领导审核不同意";
  		
  		//四级审批同意
  		String STR_LABCONSTRUCTIONPURCHASE_SUCCESS_FOUR="项目建设申请，财务总监审核同意，审核通过";
  		//四级审批不同意
  		String STR_LABCONSTRUCTIONPURCHASE_FAIL_FOUR="项目建设申请，财务总监审核不同意";
  		
  		
  		
  		
  		
  		//项目经费申请
  		String STR_LABCONSTRUCTIONFUNDING_TITLE="项目经费申请";
  		//一级审批同意
  		String STR_LABCONSTRUCTIONFUNDING_SUCCESS_ONE="项目经费申请，实验中心主任审核同意，请资产管理处审核";
  		//一级审批不同意
  		String STR_LABCONSTRUCTIONFUNDING_FAIL_ONE="项目经费申请，实验中心主任审核不同意";
  		
  		//二级审批同意
  		String STR_LABCONSTRUCTIONFUNDING_SUCCESS_TWO="项目经费申请，资产管理处审核同意，请校领导审核";
  		//二级审批不同意
  		String STR_LABCONSTRUCTIONFUNDING_FAIL_TWO="项目经费申请，资产管理处审核不同意";
  		
  		//三级审批同意
  		String STR_LABCONSTRUCTIONFUNDING_SUCCESS_THREE="项目经费申请，校领导审核同意，请财务总监审核";
  		//三级审批不同意
  		String STR_LABCONSTRUCTIONFUNDING_FAIL_THREE="项目经费申请，校领导审核不同意";
  		
  		//四级审批同意
  		String STR_LABCONSTRUCTIONFUNDING_SUCCESS_FOUR="项目经费申请，财务总监审核同意，审核通过";
  		//四级审批不同意
  		String STR_LABCONSTRUCTIONFUNDING_FAIL_FOUR="项目经费申请，财务总监审核不同意";
  		
  		
  		//字典表类型
  		String STR_TCOURSESITE_CHANNELSTATE = "c_tcoursesite_channelstate";
    
    
}

package net.xidlims.web;


/**
 * 分页类,分页查询的对象模型
 * 作者：陈春辉
 * 更新：2014年8月11日
 */
public class PageModel {
	
    public int currPage;    //当前页
	public int totalRecords;//总记录
    public int pageSize;//分页大小
    public int totalPage; //总页数
    public int preiviousPage;//前一页
    public int nextPage;//后一页
    public int firstPage=1;//第一页
    public int lastPage; //末页
    


	//构造函数
    public PageModel(int currPage,int totalRecords,int pageSize){
    	this.currPage=currPage;
    	this.totalRecords=totalRecords;
    	this.pageSize=pageSize;
    	//计算总页数    	
    	this.totalPage= (this.totalRecords+this.pageSize-1)/this.pageSize;      
    	//计算前一页   	
    	this.preiviousPage=this.currPage<=1?1:this.currPage-1; 	           
        //计算后一页     
        this.nextPage=this.currPage>=this.totalPage?this.totalPage:this.currPage+1;       
    	//计算末页
        this.lastPage= this.totalPage<=0?1:this.totalPage;        
    }
    
    public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	
    public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPreiviousPage() {
		return preiviousPage;
	}

	public void setPreiviousPage(int preiviousPage) {
		this.preiviousPage = preiviousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
}

package excelTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

public class DBConnect {
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/xidlims"; 
	public static final String USERNAME = "root";
	public static final String PASSWORD = "gengshang";  
	public String IP;
	public String dBName;
	
	
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getdBName() {
		return dBName;
	}
	public void setdBName(String dBName) {
		this.dBName = dBName;
	}
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getMSQLConnection(String IP,String dBName){
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection("jdbc:mysql://"+IP+":3306/"+dBName,USERNAME,PASSWORD);
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("连接数据库异常············");
			conn=null;
		}
		return conn;
	}
	/**
	 * 关闭连接
	 * @param conn 链接对象
	 * @param pstmt 预编译对象
	 * @param rs 结果集
	 */
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
		try{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 执行sql语句
	 * @param sql
	 * @return
	 */
	public ResultSet executeSql(String sql,String IP,String dBName){
		ResultSet result=null;
		Connection conn=null;
		PreparedStatement  pstmt=null;
		
		try {
			conn=this.getMSQLConnection(IP,dBName);
			pstmt=conn.prepareStatement(sql);
			result=pstmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}/*finally{
			this.closeAll(conn, pstmt, null);
		}*/
		return  result;
	}
}

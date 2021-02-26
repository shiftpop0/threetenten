package cnedu.ncist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class Calculation {
	static Boolean flag = false;
	/**
	 * 获得一个数据库的连接
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/student";
		return DriverManager.getConnection(url, "root", "root");
	}
	/**
	 * 创建表
	 * 
	 */
	public Boolean createTable(String create_tb_Sql1,String create_tb_Sql2){
		//定义一个数据库连接对象
		Connection conn = null;
		//定义一个sql容器
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			int executeUpdate = stmt.executeUpdate(create_tb_Sql1);
			int executeUpdate2 = stmt.executeUpdate(create_tb_Sql2);
			//创建数据库表
			if(executeUpdate == executeUpdate2){
				System.out.println("数据库表已经创建成功！");
				flag = true;
			}else{
				System.out.println("数据库表创建失败！");
			}
		} catch (Exception e) {
			System.out.println("数据库表创建失败");
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	/**
	 * 将一个将一个List集合当中的数据保存到数据库当中
	 */
	public void save(List<Map<String,String>> list){
		//定义一个数据库的连接对象
		Connection conn = null;
		//定义一个sql容器对象
		Statement stmt =null;
		
		//遍历List集合
		for(int index = 0 ; index < list.size() ; index++) {
			Map<String, String> map = list.get(index);
			String insert ="INSERT INTO tb_rate (ZYDH, ZYMC, KLDM, KLMC,YEAR1,LQZY_sum, "
					                          + " BKZY1_sum, BKZY2_sum,BKZY3_sum,BKZY4_sum, "
					                          + " BKZY5_sum, "
					                          + " BKZY6_sum, "
					                          + " BKZY_allsum, LQZY1_sum,"
					                          + " qz, LQZY1_rate,BKZY1_rate,BD_rate,BD_sum "
					                          + ",TiaoJi_rate,TiaoJi_sum "
					                          + ") "
					+ "VALUES('"
					           + map.get("ZYDH")+"','"
					           + map.get("ZYMC")+"','"
					           + map.get("KLDM")+"','"
					           + map.get("KLMC")+"','"
					           + map.get("YEAR1")+"','"
					           + map.get("LQZY_sum")+"','"
					           + map.get("BKZY1_sum")+"','"
					           + map.get("BKZY2_sum")+"','"
					           + map.get("BKZY3_sum")+"','"
					           + map.get("BKZY4_sum")+"','"
					           + map.get("BKZY5_sum")+"','"
					           + map.get("BKZY6_sum")+"','"
					           + map.get("BKZY_allsum")+"','"
					           + map.get("LQZY1_sum")+"','"
					           + map.get("qz")+"','"
					           + map.get("LQZY1Rate")+"','"
					           + map.get("LQZY1Rate")+"','"
					           + map.get("BDRate")+"','"
					           + map.get("BD_sum")+"','"
					           + map.get("TiaoJiRate")+"','"
					           + map.get("TiaoJi_sum")
					           +"')";
			System.out.println(insert);
		   try {
				conn = getConnection();
				stmt = conn.createStatement();
				//创建数据库表
				boolean execute = stmt.execute(insert);
				System.out.println(execute);
				if(!execute){
					System.out.println("数据添加成功");
				}else{
					System.out.println("数据添加失败！");
				}
			} catch (Exception e) {
				System.out.println("数据添加失败");
				e.printStackTrace();
			}finally{
				if(conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(stmt != null){
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
	public void insert(List<Map<String,String>> major,List<Map<String,String>> yearList){
		//定义一个数据库的连接对象
		Connection conn = null;
		//定义一个sql容器对象
		Statement stmt =null;
		
		//遍历List集合
		for(int i = 0; i < yearList.size(); i++){
			Map<String, String> yearmap = yearList.get(i);
			System.out.println("---"+yearmap);
			for(int index = 0 ; index < major.size() ; index++) {
				Map<String, String> map = major.get(index);
				String insert ="INSERT INTO sheet2 (ZYDH, ZYMC, KLDM, KLMC,YEAR1) "
						+ "VALUES('"
						+ map.get("ZYDH")+"','"
						+ map.get("ZYMC")+"','"
						+ map.get("KLDM")+"','"
						+ map.get("KLMC")+"','"
						+ yearmap.get("YEAR1")
						+"')";
				System.out.println(insert);
				try {
					conn = getConnection();
					stmt = conn.createStatement();
					//创建数据库表
					boolean execute = stmt.execute(insert);
					System.out.println(execute);
					if(!execute){
						System.out.println("数据添加成功");
					}else{
						System.out.println("数据添加失败！");
					}
				} catch (Exception e) {
					System.out.println("数据添加失败");
					e.printStackTrace();
				}finally{
					if(conn != null){
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(stmt != null){
						try {
							stmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		}
	}
	

}

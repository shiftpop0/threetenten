package cnedu.ncist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MysqlDemo {
//	public static void main(String[] args) {
//		MysqlDemo mysql = new MysqlDemo();
//		Map<String, Object> map = new HashMap();
//		map.put("ID", 1);
//		map.put("username", "ss");
//		map.put("password", "1233");
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		list.add(map);
//		//mysql.save(list);
//		mysql.truncateTable();
////	    mysql.createTable();
////		mysql.selectTable();
////		mysql.createProvinceT();
//	}
	/**
	 * 获得一个数据库的连接
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://59.110.243.60/pro";
		return DriverManager.getConnection(url, "root", "admin");
	}
	/**
	 * 创建省份表
	 * 
	 */
	public void createProvinceT(){
		String createTableSql = "CREATE TABLE province("
				+ "id int primary key auto_increment,"
				+ "name varchar(20)"
				+ ")"; 
		//定义一个数据库连接对象
		Connection conn = null;
		//定义一个sql容器
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			//创建数据库表
			if(0 == stmt.executeUpdate(createTableSql)){
				System.out.println("数据库表已经创建成功！");
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
	}
	/**
	 * 动态的创建一个mysql的数据库表
	 */
	public void createTable(){
		String createTableSql = "CREATE TABLE enter_student("
				+"KSH varchar(14) ,"            //1考生号
//				+"ZKZH varchar(9),"             //2准考证号
				+"XM varchar(64),"      		//3姓名
//				+"CSNY date,"					//4出生年月
				+"XBMC varchar(2),"					//5性别名称
//				+"ZZMMMC varchar(32),"		    //6政治面貌名称
//				+"MZMC varchar(20),"				//7名族名称
//				+"KSLBMC varchar(16),"				//8考生类别名称
//				+"BYLBMC varchar(32),"				//9毕业类别名称
				+"ZXDM varchar(9),"				//10中学代码
				+"ZXMC varchar(30),"			//11中学名称
				+"YZBM varchar(30),"			//11中学名称
//				+"WYYZMC varchar(12),"				//12外语语种名称
//				+"HKDJ varchar(1),"				//13 未知设置为空
//				+"BMDW varchar(4),"				//15部门单位
//				+"KSTZ varchar(10),"			//16部门单位
//				+"XTDW varchar(20),"				 	//17部门单位
				//+"DQMC varchar(64),"				//18地区名称
				+"DQDM varchar(64),"				//18地区名称
				+"SFZH varchar(18),"			//19省份证号
				+"JTDZ varchar(128),"			//20部门单位
				//+"YZBM varchar(6),"				//21部门单位
//				+"LXDH varchar(30),"			//22部门单位
//				+"HKKH varchar(12),"			//23部门单位
//				+"KSTC varchar(64),"			//24部门单位
//				+"KSJLHCF varchar(100),"		//25部门单位
//				+"WYKS varchar(10),"				//26部门单位
//				+"ZSYJ varchar(200),"			//27部门单位
//				+"KSLXMC varchar(64),"				//考生类型名称
//				+"SJR varchar(12),"				//收件人
//				+"YSJZDM varchar(10),"				//30部门单位
//				+"WYTL varchar(10),"				//31部门单位
//				+"PCMC varchar(16),"			//批次名称
//				+"KLMC varchar(16),"			//考类名称
//				+"TDDW varchar(20),"					//34部门单位
//				+"JHXZ varchar(2),"					//35部门单位
				+"CJ double,"					//36部门单位
//				+"TZCJ double,"					//37部门单位
//				+"TDCJ double,"					//38部门单位
//				+"ZGF double,"					//39部门单位
//				+"YXDRCJ double,"				//40部门单位
//				+"ZYYTJZCJ double,"				//41部门单位
//				+"ZYYTFJCJ double,"				//42部门单位
//				+"TDZY varchar(1),"				//43部门单位
//				+"DQTDDW varchar(6),"				//44部门单位
//				+"YTZY varchar(4),"					//46部门单位
				+"LQZY varchar(4),"					//46部门单位
//				+"TDYYDM varchar(4),"				//47部门单位
//				+"BZ varchar(254),"				//48部门单位
//				+"ZYHG varchar(1),"				//49部门单位
//				+"TJHG varchar(1),"				//50部门单位
				+"LQFS varchar(32),"					//51部门单位
//				+"KSZT varchar(1),"				//52部门单位
//				+"SDBZ varchar(1),"				//53部门单位
//				+"TDDWDM1 varchar(6),"				//54部门单位
				+"ZYDH1 varchar(4),"				//55部门单位
//				+"TDDWDM2 varchar(6),"				//54部门单位
				+"ZYDH2 varchar(4),"				//55部门单位
//				+"TDDWDM3 varchar(6),"				//54部门单位
				+"ZYDH3 varchar(4),"				//55部门单位
//				+"TDDWDM4 varchar(6),"				//54部门单位
				+"ZYDH4 varchar(4),"				//55部门单位
//				+"TDDWDM5 varchar(6),"				//54部门单位
				+"ZYDH5 varchar(4),"				//55部门单位
//				+"TDDWDM6 varchar(6),"				//54部门单位
				+"ZYDH6 varchar(4),"				//55部门单位
//				+"ZYTZ varchar(5),"				//55部门单位
				+"ZYZYTJ varchar(50),"			//55部门单位
//				+"DXTJ varchar(1),"				//55部门单位
//				+"ZYTJFC varchar(80),"			//55部门单位
				/*+"ZYSXNR1 varchar(225),"				//
				+"ZYSXNR2 varchar(225),"				//55部门单位
				+"ZYSXNR3 varchar(225),"				//55部门单位
				+"ZYSXNR4 varchar(225),"				//55部门单位
				+"ZYSXNR5 varchar(225),"				//55部门单位
				+"ZYSXNR6 varchar(225),"				//55部门单位
//*/				//+"TJJLDM varchar(4),"				//55部门单位
//				+"BH double,"					//55部门单位
//				+"KSLSCZBZ varchar(1),"			//55部门单位
				+"GKCJX01 varchar(15),"				//55部门单位
				+"GKCJX02 varchar(15),"				//55部门单位
				+"GKCJX03 varchar(15),"				//55部门单位
				+"GKCJX04 varchar(15),"				//55部门单位
//				+"GKCJX05 varchar(15),"				//55部门单位
//				+"GKCJX06 varchar(15),"				//55部门单位
//				+"GKCJX07 varchar(15),"				//55部门单位
//				+"GKCJX08 varchar(15),"				//55部门单位
//				+"GKCJX09 varchar(15),"				//55部门单位
//				+"GKCJX10 varchar(15),"				//55部门单位
//				+"GKCJX11 varchar(15),"				//55部门单位
//				+"GKCJX12 varchar(15),"				//55部门单位
//				+"GKCJX13 varchar(15),"				//55部门单位
//				+"GKCJX14 varchar(15),"				//55部门单位
//				+"GKCJX15 varchar(15),"				//55部门单位
//				+"GKCJX16 varchar(15),"				//55部门单位
//				+"GKCJX17 varchar(15)"				//55部门单位
				+")charset=utf8";
		
		String checkTable = "select * from user_all_tables where table_name='bishe'";
		//定义一个数据库的连接对象
		Connection conn = null;
		//定义一个sql容器对象
		Statement stmt =null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			//创建数据库表
			
			if(0 == stmt.executeUpdate(createTableSql)){
				System.out.println("数据库表已经创建成功！");
			}else{
				System.out.println("数据库表创建失败！");
			}
		//	ResultSet executeQuery = stmt.executeQuery(checkTable);
			//System.out.println(executeQuery);
			
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
	}
	
	/**
	 * 清空mysql数据库表中的所有数据，但是保留表结构
	 */
	public void truncateTable(){
		String truncateTableSql = "truncate table enter_student";
		//定义一个数据库的连接对象
		Connection conn = null;
		//定义一个sql容器对象
		Statement stmt =null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			//清空数据库表
			if(0 == stmt.executeUpdate(truncateTableSql)){
				System.out.println("已经将数据库表清空！");
			}else{
				System.out.println("数据库表清空是被！");
			}
		} catch (Exception e) {
			System.out.println("数据库表清空是被");
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
	
	/**
	 * 将一个将一个List集合当中的数据保存到数据库当中
	 */
	public void save(List<Map<String,Object>> list,String year){
		//定义一个数据库的连接对象
		Connection conn = null;
		//定义一个sql容器对象
		Statement stmt =null;
		
		String year1 = year;
		
		//遍历List集合
		for(int index = 0 ; index < list.size() ; index++) {
			Map<String, Object> map = list.get(index);
			String insert ="INSERT INTO enter_student (KSH, XM, XBDM, ZXDM,ZXMC, "
					                          + " DQDM,SFZH, JTDZ,YZBM,LQFS, "
					                          + " CJ, "
					                          + "LQZY, "
					                          + "ZYDH1, ZYDH2, ZYDH3, "
					                          + "ZYDH4, ZYDH5, ZYDH6,ZYZYTJ, "
					                         /* + "ZYSXNR1, ZYSXNR2, ZYSXNR3, ZYSXNR4, ZYSXNR5, ZYSXNR6, "*/
					                          //+ "TJJLDM, BH, KSLSCZBZ, "
					                          + "GKCJX01, GKCJX02, GKCJX03, GKCJX04,BD,YEAR1 "
					                         // + "GKCJX05, GKCJX06, GKCJX07, GKCJX08, GKCJX09,"
					                         // + " GKCJX10, GKCJX11, GKCJX12, GKCJX13,GKCJX14,GKCJX15, GKCJX16,GKCJX17"
					                          + ") "
					+ "VALUES('"
					           + map.get("KSH")+"','"
					          // + map.get("ZKZH")+"','"
					           + map.get("XM")+"','"
					          // + map.get("CSNY")+"','"
					           + map.get("XBDM")+"','"
					         //  + map.get("ZZMMMC")+"','"
					         //  + map.get("MZMC")+"','"
					          // + map.get("KSLBMC")+"','"
					          // + map.get("BYLBMC")+"','"
					           + map.get("ZXDM")+"','"
					           + map.get("ZXMC")+"','"
					          // + map.get("WYYZMC")+"','"
					         //  + map.get("BMDW")+"','"
					         //  + map.get("KSTZ")+"','"
					         //  + map.get("XTDW")+"','"
					          // + map.get("DQMC")+"','"
					           + map.get("DQDM")+"','"
					           + map.get("SFZH")+"','"
					           + map.get("JTDZ")+"','"
					           + map.get("YZBM")+"','"
					           + map.get("LQFS")+"','"
					         //  + map.get("LXDH")+"','"
					          // + map.get("HKKH")+"','"
					          // + map.get("KSTC")+"','"
					          // + map.get("KSJLHCF")+"','"
					           //+ map.get("WYKS")+"','"
					          // + map.get("ZSYJ")+"','"
					          // + map.get("KSLXMC")+"','"
					           //+ map.get("SJR")+"','"
					          // + map.get("YSJZDM")+"','"
					          // + map.get("WYTL")+"','"
					          // + map.get("PCMC")+"','"
					          // + map.get("KLMC")+"','"
					          // + map.get("TDDW")+"','"
					          // + map.get("JHXZ")+"','"
					           + map.get("CJ")+"','"
					          // + map.get("TZCJ")+"','"
					          // + map.get("TDCJ")+"','"
					         //  + map.get("ZGF")+"','"
					         //  + map.get("YXDRCJ")+"','"
					         //  + map.get("ZYYTJZCJ")+"','"
					          // + map.get("ZYYTFJCJ")+"','"
					          // + map.get("TDZY")+"','"
					         //  + map.get("DQTDDW")+"','"
					         //  + map.get("YTZY")+"','"
					           + map.get("LQZY")+"','"
					         //  + map.get("TDYYDM")+"','"
					         //  + map.get("BZ")+"','"
					         //  + map.get("ZYHG")+"','"
					         //  + map.get("TJHG")+"','"
					         //  + map.get("LQFS")+"','"
					         //  + map.get("KSZT")+"','"
					         //  + map.get("SDBZ")+"','"
					         //  + map.get("TDDWDM1")+"','"
					           + map.get("ZYDH1")+"','"
					         //  + map.get("TDDWDM2")+"','"
					           + map.get("ZYDH2")+"','"
					          // + map.get("TDDWDM3")+"','"
					           + map.get("ZYDH3")+"','"
					          // + map.get("TDDWDM4")+"','"
					           + map.get("ZYDH4")+"','"
					          // + map.get("TDDWDM5")+"','"
					           + map.get("ZYDH5")+"','"
					          // + map.get("TDDWDM6")+"','"
					           + map.get("ZYDH6")+"','"
					          // + map.get("ZYTZ")+"','"
					           + map.get("ZYZYTJ")+"','"
					          // + map.get("DXTJ")+"','"
					          // + map.get("ZYTJFC")+"','"
					          /* + map.get("ZYSXNR1")+"','"
					           + map.get("ZYSXNR2")+"','"
					           + map.get("ZYSXNR3")+"','"
					           + map.get("ZYSXNR4")+"','"
					           + map.get("ZYSXNR5")+"','"
					           + map.get("ZYSXNR6")+"','"*/
					         //  + map.get("TJJLDM")+"','"
					        //   + map.get("BH")+"','"
					           //+ map.get("KSLSCZBZ")+"','"
					           + map.get("GKCJX01")+"','"
					           + map.get("GKCJX02")+"','"
					           + map.get("GKCJX03")+"','"
					           + map.get("GKCJX04")+"','"
					           + 0+"','"
					           + year1+"'"
//					           + map.get("GKCJX06")+"','"
//					           + map.get("GKCJX07")+"','"
//					           + map.get("GKCJX08")+"','"
//					           + map.get("GKCJX09")+"','"
//					           + map.get("GKCJX10")+"','"
//					           + map.get("GKCJX11")+"','"
//					           + map.get("GKCJX12")+"','"
//					           + map.get("GKCJX13")+"','"
//					           + map.get("GKCJX14")+"','"
//					           + map.get("GKCJX15")+"','"
//					           + map.get("GKCJX16")+"','"
//					           + map.get("GKCJX17")
					           +");";
		//	System.out.println("GKCJX14--"+map.get("GKCJX14"));
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
	
	/**
	 * 查询mysql当中的数据
	 */
	public void selectTable(){
		String selectTableSql = "select * from bishe2 where KSH='18130102150587'";
		//定义一个数据库的连接对象
		Connection conn = null;
		//定义一个sql容器对象
		Statement stmt =null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			//创建数据库表
			ResultSet rs = stmt.executeQuery(selectTableSql);
			  
			while(rs.next()){
				String xm = rs.getString("XM");
				System.out.println(xm);
			}
		} catch (Exception e) {
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
	public List<Integer> yearList(){
		String yearList_sql = "SELECT enter_student.YEAR1 FROM enter_student GROUP BY YEAR1 DESC";
		//定义一个数据库的连接对象
		Connection conn = null;
		//定义一个sql容器对象
		Statement stmt =null;
		List<Integer> yearList = new ArrayList<Integer>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			//创建数据库表
			ResultSet rs = stmt.executeQuery(yearList_sql);
			
			while(rs.next()){
				yearList.add(rs.getInt("YEAR1"));
			}
		} catch (Exception e) {
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
		return yearList;
	}
}

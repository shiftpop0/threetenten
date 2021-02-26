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
	 * ���һ�����ݿ������
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/student";
		return DriverManager.getConnection(url, "root", "root");
	}
	/**
	 * ������
	 * 
	 */
	public Boolean createTable(String create_tb_Sql1,String create_tb_Sql2){
		//����һ�����ݿ����Ӷ���
		Connection conn = null;
		//����һ��sql����
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			int executeUpdate = stmt.executeUpdate(create_tb_Sql1);
			int executeUpdate2 = stmt.executeUpdate(create_tb_Sql2);
			//�������ݿ��
			if(executeUpdate == executeUpdate2){
				System.out.println("���ݿ���Ѿ������ɹ���");
				flag = true;
			}else{
				System.out.println("���ݿ����ʧ�ܣ�");
			}
		} catch (Exception e) {
			System.out.println("���ݿ����ʧ��");
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
	 * ��һ����һ��List���ϵ��е����ݱ��浽���ݿ⵱��
	 */
	public void save(List<Map<String,String>> list){
		//����һ�����ݿ�����Ӷ���
		Connection conn = null;
		//����һ��sql��������
		Statement stmt =null;
		
		//����List����
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
				//�������ݿ��
				boolean execute = stmt.execute(insert);
				System.out.println(execute);
				if(!execute){
					System.out.println("������ӳɹ�");
				}else{
					System.out.println("�������ʧ�ܣ�");
				}
			} catch (Exception e) {
				System.out.println("�������ʧ��");
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
		//����һ�����ݿ�����Ӷ���
		Connection conn = null;
		//����һ��sql��������
		Statement stmt =null;
		
		//����List����
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
					//�������ݿ��
					boolean execute = stmt.execute(insert);
					System.out.println(execute);
					if(!execute){
						System.out.println("������ӳɹ�");
					}else{
						System.out.println("�������ʧ�ܣ�");
					}
				} catch (Exception e) {
					System.out.println("�������ʧ��");
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

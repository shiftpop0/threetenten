package cnedu.ncist.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessDemo {
	/**
	 * ����
	 */
   /* public static void main(String[] args){
    //	String path = "G:/bishe/Hebei/1110400/NacuesCUniv.mdb";
//    	String path = "C:/Users/dell/Desktop/�а���Ŀ/2014����������/Shanxi61/1110400/NacuesCUniv.mdb";
    	String path = "C:/Users/dell/Desktop/�а���Ŀ/2018����������/Anhui/1110400/NacuesCUniv.mdb";
    	AccessDemo access = new AccessDemo();
    	access.connection(path);
	}*/
	public List<Map<String, Object>> connection(String path){
		//������Ҫ�Ķ���
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 1;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver");
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//			String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+path;
//			conn = DriverManager.getConnection(url);
			conn = DriverManager.getConnection("jdbc:Access:///"+path);
			//conn = DriverManager.getConnection("jdbc:Access:///G:/bishe/text1.mdb");
			ps = conn.prepareStatement("select * from T_TDD ");
			//ps = conn.prepareStatement("select * from user");
			rs = ps.executeQuery();
		
			ArrayList<String> specialNameList1 = new ArrayList<String>();
			specialNameList1.add("XBDM"); 
			specialNameList1.add("ZZMMDM");
			specialNameList1.add("MZDM");
			specialNameList1.add("KSLBDM");
			specialNameList1.add("BYLBDM");
			specialNameList1.add("WYYZDM");
//			specialNameList1.add("DQDM");
			specialNameList1.add("KSLXDM");
			specialNameList1.add("PCDM");
			specialNameList1.add("KLDM");
			specialNameList1.add("ZYSXDM1");
			specialNameList1.add("ZYSXDM2");
			specialNameList1.add("ZYSXDM3");
			specialNameList1.add("ZYSXDM4");
			specialNameList1.add("ZYSXDM5");
			specialNameList1.add("ZYSXDM6");
			specialNameList1.add("LQFS");
			ArrayList<String> specialNameList2 = new ArrayList<String>();
			specialNameList2.add("XTDW"); 
			specialNameList2.add("YSJZDM");
			specialNameList2.add("TDDW");
			specialNameList2.add("ZGF");
			specialNameList2.add("DQTDDW");
			specialNameList2.add("YTZY");
//			specialNameList2.add("LQZY");
			specialNameList2.add("TDYYDM");
//			specialNameList2.add("LQFS");
			specialNameList2.add("TDDWDM1");
//			specialNameList2.add("ZYDH1");
			specialNameList2.add("TDDWDM2");
//			specialNameList2.add("ZYDH2");
			specialNameList2.add("TDDWDM3");
//			specialNameList2.add("ZYDH3");
			specialNameList2.add("TDDWDM4");
//			specialNameList2.add("ZYDH4");
			specialNameList2.add("TDDWDM5");
//			specialNameList2.add("ZYDH5");
			specialNameList2.add("TDDWDM6");
//			specialNameList2.add("ZYDH6");
			specialNameList2.add("TJJLDM");
			specialNameList2.add("GKCJX05");
			specialNameList2.add("GKCJX10");
			specialNameList2.add("GKCJX16");
			specialNameList2.add("GKCJX17");
			ArrayList<String> specialNameList4 = new ArrayList<String>();
			specialNameList4.add("ZYDH1");
			specialNameList4.add("ZYDH2");
			specialNameList4.add("ZYDH3");
			specialNameList4.add("ZYDH4");
			specialNameList4.add("ZYDH5");
			specialNameList4.add("ZYDH6");
			specialNameList4.add("LQZY");
			ArrayList<String> specialNameList3 = new ArrayList<String>();
			specialNameList3.add("130503");
			specialNameList3.add("130504");
			specialNameList3.add("B0829H");
			specialNameList3.add("B080601");
			specialNameList3.add("B080701");
			                           
			specialNameList3.add("B080703");
			specialNameList3.add("B040203");
			specialNameList3.add("0120203");
			specialNameList3.add("0020401");
			specialNameList3.add("0120202");
			                           
			specialNameList3.add("0120201");
			specialNameList3.add("0050101");
			specialNameList3.add("0030101");
			specialNameList3.add("0050103");
			specialNameList3.add("0050301");
			                           
			specialNameList3.add("0050107");
			specialNameList3.add("0050306");
			specialNameList3.add("0050201");
			specialNameList3.add("0050207");
			specialNameList3.add("B080202");
			                            
			specialNameList3.add("B080601");
			specialNameList3.add("B080203");
			specialNameList3.add("B080204");
			specialNameList3.add("B080701");
			specialNameList3.add("B080801");
			                           
			specialNameList3.add("B080703");
			specialNameList3.add("B081004");
			specialNameList3.add("B080706");
			specialNameList3.add("B080901");
			specialNameList3.add("B120102");
			                            
			specialNameList3.add("B080903");
			specialNameList3.add("B080902");
			specialNameList3.add("B080905");
			specialNameList3.add("B080911");
			specialNameList3.add("B081001");
			                            
			specialNameList3.add("B081002");
			specialNameList3.add("B120103");
			specialNameList3.add("B081201");
			specialNameList3.add("B120104");
			specialNameList3.add("B070504");
			                            
			specialNameList3.add("B082901");
			specialNameList3.add("B081501");
			specialNameList3.add("B081401");
			specialNameList3.add("B081005");
			specialNameList3.add("B082502");
			                            
			specialNameList3.add("B081503");
			specialNameList3.add("B081301");
			specialNameList3.add("B080401");
			specialNameList3.add("B070302");
			specialNameList3.add("B120203");
			                            
			specialNameList3.add("B020401");
			specialNameList3.add("B120202");
			specialNameList3.add("B120201");
			specialNameList3.add("B120801");
			specialNameList3.add("B120401");
			                           
			specialNameList3.add("B050306");
			specialNameList3.add("B070102");
			specialNameList3.add("B071202");
			                      
			Map<String, String> zydm = new HashMap<String, String>();
			zydm.put("130503", "13");
			zydm.put("130504", "14");
			zydm.put("B0829H", "15");
			zydm.put("B080601", "56");
			zydm.put("B080701", "57");
			
			zydm.put("B080703", "58");
			zydm.put("B040203", "55");
			zydm.put("0120203", "01");
			zydm.put("0020401", "02");
			zydm.put("0120202", "03");
			          
			zydm.put("0120201", "04");
			zydm.put("0050101", "05");
			zydm.put("0030101", "06");
			zydm.put("0050103", "07");
			zydm.put("0050301", "08");
			          
			zydm.put("0050107", "09");
			zydm.put("0050306", "10");
			zydm.put("0050201", "11");
			zydm.put("0050207", "12");
			zydm.put("B080202", "16");
			          
			zydm.put("B080601", "17");
			zydm.put("B080203", "18");
			zydm.put("B080204", "19");
			zydm.put("B080701", "20");
			zydm.put("B080801", "21");
			          
			zydm.put("B080703", "22");
			zydm.put("B081004", "23");
			zydm.put("B080706", "24");
			zydm.put("B080901", "25");
			zydm.put("B120102", "26");
			          
			zydm.put("B080903", "27");
			zydm.put("B080902", "28");
			zydm.put("B080905", "29");
			zydm.put("B080911", "30");
			zydm.put("B081001", "31");
			          
			zydm.put("B081002", "32");
			zydm.put("B120103", "33");
			zydm.put("B081201", "34");
			zydm.put("B120104", "35");
			zydm.put("B070504", "36");
			          
			zydm.put("B082901", "37");
			zydm.put("B081501", "38");
			zydm.put("B081401", "39");
			zydm.put("B081005", "40");
			zydm.put("B082502", "41");
			          
			zydm.put("B081503", "42");
			zydm.put("B081301", "43");
			zydm.put("B080401", "44");
			zydm.put("B070302", "45");
			zydm.put("B120203", "46");
			          
			zydm.put("B020401", "47");
			zydm.put("B120202", "48");
			zydm.put("B120201", "49");
			zydm.put("B120801", "50");
			zydm.put("B120401", "51");
			          
			zydm.put("B050306", "52");
			zydm.put("B070102", "53");
			zydm.put("B071202", "54");
			
			Map<String, String> lqfs = new HashMap<String, String>();
			lqfs.put("ͳ��", "10");
			lqfs.put("����", "12");
			lqfs.put("����", "13");
			lqfs.put("����ר��", "23");
			
			//��¼ȡ��ʽ��浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_LQFSDM where (LQFSMC in('Զ��','����ͳ����','ͳ��¼ȡ','����¼ȡ','һ��¼ȡ','����','����¼ȡ','����ͳ����','ͳ��','����','һ��ͳ����'))"
					+ " or (LQFSMC like '����%') or (LQFSMC like '����%') or (LQFSMC like '����ר��%') or (LQFSMC like 'ƶ��%')");
			ResultSet LQFS = ps.executeQuery();
			Map<String, Object> mapLQFS = new HashMap<String, Object>();
			while(LQFS.next()){
				if(LQFS.getString("LQFSMC").contains("����¼ȡ") || LQFS.getString("LQFSMC").contains("����ͳ����") || LQFS.getString("LQFSMC").contains("ͳ��") || LQFS.getString("LQFSMC").contains("����")
				    || LQFS.getString("LQFSMC").contains("һ��ͳ����") || LQFS.getString("LQFSMC").contains("����") || LQFS.getString("LQFSMC").contains("һ��¼ȡ") || LQFS.getString("LQFSMC").contains("����¼ȡ")
				    || LQFS.getString("LQFSMC").contains("ͳ��¼ȡ") || LQFS.getString("LQFSMC").contains("����ͳ����") || LQFS.getString("LQFSMC").contains("Զ��")){
					mapLQFS.put(binaryAmind(LQFS.getString("LQFSDM")),"ͳ��");
				}else if(LQFS.getString("LQFSMC").contains("����")){
					mapLQFS.put(binaryAmind(LQFS.getString("LQFSDM")),"����");
				}else if(LQFS.getString("LQFSMC").contains("����")){
					mapLQFS.put(binaryAmind(LQFS.getString("LQFSDM")),"����");
				}else if(LQFS.getString("LQFSMC").contains("����ר��") || LQFS.getString("LQFSMC").contains("ƶ��ר��")){
					mapLQFS.put(binaryAmind(LQFS.getString("LQFSDM")),"����ר��");
				}else{
					
					mapLQFS.put(binaryAmind(LQFS.getString("LQFSDM")), LQFS.getString("LQFSMC"));
				}
			}
			if(LQFS != null){
				LQFS.close();
				LQFS = null;
			}
			System.out.println(mapLQFS);
			
			
			//����浽map���ϵ���
		    ps = conn.prepareStatement("select * from t_tddzdsx where (ZDMC like '����%') or (ZDMC in('��ѧ','����','����ɼ�','�Ŀ���ѧ','�����ѧ','�Ŀ��ۺ�','����ۺ�','����/����')) or (ZDMC like '�ۺ�%') or (ZDMC like '��ѧ%')");
			ResultSet tddzdsx = ps.executeQuery();
			Map<String, String> mapTddzdsx = new HashMap<String, String>();
			while(tddzdsx.next()){
				if(tddzdsx.getString("ZDMC").contains("�ۺ�") || tddzdsx.getString("ZDMC").contains("����") ){
					mapTddzdsx.put("�ۺ�", tddzdsx.getString("ZDDH"));
				}else if(tddzdsx.getString("ZDMC").contains("����")){
					mapTddzdsx.put("����", tddzdsx.getString("ZDDH"));
				}else if(tddzdsx.getString("ZDMC").contains("��ѧ")){
					mapTddzdsx.put("��ѧ", tddzdsx.getString("ZDDH"));
				}else if(tddzdsx.getString("ZDMC").contains("����")){
					mapTddzdsx.put("����", tddzdsx.getString("ZDDH"));
				}
				
//			   mapTddzdsx.put(tddzdsx.getString("ZDMC"),tddzdsx.getString("ZDDH"));
		    }
			if(tddzdsx != null){
				tddzdsx.close();
				tddzdsx = null;
			}
			System.out.println(mapTddzdsx);
			
//			ps = conn.prepareStatement("select * from TD_XBDM where XBDM="+rs.getString("XBDM"));
			//���Ա��浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_XBDM");
			ResultSet XB = ps.executeQuery();
			Map<String, Object> mapXB = new HashMap<String, Object>();
			while(XB.next()){
			   mapXB.put(XB.getString("XBDM"), XB.getString("XBMC"));
		    }
			if(XB != null){
				XB.close();
				XB = null;
			}
//			System.out.println(mapXB);
		
			//��������ò��浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_ZZMMDM");
			ResultSet ZZMM = ps.executeQuery();
			Map<String, Object> mapZZMM = new HashMap<String, Object>();
			while(ZZMM.next()){
				mapZZMM.put(binaryAmind(ZZMM.getString("ZZMMDM")), ZZMM.getString("ZZMMMC"));
		    }
			if(ZZMM != null){
				ZZMM.close();
				ZZMM = null;
			}
//			System.out.println(mapZZMM);
			
			//�������浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_MZDM");
			ResultSet MZ = ps.executeQuery();
			Map<String, Object> mapMZ = new HashMap<String, Object>();
			while(MZ.next()){
				mapMZ.put(binaryAmind(MZ.getString("MZDM")), MZ.getString("MZMC"));
		    }
			if(MZ != null){
				MZ.close();
				MZ = null;
			}
//			System.out.println(mapMZ);
			
			//����������浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_KSLBDM");
			ResultSet KSLB = ps.executeQuery();
			Map<String, Object> mapKSLB = new HashMap<String, Object>();
			while(KSLB.next()){
				mapKSLB.put(KSLB.getString("KSLBDM"), KSLB.getString("KSLBMC"));
		    }
			if(KSLB != null){
				KSLB.close();
				KSLB = null;
			}
//			System.out.println(mapKSLB);
			
			//����ҵ����浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_BYLBDM");
			ResultSet BYLB = ps.executeQuery();
			Map<String, Object> mapBYLB = new HashMap<String, Object>();
			while(BYLB.next()){
				mapBYLB.put(BYLB.getString("BYLBDM"), BYLB.getString("BYLBMC"));
		    }
			if(BYLB != null){
				BYLB.close();
				BYLB = null;
			}
//			System.out.println(mapBYLB);
			
			//���������ֱ�浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_WYYZDM");
			ResultSet WYYZ = ps.executeQuery();
			Map<String, Object> mapWYYZ = new HashMap<String, Object>();
			while(WYYZ.next()){
				mapWYYZ.put(WYYZ.getString("WYYZDM"), WYYZ.getString("WYYZMC"));
		    }
			if(WYYZ != null){
				WYYZ.close();
				WYYZ = null;
			}
//			System.out.println(mapWYYZ);
			
			//��������浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_DQDM");
			ResultSet DQ = ps.executeQuery();
			Map<String, Object> mapDQ = new HashMap<String, Object>();
			while(DQ.next()){
				mapDQ.put(binaryAmind(DQ.getString("DQDM")), DQ.getString("DQMC"));
		    }
			if(DQ != null){
				DQ.close();
				DQ = null;
			}
//			System.out.println(mapDQ);
			
			//���������ͱ�浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_KSLXDM");
			ResultSet KSLX = ps.executeQuery();
			Map<String, Object> mapKSLX = new HashMap<String, Object>();
			while(KSLX.next()){
				mapKSLX.put(binaryAmind(KSLX.getString("KSLXDM")), KSLX.getString("KSLXMC"));
			}
			if(KSLX != null){
				KSLX.close();
				KSLX = null;
			}
//			System.out.println(mapKSLX);
			
			//�����α�浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_PCDM");
			ResultSet PC = ps.executeQuery();
			Map<String, Object> mapPC = new HashMap<String, Object>();
			while(PC.next()){
				mapPC.put(PC.getString("PCDM"), PC.getString("PCMC"));
			}
			if(PC != null){
				PC.close();
				PC = null;
			}
//			System.out.println(mapPC);
			
			//�������浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_KLDM");
			ResultSet KL = ps.executeQuery();
			Map<String, Object> mapKL = new HashMap<String, Object>();
			while(KL.next()){
				mapKL.put(KL.getString("KLDM"), KL.getString("KLMC"));
			}
			if(KL != null){
				KL.close();
				KL = null;
			}
//			System.out.println(mapKL);
			
			//����������浽map���ϵ���
			ps = conn.prepareStatement("select * from TD_ZYSXDM");
			ResultSet ZYSX = ps.executeQuery();
			Map<String, Object> mapZYSX = new HashMap<String, Object>();
			while(ZYSX.next()){
				mapZYSX.put(binaryAmind(ZYSX.getString("ZYSXDM")), ZYSX.getString("ZYSXNR"));
			}
			if(ZYSX != null){
				ZYSX.close();
				ZYSX = null;
			}
//			System.out.println(mapZYSX);
			
			
			//��¼ȡרҵ��浽map���ϵ���
			ps = conn.prepareStatement("select * from t_jhk");
			ResultSet jhk = ps.executeQuery();
			Map<String, Object> mapJhk = new HashMap<String, Object>();
			while(jhk.next()){
				//���ݴ˱��еĿ�������Ĵ�mapKL���л�Ŀ�������
				String str= (String)mapKL.get(binaryAmind(jhk.getString("KLDM")));
				if(str.equals("�����ۺ�")){
					String[] s1 = {"01","02"};
					String[] s2 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
							       "21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38"};
					String[] s3 = {"39","40","41","42","43","44","45","46","47"};
					if(Arrays.asList(s1).contains(binaryAmind(jhk.getString("ZYDH")))){
						//����
						mapJhk.put(binaryAmind(jhk.getString("PCDM")+jhk.getString("KLDM")+jhk.getString("ZYDH")), jhk.getString("ZYDM"));
					}else if(Arrays.asList(s2).contains(binaryAmind(jhk.getString("ZYDH")))){
						//���
						mapJhk.put(binaryAmind(jhk.getString("PCDM")+jhk.getString("KLDM")+jhk.getString("ZYDH")), "B"+jhk.getString("ZYDM"));
					}else if(Arrays.asList(s3).contains(binaryAmind(jhk.getString("ZYDH")))){
						//�Ŀ�
						mapJhk.put(binaryAmind(jhk.getString("PCDM")+jhk.getString("KLDM")+jhk.getString("ZYDH")), "0"+jhk.getString("ZYDM"));
					}else{
						//����
						mapJhk.put(binaryAmind(jhk.getString("PCDM")+jhk.getString("KLDM")+jhk.getString("ZYDH")), jhk.getString("ZYDM"));
					}
				}else if(str.contains("��")){
					mapJhk.put(binaryAmind(jhk.getString("PCDM")+jhk.getString("KLDM")+jhk.getString("ZYDH")), "0"+jhk.getString("ZYDM"));
				}else if(str.contains("��")){
					mapJhk.put(binaryAmind(jhk.getString("PCDM")+jhk.getString("KLDM")+jhk.getString("ZYDH")), "B"+jhk.getString("ZYDM"));
				}else{
					mapJhk.put(binaryAmind(jhk.getString("PCDM")+jhk.getString("KLDM")+jhk.getString("ZYDH")), jhk.getString("ZYDM"));
				}
			}
			if(jhk != null){
				jhk.close();
				jhk = null;
			}
			System.out.println(mapJhk);
			
			
			while(rs.next()){
//				System.out.println(rs.getString("YTZY"));
//				 System.out.println(rs.getString("ZYDH1")+"---"+rs.getString("ZYDH2")+"---"+rs.getString("ZYDH3")+"---"+rs.getString("ZYDH4")+"---"+rs.getString("ZYDH5")+"---"+rs.getString("ZYDH6"));
				
			   //����һ��map���ϣ��洢rs�����еļ�¼
			   Map<String, Object> map = new HashMap<String, Object>();
			   //
			   ResultSetMetaData metaData = rs.getMetaData();
//			   System.out.println(metaData.getColumnCount());
			   for(int i = 0;i<metaData.getColumnCount();i++){
				   String cName = metaData.getColumnName(i+1);
				   //System.out.println(cName);
				   if(specialNameList1.contains(cName)){
					 //XBDM ZZMMDM MZDM KSLBDM BYLBDM WYYZDM DQDM KSLXDM PCDM KLDM ZYSXDM
					   if("XBDM".equals(cName)){
						   map.put("XBDM", rs.getString(cName));
//						   System.out.println(rs.getString(cName));
						   map.put("XBMC", mapXB.get(rs.getString(cName)));
					   }
					   if("ZZMMDM".equals(cName)){
						   map.put("ZZMMMC", mapZZMM.get(binaryAmind(rs.getString(cName))));
					   }
					   if("MZDM".equals(cName)){
						   map.put("MZMC", mapMZ.get(binaryAmind(rs.getString(cName))));
					   }
					   if("KSLBDM".equals(cName)){
						   map.put("KSLBMC", mapKSLB.get(rs.getString(cName)));
					   }
					   if("BYLBDM".equals(cName)){
						   map.put("BYLBMC", mapBYLB.get(rs.getString(cName)));
					   }
					   /*if("WYYZDM".equals(cName)){
						   map.put("WYYZMC", mapWYYZ.get(rs.getString(cName)));
					   }*/
					  /* if("DQDM".equals(cName)){
						   map.put("DQDM", binaryAmind(rs.getString(cName)));
						   map.put("DQMC", mapDQ.get(binaryAmind(rs.getString(cName))));
					   }*/
					   if("KSLXDM".equals(cName)){
						   map.put("KSLXMC", mapKSLX.get(binaryAmind(rs.getString(cName))));
					   }
					   if("PCDM".equals(cName)){
						   map.put("PCMC", mapPC.get(binaryAmind(rs.getString(cName))));
					   }
					   if("KLDM".equals(cName)){
						   map.put("KLMC", mapKL.get(binaryAmind(rs.getString(cName))));
					   }
					   if("ZYSXDM1".equals(cName)){
						   map.put("ZYSXNR1", mapZYSX.get(binaryAmind(rs.getString("ZYSXDM1"))));
					   }
					   if("ZYSXDM2".equals(cName)){
						   map.put("ZYSXNR2", mapZYSX.get(binaryAmind(rs.getString("ZYSXDM2"))));
					   }
					   if("ZYSXDM3".equals(cName)){
						   map.put("ZYSXNR3", mapZYSX.get(binaryAmind(rs.getString("ZYSXDM3"))));
					   }
					   if("ZYSXDM4".equals(cName)){
						   map.put("ZYSXNR4", mapZYSX.get(binaryAmind(rs.getString("ZYSXDM4"))));
					   }
					   if("ZYSXDM5".equals(cName)){
						   map.put("ZYSXNR5", mapZYSX.get(binaryAmind(rs.getString("ZYSXDM5"))));
					   }
					   if("ZYSXDM6".equals(cName)){
						   map.put("ZYSXNR6", mapZYSX.get(binaryAmind(rs.getString("ZYSXDM6"))));
					   }
					   if("LQFS".equals(cName)){
						   String str = binaryAmind(rs.getString("LQFS"));
						  // map.put("LQDM", binaryAmind(rs.getString("LQFS")));
						   /*String s = (String)mapLQFS.get(str);
						   map.put("LQFS", lqfs.get(s));*/
						   if(str == null || str.equals("")){
							   map.put("LQFS", "10"); 
						   }else{
							   String s = (String)mapLQFS.get(str);
							   map.put("LQFS", lqfs.get(s));
						   }
//						   System.out.println(binaryAmind(rs.getString("LQFS")));
					   }
					   
				   }else if(specialNameList2.contains(cName)){
					   map.put(cName,  binaryAmind(rs.getString(cName)));
				   }else if(specialNameList4.contains(cName)){
					   /*String str = binaryAmind(rs.getString("PCDM")+rs.getString("KLDM")+rs.getString(cName));
					   String str1 = binaryAmind((String)mapJhk.get(str));
					   if(specialNameList3.contains(str1)){
						   map.put(cName, zydm.get(str1));
					   }else{
						   map.put(cName,  binaryAmind(rs.getString(cName)));
					   }*/
					   if("45".equals(rs.getString("KSH").substring(2, 4))){
						   String str = binaryAmind(rs.getString("PCDM")+rs.getString("KLDM")+rs.getString(cName));
						   String str1 = binaryAmind((String)mapJhk.get(str));
						   if(specialNameList3.contains(str1)){
							   map.put(cName, zydm.get(str1));
						   }else{
							   map.put(cName,  binaryAmind(rs.getString(cName)));
						   }
					   }else{
						   String str = binaryAmind(rs.getString("PCDM")+rs.getString("KLDM")+rs.getString(cName));
						   String str1 = binaryAmind((String)mapJhk.get(str));
						   if(specialNameList3.contains(str1)){
							   map.put(cName, zydm.get(str1));
						   }else{
							   map.put(cName,  binaryAmind(rs.getString(cName)));
						   } 
					   }
				   }else if("DQDM".equals(cName)){
					   String short1 = rs.getString("KSH").substring(2, 4)+"0000";
					   map.put(cName, short1);
					   map.put("DQMC", mapDQ.get(binaryAmind(rs.getString(cName))));
//					   System.out.println(short1);
				   }else if("GKCJX01".equals(cName)){
					   String str = mapTddzdsx.get("����");
					   map.put(cName, rs.getString(str));
//					   System.out.println(short1);
				   }else if("GKCJX02".equals(cName)){
					   String str = mapTddzdsx.get("��ѧ");
					   map.put(cName, rs.getString(str));
//					   System.out.println(short1);
				   }else if("GKCJX03".equals(cName)){
					   String str = mapTddzdsx.get("����");
					   map.put(cName, rs.getString(str));
//					   System.out.println(short1);
				   }else if("GKCJX04".equals(cName)){
					   if("32".equals(rs.getString("KSH").substring(2, 4))){
						   map.put(cName, rs.getString(cName));
					   }else if("41".equals(rs.getString("KSH").substring(2, 4))){
						   map.put(cName, rs.getString("GKCJX03"));
					   }else if("21".equals(rs.getString("KSH").substring(2, 4))){
						   map.put(cName, rs.getString("GKCJX24"));
					   }else{
						   String str = mapTddzdsx.get("�ۺ�");
						   map.put(cName, rs.getString(str));
					   }
//					   System.out.println(short1);
				   }else{
					   map.put(cName, rs.getString(cName));
				   }
			   }
			   if("62".equals(rs.getString("KSH").substring(2, 4))){
				   map.put("GKCJX03", rs.getString("GKCJX06"));
				   map.put("GKCJX04", rs.getString("GKCJX07"));
			   }else if("45".equals(rs.getString("KSH").substring(2, 4))){
				   map.put("GKCJX01", rs.getString("GKCJX11"));
				   map.put("GKCJX02", rs.getString("GKCJX12"));
				   map.put("GKCJX03", rs.getString("GKCJX14"));
				   map.put("GKCJX04", rs.getString("GKCJX15"));
			   }else if("37".equals(rs.getString("KSH").substring(2, 4))){
				   
				   map.put("GKCJX04", rs.getString("GKCJX09"));
			   }else{
				   
			   }
			   if(map.get("LQFS")==null){
				   map.put("LQFS", 10);
			   }
//			   System.out.println(map.get("LQZY")+"---"+map.get("ZYDH1")+"---"+map.get("ZYDH2")+"---"+map.get("ZYDH3")+"---"+map.get("ZYDH4")+"---"+map.get("ZYDH5")+"---"+map.get("ZYDH6"));
//			   System.out.println(map.get(rs.getString("ZYSXDM1")));
//			   System.out.println(map.get("ZYSXNR1"));
//			   System.out.println(map);
//		 
//			   System.out.println(map.get("LQFS"));
	
			   list.add(map);
			}
			System.out.println(list.size());
			//System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return list; 
	}
	/**
	 * ��
	 * @param binary
	 * @return
	 */
	public String binaryAmind(String binary){
		byte[] a = {0};
		byte[] b = {32};
		if(binary==null){
			return binary="0";
		}
		return binary.replaceAll(new String(a),"").replaceAll(new String(b),"");
		
	}
	/**
	 * ��ȡ����,�����ݴ浽list���ϵ���
	 */
	public void read(){
		
	}
}

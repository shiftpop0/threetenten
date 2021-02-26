package cnedu.ncist.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cnedu.ncist.util.AccessDemo;
import cnedu.ncist.util.MysqlDemo;

public class DatainputService {
	
	/**
	 * 从access数据库当中取出数据
	 * 
	 */
	public static List<Map<String, Object>> read(String path){
		
		AccessDemo access = new AccessDemo();
		List<Map<String, Object>> list = access.connection(path);
		return list;
	}
	
	/**
	 * 将读取的数据存入到mysql数据库中
	 */
	public static void input(String path,String year){
		List<String> folder = DatainputService.folder(path);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		String year1 = year;
		
		for(String fpath : folder){
			System.out.println(folder);
			list = DatainputService.read(fpath);
			//System.out.println(list.size());
			MysqlDemo mysql = new MysqlDemo();
			
			mysql.save(list,year1);
		}
	}
	
	/**
	 * 遍历指定路径下的所有文件夹
	 */
	
	public static List<String> folder(String path){
//		AccessDemo access = new AccessDemo();
		File file = new File(path);
		List<String> plist = new ArrayList<String>();
		if(file.isFile()){
			String name = file.getName();
			if(name.endsWith(".mdb")){
				String path2 = file.getPath();
				String fpath = path2.replace("\\", "/");
				
				
//				access.connection(fpath);
				plist.add(fpath);
			}
		}else{
			File[] list = file.listFiles();
			if(list != null){
				for(File f : list){
					if(f.isDirectory()){
						String name = f.getName();
//					System.out.println(name);
						String s = f+"/1110400/NacuesCUniv.mdb";
						String fpath = s.replace("\\", "/");
					//	System.out.println(fpath);
//						access.connection(fpath);
						plist.add(fpath);
					}
				}
			}
		}
		System.out.println(plist);
		return plist;
	}
	
	/**
	 * 测试
	 */
//	public static void main(String[] args) {
////		String path = "G:/bishe/2018年招生数据";
//		String path = "C:/Users/dell/Desktop/招办项目/2018年招生数据";
////		String path = "G:/bishe/Beijing/1110400/NacuesCUniv.mdb";
//		DatainputService d = new DatainputService();
//		d.input(path);
////	    d.folder(path);
//	}
	
}

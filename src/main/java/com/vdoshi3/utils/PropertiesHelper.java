package com.vdoshi3.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {
	public static Properties props;
	
	static{
		props = new Properties();
		InputStream is = null;
		try{
			is = PropertiesHelper.class.getResourceAsStream("/resources/app.properties");
			props.load(is);
		}catch(IOException fe){
			System.out.println(fe);
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	} //End of static block
	
	public static String getTtl(){
		return props.getProperty("jwt.ttl");
	}
	
	public static String getTtr(){
		return props.getProperty("jwt.ttr");
	}
	
}

package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class TestRun {
	/*
	 * Properties
	 * - Map 계열의 컬렉션 => key : value 형태로 데이터 저장
	 * - 문자열(String) 형태로 데이터를 저장
	 * 
	 * - 값을 저장할 때 : setProperty(key, value)
	 * - 값을 꺼내올 때 : getProperty(key)
	 * 
	 * - 저장되는 파일 종류 : .properties / .xml
	 */
	public static void main(String[] args) {
		jdbcSettingTest();
		queryTest();
		
	}
	public static void jdbcSettingTest() {
		Properties prop = new Properties();
		
		prop.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
		prop.setProperty("url", "jdbc:oracle:thin:@localhost:1521:xe");
		prop.setProperty("username", "C##JDBC");
		prop.setProperty("password", "JDBC");
		
		try {
			prop.store(new FileOutputStream("resources/driver.properties"), "DB 드라이버 및 연결정보");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("driver"));
		System.out.println(prop.getProperty("url"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
	}
	
	public static void queryTest() {}
	
}
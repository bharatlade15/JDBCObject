package com.jspiders.jdbcobject.update;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.jspiders.jdbcobject.entity.Student;

public class StudentUpdate {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static int result;
	private static FileReader fileReader;
	private static Properties properties;
	private static String query;
	private static String filePath = "C:\\WEJA1\\JDBCObject\\resources\\db_info.properties";

	public static void main(String[] args) {
		try {
			fileReader = new FileReader(filePath);
			properties = new Properties();
			properties.load(fileReader);

			// 1.Load Driver Class
			Class.forName(properties.getProperty("driverPath"));

			// 2.Open Connection
			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);

			// 3.prepareStatement
			query = "update student set contact=9876556789 where id=4";
			preparedStatement = connection.prepareStatement(query);

			// 4.process result
			result = preparedStatement.executeUpdate();// no argument method
			Student student=new Student();
			student.setId(result);
			System.out.println("Query OK,"+student.getId()+" row(s) affected.");
//			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//5.close connections
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

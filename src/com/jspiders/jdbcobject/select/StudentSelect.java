package com.jspiders.jdbcobject.select;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.jspiders.jdbcobject.entity.Student;

public class StudentSelect {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
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
			query = "select * from student";
			preparedStatement = connection.prepareStatement(query);

			// 4.process result
			resultSet = preparedStatement.executeQuery();// no argument method

			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setEmail(resultSet.getString(3));
				student.setContact(resultSet.getLong(4));
				System.out.println(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
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

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import utils.MySQLHelper;

public class StudentDAO { //DAO : Data Access Object
	public List<Student> selectAll(){
		List<Student> students = new ArrayList<Student>();
		String sql = "select * from student";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();//constructor
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				
				students.add(student);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}
	public Student selectById(String id){
		Student student = new Student();
		String sql = "select * from student where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {				
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));								
			}
			
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	public boolean insert(Student student) {
		boolean result = false;
		String sql = "insert into student(id, name, age) values(?, ?, ?)";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, student.getId());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getAge());
			
			int row = ps.executeUpdate();
			if(row>0){
				result = true;
			}
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	public boolean update(Student student) {
		boolean result = false;
		String sql = "update student set name = ?, age = ? where id = ?";
		try {
			
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getAge());
			ps.setString(3, student.getId());
			
			int row = ps.executeUpdate();
			if(row>0){
				result = true;
			}
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean delete(String id) {
		boolean result = false;
		String sql = "delete from student where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, id);			
			
			int row = ps.executeUpdate();
			if(row>0){
				result = true;
			}
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}

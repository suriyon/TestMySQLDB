package test;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import dao.StudentDAO;
import model.Student;
import utils.MySQLHelper;

public class TestDB {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		do{
			System.out.println("++++++++Main Menu+++++++++");
			System.out.println("1. Select All Student");
			System.out.println("2. Select Student by Id");
			System.out.println("3. Insert Student");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. End Program");
			System.out.println("++++++++++++++++++++++++++");
			System.out.print("Select Menu : ");			
			choice = sc.nextInt();
			StudentDAO dao = new StudentDAO();
			List<Student> students;
			Student student;
			String id, name;
			int age;
			boolean result;
			switch (choice) {
			case 1: System.out.println("Show All Student");
					
					students = dao.selectAll();
					for (Student std : students) {
						System.out.print(std.getId() + "\t" + std.getName() + "\t" + std.getAge());
						System.out.println();
					}
				break;
			case 2: System.out.println("Show Student By Id");
					System.out.print("Enter Id : ");
					id = sc.next();
					
					student = dao.selectById(id);
					
					System.out.print(student.getId() + "\t" + student.getName() + "\t" + student.getAge());
					System.out.println();
			
				break;
			case 3: System.out.println("Insert Student");
					System.out.print("Enter Id : ");
					id = sc.next();
					System.out.print("Enter Name : ");
					sc.nextLine();
					name = sc.nextLine();
					System.out.print("Enter Age : ");
					age = sc.nextInt();
					
					student = new Student();
					student.setId(id);
					student.setName(name);
					student.setAge(age);
					
					result = dao.insert(student);
					if(result){
	//					JOptionPane.showMessageDialog(null, "เพิ่มข้อมูลสำเร็จ");
						students = dao.selectAll();
						for (Student std : students) {
							System.out.print(std.getId() + "\t" + std.getName() + "\t" + std.getAge());
							System.out.println();
						}
					}else{
						JOptionPane.showMessageDialog(null, "เพิ่มข้อมูลไม่สำเร็จ");
					}
					
				break;
			case 4: System.out.println("Update Student");
				
				System.out.print("Enter Id : ");
				id = sc.next();
				System.out.print("Enter Name : ");
				sc.nextLine();
				name = sc.nextLine();
				
				System.out.print("Enter Age : ");
				age = sc.nextInt();
			
				student = new Student();
				student.setId(id);
				student.setName(name);
				student.setAge(age);
			
				result = dao.update(student);
			if(result){
	//			JOptionPane.showMessageDialog(null, "เพิ่มข้อมูลสำเร็จ");
				students = dao.selectAll();
				for (Student std : students) {
					System.out.print(std.getId() + "\t" + std.getName() + "\t" + std.getAge());
					System.out.println();
				}
			}else{
				//JOptionPane.showMessageDialog(null, "แก้ไขข้อมูลไม่สำเร็จ");
			}
			
				break;
			case 5: System.out.println("Delete Student");
			
			System.out.print("Enter Id : ");
			id = sc.next();
				
			result = dao.delete(id);
			if(result){
	//		JOptionPane.showMessageDialog(null, "เพิ่มข้อมูลสำเร็จ");
			students = dao.selectAll();
			for (Student std : students) {
				System.out.print(std.getId() + "\t" + std.getName() + "\t" + std.getAge());
				System.out.println();
				}
			}else{
				//JOptionPane.showMessageDialog(null, "ลบข้อมูลไม่สำเร็จ");
			}	
			break;
			default:
				break;
			}
		}while(choice!=6);
		System.out.println("End Program.");
		sc.close();
	}

}

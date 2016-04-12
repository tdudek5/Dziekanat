package pl.edu.agh.dziekanat.application.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pl.edu.agh.dziekanat.model.GroupStudent;
import pl.edu.agh.dziekanat.model.GroupStudentType;
import pl.edu.agh.dziekanat.persistance.BusinessSessionFactory;
import pl.edu.agh.dziekanat.person.Person;
import pl.edu.agh.dziekanat.person.PersonType;
import pl.edu.agh.dziekanat.person.Student;


public class GroupTest {

	BusinessSessionFactory bsf;
	Session session;

	public GroupTest() {
		bsf = BusinessSessionFactory.getInstance();
		session = bsf.getSession().openSession();

	}

	public void closeSession() {
		bsf.close();
	}

	public static void main(String[] args) {

		GroupTest groupTest = new GroupTest();
//		groupTest.createGroup();
//		groupTest.wylistujGrupy();
//
//		groupTest.wylistujGrupy(GroupStudentType.LAB);
		
		groupTest.wyszukajStudenta();
		groupTest.closeSession();
	}

	private void createGroup(){
		GroupStudent group = new GroupStudent();
		group.setName("Grupa IV");
		group.setGroupStudentType(GroupStudentType.LAB);
		
		session.beginTransaction();
		session.save(group);
		session.getTransaction().commit();
	}
	
	private void wylistujGrupy() {

		Query query = session.createQuery("FROM GroupStudent");
		List<GroupStudent> groupStudents = query.list();

		for (GroupStudent groupStudent : groupStudents) {
			System.out.println(	groupStudent.getName() + " " + groupStudent.getGroupStudentType().getDescription());	
		}
	}
	
	
	private void wylistujGrupy(GroupStudentType groupStudentType) {
		Query query = session.createQuery("FROM GroupStudent where groupStudentType=:groupStudentType");
		query.setString("groupStudentType", groupStudentType.toMnemonic());
		
		List<GroupStudent> groupStudents = query.list();

		for (GroupStudent groupStudent : groupStudents) {
			
			System.out.println(	groupStudent.getName() + " " + groupStudent.getGroupStudentType().getDescription());	
		}
	}
	
	private void przypisz(){
		session.beginTransaction();
		GroupStudent groupStudent = (GroupStudent)session.load(GroupStudent.class, 1002);
		Student student = (Student)session.load(Student.class, 14001);
		System.out.println(student.getLastName());
		
		
		System.out.println(groupStudent.getName());
		//przypiszGrupe(student, groupStudent);
		
		student.setGroupStudent(groupStudent);
		session.save(student);
		session.getTransaction().commit();
	}
	
	private void wyszukajStudenta(){
		Student student = (Student)session.load(Student.class, 14001);
		System.out.println(student.getGroupStudent().getName());
		
	}
	
	
}
package pl.wit.core;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

	private final List<Student> students = new ArrayList<>();

	public StudentRepository() {
		students.add(new Student("123","Paweł Wesołowski","ABC",12,12,12,12,12,12));
		students.add(new Student("123","Piotr Bródka","ABC",12,12,12,12,12,12));
	}

	public List<Student> getAllStudents() {
		return students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public void removeStudentByAlbum(String album) {
		Student student = students.stream()
				.filter(s -> s.getAlbum().equals(album))
				.findAny()
				.orElse(null);
		students.remove(student);
	}

	public void updateStudent(Student student) {
		students.stream()
				.filter(s -> s.getAlbum().equals(student.getAlbum()))
				.findAny()
				.ifPresent(s -> s.update(student));
	}
}

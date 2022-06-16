package pl.wit.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository of saved students
 * @author pawel.wesolowski
 */
class StudentRepository {
	/**
	 * List of students
	 */
	private final List<Student> students = new ArrayList<>();

	/**
	 * List of students getter
	 * @return list of students
	 */
	List<Student> getAllStudents() {
		return this.students;
	}

	/**
	 * Adding student to the list
	 * @param student Student Object to be saved
	 * @throws StudentAlreadyExistsException proven object already exists
	 */
	void addStudent(Student student) throws StudentAlreadyExistsException {
		if (findStudentByAlbum(student.getAlbum()).isPresent()) {
			throw new StudentAlreadyExistsException();
		}
		this.students.add(student);
	}

	/**
	 * Remove student from list using id
	 * @param album student's id
	 */
	void removeStudentByAlbum(int album) {
		this.students.stream()
				.filter(student -> student.getAlbum().equals(album))
				.findAny()
				.ifPresent(students::remove);
	}

	/**
	 * Load students from file
	 * @param students list of students from file
	 */
	void load(List<Student> students) {
		this.students.clear();
		this.students.addAll(students);
	}

	/**
	 * Finding student using id
	 * @param album student's id
	 * @return object of found student
	 */
	Optional<Student> findStudentByAlbum(int album) {
		return this.students.stream()
				.filter(s -> s.getAlbum().equals(album))
				.findAny();
	}
}

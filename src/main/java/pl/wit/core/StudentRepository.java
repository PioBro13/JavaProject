package pl.wit.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author pawel.wesolowski
 */
class StudentRepository {

	private final List<Student> students = new ArrayList<>();

	List<Student> getAllStudents() {
		return this.students;
	}

	void addStudent(Student student) throws StudentAlreadyExistsException {
		if (findStudentByAlbum(student.getAlbum()).isPresent()) {
			throw new StudentAlreadyExistsException();
		}
		this.students.add(student);
	}

	void removeStudentByAlbum(int album) {
		this.students.stream()
				.filter(student -> student.getAlbum().equals(album))
				.findAny()
				.ifPresent(students::remove);
	}

	void load(List<Student> students) {
		this.students.clear();
		this.students.addAll(students);
	}

	Optional<Student> findStudentByAlbum(int album) {
		return this.students.stream()
				.filter(s -> s.getAlbum().equals(album))
				.findAny();
	}
}

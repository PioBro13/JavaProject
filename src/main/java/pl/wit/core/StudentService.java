package pl.wit.core;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import pl.wit.core.validator.StudentValidator;
import pl.wit.core.validator.StudentValidatorImpl;

/**
 * @author pawel.wesolowski
 */
public class StudentService {

	private final StudentRepository studentRepository;

	private final StudentValidator studentValidator;

	private final StudentFileReaderWriter studentDataFileReaderWriter;

	public StudentService() {
		this.studentRepository = new StudentRepository();
		this.studentValidator = new StudentValidatorImpl();
		this.studentDataFileReaderWriter = new StudentFileReaderWriter();
	}

	public void save(String filename) throws IOException {
		List<Student> allStudents = studentRepository.getAllStudents();
		studentDataFileReaderWriter.save(allStudents, filename);
	}

	public void load(String filename) throws IOException {
		List<Student> students = studentDataFileReaderWriter.load(filename);
		studentRepository.load(students);
	}

	public List<Student> getAllStudents() {
		return studentRepository.getAllStudents();
	}

	public void removeStudentByAlbum(int album) {
		studentRepository.removeStudentByAlbum(album);
	}

	public StudentValidator getStudentValidator() {
		return studentValidator;
	}

	public Optional<Student> findStudentByAlbum(int album) {
		return studentRepository.findStudentByAlbum(album);
	}

	public void addStudent(Student student) throws StudentAlreadyExistsException {
		studentRepository.addStudent(student);
	}
}

package pl.wit.core;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import pl.wit.core.validator.StudentValidator;
import pl.wit.core.validator.StudentValidatorImpl;

/**
 * Service for each student
 * @author pawel.wesolowski
 */
public class StudentService {

	/**
	 * Repository of current students
	 */
	private final StudentRepository studentRepository;

	/**
	 * Valdator for given data
	 */
	private final StudentValidator studentValidator;

	/**
	 * Reader and writer of student's data
	 */
	private final StudentFileReaderWriter studentDataFileReaderWriter;

	/**
	 * Constructor of the service
	 */
	public StudentService() {
		this.studentRepository = new StudentRepository();
		this.studentValidator = new StudentValidatorImpl();
		this.studentDataFileReaderWriter = new StudentFileReaderWriter();
	}

	/**
	 * Saving object's data to file
	 * @param filename name of file
	 * @throws IOException wrong data
	 */
	public void save(String filename) throws IOException {
		List<Student> allStudents = studentRepository.getAllStudents();
		studentDataFileReaderWriter.save(allStudents, filename);
	}

	/**
	 * Loading data from file
	 * @param filename name of file
	 * @throws IOException wrong data
	 */
	public void load(String filename) throws IOException {
		List<Student> students = studentDataFileReaderWriter.load(filename);
		studentRepository.load(students);
	}

	/**
	 * All saved students getter
	 * @return list of all saved students
	 */
	public List<Student> getAllStudents() {
		return studentRepository.getAllStudents();
	}

	/**
	 * Remove student using his/hers id
	 * @param album student's id
	 */
	public void removeStudentByAlbum(int album) {
		studentRepository.removeStudentByAlbum(album);
	}

	/**
	 * Student validator getter
	 * @return validator object
	 */
	public StudentValidator getStudentValidator() {
		return studentValidator;
	}

	/**
	 * Finding student from list using his/hers id
	 * @param album student's id
	 * @return Object with student's data
	 */
	public Optional<Student> findStudentByAlbum(int album) {
		return studentRepository.findStudentByAlbum(album);
	}

	/**
	 * Adding new student to list
	 * @param student Student Object
	 * @throws StudentAlreadyExistsException exception when given student already exists
	 */
	public void addStudent(Student student) throws StudentAlreadyExistsException {
		studentRepository.addStudent(student);
	}
}

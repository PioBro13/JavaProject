package pl.wit.core;

import java.util.Arrays;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StudentRepositoryTest {

	StudentRepository studentRepository;

	Student student1 = new Student(1800, "Paweł Kwiatkowski", "IO3", 5, 5, 10, 18, 16, 30);

	Student student2 = new Student(1801, "Jan Kowalski", "IO3", 5, 5, 10, 18, 16, 30);

	@Before
	public void setUp() {
		studentRepository = new StudentRepository();
	}

	@Test
	public void getAllStudentsTest() throws StudentAlreadyExistsException {
		//given
		assertEquals(0, studentRepository.getAllStudents().size());

		//when
		studentRepository.addStudent(student1);
		studentRepository.addStudent(student2);

		//then
		assertEquals(2, studentRepository.getAllStudents().size());
	}

	@Test
	public void addStudentTest() throws StudentAlreadyExistsException {
		//given
		assertEquals(0, studentRepository.getAllStudents().size());
		studentRepository.addStudent(student1);

		//when
		Optional<Student> studentByAlbum = studentRepository.findStudentByAlbum(student1.getAlbum());

		//then
		assertTrue(studentByAlbum.isPresent());
		boolean exceptionThrown = false;
		try {
			studentRepository.addStudent(student1);
		} catch (Exception exception) {
			exceptionThrown = true;
			assertEquals("Student o podanym numerze albumu już istnieje!", exception.getMessage());
		}
		assertTrue(exceptionThrown);
	}

	@Test
	public void removeStudentByAlbumTest() throws StudentAlreadyExistsException {
		//given
		studentRepository.addStudent(student1);
		studentRepository.addStudent(student2);
		assertEquals(2, studentRepository.getAllStudents().size());

		//when
		studentRepository.removeStudentByAlbum(student2.getAlbum());

		//then
		assertEquals(1, studentRepository.getAllStudents().size());

	}

	@Test
	public void loadTest() {
		//given
		assertEquals(0, studentRepository.getAllStudents().size());

		//when
		studentRepository.load(Arrays.asList(student1, student2));

		//then
		assertEquals(2, studentRepository.getAllStudents().size());
	}

	@Test
	public void findStudentByAlbumTest() throws StudentAlreadyExistsException {
		//given
		studentRepository.addStudent(student1);
		studentRepository.addStudent(student2);
		assertEquals(2, studentRepository.getAllStudents().size());

		//when
		Student student = studentRepository.findStudentByAlbum(student2.getAlbum()).orElse(null);

		//then
		assertNotNull(student);
		assertEquals(student, student2);
	}
}
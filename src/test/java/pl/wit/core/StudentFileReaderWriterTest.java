package pl.wit.core;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StudentFileReaderWriterTest {

	private static final File OUTPUT_FILE = new File("src/test/resources/output.txt");

	Student student1 = new Student(1800, "Paweł Kwiatkowski", "IO3", 5, 5, 10, 18, 16, 30);

	Student student2 = new Student(1801, "Jan Kowalski", "IO3", 5, 5, 10, 18, 16, 30);

	StudentFileReaderWriter studentFileReaderWriter;

	@Before
	public void setUp() {
		studentFileReaderWriter = new StudentFileReaderWriter();
		deleteFileIfExists();
	}

	@After
	public void tearDown() {
		deleteFileIfExists();
	}

	private void deleteFileIfExists() {
		if (OUTPUT_FILE.exists()) {
			OUTPUT_FILE.delete();
		}
	}

	@Test
	public void saveAndLoadTest() throws IOException {
		//given
		studentFileReaderWriter.save(Arrays.asList(student1, student2), OUTPUT_FILE.getAbsolutePath());

		//when
		List<Student> students = studentFileReaderWriter.load(OUTPUT_FILE.getAbsolutePath());

		//then
		assertEquals(2, students.size());
	}
}
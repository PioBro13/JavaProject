package pl.wit.core;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StudentTest {

	@Test
	public void updateTest() {
		//given
		Student student = new Student(1800, "Paweł Kwiatkowski", "IO2", 5, 5, 10, 18, 16, 30);
		Student updateStudent = new Student(1800, "Jan Kowalski", "IO3", 5, 5, 10, 18, 16, 30);
		assertEquals("Paweł Kwiatkowski", student.getName());
		assertEquals("IO2", student.getGroup());
		assertEquals("Jan Kowalski", updateStudent.getName());
		assertEquals("IO3", updateStudent.getGroup());


		//when
		student.update(updateStudent);

		//then
		assertEquals("Jan Kowalski", student.getName());
		assertEquals("IO3", student.getGroup());
	}

	@Test
	public void sumTest() {
		//given
		Student student = new Student(1800, "Paweł Kwiatkowski", "IO2", 5, 5, 10, 18, 16, 30);

		//when
		int sum = student.getSum();

		//then
		assertEquals(84, sum);
	}
}
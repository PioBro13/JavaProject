package pl.wit.core.validator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author pawel.wesolowski
 */
public class StudentValidatorImplTest {

	private final StudentValidator studentValidator = new StudentValidatorImpl();

	@Test
	public void validateAlbumTest() {
		String message = "Numer albumu musi być większy od 0.";
		StudentFieldValidationResult result = studentValidator.validateAlbum(0);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateAlbum(-1);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateAlbum(1);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());
	}

	@Test
	public void validateNameTest() {
		String message = "Imię i naziwsko musi składać się z conajmniej dwóch członów.";
		StudentFieldValidationResult result = studentValidator.validateName("Paweł");
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateName("Paweł W");
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());

		result = studentValidator.validateName("");
		assertFalse(result.isValid());
		assertEquals("Imię i nazwisko nie może być puste.", result.getValidationMessage());

		result = studentValidator.validateName(null);
		assertFalse(result.isValid());
		assertEquals("Imię i nazwisko nie może być puste.", result.getValidationMessage());
	}

	@Test
	public void validateGroupTest() {
		String message = "Identyfikator grupy nie może być pusty.";
		StudentFieldValidationResult result = studentValidator.validateGroup(null);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateGroup("");
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateGroup("IO3");
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());
	}

	@Test
	public void validateActivityPointsTest() {
		String message = "Liczba punktów za aktywność musi mieścić się w przedziale <0, 5>.";
		StudentFieldValidationResult result = studentValidator.validateActivityPoints(10);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateActivityPoints(0);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());

		result = studentValidator.validateActivityPoints(5);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());

	}

	@Test
	public void validateHomeworkPointsTest() {
		String message = "Liczba punktów z pracy domowej musi mieścić się w przedziale <0, 5>.";
		StudentFieldValidationResult result = studentValidator.validateHomeworkPoints(10);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateHomeworkPoints(0);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());

		result = studentValidator.validateHomeworkPoints(5);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());
	}

	@Test
	public void validateProjectPointsTest() {
		String message = "Liczba punktów z projektu dodatkowego musi mieścić się w przedziale <0, 10>.";
		StudentFieldValidationResult result = studentValidator.validateProjectPoints(20);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateProjectPoints(0);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());

		result = studentValidator.validateProjectPoints(10);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());
	}

	@Test
	public void validateFirstTestPointsTest() {
		String message = "Liczba punktów z pierwszego kolokwium musi mieścić się w przedziale <0, 20>.";
		StudentFieldValidationResult result = studentValidator.validateFirstTestPoints(-5);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateFirstTestPoints(0);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());

		result = studentValidator.validateFirstTestPoints(20);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());
	}

	@Test
	public void validateSecondTestPointsTest() {
		String message = "Liczba punktów z drugiego kolokwium musi mieścić się w przedziale <0, 20>.";
		StudentFieldValidationResult result = studentValidator.validateSecondTestPoints(-5);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateSecondTestPoints(0);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());

		result = studentValidator.validateSecondTestPoints(20);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());
	}

	@Test
	public void validateExamPointsTest() {
		String message = "Liczba punktów z egzaminu musi mieścić się w przedziale <0, 40>.";
		StudentFieldValidationResult result = studentValidator.validateExamPoints(50);
		assertFalse(result.isValid());
		assertEquals(message, result.getValidationMessage());

		result = studentValidator.validateExamPoints(0);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());

		result = studentValidator.validateExamPoints(40);
		assertTrue(result.isValid());
		assertNull(result.getValidationMessage());
	}
}
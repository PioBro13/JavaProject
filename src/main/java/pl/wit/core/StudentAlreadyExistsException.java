package pl.wit.core;

/**
 * Exception that prevents adding two identical students
 */
public class StudentAlreadyExistsException extends Exception {

	/**
	 * Information given to user
	 */
	public StudentAlreadyExistsException() {
		super("Student o podanym numerze albumu już istnieje!");
	}
}

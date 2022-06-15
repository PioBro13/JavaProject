package pl.wit.core;

public class StudentAlreadyExistsException extends Exception {

	public StudentAlreadyExistsException() {
		super("Student o podanym numerze albumu już istnieje!");
	}
}

package pl.wit.core;

class StudentAlreadyExistsException extends Exception {

	public StudentAlreadyExistsException() {
		super("Student o podanym numerze albumu już istnieje!");
	}
}

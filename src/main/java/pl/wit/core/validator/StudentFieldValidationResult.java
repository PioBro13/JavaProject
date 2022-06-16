package pl.wit.core.validator;

/**
 * Result of validation in each cell
 * @author pawel.wesolowski
 */
public class StudentFieldValidationResult {

	/**
	 * Final result of validation
	 */
	private final boolean isValid;

	/**
	 * Message given to user
	 */
	private final String validationMessage;

	/**
	 * Validation result constructor
	 * @param isValid result of validation
	 * @param validationMessage error message if value is not valid
	 */
	private StudentFieldValidationResult(boolean isValid, String validationMessage) {
		this.isValid = isValid;
		this.validationMessage = validationMessage;
	}

	/**
	 * Constructor of failed validation
	 * @param validationMessage error message
	 * @return failed validation object
	 */
	public static StudentFieldValidationResult invalid(String validationMessage) {
		return new StudentFieldValidationResult(false, validationMessage);
	}

	/**
	 * Constructor of passed validation
	 * @return passed validation object
	 */
	public static StudentFieldValidationResult valid() {
		return new StudentFieldValidationResult(true, null);
	}

	/**
	 * Getter for validation result
	 * @return boolean value of validation
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * Validation message
	 * @return message which will be shown to user
	 */
	public String getValidationMessage() {
		return validationMessage;
	}

	@Override
	public String toString() {
		return "StudentSingleFieldValidationResult{" +
				"isValid=" + isValid +
				", validationMessage='" + validationMessage + '\'' +
				'}';
	}
}

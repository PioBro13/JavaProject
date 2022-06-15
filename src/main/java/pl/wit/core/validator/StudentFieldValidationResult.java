package pl.wit.core.validator;

/**
 * @author pawel.wesolowski
 */
public class StudentFieldValidationResult {

	private final boolean isValid;

	private final String validationMessage;

	private StudentFieldValidationResult(boolean isValid, String validationMessage) {
		this.isValid = isValid;
		this.validationMessage = validationMessage;
	}

	public static StudentFieldValidationResult invalid(String validationMessage) {
		return new StudentFieldValidationResult(false, validationMessage);
	}

	public static StudentFieldValidationResult valid() {
		return new StudentFieldValidationResult(true, null);
	}

	public boolean isValid() {
		return isValid;
	}

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

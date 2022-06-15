package pl.wit.core.validator;

/**
 * @author pawel.wesolowski
 */
public class StudentValidatorImpl implements StudentValidator {

	@Override
	public StudentFieldValidationResult validateAlbum(int album) {
		if (album <= 0) {
			return StudentFieldValidationResult.invalid("Numer albumu musi być większy od 0.");
		}
		return StudentFieldValidationResult.valid();
	}

	@Override
	public StudentFieldValidationResult validateName(String name) {
		if (name == null || name.isBlank()) {
			return StudentFieldValidationResult.invalid("Imię i nazwisko nie może być puste.");
		}
		return name.trim().contains(" ") ? StudentFieldValidationResult.valid() :
				StudentFieldValidationResult.invalid("Imię i naziwsko musi składać się z conajmniej dwóch członów.");
	}

	@Override
	public StudentFieldValidationResult validateGroup(String group) {
		return group != null && !group.isBlank() ? StudentFieldValidationResult.valid() :
				StudentFieldValidationResult.invalid("Identyfikator grupy nie może być pusty.");
	}

	@Override
	public StudentFieldValidationResult validateActivityPoints(int activityPoints) {
		return activityPoints >= 0 && activityPoints <= 5 ? StudentFieldValidationResult.valid()
				: StudentFieldValidationResult.invalid("Liczba punktów za aktywność musi mieścić się w przedziale <0, 5>.");
	}

	@Override
	public StudentFieldValidationResult validateHomeworkPoints(int homeworkPoints) {
		return homeworkPoints >= 0 && homeworkPoints <= 5 ? StudentFieldValidationResult.valid()
				: StudentFieldValidationResult.invalid("Liczba punktów z pracy domowej musi mieścić się w przedziale <0, 5>.");
	}

	@Override
	public StudentFieldValidationResult validateProjectPoints(int projectPoints) {
		return projectPoints >= 0 && projectPoints <= 10 ? StudentFieldValidationResult.valid()
				: StudentFieldValidationResult.invalid("Liczba punktów z projektu dodatkowego musi mieścić się w przedziale <0, 10>.");
	}

	@Override
	public StudentFieldValidationResult validateFirstTestPoints(int firstTestPoints) {
		return firstTestPoints >= 0 && firstTestPoints <= 20 ? StudentFieldValidationResult.valid()
				: StudentFieldValidationResult.invalid("Liczba punktów z pierwszego kolokwium musi mieścić się w przedziale <0, 20>.");
	}

	@Override
	public StudentFieldValidationResult validateSecondTestPoints(int secondTestPoints) {
		return secondTestPoints >= 0 && secondTestPoints <= 20 ? StudentFieldValidationResult.valid()
				: StudentFieldValidationResult.invalid("Liczba punktów z drugiego kolokwium musi mieścić się w przedziale <0, 20>.");
	}

	@Override
	public StudentFieldValidationResult validateExamPoints(int examPoints) {
		return examPoints >= 0 && examPoints <= 40 ? StudentFieldValidationResult.valid()
				: StudentFieldValidationResult.invalid("Liczba punktów z egzaminu musi mieścić się w przedziale <0, 40>.");
	}
}

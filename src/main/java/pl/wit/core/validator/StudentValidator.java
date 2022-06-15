package pl.wit.core.validator;

/**
 * @author pawel.wesolowski
 */
public interface StudentValidator {

	StudentFieldValidationResult validateAlbum(int album);

	StudentFieldValidationResult validateName(String name);

	StudentFieldValidationResult validateGroup(String group);

	StudentFieldValidationResult validateActivityPoints(int activityPoints);

	StudentFieldValidationResult validateHomeworkPoints(int homeworkPoints);

	StudentFieldValidationResult validateProjectPoints(int projectPoints);

	StudentFieldValidationResult validateFirstTestPoints(int firstTestPoints);

	StudentFieldValidationResult validateSecondTestPoints(int secondTestPoints);

	StudentFieldValidationResult validateExamPoints(int examPoints);
}

package pl.wit.core.validator;

/**
 * Interface for validate each cell
 * @author pawel.wesolowski
 */
public interface StudentValidator {

	/**
	 * Student's id validation
	 * @param album student's id
	 * @return validation result
	 */
	StudentFieldValidationResult validateAlbum(int album);

	/**
	 * Student's first and last name validation
	 * @param name student's first and last name
	 * @return validation result
	 */
	StudentFieldValidationResult validateName(String name);

	/**
	 * Student's group id
	 * @param group group id
	 * @return validation result
	 */
	StudentFieldValidationResult validateGroup(String group);

	/**
	 * Student's activity points
	 * @param activityPoints number of points
	 * @return validation result
	 */
	StudentFieldValidationResult validateActivityPoints(int activityPoints);

	/**
	 * Student's homework points
	 * @param homeworkPoints number of points
	 * @return validation result
	 */
	StudentFieldValidationResult validateHomeworkPoints(int homeworkPoints);

	/**
	 * Student's project points
	 * @param projectPoints number of points
	 * @return validation result
	 */
	StudentFieldValidationResult validateProjectPoints(int projectPoints);

	/**
	 * Student's homework points
	 * @param firstTestPoints number of points
	 * @return validation result
	 */
	StudentFieldValidationResult validateFirstTestPoints(int firstTestPoints);

	/**
	 * Student's homework points
	 * @param secondTestPoints number of points
	 * @return validation result
	 */
	StudentFieldValidationResult validateSecondTestPoints(int secondTestPoints);

	/**
	 * Total student's points
	 * @param examPoints number of points
	 * @return validation result
	 */
	StudentFieldValidationResult validateExamPoints(int examPoints);
}

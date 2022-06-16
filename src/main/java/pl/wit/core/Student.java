package pl.wit.core;

import java.util.Objects;

/**
 * Class that represents each student
 * @author pawel.wesolowski
 */
public class Student {
	/**
	 * Student's id
	 */
	private Integer album;

	/**
	 * Student's first and last name
	 */
	private String name;

	/**
	 * Student's group id
	 */
	private String group;

	/**
	 * Student's homework points
	 */
	private int homeworkPoints;

	/**
	 * Student's activity points
	 */
	private int activityPoints;

	/**
	 * Student's project points
	 */
	private int projectPoints;

	/**
	 * Student's first test points
	 */
	private int firstTestPoints;

	/**
	 * Student's second test points
	 */
	private int secondTestsPoints;

	/**
	 * Student's exam points
	 */
	private int examPoints;

	/**
	 * empty constructor
	 */
	public Student() {
	}

	/**
	 * Constructor with initialization of each field
	 * @param album if
	 * @param name first and last name
	 * @param group group id
	 * @param homeworkPoints homework points
	 * @param activityPoints activity points
	 * @param projectPoints  project points
	 * @param firstTestPoints first test points
	 * @param secondTestsPoints second test points
	 * @param examPoints exam points
	 */
	public Student(Integer album,
			String name,
			String group,
			int homeworkPoints,
			int activityPoints,
			int projectPoints,
			int firstTestPoints,
			int secondTestsPoints,
			int examPoints) {
		this.album = album;
		this.name = name;
		this.group = group;
		this.homeworkPoints = homeworkPoints;
		this.activityPoints = activityPoints;
		this.projectPoints = projectPoints;
		this.firstTestPoints = firstTestPoints;
		this.secondTestsPoints = secondTestsPoints;
		this.examPoints = examPoints;
	}

	/**
	 * Student's id getter
	 * @return int value of student's id
	 */
	public Integer getAlbum() {
		return album;
	}

	/**
	 * Student's first and last name getter
	 * @return first and last name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Student's group id getter
	 * @return group id
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Student's homework points getter
	 * @return homework points
	 */
	public int getHomeworkPoints() {
		return homeworkPoints;
	}

	/**
	 * Student's activity points getter
	 * @return activity points
	 */
	public int getActivityPoints() {
		return activityPoints;
	}

	/**
	 * Student's project points getter
	 * @return project points
	 */
	public int getProjectPoints() {
		return projectPoints;
	}

	/**
	 * Student's first test points getter
	 * @return first test points
	 */
	public int getFirstTestPoints() {
		return firstTestPoints;
	}

	/**
	 * Student's second test points getter
	 * @return second test points
	 */
	public int getSecondTestsPoints() {
		return secondTestsPoints;
	}

	/**
	 * Student's exam points getter
	 * @return exam points
	 */
	public int getExamPoints() {
		return examPoints;
	}

	/**
	 * Update of object's values
	 * @param student Student object with new values
	 */
	public void update(Student student) {
		this.album = student.album;
		this.name = student.name;
		this.group = student.group;
		this.homeworkPoints = student.homeworkPoints;
		this.activityPoints = student.activityPoints;
		this.projectPoints = student.projectPoints;
		this.firstTestPoints = student.firstTestPoints;
		this.secondTestsPoints = student.secondTestsPoints;
		this.examPoints = student.examPoints;
	}

	/**
	 * Calculating total value of points
	 * @return student's total points
	 */
	public int getSum() {
		return homeworkPoints + activityPoints + projectPoints + firstTestPoints + secondTestsPoints + examPoints;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Student student = (Student) o;
		return homeworkPoints == student.homeworkPoints && activityPoints == student.activityPoints && projectPoints == student.projectPoints
				&& firstTestPoints == student.firstTestPoints && secondTestsPoints == student.secondTestsPoints && examPoints == student.examPoints
				&& Objects.equals(album, student.album) && Objects.equals(name, student.name) && Objects.equals(group, student.group);
	}

	@Override
	public int hashCode() {
		return Objects.hash(album);
	}
}

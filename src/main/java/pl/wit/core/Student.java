package pl.wit.core;

/**
 * @author pawel.wesolowski
 */
public class Student {

	private String album;

	private String name;

	private String group;

	private int homeworkPoints;

	private int activityPoints;

	private int projectPoints;

	private int firstTestPoints;

	private int secondTestsPoints;

	private int examPoints;

	public Student() {
	}

	public Student(String album,
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

	public String getAlbum() {
		return album;
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

	public int getHomeworkPoints() {
		return homeworkPoints;
	}

	public int getActivityPoints() {
		return activityPoints;
	}

	public int getProjectPoints() {
		return projectPoints;
	}

	public int getFirstTestPoints() {
		return firstTestPoints;
	}

	public int getSecondTestsPoints() {
		return secondTestsPoints;
	}

	public int getExamPoints() {
		return examPoints;
	}

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

	public int getSum() {
		return homeworkPoints + activityPoints + projectPoints + firstTestPoints + secondTestsPoints + examPoints;
	}
}

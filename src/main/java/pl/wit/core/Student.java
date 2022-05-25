package pl.wit.core;

/**
 * @author pawel.wesolowski
 */
public class Student {

	private final String album;

	private final String name;

	private final String group;

	private final byte homeworkPoints;

	private final byte activityPoints;

	private final byte projectPoints;

	private final byte firstTestPoints;

	private final byte secondTestsPoints;

	private final byte examPoints;

	public Student(String album,
			String name,
			String group,
			byte homeworkPoints,
			byte activityPoints,
			byte projectPoints,
			byte firstTestPoints,
			byte secondTestsPoints,
			byte examPoints) {
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

	public byte getHomeworkPoints() {
		return homeworkPoints;
	}

	public byte getActivityPoints() {
		return activityPoints;
	}

	public byte getProjectPoints() {
		return projectPoints;
	}

	public byte getFirstTestPoints() {
		return firstTestPoints;
	}

	public byte getSecondTestsPoints() {
		return secondTestsPoints;
	}

	public byte getExamPoints() {
		return examPoints;
	}
}

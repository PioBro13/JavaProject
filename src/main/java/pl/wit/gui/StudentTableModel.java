package pl.wit.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pl.wit.core.Student;
import pl.wit.core.StudentService;
import pl.wit.core.validator.StudentFieldValidationResult;
import pl.wit.core.validator.StudentValidator;

/**
 * @author pawel.wesolowski
 */
class StudentTableModel extends DefaultTableModel {

	private static final int ALBUM_INDEX = 0;

	private static final int NAME_INDEX = 1;

	private static final int GROUP_INDEX = 2;

	private static final int HOMEWORK_INDEX = 3;

	private static final int ACTIVITY_INDEX = 4;

	private static final int PROJECT_INDEX = 5;

	private static final int FIRST_TEST_INDEX = 6;

	private static final int SECOND_TEST_INDEX = 7;

	private static final int EXAM_INDEX = 8;

	private static final int SUM_INDEX = 9;

	private final StudentService studentService;

	private static final Map<Integer, BiFunction<StudentValidator, Object, StudentFieldValidationResult>> VALIDATORS = new HashMap<>();

	static {
		VALIDATORS.put(ALBUM_INDEX, (studentValidator, album) -> studentValidator.validateAlbum((Integer) album));
		VALIDATORS.put(NAME_INDEX, (studentValidator, name) -> studentValidator.validateName((String) name));
		VALIDATORS.put(GROUP_INDEX, (studentValidator, group) -> studentValidator.validateGroup((String) group));
		VALIDATORS.put(HOMEWORK_INDEX, (studentValidator, homeworkPoints) -> studentValidator.validateHomeworkPoints((Integer) homeworkPoints));
		VALIDATORS.put(ACTIVITY_INDEX, (studentValidator, activityPoints) -> studentValidator.validateActivityPoints((Integer) activityPoints));
		VALIDATORS.put(PROJECT_INDEX, (studentValidator, projectPoints) -> studentValidator.validateProjectPoints((Integer) projectPoints));
		VALIDATORS.put(FIRST_TEST_INDEX, (studentValidator, firstTestPoints) -> studentValidator.validateFirstTestPoints((Integer) firstTestPoints));
		VALIDATORS.put(SECOND_TEST_INDEX, (studentValidator, secondTestPoints) -> studentValidator.validateSecondTestPoints((Integer) secondTestPoints));
		VALIDATORS.put(EXAM_INDEX, (studentValidator, examPoints) -> studentValidator.validateExamPoints((Integer) examPoints));
	}

	StudentTableModel(StudentService studentService) {
		super(getStudentsArray(studentService), getColumnNames());
		this.studentService = studentService;
	}

	private static String[] getColumnNames() {
		return new String[]{
				"Nr albumu", "Osoba", "Grupa", "Praca domowa", "Aktywność", "Projekt", "Kolokwium 1", "Kolokwium 2", "Egzamin", "Suma"
		};
	}

	private static Object[][] getStudentsArray(StudentService studentRepository) {
		List<Student> allStudents = studentRepository.getAllStudents();
		return allStudents.stream()
				.map(student -> new Object[]{
						student.getAlbum(),
						student.getName(),
						student.getGroup(),
						student.getHomeworkPoints(),
						student.getActivityPoints(),
						student.getProjectPoints(),
						student.getFirstTestPoints(),
						student.getSecondTestsPoints(),
						student.getExamPoints(),
						student.getSum()}
				).toArray(value -> new Object[allStudents.size()][]);
	}

	private final Class<?>[] types = new Class[]{
			Integer.class,
			String.class,
			String.class,
			Integer.class,
			Integer.class,
			Integer.class,
			Integer.class,
			Integer.class,
			Integer.class,
			Integer.class
	};

	private final boolean[] canEdit = new boolean[]{
			true, true, true, true, true, true, true, true, true, false
	};

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return types[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		validateRequestedFieldOnly(value, column).ifPresentOrElse(
				validationError -> JOptionPane.showMessageDialog(null, validationError, "", JOptionPane.ERROR_MESSAGE),
				() -> {
					super.setValueAt(value, row, column);
					super.setValueAt(calculateSumFromRow(row), row, SUM_INDEX);
					if (allColumnsFilled(row)) {
						Student studentFromRow = createStudentFromRow(row);
						Integer album = (Integer) getValueAt(row, ALBUM_INDEX);
						studentService.findStudentByAlbum(album).ifPresentOrElse(
								student -> {
									JOptionPane.showMessageDialog(null, "Dane studenta zostały zaktualizowane", "", JOptionPane.INFORMATION_MESSAGE);
									student.update(studentFromRow);
								},
								() -> {
									JOptionPane.showMessageDialog(null, "Student został dodany.", "", JOptionPane.INFORMATION_MESSAGE);
									studentService.addStudent(studentFromRow);

								}
						);
					}
				});

	}

	private Optional<String> validateRequestedFieldOnly(Object value, int column) {
		StudentFieldValidationResult result = VALIDATORS.get(column).apply(studentService.getStudentValidator(), value);
		return result.isValid() ? Optional.empty() : Optional.of(result.getValidationMessage());
	}

	private Integer calculateSumFromRow(int row) {
		return (Integer) getValueAt(row, HOMEWORK_INDEX) +
				(Integer) getValueAt(row, ACTIVITY_INDEX) +
				(Integer) getValueAt(row, PROJECT_INDEX) +
				(Integer) getValueAt(row, FIRST_TEST_INDEX) +
				(Integer) getValueAt(row, SECOND_TEST_INDEX) +
				(Integer) getValueAt(row, EXAM_INDEX);
	}

	private Student createStudentFromRow(int row) {
		return new Student(
				(Integer) getValueAt(row, ALBUM_INDEX),
				(String) getValueAt(row, NAME_INDEX),
				(String) getValueAt(row, GROUP_INDEX),
				(Integer) getValueAt(row, HOMEWORK_INDEX),
				(Integer) getValueAt(row, ACTIVITY_INDEX),
				(Integer) getValueAt(row, PROJECT_INDEX),
				(Integer) getValueAt(row, FIRST_TEST_INDEX),
				(Integer) getValueAt(row, SECOND_TEST_INDEX),
				(Integer) getValueAt(row, EXAM_INDEX)
		);
	}

	private boolean allColumnsFilled(int row) {
		return getValueAt(row, ALBUM_INDEX) != null &&
				getValueAt(row, NAME_INDEX) != null &&
				getValueAt(row, GROUP_INDEX) != null &&
				getValueAt(row, HOMEWORK_INDEX) != null &&
				getValueAt(row, ACTIVITY_INDEX) != null &&
				getValueAt(row, PROJECT_INDEX) != null &&
				getValueAt(row, FIRST_TEST_INDEX) != null &&
				getValueAt(row, SECOND_TEST_INDEX) != null &&
				getValueAt(row, EXAM_INDEX) != null;
	}
}

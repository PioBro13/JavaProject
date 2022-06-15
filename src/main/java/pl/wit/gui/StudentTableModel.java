package pl.wit.gui;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import pl.wit.core.Student;
import pl.wit.core.StudentRepository;

class StudentTableModel extends DefaultTableModel {

	StudentTableModel(StudentRepository studentRepository) {
		super(getStudentsArray(studentRepository), getColumnNames());
	}

	private static String[] getColumnNames() {
		return new String[]{
				"Nr albumu", "Osoba", "Grupa", "Praca domowa", "Aktywność", "Projekt", "Kolokwium 1", "Kolokwium 2", "Egzamin", "Suma"
		};
	}

	private static Object[][] getStudentsArray(StudentRepository studentRepository) {
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pl.wit.gui;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wit.core.StudentRepository;
import pl.wit.core.StudentService;
import static java.awt.EventQueue.invokeLater;

/**
 * @author Piotr Bródka
 */
public class MainForm extends JFrame {

	private final Logger log = LoggerFactory.getLogger(MainForm.class);

	private final DefaultTableModel dataModel;

	private final JTable studentsTable;

	private final StudentRepository studentRepository = new StudentRepository();

	private final StudentService studentService = new StudentService(studentRepository);

	public MainForm() {
		this.studentsTable = new JTable();
		this.dataModel = (DefaultTableModel) studentsTable.getModel();
		initComponents();

//		//Listener do dynamicznego wyliczania sumy punktów
//		studentsTable.getModel().addTableModelListener(tme -> {
//			if (tme.getType() == TableModelEvent.UPDATE) {
//				if (tme.getColumn() != 9) {
//					int currentRow = studentsTable.getSelectedRow();
//					//					if (!validator(currentRow, tme.getColumn())) {
//					//						JOptionPane.showMessageDialog(null, validationFailMessage(tme.getColumn()), "Błąd walidacji", JOptionPane
//					//						.ERROR_MESSAGE);
//					//					}
//					dataModel.setValueAt(pointsSum(currentRow), studentsTable.getSelectedRow(), 9);
//				}
//			}
//		});

	}

	public static void main(String[] args) {
		invokeLater(MainForm::new);
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Dane studentów");
		setVisible(true);
		studentsTable.setModel(getDataModel());
		JScrollPane tablePane = new JScrollPane();
		tablePane.setViewportView(studentsTable);
		JButton saveButton = configureSaveButton();
		JButton uploadFile = configureUploadFileButton();
		JButton addRow = configureAddRowButton();
		JButton removeRow = configureRemoveRowButton();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 919, GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(addRow)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(removeRow))
										.addGroup(layout.createSequentialGroup()
												.addComponent(saveButton)
												.addGap(18, 18, 18)
												.addComponent(uploadFile)))
								.addContainerGap(30, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(34, 34, 34)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(uploadFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(saveButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(31, 31, 31)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(addRow)
										.addComponent(removeRow))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(30, Short.MAX_VALUE))
		);

		pack();
	}

	private StudentTableModel getDataModel() {
		return new StudentTableModel(studentRepository);
	}

	private JButton configureSaveButton() {
		JButton saveButton = new JButton();
		saveButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/save.png"))));
		saveButton.addActionListener(this::saveButtonActionPerformed);
		return saveButton;
	}

	private JButton configureUploadFileButton() {
		JButton uploadFile = new JButton();
		uploadFile.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/upload.png"))));
		uploadFile.addActionListener(this::uploadFileActionPerformed);
		return uploadFile;
	}

	private JButton configureRemoveRowButton() {
		JButton removeRow = new JButton();
		removeRow.setText("-");
		removeRow.addActionListener(this::removeRowActionPerformed);
		return removeRow;
	}

	private JButton configureAddRowButton() {
		JButton addRow = new JButton();
		addRow.setText("+");
		addRow.addActionListener(this::addRowActionPerformed);
		return addRow;
	}

	private void addRowActionPerformed(ActionEvent evt) {
		dataModel.addRow(new Object[]{null, null, null, null, null, null, null, null, null, null});
	}

	private void saveButtonActionPerformed(ActionEvent evt) {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
				log.debug("Selected file: " + absolutePath);
				studentService.save(absolutePath);
			} else {
				log.debug("No file selected.");
			}
		} catch (IOException e) {
			showError(e);
		}
	}

	private void showError(Exception exception) {
		JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void removeRowActionPerformed(ActionEvent evt) {
		String album = (String) studentsTable.getValueAt(studentsTable.getSelectedRow(), 0);
		studentRepository.removeStudentByAlbum(album);
		dataModel.setRowCount(0);
		studentsTable.setModel(getDataModel());
	}

	private void uploadFileActionPerformed(ActionEvent evt) {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
				log.debug("Selected file: " + absolutePath);
				studentService.load(absolutePath);
				studentsTable.setModel(getDataModel());
			} else {
				log.debug("No file selected.");
			}
		} catch (IOException e) {
			showError(e);
		}
	}

	//zliczanie sumy punktów w edytowanym wierszu
	private int pointsSum(int currentRow) {
		int sum = 0;
		for (int i = 3; i < 9; i++) {
			Integer value = (Integer) this.studentsTable.getValueAt(currentRow, i);
			if (value != null) {
				sum += value;
			}
		}
		return sum;
	}

	//wyczyszczenie wartości w polu po wpisaniu nieprawidłowej wartości
	private void validationFail(int currentRow, int currentCell) {
		dataModel.setValueAt(null, currentRow, currentCell);
	}

	//sprawdzenie czy którekolwiek z pól jest puste
	private boolean isAnyNull(int currentRow) {
		boolean flag = true;
		for (int i = 0; i < 9; i++) {
			if (this.studentsTable.getValueAt(currentRow, i) == null) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}

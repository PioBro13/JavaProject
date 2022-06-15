/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pl.wit.gui;

import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import pl.wit.core.StudentRepository;
import static java.awt.EventQueue.invokeLater;

/**
 * @author Piotr Bródka
 */
public class MainForm extends JFrame {

	private final DefaultTableModel dataModel;

	private JTable studentsTable;

	private final StudentRepository studentRepository = new StudentRepository();

	/**
	 * Creates new form MainForm
	 */
	public MainForm() {
		initComponents();
		this.dataModel = (DefaultTableModel) studentsTable.getModel();

		//Listener do dynamicznego wyliczania sumy punktów
		studentsTable.getModel().addTableModelListener(tme -> {
			if (tme.getType() == TableModelEvent.UPDATE) {
				if (tme.getColumn() != 9) {
					int currentRow = studentsTable.getSelectedRow();
					if (!validator(currentRow, tme.getColumn())) {
						JOptionPane.showMessageDialog(null, validationFailMessage(tme.getColumn()), "Błąd walidacji", JOptionPane.ERROR_MESSAGE);
					}
					dataModel.setValueAt(pointsSum(currentRow), studentsTable.getSelectedRow(), 9);
				}
			}
		});

	}

	private void initComponents() {

		JButton saveButton = new JButton();
		JButton uploadFile = new JButton();
		JScrollPane tablePane = new JScrollPane();
		studentsTable = new JTable();
		JButton addRow = new JButton();
		JButton removeRow = new JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Dane studentów");

		saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
		saveButton.addActionListener(this::saveButtonActionPerformed);

		uploadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/upload.png"))); // NOI18N
		uploadFile.addActionListener(this::uploadFileActionPerformed);

		studentsTable.setModel(new StudentTableModel(studentRepository));
		tablePane.setViewportView(studentsTable);

		addRow.setText("+");
		addRow.addActionListener(this::addRowActionPerformed);

		removeRow.setText("-");
		removeRow.addActionListener(this::removeRowActionPerformed);

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

	//dodanie wiersza w tabeli
	private void addRowActionPerformed(ActionEvent evt) {
		if (this.studentsTable.getRowCount() == 0 || isAnyNull(this.studentsTable.getRowCount() - 1)) {
			dataModel.addRow(new Object[]{null, null, null, null, null, null, null, null, null, null});
		} else {
			JOptionPane.showMessageDialog(null, validationFailMessage(10), "Błąd walidacji", JOptionPane.ERROR_MESSAGE);
		}
	}

	//zapisanie danych z tabeli
	private void saveButtonActionPerformed(ActionEvent evt) {
		// TODO zapisywanie danych z tabeli to pliku
	}

	//usunięcie wiersza z tabeli
	private void removeRowActionPerformed(ActionEvent evt) {
		try {
			dataModel.removeRow(studentsTable.getSelectedRow());
		} catch (Exception e) {
		}
	}

	//upload danych z pliku
	private void uploadFileActionPerformed(ActionEvent evt) {
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

	//metoda zwracająca wartość komunikatu błędu
	private String validationFailMessage(int currentCell) {
		String text;
		switch (currentCell) {
			case (0):
				text = "Proszę wpisać same cyfry.";
				break;
			case (1):
				text = "Wartość w polu musi się składać z co najmniej dwóch wyrazów.";
				break;
			case (2):
				text = "Proszę podać numer grupy.";
				break;
			case (3):
				text = "Proszę podać wartość od 0 do 5.";
				break;
			case (4):
				text = "Proszę podać wartość od 0 do 5.";
				break;
			case (5):
				text = "Proszę podać wartość od 0 do 10.";
				break;
			case (6):
				text = "Proszę podać wartość od 0 do 20.";
				break;
			case (7):
				text = "Proszę podać wartość od 0 do 20.";
				break;
			case (8):
				text = "Proszę podać wartość od 0 do 40.";
				break;
			case (10):
				text = "Co najmniej jedno pole jest niewypełnione.\nWypełnij wszystkie pola przed dodaniem następnej pozycji.";
				break;
			case (11):
				text = "Wypełnij wszystkie pola przed zapisem danych do pliku.";
				break;
			default:
				text = "Błąd walidacji";
				break;
		}
		return text;
	}

	//metoda walidująca pola
	private boolean validator(int currentRow, int currentCell) {
		Object value = this.studentsTable.getValueAt(currentRow, currentCell);
		if (value == null) {
			return true;
		}
		switch (currentCell) {
			case (1):
				String[] words = String.valueOf(value).split("\\s+");
				if (words.length < 2) {
					validationFail(currentRow, currentCell);
					return false;
				}
				return true;
			case (3):
				if ((Integer) value >= 0 && (Integer) value <= 5) {
					return true;
				} else {
					validationFail(currentRow, currentCell);
					return false;
				}
			case (4):
				if ((Integer) value >= 0 && (Integer) value <= 5) {
					return true;
				} else {
					validationFail(currentRow, currentCell);
					return false;
				}
			case (5):
				if ((Integer) value >= 0 && (Integer) value <= 10) {
					return true;
				} else {
					validationFail(currentRow, currentCell);
					return false;
				}
			case (6):
				if ((Integer) value >= 0 && (Integer) value <= 20) {
					return true;
				} else {
					validationFail(currentRow, currentCell);
					return false;
				}
			case (7):
				if ((Integer) value >= 0 && (Integer) value <= 20) {
					return true;
				} else {
					validationFail(currentRow, currentCell);
					return false;
				}
			case (8):
				if ((Integer) value >= 0 && (Integer) value <= 40) {
					return true;
				} else {
					validationFail(currentRow, currentCell);
					return false;
				}
			default:
				return true;
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		//		/* Set the Nimbus look and feel */
		//		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		//		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		//		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		//		 */
		//		try {
		//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		//				if ("Nimbus".equals(info.getName())) {
		//					UIManager.setLookAndFeel(info.getClassName());
		//					break;
		//				}
		//		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
		//			Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
		//		}
		//		//</editor-fold>

		/* Create and display the form */
		invokeLater(() -> new MainForm().setVisible(true));
	}

}

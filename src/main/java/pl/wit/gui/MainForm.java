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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import pl.wit.core.StudentService;
import static java.awt.EventQueue.invokeLater;

/**
 * @author Piotr Bródka
 */
public class MainForm extends JFrame {

	private final JTable studentsTable;

	private final StudentService studentService = new StudentService();

	public MainForm() {
		this.studentsTable = new JTable();
		initComponents();
	}

	public static void main(String[] args) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
			System.err.println(ex.getMessage());
		}
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
		return new StudentTableModel(studentService);
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
		((DefaultTableModel) studentsTable.getModel()).addRow(new Object[]{null, null, null, 0, 0, 0, 0, 0, 0, 0});
	}

	private void saveButtonActionPerformed(ActionEvent evt) {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
				studentService.save(absolutePath);
			}
		} catch (IOException e) {
			showError(e);
		}
	}

	private void removeRowActionPerformed(ActionEvent evt) {
		int selectedRow = studentsTable.getSelectedRow();
		if (selectedRow != -1) {
			Integer album = (Integer) studentsTable.getValueAt(selectedRow, 0);
			if (album != null) {
				studentService.removeStudentByAlbum(album);
			}
			((DefaultTableModel) studentsTable.getModel()).setRowCount(0);
			studentsTable.setModel(getDataModel());
		}
	}

	private void uploadFileActionPerformed(ActionEvent evt) {
		try {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
				studentService.load(absolutePath);
				studentsTable.setModel(getDataModel());
			}
		} catch (IOException e) {
			showError(e);
		}
	}

	private void showError(Exception exception) {
		JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

}

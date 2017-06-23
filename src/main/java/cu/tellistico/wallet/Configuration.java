package cu.tellistico.wallet;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Roberto on 14/06/17.
 */
public class Configuration {
    private JTextField userField;
    private JPasswordField passField;
    private JTextField fileField;
    private JButton openButton;
    private JButton startButton;
    private JPanel panel;

    public Configuration() {
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFileChooser = new JFileChooser(System.getProperty("user.dir"));
                openFileChooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xls", "xlsx"));

                if (openFileChooser.showOpenDialog(openButton.getRootPane()) == JFileChooser.APPROVE_OPTION) {
                    File file = openFileChooser.getSelectedFile();
                    fileField.setText(file.getAbsolutePath());
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("starting Selenium web driver");

                    FileInputStream excelFile = new FileInputStream(new File(fileField.getText()));
                    Workbook workbook = new XSSFWorkbook(excelFile);

                    AppController controller = new AppController();
                    controller.login(userField.getText(), passField.getText());

                    Sheet datatypeSheet = workbook.getSheetAt(0);
                    for (int i = 1; i < datatypeSheet.getPhysicalNumberOfRows(); i++) {
                        double amount = datatypeSheet.getRow(i).getCell(0).getNumericCellValue();
                        String category = datatypeSheet.getRow(i).getCell(1).getStringCellValue();
                        String subcategory = datatypeSheet.getRow(i).getCell(2).getStringCellValue();
                        String account = datatypeSheet.getRow(i).getCell(3).getStringCellValue();
                        String date = datatypeSheet.getRow(i).getCell(4).getStringCellValue();
                        int hours = (int) datatypeSheet.getRow(i).getCell(5).getNumericCellValue();
                        int minutes = (int) datatypeSheet.getRow(i).getCell(6).getNumericCellValue();
                        String paymentType = datatypeSheet.getRow(i).getCell(7).getStringCellValue();
                        int type = (int) datatypeSheet.getRow(i).getCell(8).getNumericCellValue();
                        String notes = datatypeSheet.getRow(i).getCell(9).getStringCellValue();

                        Record record = new Record(amount, category, subcategory, account, date, hours, minutes, paymentType, type, notes);
                        System.out.println(record.toString());
                        controller.addRecord(record);
                    }


                } catch (IOException | InterruptedException e1) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please check your data!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getUser() {
        return userField.getText();
    }

    public String getPass() {
        return String.valueOf(passField.getPassword());
    }

    public JTextField getFileField() {
        return fileField;
    }
}

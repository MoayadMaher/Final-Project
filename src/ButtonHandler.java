import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonHandler implements ActionListener {
    private final FirstFrame firstFrame;
    private final SecondFrame secondFrame;

    public ButtonHandler(FirstFrame firstFrame, SecondFrame secondFrame) {
        this.firstFrame = firstFrame;
        this.secondFrame = secondFrame;
    }

    public void actionPerformed(ActionEvent event) {

        if (secondFrame != null) {

            // Get the selected instructor name from the combo box
            // and construct the path to the instructor's folder
            String selectedInstructor = firstFrame.getInstructorSelectComboBox().getSelectedItem().toString();
            String path = "C:\\Users\\mmj_p\\Documents\\JavaFinalProject\\" + selectedInstructor;

            // Add Student Button handler
            if (event.getSource() == secondFrame.getAddStudentButton()) {

                // Get the student name and grade from the text fields
                String studentName = secondFrame.getStudentNameTextField().getText();
                String studentGrade = secondFrame.getStudentGradeTextField().getText();

                // Get the selected subject name from the JList
                String selectedSubject = secondFrame.getSubjectList().getSelectedValue().toString();

                // Construct the file path within the selected instructor's folder for the student's subject
                String filePath = path + "\\" + selectedSubject + ".txt";
                File file = new File(filePath);

                // if file doesn't exist, create it'
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                try {
                    FileWriter writer = new FileWriter(file, true);
                    writer.write(studentName + "\n");
                    writer.write(studentGrade + "\n");
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }



            // Show Students Button handler
            if (event.getSource() == secondFrame.getShowStudentsButton()) {
                File file = new File(path + "\\" + secondFrame.getSubjectList().toString() + ".txt");
                try {
                    Scanner scanner = new Scanner(file);
                    String output = "";
                    while (scanner.hasNextLine()) {
                        output += scanner.nextLine() + "\n";
                    }
                    secondFrame.getOutputTextArea().setText(output);
                    scanner.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            // AvgMinMax Button handler



            // Back Button handler
            if (event.getSource() == secondFrame.getBackButton()) {
                FirstFrame frame1 = new FirstFrame();
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setSize(500, 500);
                frame1.setVisible(true);
                secondFrame.setVisible(false);
            }
        }
    }
}
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
    private FirstFrame firstFrame;
    private SecondFrame secondFrame;

    public ButtonHandler(FirstFrame firstFrame, SecondFrame secondFrame) {
        this.firstFrame = firstFrame;
        this.secondFrame = secondFrame;
    }

    public void actionPerformed(ActionEvent event) {
        if (firstFrame != null) {
            if (event.getSource() == firstFrame.getExitButton()) {
                System.exit(0);
            } else if (event.getSource() == firstFrame.getNextPageButton()) {
                String selected = (String) firstFrame.getInstructorSelectComboBox().getSelectedItem();
                SecondFrame frame2 = new SecondFrame(selected, firstFrame);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setSize(500, 500);
                frame2.setVisible(true);
                firstFrame.setVisible(false);
            }
        }

        if (secondFrame != null) {

            // go to the instructor's folder
            String selectedInstructor = firstFrame.getInstructorSelectComboBox().getSelectedItem().toString();
            String path = "C:\\Users\\mmj_p\\Documents\\JavaFinalProject\\" + selectedInstructor;

            // set output text area to path
            secondFrame.getOutputTextArea().setText(path);

            /*
            if (event.getSource() == secondFrame.getAddStudentButton()) {

                // get the student name and grade
                String studentName = secondFrame.getStudentNameTextField().getText();
                String studentGrade = secondFrame.getStudentGradeTextField().getText();

                // create the file if it doesn't exist
                File file = new File(path + "\\" + secondFrame.getSubjectList().toString() + ".txt");

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
            */



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
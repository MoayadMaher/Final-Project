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

    // Constructor for FirstFrame
    public ButtonHandler(FirstFrame firstFrame) {
        this.firstFrame = firstFrame;
    }

    // Constructor for SecondFrame
    public ButtonHandler(SecondFrame secondFrame) {
        this.secondFrame = secondFrame;
    }

    public void actionPerformed(ActionEvent event) {
        // Handling events for FirstFrame
        if (firstFrame != null) {
            if (event.getSource() == firstFrame.getExitButton()) {
                System.exit(0);
            } else if (event.getSource() == firstFrame.getNextPageButton()) {
                // get combo box selected item
                String selected = (String) firstFrame.getInstructorSelectComboBox().getSelectedItem();
                SecondFrame frame2 = new SecondFrame(selected); // create SecondFrame
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setSize(500, 500); // set frame size
                frame2.setVisible(true); // display frame
                firstFrame.setVisible(false);
            }
        }

        // Handling events for SecondFrame
        if (secondFrame != null) {
            // change directory to the folder of selected instructor
            String selectedInstructor = firstFrame.getInstructorSelectComboBox().getSelectedItem().toString();
            String path = "C:\\Users\\mmj_p\\Documents\\JavaFinalProject\\" + selectedInstructor;

            if (event.getSource() == secondFrame.getAddStudentButton()){
                // get student name and grade
                String studentName = secondFrame.getStudentNameTextField().getText();
                String studentGrade = secondFrame.getStudentGradeTextField().getText();

                // if there is no file cerate a new one for the selected subject
                File file = new File(path + "\\" + secondFrame.getSubjectList().toString() + ".txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                // add student name and grade to the file
                try {
                    FileWriter writer = new FileWriter(file, true);
                    writer.write(studentName + "\n");
                    writer.write(studentGrade + "\n");
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

            if (event.getSource() == secondFrame.getBackButton()) {
                FirstFrame frame1 = new FirstFrame(); // create FirstFrame
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setSize(500, 500); // set frame size
                frame1.setVisible(true); // display frame
                secondFrame.setVisible(false);
            }
        }
    }
}
}

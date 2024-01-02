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

        if (firstFrame != null){

                // Next Page Button handler
                if (event.getSource() == firstFrame.getNextPageButton()) {

                    // Get the selected instructor name from the combo box
                    // and construct the path to the instructor's folder
                    String selectedInstructor = firstFrame.getInstructorSelectComboBox().getSelectedItem().toString();

                    // Create a new SecondFrame instance
                    SecondFrame frame2 = new SecondFrame(selectedInstructor, firstFrame);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame2.setSize(500, 500);
                    frame2.setVisible(true);
                    firstFrame.setVisible(false);
                }
                // Exit Button handler
                if (event.getSource() == firstFrame.getExitButton()) {
                    System.exit(0);
                }
        }

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
                secondFrame.getStudentNameTextField().setText("");
                secondFrame.getStudentGradeTextField().setText("");
            }

            // Show Students Button handler
            if (event.getSource() == secondFrame.getShowStudentsButton()) {
                String selectedSubject = secondFrame.getSubjectList().getSelectedValue().toString();
                String filePath = path + "\\" + selectedSubject + ".txt";
                File file = new File(filePath);
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
            if (event.getSource() == secondFrame.getAvgMinMaxButton()) {
                String selectedSubject = secondFrame.getSubjectList().getSelectedValue().toString();
                String filePath = path + "\\" + selectedSubject + ".txt";
                File file = new File(filePath);
                try {
                    Scanner scanner = new Scanner(file);
                    ArrayList<Integer> grades = new ArrayList<Integer>();
                    while (scanner.hasNextLine()) {
                        scanner.nextLine();
                        grades.add(Integer.parseInt(scanner.nextLine()));
                    }
                    int sum = 0;
                    int min = grades.get(0);
                    int max = grades.get(0);
                    for (int i = 0; i < grades.size(); i++) {
                        sum += grades.get(i);
                        if (grades.get(i) < min) {
                            min = grades.get(i);
                        }
                        if (grades.get(i) > max) {
                            max = grades.get(i);
                        }
                    }
                    double avg = (double) sum / grades.size();
                    String output = "Average: " + avg + "\nMin: " + min + "\nMax: " + max;
                    secondFrame.getOutputTextArea().setText(output);
                    scanner.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            if (event.getSource() == secondFrame.getSearchStudentButton()) {
                String selectedSubject = secondFrame.getSubjectList().getSelectedValue().toString();
                String filePath = path + "\\" + selectedSubject + ".txt";
                String studentName = secondFrame.getStudentNameTextField().getText();
                File file = new File(filePath);

                try {
                    Scanner scanner = new Scanner(file);
                    boolean found = false;

                    while (scanner.hasNextLine()) {
                        String name = scanner.nextLine();
                        String grade = scanner.nextLine();

                        if (name.trim().equals(studentName.trim())) {
                            secondFrame.getStudentGradeTextField().setText(grade);
                            found = true;
                            break; // Stop the loop after finding the student
                        }
                    }

                    if (!found) {
                        secondFrame.getStudentGradeTextField().setText("الطالب غير موجود");
                    }

                    scanner.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }


//            // SearchStudent Button handler
//            if(event.getSource() == secondFrame.getSearchStudentButton()){
//                String selectedSubject = secondFrame.getSubjectList().getSelectedValue().toString();
//                String filePath = path + "\\" + selectedSubject + ".txt";
//                String studentName = secondFrame.getStudentNameTextField().getText();
//                File file = new File(filePath);
//                try {
//                    Scanner scanner = new Scanner(file);
//                    while (scanner.hasNextLine()) {
//                        String name = scanner.nextLine();
//                        String grade = scanner.nextLine();
//                        if (name.equals(studentName)) {
//                            secondFrame.getStudentGradeTextField().setText(grade);
//                        }
//                    }
//                    scanner.close();
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }

            // Back Button handler
            if (event.getSource() == secondFrame.getBackButton()) {
                firstFrame.setVisible(true);
                secondFrame.setVisible(false);
            }
        }
    }
}
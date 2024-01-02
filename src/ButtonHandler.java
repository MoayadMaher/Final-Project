import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ButtonHandler implements ActionListener {
    private final FirstFrame firstFrame;
    private final SecondFrame secondFrame;

    private final String defaultStudentTextField = "إسم الطالب";
    private final String defaultGradeTextField = "علامة الطالب من 100";

    public ButtonHandler(FirstFrame firstFrame, SecondFrame secondFrame) {
        this.firstFrame = firstFrame;
        this.secondFrame = secondFrame;
    }

    public boolean isSubjectSelected() {
        Object selectedSubjectObj = secondFrame.getSubjectList().getSelectedValue();

        if (selectedSubjectObj == null) {
            secondFrame.getOutputTextArea().setText("لم يتم اختيار المادة");
            return false;
        }

        return true;
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

                // Check if the student name and grade are empty
                if (
                        studentName.equals("")
                        || studentGrade.equals("")
                        || studentName.equals(defaultStudentTextField)
                        || studentGrade.equals(defaultGradeTextField)
                ) {
                    secondFrame.getOutputTextArea().setText("الرجاء إدخال اسم الطالب والعلامة");
                    return;
                }

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
                Object selectedSubjectObj = secondFrame.getSubjectList().getSelectedValue();

                if (!isSubjectSelected()) return;

                String selectedSubject = selectedSubjectObj.toString();
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

                    System.out.println(grades);

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
                    String output = "متوسط العلامات: " + avg + "\nأقل علامة : " + min + "\nأعلى علامة : " + max;
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

                        System.out.println(name.trim());

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

            // Back Button handler
            if (event.getSource() == secondFrame.getBackButton()) {
                firstFrame.setVisible(true);
                secondFrame.setVisible(false);
            }
        }
    }

//    public void onAddStudentBtnClick(String path) {
//        // Get the student name and grade from the text fields
//        String studentName = secondFrame.getStudentNameTextField().getText();
//        String studentGrade = secondFrame.getStudentGradeTextField().getText();
//
//        // Check if the student name and grade are empty
//        if (
//                studentName.equals("")
//                || studentGrade.equals("")
//                || studentName.equals(defaultStudentTextField)
//                || studentGrade.equals(defaultGradeTextField)
//        ) {
//            secondFrame.getOutputTextArea().setText("الرجاء إدخال اسم الطالب والعلامة");
//            return;
//        }
//
//        // Get the selected subject name from the JList
//        String selectedSubject = secondFrame.getSubjectList().getSelectedValue().toString();
//
//        // Construct the file path within the selected instructor's folder for the student's subject
//        String filePath = path + "\\" + selectedSubject + ".txt";
//        File file = new File(filePath);
//
//        // if file doesn't exist, create it'
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//
//        try {
//            FileWriter writer = new FileWriter(file, true);
//            writer.write(studentName + "\n");
//            writer.write(studentGrade + "\n");
//            writer.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        secondFrame.getStudentNameTextField().setText("");
//        secondFrame.getStudentGradeTextField().setText("");
//    }
}
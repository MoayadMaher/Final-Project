import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SecondFrame  extends JFrame {

    // items declaration
    private Container container;
    private GridLayout gridLayout;
    private JLabel emptyLabel;
    private JLabel MainLabel;
    private JButton AddStudent;
    private JButton ShowStudents;
    private JButton SearchStudent;
    private JButton AvgMinMax;
    private JButton Back;
    private JTextField StudentName;
    private JTextField StudentGrade;
    private JList SubjectList;
    private JTextArea Output;

    // variables declaration
    private String[] Subjects0 = {"البرمجة المتقدمة", "الانظمة المضمنة", "الذكاء الاصطناعي"};
    private String[] Subjects1 = {"بروتوكولات الشبكات", "أمن الشبكات", "انترنت الأشياء"};
    private String[] Subjects2 = {"المنطق المشوش والشبكات العصبونية", "مختبر انظمة المعالجات الدقيقة", "انظمة المعالجات الدقيقة"};

    Font f = new Font("Arial", Font.BOLD, 16);

    public SecondFrame (String selected, FirstFrame firstFrame){
        super("تسجيل العلامات");
        gridLayout = new GridLayout(4, 3, 5, 5);
        container = getContentPane();
        setLayout(gridLayout);

        // for layout setting
        emptyLabel = new JLabel(" ");
        add(emptyLabel);

        // to show the title and the logo
        Icon c1 = new ImageIcon(getClass().getResource("b.jpg"));
        MainLabel = new JLabel("نظام العلامات", c1, SwingConstants.CENTER);
        MainLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        MainLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        MainLabel.setFont(f);
        add(MainLabel);

        emptyLabel = new JLabel(" ");
        add(emptyLabel);

        //Student Grade and Name
        StudentGrade = new JTextField("علامة الطالب من 100", 20);
        StudentGrade.setHorizontalAlignment(JTextField.RIGHT);
        StudentGrade.setFont(f);
        add(StudentGrade);

        StudentName = new JTextField("إسم الطالب", 20);
        StudentName.setHorizontalAlignment(JTextField.RIGHT);
        StudentName.setFont(f);
        add(StudentName);

        // Add Student
        AddStudent = new JButton("أضف الطالب");
        AddStudent.setFont(f);
        add(AddStudent);

        // Average, Min, Max
        AvgMinMax = new JButton("المتوسط وأعلى وأقل علامة");
        AvgMinMax.setFont(f);
        add(AvgMinMax);

        // Search Student
        SearchStudent = new JButton("بحث");
        SearchStudent.setFont(f);
        add(SearchStudent);

        // Show Students
        ShowStudents = new JButton("عرض");
        ShowStudents.setFont(f);
        add(ShowStudents);

        // Back Button
        Back = new JButton("رجوع");
        Back.setFont(f);
        add(Back);

        // Output Text Area
        Output = new JTextArea(10,15);
        Output.setFont(f);
        Output.setEditable(false);
        add(new JScrollPane(Output));

        // Subject List
        if (selected.equals("م. مازن ابوزاهر")) {
            SubjectList = new JList(Subjects0);
        } else if (selected.equals("د. رشاد رصرص")) {
            SubjectList = new JList(Subjects1);
        } else {
            SubjectList = new JList(Subjects2);
        }
        SubjectList.setFont(f);
        add(SubjectList);

        //create new ButtonHandler for button event handling
        ButtonHandler handler = new ButtonHandler(firstFrame,this); // Pass 'this' to refer to the current SecondFrame instance
        AddStudent.addActionListener(handler);
        AvgMinMax.addActionListener(handler);
        SearchStudent.addActionListener(handler);
        ShowStudents.addActionListener(handler);
        Back.addActionListener(handler);

    }

    // Getter methods for buttons
    public JButton getAddStudentButton() {
        return AddStudent;
    }
    public JButton getAvgMinMaxButton() {
        return AvgMinMax;
    }
    public JButton getSearchStudentButton() {
        return SearchStudent;
    }
    public JButton getShowStudentsButton() {
        return ShowStudents;
    }
    public JButton getBackButton() {
        return Back;
    }
    public JTextField getStudentNameTextField() {
        return StudentName;
    }
    public JTextField getStudentGradeTextField() {
        return StudentGrade;
    }
    public JList getSubjectList() {
        return SubjectList;
    }
    public JTextArea getOutputTextArea() {
        return Output;
    }

}

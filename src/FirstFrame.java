import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FirstFrame extends JFrame {

    // Member variable declarations
    private Container container;
    private GridLayout gridLayout;
    private JLabel emptyLabel;
    private JLabel MainLabel;
    private JComboBox InstructorSelect;
    private JButton NextPage;
    private JButton Exit;

    private String[] Instructors = {"م. مازن ابوزاهر", "د. أشرف الشرعة", "د. رشاد رصرص"};

    Font f = new Font("Arial", Font.BOLD, 16);

    public FirstFrame() {
        super("تسجيل العلامات");
        gridLayout = new GridLayout(2, 3, 5, 5);
        container = getContentPane();
        setLayout(gridLayout);

        // This empty label is used to add space between components
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

        // next page and exit buttons adding
        Exit = new JButton("خروج");
        Exit.setFont(f);
        add(Exit);

        NextPage = new JButton("الصفحة التالية");
        NextPage.setFont(f);
        add(NextPage);

        // instructor combo box adding
        InstructorSelect = new JComboBox(Instructors);
        InstructorSelect.setFont(f);
        InstructorSelect.setMaximumRowCount(2);
        add(InstructorSelect);

        // Instantiate ButtonHandler for button event handling
        ButtonHandler handler = new ButtonHandler(this, null);
        // Pass 'this' to refer to the current FirstFrame instance
        // Pass null to refer to there in no need to refer to SecondFrame instance

        Exit.addActionListener(handler);
        NextPage.addActionListener(handler);
    }

    // Getter methods for buttons and combo box
    public JButton getExitButton() {
        return Exit;
    }

    public JButton getNextPageButton() {
        return NextPage;
    }

    public JComboBox getInstructorSelectComboBox() {
        return InstructorSelect;
    }

}

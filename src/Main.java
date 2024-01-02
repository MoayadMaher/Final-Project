import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FirstFrame firstFrame = new FirstFrame(); // create FirstFrame instance
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setSize(500, 500); // set frame size
        firstFrame.setVisible(true); // display frame
    }
}

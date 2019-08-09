package swingDemo;

import javax.swing.*;

/**
 * @Description
 * @author:awei
 * @date:2019/7/30
 * @ver:1.0
 **/
public class SwingDemo extends JFrame {

    public SwingDemo(){
        setTitle("socket");
        setBounds(400,500,400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SwingDemo();
    }
}

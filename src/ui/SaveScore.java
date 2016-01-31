package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by StReaM on 2/1/2016.
 */
public class SaveScore  extends JFrame{

    private JButton okToSaveButton = null;
    private JLabel scoreLabel = null;
    private JTextField textz = null;

    public SaveScore() {
        this.setTitle("保存记录");
        this.setSize(256,256);
        FrameUtil.setCenter(this);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.createButton();
        // test
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    private void createButton() {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.scoreLabel = new JLabel("Your score: 999");
        north.add(scoreLabel);
        this.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        this.textz = new JTextField(10);

        center.add(new JLabel("Your name: "));
        center.add(this.textz);
        this.add(center, BorderLayout.CENTER);


        this.okToSaveButton = new JButton("OK");
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south.add(okToSaveButton);
        this.add(south, BorderLayout.SOUTH);

    }


    // test
//    public static  void  main(String[] args) {
//        new SaveScore();
//    }

}

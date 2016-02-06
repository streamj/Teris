package ui;

import control.GameControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by StReaM on 2/1/2016.
 */
public class LayerSaveScore extends JFrame{

    private JButton okToSaveButton = null;
    private JLabel scoreLabel = null;
    private JTextField playerName = null;
    private JLabel errorMsg = null;
    private GameControl gameControl = null;

    public LayerSaveScore(GameControl gameControl) {
        this.gameControl = gameControl;
        this.setTitle("保存记录");
        this.setSize(256,192);
        FrameUtil.setCenter(this);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.createButton();
        this.createAction();

    }


    private void createAction(){
        this.okToSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = playerName.getText();
                if(name.length() > 21) {
                    errorMsg.setText("less than 20");
                } else if (name == null || "".equals(name)){
                    errorMsg.setText("cannot be blank");
                } else {
                    setVisible(false);
                    gameControl.saveScore(name);
                }
            }
        });
    }

    private void createButton() {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.scoreLabel = new JLabel();
        north.add(scoreLabel);

        this.errorMsg = new JLabel();
        this.errorMsg.setForeground(Color.RED);
        north.add(errorMsg);

        this.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        this.playerName = new JTextField(21);

        center.add(new JLabel("Your name: "));
        center.add(this.playerName);
        this.add(center, BorderLayout.CENTER);


        this.okToSaveButton = new JButton("OK");
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south.add(okToSaveButton);
        this.add(south, BorderLayout.SOUTH);

    }

    public void showScore(int score) {
        this.scoreLabel.setText("Your score: " + score);
        this.setVisible(true);

    }

    // test
//    public static  void  main(String[] args) {
//        new LayerSaveScore();
//    }

}
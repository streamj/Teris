package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Created by StReaM on 11/29/2015.
 */
public class PlayerControl extends KeyAdapter {

    private GameControl gameControl;

    public PlayerControl(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    /**
     * 键盘按下事件
     * @param e
     */

    @Override

    public void keyPressed(KeyEvent e) {
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_UP:
//                this.gameControl.up();
//                break;
//            case KeyEvent.VK_DOWN:
//                this.gameControl.down();
//                break;
//            case KeyEvent.VK_LEFT:
//                this.gameControl.left();
//                break;
//            case KeyEvent.VK_RIGHT:
//                this.gameControl.right();
//                break;
//            default:
//                break;
//        }
        this.gameControl.actionByKeyCode(e.getKeyCode());
    }
}

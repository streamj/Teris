package dto;

import java.io.Serializable;

/**
 * Created by StReaM on 11/29/2015.
 */
public class Player implements Comparable<Player> , Serializable{

    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Player pla) {

        return pla.score - this.score;
    }
}

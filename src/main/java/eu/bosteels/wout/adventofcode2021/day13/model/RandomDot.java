package eu.bosteels.wout.adventofcode2021.day13.model;

public class RandomDot {

    private int xCoord;

    private int yCoord;

    private boolean isDot;

    public RandomDot() {
    }

    public RandomDot(int xCoord, int yCoord, boolean isDot) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.isDot = isDot;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }


    public boolean getIsDot() {
        return isDot;
    }

    private void setIsDot(boolean isDot) {
        this.isDot = isDot;
    }

    public void setDot(boolean dot) {
        isDot = dot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RandomDot)) return false;

        RandomDot randomDot = (RandomDot) o;

        if (getxCoord() != randomDot.getxCoord()) return false;
        if (getyCoord() != randomDot.getyCoord()) return false;
        return isDot == randomDot.isDot;
    }

    @Override
    public String toString() {
        return "x = " + xCoord + ", y = " + yCoord + " dot = " + isDot;
    }
}

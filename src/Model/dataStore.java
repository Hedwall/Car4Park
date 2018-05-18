package Model;

public class dataStore {

    private int front = 0;
    private int side = 0;
    private int back = 0;


    public void setFront(int front) {
        this.front = front;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public int getFront() {
        return front;
    }

    public int getSide() {
        return side;
    }

    public int getBack() {
        return back;
    }

}

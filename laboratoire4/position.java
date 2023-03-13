package laboratoire4;

public class position  {
    private int x;
    private int y;
    private Double score = (double) 0;

    public Double getScore() {
        return new Double(score);
    }

    public void setScore(Double score) {
        this.score = new Double(score);
    }

    public position(int x, int y){
        this.x = new Integer(x);
        this.y = new Integer(y);
    }

    public int getX(){
        return new Integer(this.x);
    }

    public int getY(){
        return new Integer(this.y);
    }
}

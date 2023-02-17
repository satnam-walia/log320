package laboratoire2;

public class Noeud implements Comparable<Noeud>{
    private Noeud left;
    private Noeud right;
    private int frequency;


    public Noeud(Noeud right, Noeud left) {
        this.left = left;
        this.right = right;
        this.frequency = right.getFrequency() + left.getFrequency();
    }

    public Noeud(int frequency) {
        this.left = null;
        this.right = null;
        this.frequency=frequency;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public int compareTo(Noeud n) {
        return Integer.compare(this.frequency, n.getFrequency());
    }

    public Noeud right() {
        return this.right;
    }

    public Noeud left() {
        return this.left;
    }
}

import java.awt.*;

/**
 * Rect class
 * @author Aditi Datta
 */
public class Rect {
    int x;
    int y;
    int height;
    int width;

    boolean completelyInside = false;

    public Rect(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rect r){
        int count = 0;
        if(r.x + r.width > this.x && r.y + r.height > this.y
                && r.x < this.x+this.width
                && r.y < this.y+this.height)
            return true;

        return false;
    }

    public boolean contains(Rect r){

        return (r.x >= this.x &&
                r.y >= this.y &&
                (r.x + r.width) <= this.x + this.width &&
                (r.y + r.height) <= this.y + this.height);
    }
}

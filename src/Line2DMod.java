import java.awt.geom.Line2D;

/**
 * Created by alien on 10/11/16.
 */
public class Line2DMod extends Line2D.Double {
    ObjectType objectType;
    public Line2DMod(double x1, double y1, double x2, double y2, ObjectType objectType){
        super(x1, y1, x2, y2);
        this.objectType = objectType;
    }
}

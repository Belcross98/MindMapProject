package dsw.gerumap.app.serializer;

import lombok.Getter;
import lombok.Setter;

import java.awt.geom.Point2D;

@Getter
@Setter
public class SerializablePoint2D {

    private int x;
    private int y;

    public SerializablePoint2D(int x,int y){

        this.x = x;
        this.y = y;
    }

    public SerializablePoint2D(Point2D point2D){

        this.x = (int) point2D.getX();
        this.y = (int) point2D.getY();
    }


    public Point2D toPoint2D(){

        return new Point2D.Float(x, y);
    }
}

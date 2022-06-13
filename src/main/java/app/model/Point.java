package app.model;

import java.io.Serial;
import java.io.Serializable;

public class Point implements Serializable {
    @Serial
    private static final long serialVersionUID = 7521250368252136732L;

    private Double x;
    private Double y;

    public Point() {}

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}

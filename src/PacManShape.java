import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 A car that can be moved around.
 */
public class PacManShape implements MoveableShape
{
    /**
     Constructs a car item.
     @param x the left of the bounding rectangle
     @param y the top of the bounding rectangle
     @param width the width of the bounding rectangle
     */
    public PacManShape(int x, int y, int width)
    {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void translate(int dx, int dy, int aWidth, int aHeight)
    {
        if (x < 0) x = aWidth;
        else if (x > aWidth) x = 0;
        if (y < 0) y = aHeight;
        else if (y > aHeight) y = 0;
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g2)
    {
        g2.setColor(Color.yellow);
        g2.fillArc(0,0,width,width,30,300);
    }

    private int x;
    private int y;
    private int width;
}

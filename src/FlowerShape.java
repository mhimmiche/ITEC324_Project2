import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 A flower that can be moved around.
 */
public class FlowerShape implements MoveableShape
{
    /**
     Constructs a car item.
     @param x the left of the bounding rectangle
     @param y the top of the bounding rectangle
     @param width the width of the bounding rectangle
     */
    public FlowerShape(int x, int y, int width)
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
        Ellipse2D.Double flowerPetals  = new Ellipse2D.Double(x, y, width / 2, width / 2);
        Ellipse2D.Double flowerCenter  = new Ellipse2D.Double(x + width / 8, y + width / 8, width / 4, width / 4);
        Rectangle2D.Double stem = new Rectangle2D.Double(x + width / 5, y + width / 2, width / 10, width / 2);

        g2.setPaint(Color.YELLOW);
        g2.fill(flowerPetals);
        g2.setPaint(new Color(156, 93, 82));
        g2.fill(stem);
        g2.setPaint(Color.BLACK);
        g2.fill(flowerCenter);
        g2.draw(flowerPetals);
        g2.draw(flowerCenter);
        g2.draw(stem);
    }

    private int x;
    private int y;
    private int width;
}

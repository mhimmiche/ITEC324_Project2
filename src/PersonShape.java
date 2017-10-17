import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 A car that can be moved around.
 */
public class PersonShape implements MoveableShape
{
    /**
     Constructs a car item.
     @param x the left of the bounding rectangle
     @param y the top of the bounding rectangle
     @param width the width of the bounding rectangle
     */
    public PersonShape(int x, int y, int width)
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
        Rectangle2D.Double head = new Rectangle2D.Double(x, y + width / 4, width / 2, width / 2);
        Rectangle2D.Double neck = new Rectangle2D.Double(x + width / 6, y + width * 3 / 4, width / 6, width / 4);
        Rectangle2D.Double body = new Rectangle2D.Double(x - width / 4, y + width, width, width / 2);
        Rectangle2D.Double lEye = new Rectangle2D.Double(x + width / 7, y + 2 * width / 6, width / 10, width / 10);
        Rectangle2D.Double rEye = new Rectangle2D.Double(x + 2 * width / 7, y + 2 * width / 6, width / 10, width / 10);
        Rectangle2D.Double mouth = new Rectangle2D.Double(x + width / 7, y + 3 * width / 6, width / 4, width / 10);

        g2.setPaint(Color.pink);
        g2.fill(head);
        g2.fill(neck);
        g2.setPaint(Color.BLUE);
        g2.fill(body);
        g2.setPaint(Color.WHITE);
        g2.fill(lEye);
        g2.fill(rEye);
        g2.fill(mouth);

        g2.draw(head);
        g2.draw(neck);
        g2.draw(body);
        g2.draw(lEye);
        g2.draw(rEye);
        g2.draw(mouth);
    }

    private int x;
    private int y;
    private int width;
}

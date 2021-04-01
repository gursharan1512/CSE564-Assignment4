import java.awt.*;

/**
 * It is placeholder class to connect the different decorations with basic graph plot.
 *
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class DecoratorPlot implements Drawable {

    Drawable tempDrawable;

    public DecoratorPlot(Drawable drawable) {
        this.tempDrawable = drawable;
    }

    @Override
    public void draw(Graphics g, UMLClassModel umlClassModel) {
        if (tempDrawable != null) {
            tempDrawable.draw(g, umlClassModel);
        }
    }
}

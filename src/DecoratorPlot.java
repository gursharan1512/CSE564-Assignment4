import java.awt.*;

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

import java.awt.*;

public class UMLBasicPlot implements Drawable {
    @Override
    public void draw(Graphics g, UMLClassModel umlClassModel) {

//        g.setColor(Color.BLUE);
//        g.drawRect(10,10,200,300);
//        g.setColor(Color.WHITE);
//        g.fillRect(11,11,198,298);

        g.setColor(Color.BLACK);
        int x = umlClassModel.getxAxis();
        int y = umlClassModel.getyAxis();
        g.drawString(umlClassModel.getClassName(), x + 10, y + 10);
        g.setColor(Color.BLUE);

        int methodCount = umlClassModel.getMethodDetailsList().size();

//        if (methodCount > 4) {
//            // for greater than 4
//        } else {
//            int z = y;
            // System.out.println("size : " + classObj.className + " " + x + " " + y);
            g.drawRect(x, y, 200, Constants.HEIGHT_OF_CLASS_UML);

//            for (int i = 0; i < methodCount; i++) {
//                // System.out.println("method");
//                g.drawRect(x + 20, z + 55, 150, 45);
//                z = z + 60;
//            }
//        }


    }
}

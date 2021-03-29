import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class UMLPlotter extends JPanel implements Observer {

    private ArrayList<UMLClassModel> classAttributes = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        Drawable umlBasicPlot = new UMLConditionPlot(new UMLLoopPlot(new UMLBasicPlot()));
        for(UMLClassModel classAttribute: classAttributes) {
            umlBasicPlot.draw(g,classAttribute);
            if (classAttribute.getClassRelationList() != null) {
                for (String classRelation : classAttribute.getClassRelationList()) {
                    String[] strArr = classRelation.split("\\.");
                    drawLine(classAttribute, strArr[0], strArr[2], g);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        classAttributes.add((UMLClassModel) arg);
//        for (UMLClassModel umlClassModel: classAttributes) {
//            System.out.println(umlClassModel.getClassName()+" "+
//                    umlClassModel.getMethodDetailsList().get(0).getMethodName()+" "+
//                    umlClassModel.getClassRelationList()+" "+
//                    umlClassModel.getxAxis()+" "+
//                    umlClassModel.getyAxis());
//        }
        this.paint(getGraphics());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200, 600);
    }

    public void printDetails(){
        //System.out.println("print observer classes:" + classAttributes);
    }

    private void drawLine(UMLClassModel sourceClass, String destClass, String connection, Graphics g) {
        UMLClassModel destClassObj = null;
        for (UMLClassModel classAttribute: classAttributes) {
            if (destClass.equals(classAttribute.getClassName())) {
                destClassObj = classAttribute;
                break;
            }
        }

        if (destClassObj != null) {
            int x1 = sourceClass.getxAxis();
            int y1 = sourceClass.getyAxis();

            int x2 = destClassObj.getxAxis();
            int y2 = destClassObj.getyAxis();

            if (x1 < x2) {
                g.drawLine(x1 + 200, y1 + 150, x2, y2 + 150);
            } else if (x2 > x1) {
                g.drawLine(x1, y1 + 150, x2 + 200, y2 + 150);
            } else if (x1 == x2) {
                if (y1 < y2) {
                    g.drawLine(x1 + 100, y1 + 300, x2 + 100, y2);
                } else
                    g.drawLine(x1 + 100, y1, x2 + 100, y2 + 300);
            }
        }

    }
}

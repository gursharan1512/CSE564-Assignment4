import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class UMLPlotter extends JPanel implements Observer {

    private ArrayList<UMLClassModel> classAttributes = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        Drawable umlBasicPlot = new UMLBasicPlot();
        Drawable umlMethodPlot = new UMLMethodPlot(umlBasicPlot);
        Drawable umlLoopPlot = new UMLLoopPlot(umlMethodPlot);
        UMLCLassJoiner joiner1 = new AggregateRelationshipHandler();
        UMLCLassJoiner joiner2 = new AssociationRelationshipHandler();
        UMLCLassJoiner joiner3 = new ErrorRelationshipHandler();
        joiner1.setNextUMLClassJoiner(joiner2);
        joiner2.setNextUMLClassJoiner(joiner3);
        Drawable umlConditionPlot = new UMLConditionPlot(umlLoopPlot);
        for (UMLClassModel classAttribute : classAttributes) {
            if (!classAttribute.getMethodDetailsList().isEmpty()) {
                setUMLDimension(classAttribute);
                ArrayList<MethodDetails> methodDetails = classAttribute.getMethodDetailsList();
                boolean methodsAreEmpty = true;
                for (MethodDetails methodDetail : methodDetails) {
                    if (methodDetail.isIfCheck()) {
                        umlConditionPlot.draw(g, classAttribute);
                        methodsAreEmpty = false;
                        break;
                    }
                    if (methodDetail.isLoopCheck()) {
                        umlLoopPlot.draw(g, classAttribute);
                        methodsAreEmpty = false;
                    }

                }
                if (methodsAreEmpty) {
                    umlMethodPlot.draw(g, classAttribute);
                }


            } else {
                umlBasicPlot.draw(g, classAttribute);
            }

            if (classAttribute.getClassRelationList() != null) {
                for (String classRelation : classAttribute.getClassRelationList()) {
                    String[] strArr = classRelation.split("\\.");
//                    joiner1.drawRelationship(classAttribute, strArr[0], strArr[2], g);
                    drawConnection(classAttribute, strArr[0], strArr[2], g, joiner1);
                }
            }

        }
    }

    private void setUMLDimension(UMLClassModel classAttribute) {
        int methodCount = classAttribute.getMethodDetailsList().size();
        if (methodCount > 4) {
            int heightOfEachMethodUMLBlock = (Constants.HEIGHT_OF_CLASS_UML - 55) / methodCount;
            classAttribute.setHeightOfComponent(heightOfEachMethodUMLBlock - 5);
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
        return new Dimension(1200, 800);
    }

    public void printDetails() {
        //System.out.println("print observer classes:" + classAttributes);
    }

    private void drawConnection(UMLClassModel sourceClass, String destClass, String connection, Graphics g, UMLCLassJoiner joiner1) {
        UMLClassModel destClassObj = null;
        boolean invalidClassRelationship = true;
        for (UMLClassModel classAttribute : classAttributes) {
            if (destClass.equals(classAttribute.getClassName())) {
                destClassObj = classAttribute;
                invalidClassRelationship = false;
                break;
            }
        }
        if (invalidClassRelationship) {
            try {
                throw new ClassNotFoundException("NO valid Destination class found with class name: " + destClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (destClassObj != null) {
            int x1 = sourceClass.getxAxis();
            int y1 = sourceClass.getyAxis();

            int x2 = destClassObj.getxAxis();
            int y2 = destClassObj.getyAxis();

            if (x1 < x2) {
                x1 += 200;
                y1 += 150;
                y2 += 150;
            } else if (x2 < x1) {
                x2 += 200;
                y1 += 150;
                y2 += 150;
            } else {
                if (y1 < y2) {
                    x1 += 100;
                    y1 += 300;
                    x2 += 100;
                } else {
                    x1 += 100;
                    y2 += 300;
                    x2 += 100;

                }

            }
            g.setColor(Color.GREEN);
            g.drawLine(x1, y1, x2, y2);
            joiner1.drawRelationship(x1, y1, x2, y2, connection, g);

        }

    }
}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * class Receives the data from Lexer and generate UML diagram.
 *
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class UMLPlotter extends JPanel implements Observer {

    private ArrayList<UMLClassModel> classAttributes = new ArrayList<>();

    /**
     * method is use to get the updated data from observable.
     * @param o   - object of observable class.
     * @param arg - Updated input data from observable.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.removeAll();
        this.revalidate();
        classAttributes = (ArrayList<UMLClassModel>) arg;
        this.paint(getGraphics());
    }

    /**
     * implements different types of the graphs on the given graphics objects.
     *
     * @param g - graphics object to draw plot in.
     */
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
            if (classAttribute.getClassRelationList() != null) {
                for (String classRelation : classAttribute.getClassRelationList()) {
                    String[] strArr = classRelation.split("\\.");
                    try {
                        drawConnection(classAttribute, strArr[0], strArr[1], strArr[2], g, joiner1);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                }
            }
        }
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



        }
    }

    private void setUMLDimension(UMLClassModel classAttribute) {
        int methodCount = classAttribute.getMethodDetailsList().size();
        if (methodCount > 4) {
            int heightOfEachMethodUMLBlock = (Constants.HEIGHT_OF_CLASS_UML - 55) / methodCount;
            classAttribute.setHeightOfComponent(heightOfEachMethodUMLBlock - 5);
        }
    }

    /**
     * Sets the height and width of JPanel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1150, 800);
    }

    /**
     * Draw UML relation between two source and destination class
     *
     * @param sourceClass - Source class object to draw the UML relationship.
     * @param destClass - Source class object to draw the UML relationship.
     * @param methodName - destination class method name which source class is using.
     * @param connection - defines the type of relation between source and destination class like aggregation or association.
     * @param g - graphics object to draw plot in.
     * @param joiner1 - parent class for chain of responsibility.
     */
    private void drawConnection(UMLClassModel sourceClass, String destClass, String methodName, String connection, Graphics g, UMLCLassJoiner joiner1) throws ClassNotFoundException {
        UMLClassModel destClassObj = null;
        boolean invalidClassRelationship = true;
        for (UMLClassModel classAttribute : classAttributes) {
            if (destClass.equals(classAttribute.getClassName())) {
                destClassObj = classAttribute;
                if (destClassObj != null) {
                    ArrayList<MethodDetails> methodDetails = destClassObj.getMethodDetailsList();
                    for (MethodDetails methodDetail : methodDetails) {
                        if (methodName.equals(methodDetail.getMethodName()) || methodName.startsWith("methd")) {
                            invalidClassRelationship = false;
                            break;
                        }
                    }
                }
                break;
            }
        }
        if (invalidClassRelationship) {
                throw new ClassNotFoundException("Invalid Destination class or Method name: " + destClass + " " + methodName);

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

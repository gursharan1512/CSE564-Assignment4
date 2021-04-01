import java.util.ArrayList;

/**
 * Model class to store all the information of a class
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class UMLClassModel {

    private String className;
    private int xAxis;
    private int yAxis;
    private int heightOfComponent;
    private ArrayList<MethodDetails> methodDetailsList;
    private ArrayList<String> classRelationList;

    public UMLClassModel() {
        this.heightOfComponent = 45;
        methodDetailsList = new ArrayList<>();
        classRelationList = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<MethodDetails> getMethodDetailsList() {
        return methodDetailsList;
    }

    public void setMethodDetailsList(ArrayList<MethodDetails> methodDetailsList) {
        this.methodDetailsList = methodDetailsList;
    }

    public ArrayList<String> getClassRelationList() {
        return classRelationList;
    }

    public void setClassRelationList(ArrayList<String> classRelation) {
        this.classRelationList = classRelationList;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public void addMethodDetails(MethodDetails methodDetails) {
        this.methodDetailsList.add(methodDetails);
    }

    public void addClassRelationList(String classRelation) {
        this.classRelationList.add(classRelation);
    }

    public int getHeightOfComponent() {
        return heightOfComponent;
    }

    public void setHeightOfComponent(int heightOfComponent) {
        this.heightOfComponent = heightOfComponent;
    }
}

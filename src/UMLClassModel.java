import java.util.ArrayList;

public class UMLClassModel {
    private String className;
    private int xAxis;
    private int yAxis;
    private int heightOfComponent;
    private ArrayList<MethodDetails> methodDetailsList;
    private ArrayList<String> classRelationList;

    public String getClassName() {
        return className;
    }

    public ArrayList<MethodDetails> getMethodDetailsList() {
        return methodDetailsList;
    }

    public ArrayList<String> getClassRelationList() {
        return classRelationList;
    }

    public int getxAxis() {
        return xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodDetailsList(ArrayList<MethodDetails> methodDetailsList) {
        this.methodDetailsList = methodDetailsList;
    }

    public void setClassRelationList(ArrayList<String> classRelation) {
        this.classRelationList = classRelationList;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
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

    public UMLClassModel() {
        this.heightOfComponent = 45;
        methodDetailsList = new ArrayList<>();
        classRelationList = new ArrayList<>();
    }
}

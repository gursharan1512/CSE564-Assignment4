import java.util.ArrayList;

public class UMLClassModel {
    private String className;
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

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodDetailsList(ArrayList<MethodDetails> methodDetailsList) {
        this.methodDetailsList = methodDetailsList;
    }

    public void setClassRelationList(ArrayList<String> classRelation) {
        this.classRelationList = classRelationList;
    }

    public void addMethodDetails(MethodDetails methodDetails) {
        this.methodDetailsList.add(methodDetails);
    }

    public void addClassRelationList(String classRelation) {
        this.classRelationList.add(classRelation);
    }

    public UMLClassModel() {
        methodDetailsList = new ArrayList<>();
        classRelationList = new ArrayList<>();
    }
}

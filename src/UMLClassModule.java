import java.util.ArrayList;

public class UMLClassModule {
    private String className;
    private ArrayList<String> methodNameList;
    private ArrayList<String> classRelationList;
    private boolean loopCheck;
    private boolean ifCheck;

    public String getClassName() {
        return className;
    }

    public ArrayList<String> getMethodNameList() {
        return methodNameList;
    }

    public ArrayList<String> getClassRelationList() {
        return classRelationList;
    }

    public boolean isLoopCheck() {
        return loopCheck;
    }

    public boolean isIfCheck() {
        return ifCheck;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodNameList(ArrayList<String> methodNameList) {
        this.methodNameList = methodNameList;
    }

    public void setClassRelationList(ArrayList<String> classRelation) {
        this.classRelationList = classRelationList;
    }

    public void setLoopCheck(boolean loopCheck) {
        this.loopCheck = loopCheck;
    }

    public void setIfCheck(boolean ifCheck) {
        this.ifCheck = ifCheck;
    }

    public void addMethodNameList(String methodName) {
        this.methodNameList.add(methodName);
    }

    public void addClassRelationList(String classRelation) {
        this.classRelationList.add(classRelation);
    }

    public UMLClassModule() {
        methodNameList = new ArrayList<>();
        classRelationList = new ArrayList<>();
        loopCheck = false;
        ifCheck = false;
    }
}

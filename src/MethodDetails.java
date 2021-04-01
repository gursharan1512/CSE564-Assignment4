/**
 * Model class to store the information of a method in a class
 *
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class MethodDetails {
    private String methodName;
    private boolean ifCheck;
    private boolean loopCheck;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public boolean isIfCheck() {
        return ifCheck;
    }

    public void setIfCheck(boolean ifCheck) {
        this.ifCheck = ifCheck;
    }

    public boolean isLoopCheck() {
        return loopCheck;
    }

    public void setLoopCheck(boolean loopCheck) {
        this.loopCheck = loopCheck;
    }
}

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class UMLPlotter implements Observer {

    private ArrayList<UMLClassModule> classAttributes = new ArrayList<>();

    @Override
    public void update(Observable o, Object arg) {
//        System.out.println("observers are updated with the change");
        classAttributes.add((UMLClassModule) arg);
    }

    public void printDetails(){
        System.out.println("print observer classes:" + classAttributes);
    }
}

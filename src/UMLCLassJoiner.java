import java.awt.*;

public abstract class UMLCLassJoiner {

    public UMLCLassJoiner umlClassJoiner;

    void setNextUMLClassJoiner(UMLCLassJoiner umlClassJoiner){
        this.umlClassJoiner = umlClassJoiner;
    }

    abstract void drawRelationship(int x1, int y1, int x2, int y2, String connection, Graphics g);
}

import java.awt.*;

public interface UMLCLassJoiner {

    void setNextUMLClassJoiner(UMLCLassJoiner umlClassJoiner);

    void drawRelationship(int x1, int y1, int x2, int y2, String connection, Graphics g);
}

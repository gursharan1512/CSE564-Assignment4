import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Parser class implement methods which takes list of string tokens and parse them according to the java rules.
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class Lexer extends Observable {

    private List<UMLClassModel> umlClassModels = new ArrayList<>();
    private ArrayList<String> classList = new ArrayList<>();
    int classCount = 1;

    /**
     * Method takes input in the form of sequence of tokens and stores the information in UMLClassModel object for all the classes
     * @param tokens - List of String
     */
    public void parseToken(ArrayList<String> tokenList) throws Exception {

        for (int i = 0; i < tokenList.size(); i++) {
            if (tokenList.get(i).equals("class")) {
                classList.add(tokenList.get(i+1));
            }
        }

        classCount = 1;
        for (int i = 0; i < tokenList.size(); i++) {
            i = checkInstruction(tokenList, i);
            i = checkClass(tokenList, i);
        }
        setChanged();
        notifyObservers(umlClassModels);
    }

    /**
     * Check whether given token is Class and stores the respective information in the UMLClassModel object
     * @param tokenList - Array of token.
     * @param i - array counter.
     * @return Int updated position of array counter.
     */
    private int checkClass(ArrayList<String> tokenList, int i) {

        if (tokenList.get(i).equals("class")) {
            UMLClassModel umlClassModel = new UMLClassModel();
            i++;
            umlClassModel.setClassName(tokenList.get(i));
            if (classCount < 4) {
                umlClassModel.setxAxis((classCount - 1) * 400 + 15);
                umlClassModel.setyAxis(10);
                classCount++;
            } else {
                umlClassModel.setxAxis((classCount - 4) * 400 + 15);
                umlClassModel.setyAxis(400);
                classCount++;
            }
            i++;
            if (tokenList.get(i).equals("{")) {
                i++;
                while (!tokenList.get(i).equals("}")) {
                    i = checkAggregation(tokenList, i, umlClassModel);
                    i = checkInstruction(tokenList, i);
                    i = checkMethod(tokenList, i, umlClassModel);
                }
                if (tokenList.get(i).equals("}")) {
                    umlClassModels.add(umlClassModel);
                    return i;
                } else {
                    throw new RuntimeException("Invalid Syntax");
                }
            }
        }
        return i;
    }

    /**
     * Check whether given token is Method and stores the respective information in the UMLClassModel and MethodDetails object
     * @param tokenList - Array of token.
     * @param i - array counter.
     * @param umlClassModel - object to store information of a class.
     * @return Int updated position of array counter.
     */
    private int checkMethod(ArrayList<String> tokenList, int i, UMLClassModel umlClassModel) {

        if (tokenList.get(i + 1).equals("(")) {
            if (tokenList.get(i + 2).equals(")")) {
                if (tokenList.get(i + 3).equals("{")) {
                    MethodDetails methodDetails = new MethodDetails();
                    methodDetails.setMethodName(tokenList.get(i));
                    i = i + 4;
                    while (!tokenList.get(i).equals("}")) {
                        i = checkAssociation(tokenList, i, umlClassModel);
                        i = checkInstruction(tokenList, i);
                        i = checkIf(tokenList, i, umlClassModel, methodDetails);
                        i = checkLoop(tokenList, i, umlClassModel, methodDetails);
                    }
                    if (tokenList.get(i).equals("}")) {
                        umlClassModel.addMethodDetails(methodDetails);
                        i++;
                        return i;
                    } else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                }
                else
                    throw new RuntimeException("Invalid Syntax");
            }
        }
        return i;
    }

    /**
     * Check whether relation between two classes is association or not
     * @param tokenList - Array of token.
     * @param i - array counter.
     * @param umlClassModel - object to store information of a class.
     * @return Int updated position of array counter.
     */
    private int checkAssociation(ArrayList<String> tokenList, int i, UMLClassModel umlClassModel) {
        if (tokenList.get(i).contains(".")) {
            String className = tokenList.get(i).split("\\.")[0];
            Boolean flag = false;
            for (int j = 0; j < umlClassModel.getClassRelationList().size(); j++) {
                String[] classRelation = umlClassModel.getClassRelationList().get(j).split("\\.");
                if (classRelation[1].startsWith("methd") && classRelation[1].endsWith(className)) {
                    umlClassModel.getClassRelationList().set(j,classRelation[0]+"."+tokenList.get(i).split("\\.")[1]+".Aggregation");
                    flag = false;
                }
                else
                    flag = true;
            }
            if (flag) {
                umlClassModel.addClassRelationList(tokenList.get(i) + ".Association");
            }
            i++;
            if (tokenList.get(i).equals("(")) {
                i++;
                if (tokenList.get(i).equals(")")) {
                    i++;
                    if (tokenList.get(i).equals(";")) {
                        i++;
                        return i;
                    }
                }
            }
        }
        return i;
    }

    /**
     * Check whether relation between two classes is aggregation or not
     * @param tokenList - Array of token.
     * @param i - array counter.
     * @param umlClassModel - object to store information of a class.
     * @return Int updated position of array counter.
     */
    private int checkAggregation(ArrayList<String> tokenList, int i, UMLClassModel umlClassModel) {
        if (classList.contains(tokenList.get(i)) && tokenList.get(i+2).equals(";")) {
            umlClassModel.addClassRelationList(tokenList.get(i) + ".methd"+tokenList.get(i+1)+".Aggregation");
            i = i+3;
        }
        return i;
    }

    /**
     * Check whether given token is instruction
     * @param tokenList - Array of token.
     * @param i - array counter.
     * @return Int updated position of array counter.
     */
    private int checkInstruction(ArrayList<String> tokenList, int i) {

        while (!tokenList.get(i).equals("if") && !tokenList.get(i).equals("while") && !tokenList.get(i).equals("for") && !tokenList.get(i).equals("class") && !tokenList.get(i).equals("}") && !tokenList.get(i).contains(".") && !tokenList.get(i+1).equals("(") && !classList.contains(tokenList.get(i))) {
            i++;
            while (!tokenList.get(i).equals(";")) {
                i++;
            }
            i++;
        }
        return i;
    }

    /**
     * Check whether given token is a IF condition and stores the information in MethodDetails object
     * @param tokenList - Array of token.
     * @param i - array counter.
     * @param umlClassModel - object to store information of a class.
     * @param methodDetails - object to store information of a method.
     * @return Int updated position of array counter.
     */
    private int checkIf(ArrayList<String> tokenList, int i, UMLClassModel umlClassModel, MethodDetails methodDetails) {

        if (tokenList.get(i).equals("if")) {
            i++;
            if (tokenList.get(i).equals("(")) {
                while (!tokenList.get(i).equals(")")) {
                    i++;
                }
                i++;
                methodDetails.setIfCheck(true);
                if (tokenList.get(i).equals("{")) {
                    i++;
                    while (!tokenList.get(i).equals("}")) {

                        i = checkAssociation(tokenList, i, umlClassModel);
                        i = checkIf(tokenList, i, umlClassModel, methodDetails);
                        i = checkLoop(tokenList, i, umlClassModel, methodDetails);
                        i = checkInstruction(tokenList, i);
                    }
                    if (tokenList.get(i).equals("}")) {
                        i++;
                        return i;
                    } else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                }
                else
                    throw new RuntimeException("Invalid Syntax");
            }
        }
        return i;
    }

    /**
     * Check whether given token is a While or For loop and stores the information in MethodDetails object
     * @param tokenList - Array of token.
     * @param i - array counter.
     * @param umlClassModel - object to store information of a class.
     * @param methodDetails - object to store information of a method.
     * @return Int updated position of array counter.
     */
    private int checkLoop(ArrayList<String> tokenList, int i, UMLClassModel umlClassModel, MethodDetails methodDetails) {

        if (tokenList.get(i).equals("for") || tokenList.get(i).equals("while")) {
            i++;
            if (tokenList.get(i).equals("(")) {
                while (!tokenList.get(i).equals(")")) {
                    i++;
                }
                i++;
                methodDetails.setLoopCheck(true);
                if (tokenList.get(i).equals("{")) {
                    i++;
                    while (!tokenList.get(i).equals("}")) {

                        i = checkAssociation(tokenList, i, umlClassModel);
                        i = checkIf(tokenList, i, umlClassModel, methodDetails);
                        i = checkLoop(tokenList, i, umlClassModel, methodDetails);
                        i = checkInstruction(tokenList, i);
                    }
                    if (tokenList.get(i).equals("}")) {
                        i++;
                        return i;
                    } else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                }
                else
                    throw new RuntimeException("Invalid Syntax");
            }
        }
        return i;
    }
}
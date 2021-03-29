import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {

    List<UMLClassModule> umlClassModules = new ArrayList<>();

    public void parseToken(ArrayList<String> tokenList) {

        for (int i = 0; i < tokenList.size(); i++) {
            checkInstruction();
            i = checkClass(tokenList, i);
        }
        for (UMLClassModule umlClassModel: umlClassModules) {
            System.out.println(umlClassModel.getClassName()+" "+umlClassModel.getMethodNameList()+" "+umlClassModel.isIfCheck()+" "+umlClassModel.isLoopCheck()+" "+umlClassModel.getClassRelationList());
        }
    }

    private int checkClass(ArrayList<String> tokenList, int i) {

        if(tokenList.get(i).equals("class")) {
            UMLClassModule umlClassModule = new UMLClassModule();
            i++;
            umlClassModule.setClassName(tokenList.get(i));
            i++;
            if(tokenList.get(i).equals("{")) {
                i++;
                while(!tokenList.get(i).equals("}")) {
                    i = checkAggregation(tokenList, i, umlClassModule);
                    i = checkMethod(tokenList, i, umlClassModule);
                }
                if(tokenList.get(i).equals("}")) {
                    umlClassModules.add(umlClassModule);
                    return i;
                }
                else {
                    throw new RuntimeException("Invalid Syntax");
                }
            }
        }
        return i;
    }

    private int checkMethod(ArrayList<String> tokenList, int i, UMLClassModule umlClassModule) {

        if(tokenList.get(i+1).equals("(")) {
            if (tokenList.get(i+2).equals(")")) {
                if (tokenList.get(i+3).equals("{")) {
                    umlClassModule.addMethodNameList(tokenList.get(i));
                    i = i + 4;
                    i = checkAssociation(tokenList, i, umlClassModule);
                    checkInstruction();
                    i = checkIf(tokenList, i, umlClassModule);
                    i = checkLoop(tokenList, i, umlClassModule);
                    if (tokenList.get(i).equals("}")) {
                        i++;
                        return i;
                    }
                    else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                }
            }
        }
        return i;
    }

    private int checkAssociation(ArrayList<String> tokenList, int i, UMLClassModule umlClassModule) {

        //System.out.println(tokenList.get(i));
        if(tokenList.get(i).contains(".")) {
            umlClassModule.addClassRelationList(tokenList.get(i)+".Association");
            i++;
            if(tokenList.get(i).equals("(")) {
                i++;
                if (tokenList.get(i).equals(")")) {
                    i++;
                    return i;
                }
            }
        }
        return i;
    }

    private int checkAggregation(ArrayList<String> tokenList, int i, UMLClassModule umlClassModule) {

        //System.out.println(tokenList.get(i));
        if(tokenList.get(i).contains(".")) {
            umlClassModule.addClassRelationList(tokenList.get(i)+".Aggregation");
            i++;
            if(tokenList.get(i).equals("(")) {
                i++;
                if (tokenList.get(i).equals(")")) {
                    i++;
                    return i;
                }
            }
        }
        return i;
    }

    private boolean checkInstruction() {

        return false;
    }

    private int checkIf(ArrayList<String> tokenList, int i, UMLClassModule umlClassModule) {

        if(tokenList.get(i).equals("if")) {
            i++;
            if(tokenList.get(i).equals("(")) {
                while(!tokenList.get(i).equals(")")) {
                    i++;
                }
                i++;
                umlClassModule.setIfCheck(true);
                if(tokenList.get(i).equals("{")) {
                    i++;
                    i = checkAssociation(tokenList, i, umlClassModule);
                    i = checkIf(tokenList, i, umlClassModule);
                    i = checkLoop(tokenList, i, umlClassModule);
                    checkInstruction();
                    if(tokenList.get(i).equals("}")) {
                        i++;
                        return i;
                    }
                    else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                }
            }
        }
        return i;
    }

    private int checkLoop(ArrayList<String> tokenList, int i, UMLClassModule umlClassModule) {

        if(tokenList.get(i).equals("for") || tokenList.get(i).equals("while")) {
            i++;
            if(tokenList.get(i).equals("(")) {
                while (!tokenList.get(i).equals(")")) {
                    i++;
                }
                i++;
                umlClassModule.setLoopCheck(true);
                if(tokenList.get(i).equals("{")) {
                    i++;
                    i = checkAssociation(tokenList, i, umlClassModule);
                    i = checkIf(tokenList, i, umlClassModule);
                    i = checkLoop(tokenList, i, umlClassModule);
                    checkInstruction();
                    if(tokenList.get(i).equals("}")) {
                        i++;
                        return i;
                    }
                    else {
                        throw new RuntimeException("Invalid Syntax");
                    }
                }
            }
        }
        return i;
    }
}
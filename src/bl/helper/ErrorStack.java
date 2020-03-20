package bl.helper;

import bl.annotations.Tested;
import bl.annotations.ToComplete;

import java.util.ArrayList;

public class ErrorStack {

    ArrayList<String> errorStack = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////
    private static ErrorStack ourInstance = new ErrorStack();
    public static ErrorStack getInstance() {
        return ourInstance;
    }
    private ErrorStack() {}
    ///////////////////////////////////////////////////////////////////////////

    @Tested
    public void addToStack(String error){
        errorStack.add(error);
        printToConsole(error);
    }

    @Tested
    private void printToConsole(String error) {
        System.out.println("Error:" + error);
    }


}

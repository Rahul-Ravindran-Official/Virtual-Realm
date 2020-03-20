package bl.helper;

public abstract class InitializeCop {

    private boolean isInitialised;

    public void checkIfInitialised() throws Exception {
        if(!isInitialised)
            throw new Exception("Object Not Initialised !!!");
    }

    public void initialized(){
        this.isInitialised = true;
    }

}

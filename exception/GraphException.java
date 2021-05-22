package prog.lab7.exception;

public class GraphException extends RuntimeException {

    private String errorStr;

    public GraphException (String string) {
        this.errorStr = string;
        System.out.println("This is a GraphException...");
    }

    public void getMassage() {
        System.err.println("Error: " + errorStr);
    }
}
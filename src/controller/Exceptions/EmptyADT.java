package controller.Exceptions;

public class EmptyADT extends Exception{

    public EmptyADT()
    {
        super("The ADT is empty!");
    }
    public EmptyADT(String message){
        super(message);
    }
}

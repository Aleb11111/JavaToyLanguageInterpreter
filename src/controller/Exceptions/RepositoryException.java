package controller.Exceptions;

public class RepositoryException extends Exception{

    public RepositoryException(){
        super("An error occurred in the repository!");
    }

    public RepositoryException(String message) {super(message);}
}

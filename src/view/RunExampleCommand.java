package view;

import controller.Controller;

public class RunExampleCommand extends Command{

    private Controller controller;

    public RunExampleCommand(String key, String description,Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try{
            controller.fullProgramExecution();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Controller getController(){
        return this.controller;
    }

    public String toString(){
        return this.getKey() + " " + this.getDescription();
    }

}

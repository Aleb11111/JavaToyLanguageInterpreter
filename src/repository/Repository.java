package repository;

import controller.Exceptions.RepositoryException;
import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepoInterface{

    private List<ProgramState> programStatesQueue;
    private final String logFilePath;

    public Repository(String filePath){
        programStatesQueue = new ArrayList<>();
        this.logFilePath = filePath;
        try{
            clearFile();
        }
        catch (RepositoryException ignored){}
    }

    @Override
    public void clearFile() throws RepositoryException {
        try
        {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, false)));
            logFile.append("");
            logFile.close();
        }
        catch (IOException exception){
            throw new RepositoryException("Error opening the file!\n");
        }
    }

    @Override
    public void addProgramState(ProgramState newProgramState)
    {
        programStatesQueue.add(newProgramState);
    }

    @Override
    public List<ProgramState> getProgramList() {
        return this.programStatesQueue;
    }

    @Override
    public ProgramState getByIndex(int index) {
        return this.getProgramList().get(index);
    }

    @Override
    public void logPrgStateExec(ProgramState program) throws RepositoryException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(program.toString());
            logFile.close();
        }
        catch (IOException exception){
            throw new RepositoryException("Error opening the file!\n");
        }
    }

    @Override
    public void setProgramList(List<ProgramState> prg) {
        this.programStatesQueue = prg;
    }


}

package repository;


import controller.Exceptions.RepositoryException;
import model.ProgramState;
import java.util.List;

public interface RepoInterface {

    void clearFile() throws RepositoryException;

    void addProgramState(ProgramState newProgramstate);
    List<ProgramState> getProgramList();

    ProgramState getByIndex(int index);

    void logPrgStateExec(ProgramState program) throws RepositoryException;

    void setProgramList(List<ProgramState> prg);

}

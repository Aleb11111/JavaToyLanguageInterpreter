package model.statement.procedures;

import javafx.util.Pair;
import model.ProgramState;
import model.statement.StatementInterface;
import model.type.TypeInterface;
import utils.*;

import java.util.List;

public class ProcedureDeclarationStatement implements StatementInterface {

    String procName;
    MyList<String> procVariables;

    StatementInterface statement;

    public ProcedureDeclarationStatement(String procName, MyList<String> procVariables, StatementInterface statement) {
        this.procName = procName;
        this.procVariables = procVariables;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {


        ProcTable<String, Pair<MyList<String>,StatementInterface>> globalTable = state.getGlobalTable();
        globalTable.insert(procName,new Pair<>(procVariables,statement));
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "ProcedureDeclarationStatement{" +
                "procName='" + procName + '\'' +
                ", procVariables=" + procVariables +
                ", statement=" + statement +
                '}';
    }
}

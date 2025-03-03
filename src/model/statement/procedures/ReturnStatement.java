package model.statement.procedures;

import model.ProgramState;
import model.statement.StatementInterface;
import model.type.TypeInterface;
import utils.DictionaryInterface;
import utils.SemaphoreInterface;

import javax.swing.plaf.nimbus.State;

public class ReturnStatement implements StatementInterface {

    public ReturnStatement() {
    }

    @Override
    public String toString() {
        return "ReturnStatement{}";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        state.getSymbolTableStack().pop();
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        return null;
    }
}

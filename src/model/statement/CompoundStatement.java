package model.statement;

import model.ProgramState;
import model.type.TypeInterface;
import utils.DictionaryInterface;
import utils.StackInterface;

public class CompoundStatement implements StatementInterface {

    private StatementInterface firstStatement;
    private StatementInterface secondStatement;


    public CompoundStatement(StatementInterface firstStatement, StatementInterface secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }




    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> executionStack = state.getExeStack();
        executionStack.push(secondStatement);
        executionStack.push(firstStatement);
        return null;
    }

    @Override
    public String toString()
    {
        return "("+firstStatement.toString()+";"+secondStatement.toString()+")";
    }

    @Override
    public StatementInterface deepCopy() {
        return new CompoundStatement(firstStatement,secondStatement);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        return this.secondStatement.typeCheck(this.firstStatement.typeCheck(typeEnv));

    }

}

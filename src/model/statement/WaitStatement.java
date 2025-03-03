package model.statement;

import model.ProgramState;
import model.expresion.ValueExpression;
import model.type.TypeInterface;
import model.value.IntValue;
import utils.DictionaryInterface;
import utils.StackInterface;

public class WaitStatement implements StatementInterface{

    Integer seconds;

    public WaitStatement(Integer seconds) {
        this.seconds = seconds;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        StackInterface<StatementInterface> exeStack = state.getExeStack();
        if(seconds > 0)
            exeStack.push(new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(seconds))),
                    new WaitStatement(new IntValue(seconds - 1).getValue())));
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
        return "wait(" + seconds+") ";
    }
}

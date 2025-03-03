package model.statement;

import model.ProgramState;
import model.type.TypeInterface;
import utils.DictionaryInterface;

public class SleepStatement implements StatementInterface{

    Integer seconds;

    public SleepStatement(Integer number) {
        this.seconds = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        if(seconds == 0)
            return null;
        else
        {
            state.getExeStack().push(new SleepStatement(seconds-1));
        }
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
        return "Sleep(" + seconds +
                ')';
    }
}

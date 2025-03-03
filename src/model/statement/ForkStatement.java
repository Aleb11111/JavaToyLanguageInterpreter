package model.statement;

import javafx.util.Pair;
import model.ProgramState;
import model.type.TypeInterface;
import model.value.ValueInterface;
import utils.*;

public class ForkStatement implements StatementInterface{

    StatementInterface statement;

    public ForkStatement(StatementInterface statement)
    {
        this.statement = statement;
    }



    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> newStack = new MyStack<>();
        StackInterface<DictionaryInterface<String, ValueInterface>> symbolTable = state.getSymbolTableStack();
        ProcTable<String,Pair<MyList<String>,StatementInterface>> global = state.getGlobalTable();


        MyStack<DictionaryInterface<String, ValueInterface>> newDictionary = new MyStack<>();

        for(DictionaryInterface<String,ValueInterface> dict : symbolTable.reverse())
            newDictionary.push(dict.copy());

        LatchTableInterface<Integer,Integer> latch = state.getLatchTable();
        SemaphoreInterface<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>> newSemTable = state.getSemaphoreTable();

        return new ProgramState(newStack,newDictionary,state.getOut(),state.getFileTable(),state.getHeap(),
                ProgramState.generateNewID(),this.statement,newSemTable,latch,global);
    }

    @Override
    public StatementInterface deepCopy() {
        return new ForkStatement(this.statement);

    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {

        this.statement.typeCheck(typeEnv.copy());
        return typeEnv;
    }

    @Override
    public String toString(){
        return "fork( " + this.statement + " );";
    }

}

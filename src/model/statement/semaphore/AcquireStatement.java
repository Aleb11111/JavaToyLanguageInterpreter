package model.statement.semaphore;

import javafx.util.Pair;
import model.ProgramState;
import model.statement.StatementInterface;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;
import utils.*;

public class AcquireStatement implements StatementInterface {

    String variableName;

    public AcquireStatement(String name)
    {
        this.variableName = name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        SemaphoreInterface<Integer, Pair<Integer, Pair<MyList<Integer>, Integer>>> semTable = state.getSemaphoreTable();

        if(symbolTable.containsKey(this.variableName))
        {
            TypeInterface type = (symbolTable.getValue(variableName).getType());
            if(type instanceof IntType)
            {
                IntValue address = (IntValue) symbolTable.getValue(this.variableName);
                if(semTable.containsKey(address.getValue()))
                {
                    Pair<Integer,Pair<MyList<Integer>,Integer>> pair = semTable.getValue(address.getValue());
                    ListInterface<Integer> list = pair.getValue().getKey();
                    int length = list.getContent().size();
                    if((pair.getKey() - pair.getValue().getValue()) > length)
                    {
                        if(!list.search(state.getID()))
                            list.add(state.getID());
                    }
                    else
                    {
                        StackInterface<StatementInterface> exeStack = state.getExeStack();
                        exeStack.push(new AcquireStatement(variableName));
                    }


                }
                else
                {
                    throw new Exception("Address not in semaphore table");
                }
            }
            else
            {
                throw new Exception("Name not an IntValue type.");
            }
        }
        else
        {
            throw new Exception("Name not defined in symbol table.");
        }


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


    @Override
    public String toString() {
        return "AcquireStatement{" +
                "variableName='" + variableName + '\'' +
                '}';
    }
}

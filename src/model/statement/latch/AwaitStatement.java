package model.statement.latch;

import javafx.util.Pair;
import model.ProgramState;
import model.statement.StatementInterface;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;
import utils.*;

public class AwaitStatement implements StatementInterface {

    String varName;

    public AwaitStatement(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        SemaphoreInterface<Integer, Pair<Integer, Pair<MyList<Integer>, Integer>>> semTable = state.getSemaphoreTable();
        LatchTableInterface<Integer,Integer> latch = state.getLatchTable();


        if(symbolTable.containsKey(this.varName)) {
            TypeInterface type = (symbolTable.getValue(varName).getType());

            if (type instanceof IntType)
            {
                IntValue address = (IntValue) symbolTable.getValue(varName);
                if(latch.containsKey(address.getValue()))
                {
                    Integer value = latch.getValue(address.getValue());
                    if(value == 0)
                        return null;
                    else
                    {
                        StackInterface<StatementInterface> exeStack = state.getExeStack();
                        exeStack.push(new AwaitStatement(varName));
                    }
                }
                else
                {
                    throw new Exception("Address not in latch table");
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
        TypeInterface typeVar = typeEnv.getValue(varName);
        if(typeVar.equals(new IntType()))
        {
            return typeEnv;
        }
        else
        {
            throw new Exception("Await: var name is not int");
        }
    }

    @Override
    public String toString() {
        return "Await(" + varName + ')';
    }
}

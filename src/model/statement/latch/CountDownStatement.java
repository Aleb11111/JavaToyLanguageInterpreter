package model.statement.latch;

import javafx.util.Pair;
import model.ProgramState;
import model.statement.StatementInterface;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;
import utils.*;

public class CountDownStatement implements StatementInterface {

    String varName;

    public CountDownStatement(String varName) {
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
                    if(value> 0)
                    {
                        latch.update(address.getValue(),value-1);
                        ListInterface<ValueInterface> outList = state.getOut();
                        outList.add(new StringValue("State id: "+state.getID()+";"));
                    }
                    else
                    {
                        ListInterface<ValueInterface> outList = state.getOut();
                        outList.add(new StringValue("State id: "+state.getID()+";"));
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
            return typeEnv;
        else
            throw new Exception("CountDown: var name is not Int");
    }

    @Override
    public String toString() {
        return "countDown("+ varName + ") ";
    }
}

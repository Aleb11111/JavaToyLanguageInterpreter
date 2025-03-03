package model.statement.semaphore;

import controller.Exceptions.InvalidTypeException;
import javafx.util.Pair;
import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.statement.StatementInterface;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;
import utils.*;

public class NewSemaphoreStatement implements StatementInterface {

    String variableName;
    ExpressionInterface expression1;
    ExpressionInterface expression2;


    public NewSemaphoreStatement(String varName, ExpressionInterface ex1, ExpressionInterface ex2)
    {
        this.variableName = varName;
        this.expression1 = ex1;
        this.expression2 = ex2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        SemaphoreInterface<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>> semaphore = state.getSemaphoreTable();

        if(symbolTable.containsKey(this.variableName))
        {
            TypeInterface id = (symbolTable.getValue(variableName).getType());
            if(id instanceof IntType)
            {
                ValueInterface value1 = expression1.evaluate(symbolTable,heap);
                ValueInterface value2 = expression2.evaluate(symbolTable,heap);

                if(value1.getType().equals(new IntType()) && value2.getType().equals(new IntType()))
                {
                    IntValue val1 = (IntValue) value1;
                    IntValue val2 = (IntValue) value2;

                    int newAddress = state.getSemaphoreTable().getLastAddress();

                    Pair<Integer,Pair<MyList<Integer>,Integer>> pair = new Pair<>(val1.getValue(),new Pair<>(new MyList<>(),val2.getValue()));
                    semaphore.insert(newAddress,pair);

                    IntValue address = new IntValue(newAddress);
                    symbolTable.update(variableName,address);
                }
                else
                {
                    throw new Exception("Somthing went wrong");
                }

            }
            else {
                throw new Exception("Somthing went wrong");
            }



        }
        else
        {
            throw new Exception("Somthing went wrong");
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {

        TypeInterface value1 = expression1.typeCheck(typeEnv);
        TypeInterface value2 = expression2.typeCheck(typeEnv);

        if(value1.equals(new IntType()) && value2.equals(new IntType()))
        {
            return typeEnv;
        }
        else
        {
            throw new InvalidTypeException("newSemaphoreStatement: the expressions values are not INT!\n");
        }

    }

    @Override
    public String toString() {
        return "new Semaphore("+variableName+expression1.toString()+", "+expression2.toString()+") ";
    }
}

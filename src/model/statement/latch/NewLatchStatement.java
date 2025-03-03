package model.statement.latch;

import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.statement.StatementInterface;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;
import utils.LatchTableInterface;

public class NewLatchStatement implements StatementInterface {

    String varname;
    ExpressionInterface expression;

    public NewLatchStatement(String varname, ExpressionInterface expression) {
        this.varname = varname;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        LatchTableInterface<Integer,Integer> latch = state.getLatchTable();

        if(symbolTable.containsKey(varname))
        {
            TypeInterface type = symbolTable.getValue(varname).getType();
            if(type instanceof IntType)
            {
                ValueInterface expressionValue = expression.evaluate(symbolTable,heap);
                if(expressionValue.getType().equals(new IntType()))
                {
                    IntValue value = (IntValue) expressionValue;
                    int newAddress = latch.getLastAddress();
                    latch.insert(newAddress, value.getValue());

                    IntValue address = new IntValue(newAddress);
                    symbolTable.update(varname,address);
                }
                else
                {
                    throw new Exception("Expression is not int type.");
                }
            }
            else
                throw new Exception("Variable type is not Int!");
        }
        else
        {
            throw  new Exception("Name not defined in symbol table.");
        }
        return null;

    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {

        TypeInterface typeVar = typeEnv.getValue(varname);
        TypeInterface typeExpresion = expression.typeCheck(typeEnv);

        if((typeVar.equals(new IntType()) && typeExpresion.equals(new IntType())))
        {
            return typeEnv;
        }
        else
        {
            throw new Exception("New Latch: var name or expression are not both int");
        }


    }

    @Override
    public String toString() {
        return "NewLatch:" +
                "varname='" + varname + '\'' +
                ", expression=" + expression +
                '}';
    }
}

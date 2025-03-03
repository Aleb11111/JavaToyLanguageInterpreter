package model.statement;

import controller.Exceptions.InvalidTypeException;
import controller.Exceptions.UndefinedVariableException;
import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.type.TypeInterface;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public class AssignStatement implements StatementInterface{

    private String id;
    private ExpressionInterface expression;

    public AssignStatement(String id, ExpressionInterface expression)
    {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return id+"="+expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        if(symbolTable.containsKey(id))
        {
            ValueInterface newExpressionValue = expression.evaluate(symbolTable,heap);
            symbolTable.update(id,newExpressionValue) ;
        }
        else
        {
            throw new UndefinedVariableException("Variable"+id+"is not defined!");
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new AssignStatement(id,expression);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        TypeInterface typeVariable = typeEnv.getValue(this.id);
        TypeInterface typeExpression = expression.typeCheck(typeEnv);
        if(typeVariable.equals(typeExpression)){
            return typeEnv;
        }
        else throw new InvalidTypeException("Assignment: right hand side and left hand side have different types!\n");

    }

}

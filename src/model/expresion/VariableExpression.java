package model.expresion;

import controller.Exceptions.InvalidTypeException;
import controller.Exceptions.UndefinedVariableException;
import model.type.TypeInterface;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public class VariableExpression implements ExpressionInterface{

    private String id;

    public VariableExpression(String id)
    {
        this.id = id;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        if (!symbolTable.containsKey(id)) {
            throw new UndefinedVariableException(id + "is not defined as a variable!");
        }
        return symbolTable.getValue(id);
    }

    public String toString(){
        return id;
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws InvalidTypeException {
        return typeEnv.getValue(this.id);
    }

}

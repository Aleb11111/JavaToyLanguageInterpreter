package model.expresion;

import controller.Exceptions.InvalidTypeException;
import model.type.TypeInterface;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public class ValueExpression implements ExpressionInterface {

    private ValueInterface value;

    public ValueExpression(ValueInterface value) {
        this.value = value;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        return value;
    }

    public String toString(){
        return value.toString();
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws InvalidTypeException {
        return this.value.getType();
    }
}

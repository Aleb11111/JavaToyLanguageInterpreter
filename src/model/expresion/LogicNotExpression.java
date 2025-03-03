package model.expresion;

import controller.Exceptions.InvalidTypeException;
import model.type.BoolType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public class LogicNotExpression implements ExpressionInterface{

    ExpressionInterface expressionToEvaluate;

    public LogicNotExpression(ExpressionInterface expressionToEvaluate) {
        this.expressionToEvaluate = expressionToEvaluate;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface result = expressionToEvaluate.evaluate(symbolTable,heap);
        if(result instanceof BoolValue boolResult)
            return new BoolValue(!boolResult.getValue());
        else
            throw new Exception("Can't apply NOT operation to non-boolean variable.");
    }


    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws InvalidTypeException {
        if (expressionToEvaluate.typeCheck(typeEnv).equals(new BoolType()))
            return new BoolType();
        throw new InvalidTypeException("Operand is not a boolean.");
    }

    @Override
    public String toString() {
        return "LogicNotExpression: " + expressionToEvaluate +
                ';';
    }
}

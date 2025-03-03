package model.expresion;

import controller.Exceptions.InvalidTypeException;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

import java.beans.Expression;

public class MULExpression implements ExpressionInterface {

    ExpressionInterface left;
    ExpressionInterface right;

    public MULExpression(ExpressionInterface left, ExpressionInterface right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface result1 = left.evaluate(symbolTable,heap);
        ValueInterface result2 = right.evaluate(symbolTable,heap);

        if(result1.getType().equals(new IntType()))
        {
            IntValue intResult1 = (IntValue) result1;
            if(result2.getType().equals(new IntType()))
            {
                IntValue intResult2 = (IntValue) result2;
                int parenthesis1 = intResult1.getValue() * intResult2.getValue();
                int parenthesis2 = intResult1.getValue() + intResult2.getValue();
                int result = parenthesis1 - parenthesis2;
                return new IntValue(result);
            }
            else
                throw new Exception("Second operand is not an integer");
        }
        else
            throw new Exception("First operand is not an integer");


    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws InvalidTypeException {
        return new IntType();
    }

    @Override
    public String toString() {
        return "MULExpression(" + left + "," + right + ')';
    }
}

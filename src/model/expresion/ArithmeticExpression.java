package model.expresion;

import controller.Exceptions.DivisionByZeroException;
import controller.Exceptions.InvalidOperatorException;
import controller.Exceptions.InvalidTypeException;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public class ArithmeticExpression implements ExpressionInterface {

    private final ExpressionInterface firstExpression;
    private final ExpressionInterface secondExpression;
    private final char operator;

    public ArithmeticExpression( char operator, ExpressionInterface firstExpression, ExpressionInterface secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }
    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface firstValue, secondValue;
        firstValue = firstExpression.evaluate(symbolTable,heap);
        secondValue = secondExpression.evaluate(symbolTable,heap);
        int firstInt = ((IntValue) firstValue).getValue();
        int secondInt = ((IntValue) secondValue).getValue();
        if (operator == '+') {
            return new IntValue(firstInt + secondInt);
        }
        if (operator == '-') {
            return new IntValue(firstInt - secondInt);
        }
        if (operator == '*') {
            return new IntValue(firstInt * secondInt);
        }
        if (operator == '/') {
            if (secondInt == 0)
                throw new DivisionByZeroException("Division by zero!");
            else
                return new IntValue(firstInt / secondInt);
        } else {
            throw new InvalidOperatorException();
        }
    }


    @Override
    public String toString() {
        return firstExpression.toString() + operator + secondExpression.toString();
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws InvalidTypeException {
        TypeInterface type1;
        TypeInterface type2;
        type1 = this.firstExpression.typeCheck(typeEnv);
        type2 = this.secondExpression.typeCheck(typeEnv);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            } else
                throw new InvalidTypeException("Invalid type for the second expression!\n");
        } else
            throw new InvalidTypeException("Invalid type for the first expression!\n");

    }


}

package model.expresion;

import controller.Exceptions.InvalidOperatorException;
import controller.Exceptions.InvalidTypeException;
import model.type.BoolType;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public class RelationalExpression implements ExpressionInterface{

    private final ExpressionInterface firstExpression;
    private final ExpressionInterface secondExpression;
    private final String operator;

    public RelationalExpression(ExpressionInterface firstExpression, ExpressionInterface secondExpression, String operator){
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }


    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface firstValue, secondValue;

        firstValue = this.firstExpression.evaluate(symbolTable,heap);
        secondValue = this.secondExpression.evaluate(symbolTable,heap);

        int firstInt = ((IntValue)firstValue).getValue();
        int secondInt = ((IntValue)secondValue).getValue();

        if(this.operator.equals("<")){
            return new BoolValue(firstInt < secondInt);
        }

        if(this.operator.equals("<=")){
            return new BoolValue(firstInt <= secondInt);
        }

        if(this.operator.equals(">")){
            return new BoolValue(firstInt > secondInt);
        }

        if(this.operator.equals(">=")){
            return new BoolValue(firstInt >= secondInt);
        }

        if(this.operator.equals("==")){
            return new BoolValue(firstInt == secondInt);
        }

        if(this.operator.equals("!=")){
            return new BoolValue(firstInt != secondInt);
        }
        else {

            throw new InvalidOperatorException();
        }
    }

    public String toString(){
        String representation = "";
        representation += (firstExpression.toString());
        representation += (" " + this.operator + " ");
        representation += (this.secondExpression.toString());
        return representation;
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws InvalidTypeException {
        TypeInterface type1, type2;
        type1 = this.firstExpression.typeCheck(typeEnv);
        type2 = this.secondExpression.typeCheck(typeEnv);

        if(!type1.equals(new IntType())){
            throw new InvalidTypeException("First expression is not an integer!\n");
        }

        if(!type2.equals(new IntType())){
            throw new InvalidTypeException("Second expression is not an integer!\n");
        }

        return new BoolType();
    }


}

package model.statement;

import controller.Exceptions.InvalidTypeException;
import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.type.BoolType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;
import utils.StackInterface;

public class IfStatement implements StatementInterface{

    private ExpressionInterface expression;
    private StatementInterface trueStatement;
    private StatementInterface falseStatement;


    public IfStatement(ExpressionInterface expression, StatementInterface trueStatement, StatementInterface falseStatement) {
        this.expression = expression;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> executionStack = state.getExeStack();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface conditionalExpressionVal = expression.evaluate(symbolTable,heap);
        if(((BoolValue) conditionalExpressionVal).getValue())
        {
            executionStack.push(trueStatement);
        }
        else
        {
            executionStack.push(falseStatement);
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new IfStatement(expression, trueStatement, falseStatement);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        TypeInterface typeExpression = this.expression.typeCheck(typeEnv);
        if(typeExpression.equals(new BoolType())){
            this.trueStatement.typeCheck(typeEnv.copy());
            this.falseStatement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new InvalidTypeException("The condition of IF has not the type bool!\n");

    }

    @Override
    public String toString() {
        return "IF(" + expression.toString() + ")THEN(" + trueStatement.toString() + ")ELSE(" + falseStatement.toString() + "))";
    }

}

package model.statement;

import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.expresion.LogicNotExpression;
import model.expresion.LogicalExpression;
import model.statement.CompoundStatement;
import model.statement.StatementInterface;
import model.statement.WhileStatement;
import model.type.BoolType;
import model.type.TypeInterface;
import utils.DictionaryInterface;
import utils.StackInterface;

public class RepeatUntilStatement implements StatementInterface {

    ExpressionInterface expression;
    StatementInterface bodyOfLoop;

    public RepeatUntilStatement(ExpressionInterface expression, StatementInterface bodyOfLoop) {
        this.expression = expression;
        this.bodyOfLoop = bodyOfLoop;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        StatementInterface flippedWhile = new CompoundStatement(bodyOfLoop,new WhileStatement(new LogicNotExpression(expression), bodyOfLoop));

        StackInterface<StatementInterface> exeStack = state.getExeStack();
        exeStack.push(flippedWhile);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        if(expression.typeCheck(typeEnv).equals(new BoolType()))
            return bodyOfLoop.typeCheck(typeEnv);
        else
            throw new Exception("Expression condition expected to be of type bool.");
    }

    @Override
    public String toString() {
        return "RepeatUntilStatement("+ expression +
                "){" + bodyOfLoop +
                '}';
    }
}

package model.statement;

import controller.Exceptions.InvalidTypeException;
import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.expresion.RelationalExpression;
import model.type.TypeInterface;
import utils.DictionaryInterface;

public class SwitchStatement implements StatementInterface{

    ExpressionInterface OGexpression;
    ExpressionInterface expression1;
    StatementInterface statement1;


    ExpressionInterface expression2;
    StatementInterface statement2;

    StatementInterface defaultStatement;

    public SwitchStatement(ExpressionInterface OGexpression, ExpressionInterface expression1, StatementInterface statement1, ExpressionInterface expression2, StatementInterface statement2, StatementInterface defaultStatement)
    {
        this.OGexpression = OGexpression;
        this.expression1 = expression1;
        this.statement1 = statement1;
        this.expression2 = expression2;
        this.statement2 = statement2;
        this.defaultStatement = defaultStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        StatementInterface statement = new IfStatement(new RelationalExpression(OGexpression,expression1,"=="),
                statement1,new IfStatement(new RelationalExpression(OGexpression,expression2,"=="),statement2,defaultStatement));
        state.getExeStack().push(statement);

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {

        TypeInterface typeOG = OGexpression.typeCheck(typeEnv);
        TypeInterface type1 = expression1.typeCheck(typeEnv);
        TypeInterface type2 = expression2.typeCheck(typeEnv);

        if(typeOG.equals(type1) && typeOG.equals(type2))
        {
            statement1.typeCheck(typeEnv.copy());
            statement2.typeCheck(typeEnv.copy());
            defaultStatement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else
            throw new InvalidTypeException("Not the same type!");

    }


    @Override
    public String toString() {
        return "SwitchStatement{" +
                "switch=" + OGexpression +
                ", case1=" + expression1 +
                ", statement1=" + statement1 +
                ", case2=" + expression2 +
                ", statement2=" + statement2 +
                ", default=" + defaultStatement +
                '}';
    }
}

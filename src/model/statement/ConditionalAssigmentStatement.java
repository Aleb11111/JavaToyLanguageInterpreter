package model.statement;

import com.sun.jdi.InvalidTypeException;
import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.type.BoolType;
import model.type.TypeInterface;
import utils.DictionaryInterface;
import utils.StackInterface;

public class ConditionalAssigmentStatement implements StatementInterface{

    String varName;
    ExpressionInterface expression1;
    ExpressionInterface expression2;
    ExpressionInterface expression3;


    public ConditionalAssigmentStatement(String varn,ExpressionInterface expression1,ExpressionInterface expression2,ExpressionInterface expression3)
    {
        this.varName = varn;
        this.expression1=expression1;
        this.expression2=expression2;
        this.expression3=expression3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        StackInterface<StatementInterface> stack = state.getExeStack();
        stack.push(new IfStatement(expression1, new AssignStatement(varName, expression2), new AssignStatement(varName, expression3)));
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {

        TypeInterface varn = typeEnv.getValue(this.varName);
        TypeInterface valExpr1 = this.expression1.typeCheck(typeEnv);
        TypeInterface valExpr2 = this.expression2.typeCheck(typeEnv);
        TypeInterface valExpr3 = this.expression3.typeCheck(typeEnv);

        if(valExpr1 instanceof BoolType)
        {
            if(varn.equals(valExpr2) && varn.equals(valExpr3))
                return typeEnv;
            else
            {
                throw new InvalidTypeException("The variable has a different type than the expressions!");
            }
        }
        else
        {
            throw new InvalidTypeException("The evaluated expression does not have Bool type!");
        }


    }

    @Override
    public String toString() {
        return ""+ varName + '=' +"( "+ expression1.toString() +
                ")?" + expression2.toString() +
                ":" + expression3.toString() +";";
    }
}

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

public class WhileStatement implements StatementInterface{

    private ExpressionInterface whileCondition;
    private  StatementInterface whileBody;

    public WhileStatement(ExpressionInterface whileCondition, StatementInterface whileBody){
        this.whileCondition = whileCondition;
        this.whileBody = whileBody;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {

        DictionaryInterface<String, ValueInterface> symTable = state.getSymbolTableStack().top();
        StackInterface<StatementInterface> executionStack = state.getExeStack();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();

        ValueInterface evaluatedExpression = this.whileCondition.evaluate(symTable, heap);
        if(((BoolValue) evaluatedExpression).getValue()){
            executionStack.push(this);
            executionStack.push(this.whileBody);
        }


        return null;
    }

    @Override
    public String toString(){
        return "while("+whileCondition.toString()+"){ "+whileBody.toString()+" }";
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStatement(this.whileCondition, this.whileBody);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        TypeInterface typeExpression = this.whileCondition.typeCheck(typeEnv);
        if(typeExpression.equals(new BoolType())){
            this.whileBody.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new InvalidTypeException("WhileStatement: Conditional expression is not boolean!\n");

    }
}

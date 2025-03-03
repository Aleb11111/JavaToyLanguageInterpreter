package model.statement;

import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.type.TypeInterface;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;
import utils.ListInterface;

public class PrintStatement implements StatementInterface {

    private ExpressionInterface expression;

    public PrintStatement(ExpressionInterface expression)
    {
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        ListInterface<ValueInterface> output = state.getOut();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        output.add(expression.evaluate(symbolTable,heap));
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new PrintStatement(expression);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        this.expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){
        return "print("+expression.toString()+")";
    }

}

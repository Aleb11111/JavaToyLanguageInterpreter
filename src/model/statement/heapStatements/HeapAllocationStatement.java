package model.statement.heapStatements;

import controller.Exceptions.InvalidTypeException;
import controller.Exceptions.UndefinedVariableException;
import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.statement.StatementInterface;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public class HeapAllocationStatement implements StatementInterface {

    private ExpressionInterface expression;
    private String variableName;

    public HeapAllocationStatement(String variableName, ExpressionInterface expression)
    {
        this.expression = expression;
        this.variableName = variableName;
    }


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symTable = state.getSymbolTableStack().top();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();

        if(!symTable.containsKey(this.variableName))
        {
            throw new UndefinedVariableException(this.variableName+"is not defined in the symbol table!\n");
        }

        ValueInterface expValue = this.expression.evaluate(symTable,heap);
        ReferenceValue refVal = ((ReferenceValue) symTable.getValue(this.variableName));
        TypeInterface innerType = ((ReferenceType) refVal.getType()).getInner();

        int copyAddress = heap.getFirstAvailablePosition();
        heap.insert(copyAddress,expValue);
        symTable.update(this.variableName, new ReferenceValue(copyAddress, innerType));

        return null;

    }

    @Override
    public String toString(){
        String representation = "";
        representation += ("new(" + this.variableName + ", " + this.expression.toString() + ");");
        return representation;
    }

    @Override
    public StatementInterface deepCopy() {
        return new HeapAllocationStatement(this.variableName, this.expression);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        TypeInterface typeVariable = typeEnv.getValue(this.variableName);
        TypeInterface typeExpression = expression.typeCheck(typeEnv);
        if(typeVariable.equals(new ReferenceType(typeExpression))){
            return typeEnv;
        }
        else
            throw new InvalidTypeException("HeapAllocationStatement: right hand side and left hand side have different types!\n");

    }
}

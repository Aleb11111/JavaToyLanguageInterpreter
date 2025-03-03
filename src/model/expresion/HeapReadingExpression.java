package model.expresion;

import controller.Exceptions.InvalidTypeException;
import controller.Exceptions.UndefinedVariableException;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public class HeapReadingExpression implements ExpressionInterface{

    private ExpressionInterface expression;

    public HeapReadingExpression(ExpressionInterface expression)
    {
        this.expression= expression;
    }

    @Override
    public String toString(){
        return "readHeap("+expression.toString()+")";
    }

    @Override
    public TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws InvalidTypeException {
        TypeInterface type = this.expression.typeCheck(typeEnv);
        if(type instanceof ReferenceType){
            ReferenceType refType = (ReferenceType) type;
            return refType.getInner();
        }
        else
            throw new InvalidTypeException("the rH argument is not a reference type");
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface value = expression.evaluate(symbolTable,heap);

        int address = ((ReferenceValue) value).getHeapAddress();
        if(!heap.containsKey(address))
        {
            throw new UndefinedVariableException("The given address is not defined in the heap.\n");
        }
        return heap.getValue(address);
    }
}

package model.expresion;


import controller.Exceptions.InvalidTypeException;
import model.type.TypeInterface;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

public interface ExpressionInterface {
    ValueInterface evaluate(DictionaryInterface<String, ValueInterface> symbolTable, HeapInterface<Integer, ValueInterface> heap) throws Exception;
    String toString();

    TypeInterface typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws InvalidTypeException;

}

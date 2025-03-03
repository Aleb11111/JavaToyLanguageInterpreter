package model.statement;

import controller.Exceptions.ExistingVariableException;
import controller.Exceptions.InvalidTypeException;
import model.ProgramState;
import model.type.*;
import model.value.*;
import utils.DictionaryInterface;

public class VariableDeclarationStatement implements StatementInterface {

    private String name;
    private TypeInterface type;

    public VariableDeclarationStatement(String name, TypeInterface type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        if(symbolTable.containsKey(name)){
            throw new ExistingVariableException("Variable " + name + "is already declared!");
        }
        else if(type.equals(new IntType())){
            symbolTable.insert(name, new IntValue());
        }
        else if(type.equals(new BoolType())){
            symbolTable.insert(name, new BoolValue());
        }
        else if(type.equals(new StringType())){
            symbolTable.insert(name, new StringValue());
        }
        else if(type instanceof ReferenceType refType){
            symbolTable.insert(name, new ReferenceValue(refType.getInner()));}
        else
        {
            throw new InvalidTypeException("Invalid type!");
        }
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new VariableDeclarationStatement(name, type);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        typeEnv.insert(this.name, this.type);
        return typeEnv;
    }

    @Override
    public String toString(){
        return type.toString() + " " + name;
    }

}

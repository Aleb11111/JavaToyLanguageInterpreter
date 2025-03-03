package model.statement.fileStatements;

import controller.Exceptions.ExistingVariableException;
import controller.Exceptions.InvalidTypeException;
import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.statement.StatementInterface;
import model.type.StringType;
import model.type.TypeInterface;
import model.value.StringValue;
import model.value.ValueInterface;
import utils.DictionaryInterface;
import utils.HeapInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements StatementInterface {

    private ExpressionInterface filePath;

    public OpenReadFileStatement(ExpressionInterface filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ValueInterface filePathValue = this.filePath.evaluate(symbolTable,heap);

        if(fileTable.containsKey((StringValue)filePathValue))
        {
            throw new ExistingVariableException("The filepath is already a key in FileTable!\\n");
        }
        try
        {
            BufferedReader fileBuffer = new BufferedReader(new FileReader(((StringValue)filePathValue).getValue()));
            fileTable.insert((StringValue) filePathValue, fileBuffer);
        }
        catch (FileNotFoundException ex)
        {
            throw new Exception(ex.getMessage());
        }
        return null;
    }

    public String toString(){
        return "openRead(" + this.filePath + ");\n";
    }

    @Override
    public StatementInterface deepCopy() {
        return new OpenReadFileStatement(this.filePath);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        TypeInterface typeExpression = this.filePath.typeCheck(typeEnv);
        if(!typeExpression.equals(new StringType())){
            throw new InvalidTypeException("OpenReadFileStatement: file path should be a stringValue!\n");
        }
        return typeEnv;
    }
}

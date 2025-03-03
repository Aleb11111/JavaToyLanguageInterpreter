package model;
import controller.Exceptions.EmptyADT;
import javafx.util.Pair;
import model.statement.StatementInterface;
import model.value.StringValue;
import model.value.ValueInterface;
import utils.*;

import java.io.BufferedReader;

public class ProgramState {

    StackInterface<StatementInterface> exeStack;
    StackInterface<DictionaryInterface<String, ValueInterface>> symbolTableStack;

    ListInterface<ValueInterface> output;

    HeapInterface<Integer, ValueInterface> heap;

    ProcTable<String,Pair<MyList<String>,StatementInterface>> globalTable;

LatchTableInterface<Integer, Integer> latchTable;

    SemaphoreInterface<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>> semaphoreTable;
    private int id;
    private static int nextID = 0;


    public static synchronized int generateNewID(){
        return nextID++;
    }

    private DictionaryInterface<StringValue, BufferedReader> fileTable;

    StatementInterface originalProgram;

    public ProgramState(StackInterface<StatementInterface> executionStack,
                        StackInterface<DictionaryInterface<String, ValueInterface>> symbolTableStack,
                        ListInterface<ValueInterface> output,
                        DictionaryInterface<StringValue, BufferedReader> fileTable,
                        HeapInterface<Integer, ValueInterface> heap, int id,
                        StatementInterface program,
                        SemaphoreInterface<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>> semaphoreTable,
                        LatchTableInterface<Integer,Integer> latchTable,
                        ProcTable<String,Pair<MyList<String>,StatementInterface>> proc) {
        this.exeStack = executionStack;
        this.symbolTableStack = symbolTableStack;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = id;
        this.originalProgram = program.deepCopy();
//        this.executionStack.push(originalProgram);
        this.exeStack.push(program);
        this.semaphoreTable = semaphoreTable;
        this.latchTable = latchTable;
        this.globalTable = proc;
    }

    public ProcTable<String, Pair<MyList<String>, StatementInterface>> getGlobalTable() {
        return globalTable;
    }

    public LatchTableInterface<Integer, Integer> getLatchTable() {
        return latchTable;
    }

    public SemaphoreInterface<Integer, Pair<Integer, Pair<MyList<Integer>, Integer>>> getSemaphoreTable() {
        return semaphoreTable;
    }
    public StackInterface<StatementInterface> getExeStack()
    {
        return exeStack;
    }

    public void setExeStack(StackInterface<StatementInterface> exeStack)
    {
        this.exeStack = exeStack;
    }

    public StackInterface<DictionaryInterface<String, ValueInterface>> getSymbolTableStack()
    {
        return this.symbolTableStack;
    }

    public void setSymbolTableStack(StackInterface<DictionaryInterface<String, ValueInterface>> symTab)
    {
        this.symbolTableStack = symTab;
    }

    public void setFileTable(DictionaryInterface<StringValue, BufferedReader> newFileTable){
        this.fileTable = newFileTable;
    }

    public ListInterface<ValueInterface> getOut()
    {
        return this.output;
    }

    public void setOut(ListInterface<ValueInterface> out)
    {
        this.output = out;
    }

    public DictionaryInterface<StringValue, BufferedReader> getFileTable(){return this.fileTable;}

    public HeapInterface<Integer, ValueInterface> getHeap(){
        return this.heap;
    }

    public void setHeap(HeapInterface<Integer, ValueInterface> newHeap){
        this.heap = newHeap;
    }

    public int getID()
    {
        return this.id;
    }
    public boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }

    public ProgramState oneStepExecution() throws Exception {
        if(this.exeStack.isEmpty()) throw new EmptyADT("ProgramState stack is empty!");
        StatementInterface currentStatement = this.exeStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString(){
        String representation = "";
        representation += "\n------------------\n";
        representation += "Program ID: " + id + "\n";
        representation += "Execution Stack: \n";
        representation += this.exeStack.toString();
        representation += "\nSymbol Table:\n";
        representation += this.symbolTableStack.toString();
        representation += "\nOutput Table:\n";
        representation += this.output.toString();
        representation += "\nFile Table:\n";
        representation += this.fileTable.toString();
        representation += "\nHeap: \n";
        representation += this.heap.toString();
        representation += "\nSemaphore Table:";
        representation+= this.semaphoreTable.toString();
        representation += "\nLatch Table:";
        representation+= this.latchTable.toString();

        return representation;
    }
}

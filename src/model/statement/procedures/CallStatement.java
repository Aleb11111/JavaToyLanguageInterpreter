package model.statement.procedures;

import javafx.util.Pair;
import model.ProgramState;
import model.expresion.ExpressionInterface;
import model.statement.StatementInterface;
import model.type.TypeInterface;
import model.value.ValueInterface;
import utils.*;

public class CallStatement implements StatementInterface {

    String procName;
    MyList<ExpressionInterface> expressions;

    public CallStatement(String procName, MyList<ExpressionInterface> expressions) {
        this.procName = procName;
        this.expressions = expressions;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTableStack().top();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        ProcTable<String, Pair<MyList<String>,StatementInterface>> globalTable = state.getGlobalTable();

        if(globalTable.containsKey(procName))
        {
            MyList<String> varList = globalTable.getValue(procName).getKey();
            StatementInterface statement = globalTable.getValue(procName).getValue();

            MyList<ValueInterface> values = new MyList<>();
            for(ExpressionInterface e:this.expressions.getContent())
            {
                values.add(e.evaluate(symbolTable,heap));
            }

            MyDict<String,ValueInterface> newSymbTable = new MyDict<>();

            for(int i=0;i<varList.getContent().size();i++)
                newSymbTable.insert(varList.getContent().get(i),values.getContent().get(i));

            StackInterface<DictionaryInterface<String, ValueInterface>> stack = state.getSymbolTableStack();
            stack.push(newSymbTable);
            state.getExeStack().push(new ReturnStatement());
            state.getExeStack().push(statement);

        }else
        {
            throw new Exception("No such procedure declared.");
        }


        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "CallStatement{" +
                "procName='" + procName + '\'' +
                ", expressions=" + expressions +
                '}';
    }
}

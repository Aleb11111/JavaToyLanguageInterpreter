package view;

import com.sun.jdi.IntegerType;
import controller.Controller;
import javafx.util.Pair;
import model.ProgramState;
import model.expresion.*;
import model.statement.*;
import model.statement.heapStatements.HeapAllocationStatement;
import model.statement.heapStatements.HeapWritingStatement;
import model.type.BoolType;
import model.type.IntType;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;
import repository.RepoInterface;
import repository.Repository;
import utils.*;

import java.io.BufferedReader;
import java.util.Scanner;

public class View {

    String Folder_Path = "D:\\facultate\\Sem 3\\Metode avansate de programare\\A2";

    private StatementInterface ex1 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
            new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))),new PrintStatement
                    (new VariableExpression("v"))));

    ProgramState currentProgramState1 = new ProgramState(new MyStack<StatementInterface>(),new MyStack< DictionaryInterface<String,ValueInterface>>(),new MyList<ValueInterface>(),
            new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),1,ex1,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
    RepoInterface repo1 = new Repository(Folder_Path + "\\log1.in");
    Controller controller1 = new Controller(repo1);
    private StatementInterface ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
            new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new ArithmeticExpression('*',new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5))))),
                    new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"),new ValueExpression(new IntValue(1)))),new PrintStatement(new VariableExpression("b"))))));

    ProgramState currentProgramState2 = new ProgramState(new MyStack<StatementInterface>(),new MyStack< DictionaryInterface<String,ValueInterface>>(),new MyList<ValueInterface>(),
            new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),2,ex2,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
    RepoInterface repo2 = new Repository(Folder_Path + "\\log2.in");
    Controller controller2 = new Controller(repo2);


    private StatementInterface ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),new CompoundStatement(new AssignStatement("a",new ValueExpression(new BoolValue(true))),new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new IntValue(2))),new AssignStatement("v",new ValueExpression(new IntValue(3)))),new PrintStatement(new VariableExpression("v"))))));

    ProgramState currentProgramState3 = new ProgramState(new MyStack<StatementInterface>(),new MyStack< DictionaryInterface<String,ValueInterface>>(),new MyList<ValueInterface>(),
            new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),3,ex3,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
    RepoInterface repo3 = new Repository(Folder_Path + "\\log3.in");
    Controller controller3 = new Controller(repo3);


    //////////////////////////////////////////////////////////////////////


// while example

    StatementInterface declare_v5 = new VariableDeclarationStatement("v", new IntType());
    StatementInterface assign_v5_1 = new AssignStatement("v", new ValueExpression(new IntValue(4)));
    ExpressionInterface rel_expr5 = new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">");
    StatementInterface print_v5_1 = new PrintStatement(new VariableExpression("v"));
    ExpressionInterface arithmetic_v5 = new ArithmeticExpression('-',new VariableExpression("v"), new ValueExpression(new IntValue(1)) );
    StatementInterface assign_v5_2 = new AssignStatement("v", arithmetic_v5);
    StatementInterface compoundStatement_v5 = new CompoundStatement(print_v5_1, assign_v5_2);
    StatementInterface whileStatement_v5 = new WhileStatement(rel_expr5, compoundStatement_v5);
    StatementInterface print_v5_2 = new PrintStatement(new VariableExpression("v"));
    private StatementInterface ex5 =new CompoundStatement(declare_v5, new CompoundStatement(assign_v5_1, new CompoundStatement(whileStatement_v5, print_v5_2)));


    ProgramState currentProgramState5 = new ProgramState(new MyStack<StatementInterface>(),new MyStack< DictionaryInterface<String,ValueInterface>>(),new MyList<ValueInterface>(),
            new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),4,ex5,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
    RepoInterface repo5 = new Repository(Folder_Path + "\\log5.in");
    Controller controller5 = new Controller(repo5);


    //////////////////////////////////////////////////////////////////////

    // Heap example
    StatementInterface declare_v4 = new VariableDeclarationStatement("v", new ReferenceType(new IntType()));
    StatementInterface alloc_v4 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
    ExpressionInterface read_v4 = new HeapReadingExpression(new VariableExpression("v"));
    StatementInterface print_v4 = new PrintStatement(read_v4);
    StatementInterface write_v4 = new HeapWritingStatement("v", new ValueExpression(new IntValue(30)));
    ExpressionInterface read_v4_2 = new HeapReadingExpression(new VariableExpression("v"));
    ExpressionInterface add4 = new ArithmeticExpression('+',read_v4_2, new ValueExpression(new IntValue(5)));
    StatementInterface print_v4_2 = new PrintStatement(add4);

    private StatementInterface ex4 =  new CompoundStatement(declare_v4, new CompoundStatement(alloc_v4, new CompoundStatement(print_v4,
            new CompoundStatement(write_v4, print_v4_2))));
    ProgramState currentProgramState4 = new ProgramState(new MyStack<StatementInterface>(),new MyStack< DictionaryInterface<String,ValueInterface>>(),new MyList<ValueInterface>(),
            new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),5,ex4,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
    RepoInterface repo4 = new Repository(Folder_Path + "\\log4.in");
    Controller controller4 = new Controller(repo4);

    ///////////////////////////////////////////////////////////////////////

    /// int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))
    StatementInterface declare_v11 = new VariableDeclarationStatement("v", new IntType());
    StatementInterface declare_a11 = new VariableDeclarationStatement("a", new ReferenceType(new IntType()));
    StatementInterface assign_v11_1 = new AssignStatement("v", new ValueExpression(new IntValue(10)));
    StatementInterface alloc_v11 = new HeapAllocationStatement("a", new ValueExpression(new IntValue(22)));
    StatementInterface write_a11 = new HeapWritingStatement("a", new ValueExpression(new IntValue(30)));
    StatementInterface assign_v11_2 = new AssignStatement("v", new ValueExpression(new IntValue(32)));
    StatementInterface print_v11_1 = new PrintStatement(new VariableExpression("v"));
    ExpressionInterface read_v11 = new HeapReadingExpression(new VariableExpression("a"));
    StatementInterface print_v11_2 = new PrintStatement(read_v11);
    StatementInterface fork_11 = new ForkStatement(new CompoundStatement(write_a11, new CompoundStatement(assign_v11_2, new CompoundStatement(print_v11_1, print_v11_2))));


    private StatementInterface ex6 =   new CompoundStatement(declare_v11, new CompoundStatement(declare_a11, new CompoundStatement(assign_v11_1,
            new CompoundStatement(alloc_v11, new CompoundStatement(fork_11, new CompoundStatement(print_v11_1, print_v11_2))))));

    ProgramState currentProgramState6 = new ProgramState(new MyStack<StatementInterface>(),new MyStack< DictionaryInterface<String,ValueInterface>>(),new MyList<ValueInterface>(),
            new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),6,ex6,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
    RepoInterface repo6 = new Repository(Folder_Path + "\\log6.in");
    Controller controller6 = new Controller(repo6);





    ///////////////////////////////////////////////////////////////////////////////////
    public View()
    {
        this.controller1.addToRepo(currentProgramState1);
        this.controller2.addToRepo(currentProgramState2);
        this.controller3.addToRepo(currentProgramState3);
        this.controller4.addToRepo(currentProgramState4);
        this.controller5.addToRepo(currentProgramState5);
        this.controller6.addToRepo(currentProgramState6);
    }

    public void menu() {
        System.out.println("0. Exit");
        System.out.println("1. Example 1 " + ex1);
        System.out.println("2. Example 2 " + ex2);
        System.out.println("3. Example 3 " + ex3);
        System.out.println("4. Example 4 " + ex4);
        System.out.println("5. Example 5 " + ex5);
        System.out.println("6. Example 6 " + ex6);
    }


    public void start()
    {
        TextMenu textMenu = new TextMenu();

        try
        {
            textMenu.addCommand(new ExitCommand("0","Exit"));
            textMenu.addCommand(new RunExampleCommand("1",ex1.toString(),controller1));
            textMenu.addCommand(new RunExampleCommand("2",ex2.toString(),controller2));
            textMenu.addCommand(new RunExampleCommand("3",ex3.toString(),controller3));
            textMenu.addCommand(new RunExampleCommand("4",ex4.toString(),controller4));
            textMenu.addCommand(new RunExampleCommand("5",ex5.toString(),controller5));
            textMenu.addCommand(new RunExampleCommand("6",ex6.toString(),controller6));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        textMenu.show();
    }

    /*public void runEx1() {

        StackInterface<StatementInterface> exeStack = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symTable = new MyDict<>();
        ListInterface<ValueInterface> output = new MyList<>();

        ProgramState program = new ProgramState(exeStack, symTable, output, ex1);

        RepoInterface repository = new Repository();
        repository.addProgramState(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void runEx2() {

        StackInterface<StatementInterface> exeStack = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symTable = new MyDict<>();
        ListInterface<ValueInterface> output = new MyList<>();

        ProgramState program = new ProgramState(exeStack, symTable, output, ex2);

        RepoInterface repository = new Repository();
        repository.addProgramState(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void runEx3() {

        StackInterface<StatementInterface> exeStack = new MyStack<>();
        DictionaryInterface<String, ValueInterface> symTable = new MyDict<>();
        ListInterface<ValueInterface> output = new MyList<>();

        ProgramState program = new ProgramState(exeStack, symTable, output, ex3);

        RepoInterface repository = new Repository();
        repository.addProgramState(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }*/

}

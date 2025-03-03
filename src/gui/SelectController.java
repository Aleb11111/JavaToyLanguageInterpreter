package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Pair;
import javafx.util.StringConverter;
import model.statement.latch.AwaitStatement;
import model.statement.latch.CountDownStatement;
import model.statement.latch.NewLatchStatement;
import model.statement.procedures.CallStatement;
import model.statement.procedures.ProcedureDeclarationStatement;
import model.statement.semaphore.AcquireStatement;
import model.statement.semaphore.NewSemaphoreStatement;
import model.statement.semaphore.ReleaseStatement;
import model.statement.RepeatUntilStatement;
import utils.*;
import model.ProgramState;
import model.expresion.*;
import model.statement.*;
import model.statement.heapStatements.HeapAllocationStatement;
import model.statement.heapStatements.HeapWritingStatement;
import model.type.*;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;
import repository.Repository;
import repository.RepoInterface;
import view.RunExampleCommand;

import java.io.BufferedReader;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class SelectController {

    @FXML
    private ListView<RunExampleCommand> examplesListView;

    public ListView<RunExampleCommand> getExamplesListView(){
        return this.examplesListView;
    }

    @FXML
    public void initialize() throws Exception {

        String Folder_Path = "D:\\facultate\\Sem 3\\Metode avansate de programare\\A2";


        /// EXAMPLE 1
        /// int v; v=2;Print(v)
        StatementInterface ex1 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))),new PrintStatement
                        (new VariableExpression("v"))));

        DictionaryInterface<String, TypeInterface> typeEnvironment1 = new MyDict<String, TypeInterface>();
        ex1.typeCheck(typeEnvironment1);
        MyStack< DictionaryInterface<String,ValueInterface>> stackDict1 = new MyStack<>();
        stackDict1.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState1 = new ProgramState(new MyStack<StatementInterface>(),stackDict1,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),1,ex1,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo1 = new Repository(Folder_Path + "\\log1.in");
        Controller controller1 = new Controller(repo1);

        controller1.addToRepo(currentProgramState1);



        /// EXAMPLE 2
         StatementInterface ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new ArithmeticExpression('*',new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5))))),
                        new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"),new ValueExpression(new IntValue(1)))),new PrintStatement(new VariableExpression("b"))))));

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict2 = new MyStack<>();
        stackDict2.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState2 = new ProgramState(new MyStack<StatementInterface>(),stackDict2,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),2,ex2,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());

        DictionaryInterface<String, TypeInterface> typeEnvironment2 = new MyDict<String, TypeInterface>();
        ex2.typeCheck(typeEnvironment2);

        RepoInterface repo2 = new Repository(Folder_Path + "\\log2.in");
        Controller controller2 = new Controller(repo2);
        controller2.addToRepo(currentProgramState2);





        /// EXAMPLE 3
         StatementInterface ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),new CompoundStatement(new AssignStatement("a",new ValueExpression(new BoolValue(true))),new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new IntValue(2))),new AssignStatement("v",new ValueExpression(new IntValue(3)))),new PrintStatement(new VariableExpression("v"))))));
        MyStack< DictionaryInterface<String,ValueInterface>> stackDict3 = new MyStack<>();
        stackDict3.push(new MyDict<String,ValueInterface>());
        ProgramState currentProgramState3 = new ProgramState(new MyStack<StatementInterface>(),stackDict3,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),3,ex3,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());

        DictionaryInterface<String, TypeInterface> typeEnvironment3 = new MyDict<String, TypeInterface>();
        ex3.typeCheck(typeEnvironment3);

        RepoInterface repo3 = new Repository(Folder_Path + "\\log3.in");
        Controller controller3 = new Controller(repo3);

        controller3.addToRepo(currentProgramState3);





        /// EXAMPLE 4
        // Heap example
        StatementInterface declare_v4 = new VariableDeclarationStatement("v", new ReferenceType(new IntType()));
        StatementInterface alloc_v4 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
        ExpressionInterface read_v4 = new HeapReadingExpression(new VariableExpression("v"));
        StatementInterface print_v4 = new PrintStatement(read_v4);
        StatementInterface write_v4 = new HeapWritingStatement("v", new ValueExpression(new IntValue(30)));
        ExpressionInterface read_v4_2 = new HeapReadingExpression(new VariableExpression("v"));
        ExpressionInterface add4 = new ArithmeticExpression('+',read_v4_2, new ValueExpression(new IntValue(5)));
        StatementInterface print_v4_2 = new PrintStatement(add4);

        StatementInterface ex4 =  new CompoundStatement(declare_v4, new CompoundStatement(alloc_v4, new CompoundStatement(print_v4,
                new CompoundStatement(write_v4, print_v4_2))));

        DictionaryInterface<String, TypeInterface> typeEnvironment4 = new MyDict<String, TypeInterface>();
        ex4.typeCheck(typeEnvironment4);
        MyStack< DictionaryInterface<String,ValueInterface>> stackDict4 = new MyStack<>();
        stackDict4.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState4 = new ProgramState(new MyStack<StatementInterface>(),stackDict4,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),5,ex4,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo4 = new Repository(Folder_Path + "\\log4.in");
        Controller controller4 = new Controller(repo4);

        controller4.addToRepo(currentProgramState4);





        /// EXAMPLE 5
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
        StatementInterface ex5 =new CompoundStatement(declare_v5, new CompoundStatement(assign_v5_1, new CompoundStatement(whileStatement_v5, print_v5_2)));

        DictionaryInterface<String, TypeInterface> typeEnvironment5 = new MyDict<String, TypeInterface>();
        ex5.typeCheck(typeEnvironment5);

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict5 = new MyStack<>();
        stackDict5.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState5 = new ProgramState(new MyStack<StatementInterface>(),stackDict5,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),5,ex5,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo5 = new Repository(Folder_Path + "\\log5.in");
        Controller controller5 = new Controller(repo5);





        /// EXAMPLE 6
        /// int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))
        StatementInterface declare_v6 = new VariableDeclarationStatement("v", new IntType());
        StatementInterface declare_a6 = new VariableDeclarationStatement("a", new ReferenceType(new IntType()));
        StatementInterface assign_v6_1 = new AssignStatement("v", new ValueExpression(new IntValue(10)));
        StatementInterface alloc_v6 = new HeapAllocationStatement("a", new ValueExpression(new IntValue(22)));
        StatementInterface write_a6 = new HeapWritingStatement("a", new ValueExpression(new IntValue(30)));
        StatementInterface assign_v6_2 = new AssignStatement("v", new ValueExpression(new IntValue(32)));
        StatementInterface print_v6_1 = new PrintStatement(new VariableExpression("v"));
        ExpressionInterface read_v6 = new HeapReadingExpression(new VariableExpression("a"));
        StatementInterface print_v6_2 = new PrintStatement(read_v6);
        StatementInterface fork_6 = new ForkStatement(new CompoundStatement(write_a6, new CompoundStatement(assign_v6_2, new CompoundStatement(print_v6_1, print_v6_2))));

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict6 = new MyStack<>();
        stackDict6.push(new MyDict<String,ValueInterface>());

        StatementInterface ex6 =   new CompoundStatement(declare_v6, new CompoundStatement(declare_a6, new CompoundStatement(assign_v6_1,
                new CompoundStatement(alloc_v6, new CompoundStatement(fork_6, new CompoundStatement(print_v6_1, print_v6_2))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment6 = new MyDict<String, TypeInterface>();
        ex6.typeCheck(typeEnvironment6);

        ProgramState currentProgramState6 = new ProgramState(new MyStack<StatementInterface>(),stackDict6,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),6,ex6,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo6 = new Repository(Folder_Path + "\\log6.in");
        Controller controller6 = new Controller(repo6);

        controller6.addToRepo(currentProgramState6);

        //Semaphore

        StatementInterface ex7 = new CompoundStatement(new VariableDeclarationStatement("v1", new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("semaphore", new IntType()),
                new CompoundStatement(new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2))),
                new CompoundStatement(new NewSemaphoreStatement("semaphore", new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(1))),
                new CompoundStatement(new ForkStatement(
                        new CompoundStatement(new AcquireStatement("semaphore"),
                        new CompoundStatement(new HeapWritingStatement("v1", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))), new ReleaseStatement("semaphore"))))),
                                new CompoundStatement(new ForkStatement(
                                new CompoundStatement(new AcquireStatement("semaphore"),
                                new CompoundStatement(new HeapWritingStatement("v1", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
                                new CompoundStatement(new HeapWritingStatement("v1", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(2)))),
                                new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))), new ReleaseStatement("semaphore")))))),
                                new CompoundStatement(new AcquireStatement("semaphore"),
                                new CompoundStatement(new PrintStatement(new ArithmeticExpression('-', new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(1)))),
                                new ReleaseStatement("semaphore")))))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment7 = new MyDict<String, TypeInterface>();


        MyStack< DictionaryInterface<String,ValueInterface>> stackDict7 = new MyStack<>();
        stackDict7.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState7 = new ProgramState(new MyStack<StatementInterface>(),stackDict7,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),7,ex7,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo7 = new Repository(Folder_Path + "\\log7.in");
        Controller controller7 = new Controller(repo7);

        controller7.addToRepo(currentProgramState7);

        /////////////////////////////////////////////////

        //switch

        StatementInterface line1 = new VariableDeclarationStatement("a", new IntType());
        StatementInterface line2 = new AssignStatement("a", new ValueExpression(new IntValue(1)));
        StatementInterface line3 = new VariableDeclarationStatement("b", new IntType());
        StatementInterface line4 = new AssignStatement("b", new ValueExpression(new IntValue(2)));
        StatementInterface line5 = new VariableDeclarationStatement("c", new IntType());
        StatementInterface line6 = new AssignStatement("c", new ValueExpression(new IntValue(5)));

        ExpressionInterface switchParam = new ArithmeticExpression('*', new VariableExpression("a"),
                new ValueExpression(new IntValue(10)));
        Map<ExpressionInterface, StatementInterface> switchBody = new LinkedHashMap<>();
        ExpressionInterface switchCase1 = new ArithmeticExpression('*', new VariableExpression("b"),
                new VariableExpression("c"));
        StatementInterface switchCaseResult1 = new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                new PrintStatement(new VariableExpression("b")));
        ExpressionInterface switchCase2 = new ValueExpression(new IntValue(10));
        StatementInterface switchCaseResult2 = new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),
                new PrintStatement(new ValueExpression(new IntValue(200))));
        StatementInterface switchDefaultResult = new PrintStatement(new ValueExpression(new IntValue(300)));
        switchBody.put(switchCase1, switchCaseResult1); switchBody.put(switchCase2, switchCaseResult2);

        StatementInterface line7 = new SwitchStatement(switchParam, switchCase1,switchCaseResult1,switchCase2,switchCaseResult2, switchDefaultResult);
        StatementInterface line8 = new PrintStatement(new ValueExpression(new IntValue(300)));

        StatementInterface ex8 = new CompoundStatement(line1,
                new CompoundStatement(line2, new CompoundStatement(line3, new CompoundStatement(line4, new CompoundStatement(line5, new CompoundStatement(line6, new CompoundStatement(line7, line8)))))));


        DictionaryInterface<String, TypeInterface> typeEnvironment8 = new MyDict<String, TypeInterface>();
        ex8.typeCheck(typeEnvironment8);
        MyStack< DictionaryInterface<String,ValueInterface>> stackDict8 = new MyStack<>();
        stackDict8.push(new MyDict<String,ValueInterface>());


        ProgramState currentProgramState8 = new ProgramState(new MyStack<StatementInterface>(),stackDict8,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),8,ex8,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo8 = new Repository(Folder_Path + "\\log8.in");
        Controller controller8 = new Controller(repo8);

        controller8.addToRepo(currentProgramState8);

        ///////////////////////////////

        //conditional assigment

        StatementInterface ex9 = new CompoundStatement(new VariableDeclarationStatement("b", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("c", new IntType()),
                new CompoundStatement(new AssignStatement("b", new ValueExpression(new BoolValue(true))),
                new CompoundStatement(new ConditionalAssigmentStatement("c", new VariableExpression("b"), new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200))),
                        new CompoundStatement(new PrintStatement(new VariableExpression("c")),
                        new CompoundStatement(new ConditionalAssigmentStatement("c",new ValueExpression(new BoolValue(false)), new ValueExpression(new IntValue(100)), new ValueExpression(new IntValue(200))),new PrintStatement(new VariableExpression("c"))))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment9 = new MyDict<String, TypeInterface>();


        MyStack< DictionaryInterface<String,ValueInterface>> stackDict9 = new MyStack<>();
        stackDict9.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState9 = new ProgramState(new MyStack<StatementInterface>(),stackDict9,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),9,ex9,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo9 = new Repository(Folder_Path + "\\log9.in");
        Controller controller9 = new Controller(repo9);

        controller9.addToRepo(currentProgramState9);


        //////////////////////////////////////////////////////////////////////////////////////

        //Latch

            StatementInterface ex10 = new CompoundStatement(new VariableDeclarationStatement("v1", new ReferenceType(new IntType())),
                    new CompoundStatement(new VariableDeclarationStatement("v2", new ReferenceType(new IntType())),
                    new CompoundStatement(new VariableDeclarationStatement("v3", new ReferenceType(new IntType())),
                    new CompoundStatement(new VariableDeclarationStatement("latch", new IntType()),
                    new CompoundStatement(new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2))),
                    new CompoundStatement(new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3))),
                    new CompoundStatement(new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4))),
                    new CompoundStatement(new NewLatchStatement("latch", new HeapReadingExpression(new VariableExpression("v2"))),
                    new CompoundStatement(new ForkStatement(
                            new CompoundStatement(new HeapWritingStatement("v1", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
                            new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))),
                            new CompoundStatement(new CountDownStatement("latch"), new ForkStatement(
                                    new CompoundStatement(new HeapWritingStatement("v2", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)))),
                                    new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v2"))),
                                    new CompoundStatement(new CountDownStatement("latch"), new ForkStatement(
                                            new CompoundStatement(new HeapWritingStatement("v3", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)))),
                                            new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v3"))), new CountDownStatement("latch")))))))))))),
                            new CompoundStatement(new AwaitStatement("latch"),
                            new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),
                            new CompoundStatement(new CountDownStatement("latch"), new PrintStatement(new ValueExpression(new IntValue(100)))))))))))))));


        DictionaryInterface<String, TypeInterface> typeEnvironment10 = new MyDict<String, TypeInterface>();
        ex10.typeCheck(typeEnvironment10);

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict10 = new MyStack<>();
        stackDict10.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState10 = new ProgramState(new MyStack<StatementInterface>(),stackDict10,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),10,ex10,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo10 = new Repository(Folder_Path + "\\log10.in");
        Controller controller10 = new Controller(repo10);

        controller10.addToRepo(currentProgramState10);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Procedures

        MyList<String> varList = new MyList<>();
        varList.add("a");
        varList.add("b");

        MyList<ExpressionInterface> expList = new MyList<>();
        expList.add(new ArithmeticExpression('*', new VariableExpression("v"), new ValueExpression(new IntValue(10))));
        expList.add(new VariableExpression("w"));

        StatementInterface ex11 = new CompoundStatement(new ProcedureDeclarationStatement("sum", varList,
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ArithmeticExpression('+', new VariableExpression("a"), new VariableExpression("b"))), new PrintStatement(new VariableExpression("v"))))),
                new CompoundStatement(new ProcedureDeclarationStatement("prod", varList, new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ArithmeticExpression('*', new VariableExpression("a"), new VariableExpression("b"))), new PrintStatement(new VariableExpression("v"))))),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                new CompoundStatement(new VariableDeclarationStatement("w", new IntType()),
                new CompoundStatement(new AssignStatement("w", new ValueExpression(new IntValue(5))),
                new CompoundStatement(new CallStatement("sum", expList),new PrintStatement(new VariableExpression("v")))))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment11 = new MyDict<String, TypeInterface>();
        ex11.typeCheck(typeEnvironment11);

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict11 = new MyStack<>();
        stackDict11.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState11 = new ProgramState(new MyStack<StatementInterface>(),stackDict11,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),11,ex11,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo11 = new Repository(Folder_Path + "\\log11.in");
        Controller controller11 = new Controller(repo11);

        controller11.addToRepo(currentProgramState11);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Sleep

        StatementInterface ex12 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))),
                new CompoundStatement(new ForkStatement(
                                new CompoundStatement(new AssignStatement("v", new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))),
                                new CompoundStatement(new AssignStatement("v", new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("v"))))),
                                new CompoundStatement(new SleepStatement(5), new PrintStatement(new ArithmeticExpression('*', new VariableExpression("v"), new ValueExpression(new IntValue(10))))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment12 = new MyDict<String, TypeInterface>();
        ex12.typeCheck(typeEnvironment12);

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict12 = new MyStack<>();
        stackDict12.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState12 = new ProgramState(new MyStack<StatementInterface>(),stackDict12,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),12,ex12,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo12 = new Repository(Folder_Path + "\\log12.in");
        Controller controller12 = new Controller(repo12);

        controller12.addToRepo(currentProgramState12);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        //WAIT

        StatementInterface line13_1 = new VariableDeclarationStatement("v", new IntType());
        StatementInterface line13_2 = new AssignStatement("v", new ValueExpression(new IntValue(20)));
        StatementInterface line13_3 = new WaitStatement(10);
        StatementInterface line13_4 = new PrintStatement(new ArithmeticExpression('*',
                new VariableExpression("v"), new ValueExpression(new IntValue(10))));

        StatementInterface ex13 = new CompoundStatement(line13_1,
                new CompoundStatement(line13_2,
                        new CompoundStatement(line13_3, line13_4)));

        DictionaryInterface<String, TypeInterface> typeEnvironment13 = new MyDict<String, TypeInterface>();
        ex13.typeCheck(typeEnvironment13);

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict13 = new MyStack<>();
        stackDict13.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState13 = new ProgramState(new MyStack<StatementInterface>(),stackDict13,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),13,ex13,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo13 = new Repository(Folder_Path + "\\log13.in");
        Controller controller13 = new Controller(repo13);

        controller13.addToRepo(currentProgramState13);

        /////////////////////////////////////////////////////////////////////////////////////////

        //MUL

        StatementInterface ex14 = new CompoundStatement(new VariableDeclarationStatement("v1",new IntType()),
                new CompoundStatement(new AssignStatement("v1",new ValueExpression(new IntValue(2))),
                new CompoundStatement(new VariableDeclarationStatement("v2",new IntType()),
                new CompoundStatement(new AssignStatement("v2",new ValueExpression(new IntValue(3))),
                new IfStatement(new ValueExpression(new BoolValue(true)),new PrintStatement(new MULExpression(new VariableExpression("v1"),new VariableExpression("v2"))),new PrintStatement(new VariableExpression("v1")))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment14 = new MyDict<String, TypeInterface>();


        MyStack< DictionaryInterface<String,ValueInterface>> stackDict14 = new MyStack<>();
        stackDict14.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState14 = new ProgramState(new MyStack<StatementInterface>(),stackDict14,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),14,ex14,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo14 = new Repository(Folder_Path + "\\log14.in");
        Controller controller14 = new Controller(repo14);

        controller14.addToRepo(currentProgramState14);

        ///////////////////////////////////////////////////////////

        //Repeat Until

        StatementInterface ex15 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new RepeatUntilStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(3)),"=="),new ForkStatement(
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new AssignStatement("v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1))))))),
                        new AssignStatement("v",new ArithmeticExpression('+',new VariableExpression("v"),new ValueExpression(new IntValue(1))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment15 = new MyDict<String, TypeInterface>();


        MyStack< DictionaryInterface<String,ValueInterface>> stackDict15 = new MyStack<>();
        stackDict15.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState15 = new ProgramState(new MyStack<StatementInterface>(),stackDict15,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),15,ex15,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo15 = new Repository(Folder_Path + "\\log15.in");
        Controller controller15 = new Controller(repo15);

        controller15.addToRepo(currentProgramState15);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // conditional assigment practic

        StatementInterface ex16 = new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("b",new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new HeapAllocationStatement("a",new ValueExpression(new IntValue(0))),
                new CompoundStatement(new HeapAllocationStatement("b",new ValueExpression(new IntValue(0))),
                new CompoundStatement(new HeapWritingStatement("a",new ValueExpression(new IntValue(1))),
                new CompoundStatement(new HeapWritingStatement("b",new ValueExpression(new IntValue(2))),
                new CompoundStatement(new ConditionalAssigmentStatement("v",
                                new RelationalExpression(new HeapReadingExpression(new VariableExpression("a")),new HeapReadingExpression(new VariableExpression("b")),"<"),
                                new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200))),
                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                new CompoundStatement(new ConditionalAssigmentStatement("v",
                                                new RelationalExpression(new ArithmeticExpression('-',new HeapReadingExpression(new VariableExpression("b")),new ValueExpression(new IntValue(2))),new HeapReadingExpression(new VariableExpression("a")),">"),
                                                new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200))),new PrintStatement(new VariableExpression("v"))))))))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment16 = new MyDict<String, TypeInterface>();
        ex16.typeCheck(typeEnvironment16);

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict16 = new MyStack<>();
        stackDict16.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState16 = new ProgramState(new MyStack<StatementInterface>(),stackDict16,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),16,ex16,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo16 = new Repository(Folder_Path + "\\log16.in");
        Controller controller16 = new Controller(repo16);

        controller16.addToRepo(currentProgramState16);

        //////////////////////////////////////////////////////////////////////////////////

        //CountDownLatch practic

        StatementInterface ex17 = new CompoundStatement(new VariableDeclarationStatement("v1", new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("v2", new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("v3", new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("cnt", new IntType()),
                new CompoundStatement(new HeapAllocationStatement("v1", new ValueExpression(new IntValue(2))),
                new CompoundStatement(new HeapAllocationStatement("v2", new ValueExpression(new IntValue(3))),
                new CompoundStatement(new HeapAllocationStatement("v3", new ValueExpression(new IntValue(4))),
                new CompoundStatement(new NewLatchStatement("cnt", new HeapReadingExpression(new VariableExpression("v2"))),
                new CompoundStatement(new ForkStatement(
                        new CompoundStatement(new HeapWritingStatement("v1", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v1"))),
                        new CompoundStatement(new CountDownStatement("cnt"), new ForkStatement(
                                new CompoundStatement(new HeapWritingStatement("v2", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)))),
                                new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v2"))),
                                new CompoundStatement(new CountDownStatement("cnt"), new ForkStatement(
                                        new CompoundStatement(new HeapWritingStatement("v3", new ArithmeticExpression('*', new HeapReadingExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)))),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v3"))), new CountDownStatement("cnt")))))))))))),
                                        new CompoundStatement(new AwaitStatement("cnt"),
                                        new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),
                                        new CompoundStatement(new CountDownStatement("cnt"), new PrintStatement(new ValueExpression(new IntValue(100)))))))))))))));

        DictionaryInterface<String, TypeInterface> typeEnvironment17 = new MyDict<String, TypeInterface>();
        ex17.typeCheck(typeEnvironment17);

        MyStack< DictionaryInterface<String,ValueInterface>> stackDict17 = new MyStack<>();
        stackDict17.push(new MyDict<String,ValueInterface>());

        ProgramState currentProgramState17 = new ProgramState(new MyStack<StatementInterface>(),stackDict17,new MyList<ValueInterface>(),
                new MyDict<StringValue, BufferedReader>(),new MyHeap<Integer, ValueInterface>(),17,ex17,new MySem<Integer, Pair<Integer, Pair<MyList<Integer>,Integer>>>(),new MyLatch<Integer,Integer>(),new MyProcTable<String,Pair<MyList<String>,StatementInterface>>());
        RepoInterface repo17 = new Repository(Folder_Path + "\\log17.in");
        Controller controller17 = new Controller(repo17);

        controller17.addToRepo(currentProgramState17);


        this.examplesListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<RunExampleCommand>() {
            @Override
            public String toString(RunExampleCommand runExampleCommand) {
                return runExampleCommand.toString();
            }

            @Override
            public RunExampleCommand fromString(String s) {
                return null;
            }
        }));

        this.examplesListView.getItems().add(new RunExampleCommand("1", ex1.toString(), controller1));
        this.examplesListView.getItems().add(new RunExampleCommand("2", ex2.toString(), controller2));
        this.examplesListView.getItems().add(new RunExampleCommand("3", ex3.toString(), controller3));
        this.examplesListView.getItems().add(new RunExampleCommand("4", ex4.toString(), controller4));
        this.examplesListView.getItems().add(new RunExampleCommand("5", ex5.toString(), controller5));
        this.examplesListView.getItems().add(new RunExampleCommand("6", ex6.toString(), controller6));
        this.examplesListView.getItems().add(new RunExampleCommand("7", ex7.toString(), controller7));
        this.examplesListView.getItems().add(new RunExampleCommand("8", ex8.toString(), controller8));
        this.examplesListView.getItems().add(new RunExampleCommand("9", ex9.toString(), controller9));
        this.examplesListView.getItems().add(new RunExampleCommand("10", ex10.toString(), controller10));
        this.examplesListView.getItems().add(new RunExampleCommand("11", ex11.toString(), controller11));
        this.examplesListView.getItems().add(new RunExampleCommand("12", ex12.toString(), controller12));
        this.examplesListView.getItems().add(new RunExampleCommand("13", ex13.toString(), controller13));
        this.examplesListView.getItems().add(new RunExampleCommand("14", ex14.toString(), controller14));
        this.examplesListView.getItems().add(new RunExampleCommand("15", ex15.toString(), controller15));
        this.examplesListView.getItems().add(new RunExampleCommand("16", ex16.toString(), controller16));
        this.examplesListView.getItems().add(new RunExampleCommand("17", ex17.toString(), controller17));




        this.examplesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
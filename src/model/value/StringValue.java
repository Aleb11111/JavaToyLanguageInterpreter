package model.value;

import model.type.StringType;
import model.type.TypeInterface;

import java.util.StringTokenizer;

public class StringValue implements ValueInterface{

    public static final String DEFAULT_STRING_VALUE = "";
    private final String value;

    public StringValue(){
        this.value = StringValue.DEFAULT_STRING_VALUE;
    }

    public StringValue(String value){
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        return (another instanceof StringValue && ((StringValue)another).getValue() == this.value);
    }

    @Override
    public TypeInterface getType() {
        return new StringType();
    }

    public String getValue() {
        return value;
    }

    public String toString(){
        return value;
    }

}

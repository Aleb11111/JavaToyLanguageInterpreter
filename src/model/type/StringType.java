package model.type;

import model.value.StringValue;
import model.value.ValueInterface;

import java.lang.reflect.Type;

public class StringType implements TypeInterface {

    @Override
    public boolean equals(Object another){ return (another instanceof StringType);}

    @Override
    public ValueInterface getDefaultValue() {
        return new StringValue();
    }

    @Override
    public String toString(){
        return "String";
    }

}

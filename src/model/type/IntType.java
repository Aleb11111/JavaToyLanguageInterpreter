package model.type;

import model.value.IntValue;
import model.value.ValueInterface;

public class IntType implements TypeInterface {


    @Override
    public ValueInterface getDefaultValue() {
        return new IntValue();
    }

    @Override
    public boolean equals(Object another){ return (another instanceof IntType);}

    @Override
    public String toString(){
        return "int";
    }

}

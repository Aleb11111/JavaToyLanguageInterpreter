package model.type;

import model.value.ReferenceValue;
import model.value.ValueInterface;

public class ReferenceType implements TypeInterface{

    TypeInterface inner;

    public ReferenceType(TypeInterface inner)
    {
        this.inner = inner;
    }

    public TypeInterface getInner()
    {
        return this.inner;
    }

    public boolean equals(Object anotherObject)
    {
        if(anotherObject instanceof ReferenceType)
            return this.inner.equals(((ReferenceType)anotherObject).getInner());
        else return false;
    }

    public String toString()
    {
        return "Ref("+this.inner.toString()+")";
    }

    @Override
    public ValueInterface getDefaultValue() {
        return new ReferenceValue(this.inner);
    }
}

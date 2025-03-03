package model.value;

import model.type.ReferenceType;
import model.type.TypeInterface;

public class ReferenceValue implements ValueInterface{

    private int heapAddress;
    private TypeInterface innerReferenceType;
    public int DEFAULT_HEAP_ADDRESS = 0;


    public ReferenceValue(TypeInterface innerReferenceType)
    {
        this.heapAddress = DEFAULT_HEAP_ADDRESS;
        this.innerReferenceType = innerReferenceType;
    }

    public ReferenceValue(int heapAddress, TypeInterface innerReferencedType){
        this.heapAddress = heapAddress;
        this.innerReferenceType = innerReferencedType;
    }

    public int getHeapAddress()
    {
        return this.heapAddress;
    }

    public String toString()
    {
        return "("+this.heapAddress+", "+innerReferenceType.toString()+")";
    }

    @Override
    public TypeInterface getType() {
        return new ReferenceType(this.innerReferenceType);
    }
}

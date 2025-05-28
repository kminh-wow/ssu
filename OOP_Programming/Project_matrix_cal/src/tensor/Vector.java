package tensor;

public interface Vector {

    //11
    void setValue(int index, Scalar val);//20
    Scalar getValue(int index);//21
    
    //13
    int size();
    
    //14
    String toString();

    //15
    boolean equals(Object obj);

    //17
    Vector clone();

    //20
    void add(Vector other);
    
    
    //21
    void multiply(Scalar val);
    
    
    //30
    Matrix toVerticalMatrix();
    
    //31
    Matrix toHorizontalMatrix();

}

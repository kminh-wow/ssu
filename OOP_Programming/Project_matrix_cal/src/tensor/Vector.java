package tensor;

public interface Vector {


    Scalar getValue(int index);
    void setValue(int index, Scalar val);
    int size();
    String toString();
    boolean equals(Object obj);
    Vector clone();
    void add(Vector other);
    void multiply(Scalar val);
    //30
    Matrix toVerticalMatrix();
    //31
    Matrix toHorizontalMatrix();

}

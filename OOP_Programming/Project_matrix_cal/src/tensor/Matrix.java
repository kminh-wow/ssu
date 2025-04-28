package tensor;
public interface Matrix extends Cloneable {
    int getRowCount();
    int getColumnCount();
    Scalar get(int row, int col);
    void set(int row, int col, Scalar value);
    String toString();
    boolean equals(Object obj);
    Matrix clone();
    void add(Matrix other);
    void multiply(Matrix other);
}

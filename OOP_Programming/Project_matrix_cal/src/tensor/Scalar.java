package tensor;

public interface Scalar {
    String getValue();
    void setValue(String val);
    String toString();

    boolean equals(Object obj);
    Scalar clone();
    void add(Scalar other);
    void multiply(Scalar other);
    int compareTo(Scalar val);
}

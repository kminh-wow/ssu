package tensor;

import java.util.*;

class VectorImpl implements Vector {
    private List<Scalar> elements;

    VectorImpl(int n, Scalar val) {
        elements = new ArrayList<>();
        for (int i = 0; i < n; i++) elements.add(val.clone());
    }

    VectorImpl(int i, int j, int n) {
        elements = new ArrayList<>();
        for (int k = 0; k < n; k++) elements.add(new ScalarImpl(i, j));
    }

    VectorImpl(Scalar[] arr) {
        elements = new ArrayList<>();
        for (Scalar s : arr) elements.add(s.clone());
    }

    public int size() { return elements.size(); }
    public Scalar get(int index) { return elements.get(index); }
    public void set(int index, Scalar value) { elements.set(index, value.clone()); }
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (Scalar s : elements) sb.append(s.toString()).append(" ");
        sb.append("]");
        return sb.toString();
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        if (this.size() != other.size()) return false;
        for (int i = 0; i < size(); i++) if (!get(i).equals(other.get(i))) return false;
        return true;
    }
    public Vector clone() {
        Scalar[] copy = new Scalar[size()];
        for (int i = 0; i < size(); i++) copy[i] = get(i).clone();
        return new VectorImpl(copy);
    }
    public void add(Vector other) {
        if (size() != other.size()) throw new DimensionMismatchException("Vector size mismatch");
        for (int i = 0; i < size(); i++) get(i).add(other.get(i));
    }
    public void multiply(Scalar scalar) {
        for (Scalar s : elements) s.multiply(scalar);
    }
    // Vector를 1 x N 행렬로 변환
    public Matrix toRowMatrix() {
        Scalar[][] arr = new Scalar[1][size()];
        for (int i = 0; i < size(); i++) {
            arr[0][i] = get(i).clone();
        }
        return new MatrixImpl(arr);
    }

    // Vector를 N x 1 행렬로 변환
    public Matrix toColumnMatrix() {
        Scalar[][] arr = new Scalar[size()][1];
        for (int i = 0; i < size(); i++) {
            arr[i][0] = get(i).clone();
        }
        return new MatrixImpl(arr);
    }

}

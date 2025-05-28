package tensor;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;




class VectorImpl implements Vector {
    private List<Scalar> elements;
    VectorImpl(int n, Scalar val) {//03번
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

    @Override
    public Scalar getValue(int index) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return elements.get(index).clone();  // 읽기 - 복사해서 반환
    }
    @Override
    public void setValue(int index, Scalar val) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        elements.set(index, val.clone());  // 쓰기 - 복사해서 저장
    }
    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.size(); i++) {
            BigDecimal val = new BigDecimal(elements.get(i).getValue());
            val = val.setScale(5, RoundingMode.HALF_UP);
            sb.append(val.stripTrailingZeros().toPlainString());
            if (i < elements.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        if (this.size() != other.size()) return false;
        
        // 소수점 6자리까지 비교
        BigDecimal epsilon = new BigDecimal("0.000001");
        for (int i = 0; i < this.size(); i++) {
            BigDecimal val1 = new BigDecimal(this.getValue(i).getValue());
            BigDecimal val2 = new BigDecimal(other.getValue(i).getValue());
            if (val1.subtract(val2).abs().compareTo(epsilon) > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Vector clone() {
        Scalar[] arr = new Scalar[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            arr[i] = elements.get(i).clone();
        }
        return new VectorImpl(arr);
    }

    @Override
    public void add(Vector other) {
        if (this.size() != other.size()) {
            throw new IllegalArgumentException("벡터의 길이가 다릅니다.");
        }
        for (int i = 0; i < this.size(); i++) {
            this.elements.get(i).add(other.getValue(i));
        }
    }

    @Override
    public void multiply(Scalar scalar) {
        for (int i = 0; i < this.size(); i++) {
            this.elements.get(i).multiply(scalar);
        }
    }

    @Override
    public Matrix toVerticalMatrix() { //30번
        int n = this.size();
        Scalar[][] arr = new Scalar[n][1];
        for (int i = 0; i < n; i++) {
            arr[i][0] = this.getValue(i).clone();
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Matrix toHorizontalMatrix() { //31번
        int n = this.size();
        Scalar[][] arr = new Scalar[1][n];
        for (int i = 0; i < n; i++) {
            arr[0][i] = this.getValue(i).clone();
        }
        return new MatrixImpl(arr);
    }

    static Vector add(Vector v1, Vector v2) {
        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException("벡터의 길이가 다릅니다.");
        }
        Scalar[] arr = new Scalar[v1.size()];
        for (int i = 0; i < v1.size(); i++) {
            arr[i] = ScalarImpl.add(v1.getValue(i), v2.getValue(i));
        }
        return new VectorImpl(arr);
    }

    static Vector multiply(Vector v, Scalar s) {
        Scalar[] arr = new Scalar[v.size()];
        for (int i = 0; i < v.size(); i++) {
            arr[i] = ScalarImpl.multiply(v.getValue(i), s);
        }
        return new VectorImpl(arr);
    }
}

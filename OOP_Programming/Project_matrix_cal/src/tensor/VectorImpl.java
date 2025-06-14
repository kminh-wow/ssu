package tensor;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;




class VectorImpl implements Vector {
    private List<Scalar> elements;
    //3 지정한 하나의 값을 모든 요소의 값으로 하는 n차원 벡터 생성
    VectorImpl(int n, Scalar val) {
        elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 9) {
                    elements.add(val.clone());
                }
            }
        }
    }
    //i이상 j미만 무작위 값을 요소로 하는 n차원 벡터 생성
    VectorImpl(int i, int j, int n) {
        elements = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            for (int m = 0; m < 10; m++) {
                if (m == 9) {
                    elements.add(new ScalarImpl(i, j));
                }
            }
        }
    }
    //4 배열을 요소로 하는 n차원 벡터 생성
    VectorImpl(Scalar[] arr) {
        elements = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 9) {
                    elements.add(arr[i].clone());
                }
            }
        }
    }
    //11 지정조회
    @Override
    public Scalar getValue(int index) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("벡터 인덱스가 범위를 벗어났습니다: 인덱스 " + index + "는 0부터 " + (elements.size() - 1) + " 사이여야 합니다.");
        }
        String temp = elements.get(index).getValue();
        return new ScalarImpl(temp);
    }
    //11 지정조회
    @Override
    public void setValue(int index, Scalar val) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("벡터 인덱스가 범위를 벗어났습니다: 인덱스 " + index + "는 0부터 " + (elements.size() - 1) + " 사이여야 합니다.");
        }
        String temp = val.getValue();
        elements.set(index, new ScalarImpl(temp));
    }
    //13 크기정보 조회
    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < elements.size(); i++) {
            count++;
        }
        return count;
    }
    //14 객체를 콘솔에 출력할 수 있다.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.size(); i++) {
            String temp = elements.get(i).getValue();
            BigDecimal val = new BigDecimal(temp);
            val = val.setScale(5, RoundingMode.HALF_UP);
            String result = val.stripTrailingZeros().toPlainString();
            sb.append(result);
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
        
        for (int i = 0; i < this.size(); i++) {
            String val1 = this.getValue(i).getValue();
            String val2 = other.getValue(i).getValue();
            BigDecimal bd1 = new BigDecimal(val1);
            BigDecimal bd2 = new BigDecimal(val2);
            if (!bd1.setScale(6, RoundingMode.HALF_UP).equals(bd2.setScale(6, RoundingMode.HALF_UP))) {
                return false;
            }
        }
        return true;
    }
    //17 객체 복제
    @Override
    public Vector clone() {
        Scalar[] arr = new Scalar[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            String temp = elements.get(i).getValue();
            arr[i] = new ScalarImpl(temp);
        }
        return new VectorImpl(arr);
    }
    //20 벡터 덧셈
    @Override
    public void add(Vector other) {
        if (this.size() != other.size()) {
            throw new IllegalArgumentException("벡터 덧셈이 불가능합니다: 첫 번째 벡터의 크기(" + this.size() + ")와 두 번째 벡터의 크기(" + other.size() + ")가 일치하지 않습니다.");
        }
        for (int i = 0; i < this.size(); i++) {
            String val1 = this.getValue(i).getValue();
            String val2 = other.getValue(i).getValue();
            Scalar sum = ScalarImpl.add(new ScalarImpl(val1), new ScalarImpl(val2));
            this.setValue(i, sum);
        }
    }
    //21 벡터 - 스칼라 곱셈셈
    @Override
    public void multiply(Scalar scalar) {
        for (int i = 0; i < this.size(); i++) {
            String val1 = this.getValue(i).getValue();
            String val2 = scalar.getValue();
            Scalar product = ScalarImpl.multiply(new ScalarImpl(val1), new ScalarImpl(val2));
            this.setValue(i, product);
        }
    }
    //30 자신으로부터 nx1행렬 반환 -> 세로행렬
    @Override
    public Matrix toVerticalMatrix() {
        int n = this.size();
        Scalar[][] arr = new Scalar[n][1];
        for (int i = 0; i < n; i++) {
            String temp = this.getValue(i).getValue();
            arr[i][0] = new ScalarImpl(temp);
        }
        return new MatrixImpl(arr);
    }
    //31 자신으로부터 1xn행렬 반환 -> 가로행렬
    @Override
    public Matrix toHorizontalMatrix() {
        int n = this.size();
        Scalar[][] arr = new Scalar[1][n];
        for (int i = 0; i < n; i++) {
            String temp = this.getValue(i).getValue();
            arr[0][i] = new ScalarImpl(temp);
        }
        return new MatrixImpl(arr);
    }

    static Vector add(Vector v1, Vector v2) {
        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException("벡터 덧셈이 불가능합니다: 첫 번째 벡터의 크기(" + v1.size() + ")와 두 번째 벡터의 크기(" + v2.size() + ")가 일치하지 않습니다.");
        }
        Scalar[] arr = new Scalar[v1.size()];
        for (int i = 0; i < v1.size(); i++) {
            String val1 = v1.getValue(i).getValue();
            String val2 = v2.getValue(i).getValue();
            arr[i] = ScalarImpl.add(new ScalarImpl(val1), new ScalarImpl(val2));
        }
        return new VectorImpl(arr);
    }

    static Vector multiply(Vector v, Scalar s) {
        Scalar[] arr = new Scalar[v.size()];
        for (int i = 0; i < v.size(); i++) {
            String val1 = v.getValue(i).getValue();
            String val2 = s.getValue();
            arr[i] = ScalarImpl.multiply(new ScalarImpl(val1), new ScalarImpl(val2));
        }
        return new VectorImpl(arr);
    }
}

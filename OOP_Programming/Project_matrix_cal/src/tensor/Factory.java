package tensor;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Factory {

    // Scalar 생성
    public static Scalar createScalar(String val) {
        return new ScalarImpl(val);
    }

    public static Scalar createScalar(int i, int j) {
        return new ScalarImpl(i, j);
    }

    // Vector 생성
    public static Vector createVector(int n, Scalar val) {
        return new VectorImpl(n, val);
    }
    
    public static Vector createVector(int i, int j, int n) {
        return new VectorImpl(i, j, n);
    }

    public static Vector createVector(Scalar[] arr) {
        return new VectorImpl(arr);
    }

    // Matrix 생성
    public static Matrix createMatrix(int m, int n, Scalar val) {
        return new MatrixImpl(m, n, val);
    }

    public static Matrix createMatrix(int i, int j, int m, int n) {
        return new MatrixImpl(i, j, m, n);
    }

    public static Matrix createMatrix(Scalar[][] arr) {
        return new MatrixImpl(arr);
    }

    public static Matrix createMatrix(String csvPath) {
        try {
            return new MatrixImpl(csvPath);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Matrix creatIdentityMatrix(int n) {
        return new MatrixImpl(n, n, Factory.createScalar("1"));
    }

    // Static 연산 메서드
    public static Scalar add(Scalar a, Scalar b) {
        BigDecimal val1 = new BigDecimal(a.getValue());
        BigDecimal val2 = new BigDecimal(b.getValue());
        BigDecimal result = val1.add(val2).setScale(5, RoundingMode.HALF_UP);
        return new ScalarImpl(result.stripTrailingZeros().toPlainString());
    }

    public static Scalar multiply(Scalar a, Scalar b) {
        BigDecimal val1 = new BigDecimal(a.getValue());
        BigDecimal val2 = new BigDecimal(b.getValue());
        BigDecimal result = val1.multiply(val2).setScale(5, RoundingMode.HALF_UP);
        return new ScalarImpl(result.stripTrailingZeros().toPlainString());
    }

    public static Vector add(Vector a, Vector b) {
        if (a.size() != b.size()) {
            throw new IllegalArgumentException("벡터의 길이가 다릅니다.");
        }
        Scalar[] result = new Scalar[a.size()];
        for (int i = 0; i < a.size(); i++) {
            result[i] = add(a.getValue(i), b.getValue(i));
        }
        return new VectorImpl(result);
    }

    public static Vector multiply(Vector v, Scalar s) {
        Scalar[] result = new Scalar[v.size()];
        for (int i = 0; i < v.size(); i++) {
            result[i] = multiply(v.getValue(i), s);
        }
        return new VectorImpl(result);
    }

    public static Matrix add(Matrix a, Matrix b) {
        if (a.rowSize() != b.rowSize() || a.colSize() != b.colSize()) {
            throw new IllegalArgumentException("행렬의 크기가 다릅니다.");
        }
        Scalar[][] result = new Scalar[a.rowSize()][a.colSize()];
        for (int i = 0; i < a.rowSize(); i++) {
            for (int j = 0; j < a.colSize(); j++) {
                result[i][j] = add(a.getValue(i, j), b.getValue(i, j));
            }
        }
        return new MatrixImpl(result);
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.colSize() != b.rowSize()) {
            throw new IllegalArgumentException("행렬 곱셈 조건이 맞지 않습니다.");
        }
        Scalar[][] result = new Scalar[a.rowSize()][b.colSize()];
        for (int i = 0; i < a.rowSize(); i++) {
            for (int j = 0; j < b.colSize(); j++) {
                BigDecimal sum = BigDecimal.ZERO;
                for (int k = 0; k < a.colSize(); k++) {
                    sum = sum.add(new BigDecimal(a.getValue(i, k).getValue())
                            .multiply(new BigDecimal(b.getValue(k, j).getValue())));
                }
                result[i][j] = new ScalarImpl(sum.setScale(5, RoundingMode.HALF_UP)
                        .stripTrailingZeros().toPlainString());
            }
        }
        return new MatrixImpl(result);
    }
}
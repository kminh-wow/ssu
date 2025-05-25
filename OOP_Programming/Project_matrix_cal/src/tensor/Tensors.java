package tensor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tensors {
    // Scalar 연산
    public static Scalar add(Scalar a, Scalar b) {
        BigDecimal valA = new BigDecimal(a.getValue());
        BigDecimal valB = new BigDecimal(b.getValue());
        BigDecimal result = valA.add(valB).setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();
        return Factory.createScalar(result.toPlainString());
    }
    public static Scalar multiply(Scalar a, Scalar b) {
        BigDecimal valA = new BigDecimal(a.getValue());
        BigDecimal valB = new BigDecimal(b.getValue());
        BigDecimal result = valA.multiply(valB).setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();
        return Factory.createScalar(result.toPlainString());
    }

    // Vector 연산
    public static Vector add(Vector a, Vector b) {
        if (a.size() != b.size()) {
            throw new IllegalArgumentException("벡터의 크기가 같아야 합니다.");
        }
        Scalar[] result = new Scalar[a.size()];
        for (int i = 0; i < a.size(); i++) {
            result[i] = add(a.getValue(i), b.getValue(i));
        }
        return Factory.createVector(result);
    }
    public static Vector multiply(Vector v, Scalar s) {
        Scalar[] result = new Scalar[v.size()];
        for (int i = 0; i < v.size(); i++) {
            result[i] = multiply(v.getValue(i), s);
        }
        return Factory.createVector(result);
    }

    // Matrix 연산
    public static Matrix add(Matrix a, Matrix b) {
        if (a.rowSize() != b.rowSize() || a.colSize() != b.colSize()) {
            throw new IllegalArgumentException("행렬의 크기가 같아야 합니다.");
        }
        Scalar[][] result = new Scalar[a.rowSize()][a.colSize()];
        for (int i = 0; i < a.rowSize(); i++) {
            for (int j = 0; j < a.colSize(); j++) {
                result[i][j] = add(a.getValue(i, j), b.getValue(i, j));
            }
        }
        return Factory.createMatrix(result);
    }
    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.colSize() != b.rowSize()) {
            throw new IllegalArgumentException("행렬의 크기가 맞지 않습니다.");
        }
        Scalar[][] result = new Scalar[a.rowSize()][b.colSize()];
        for (int i = 0; i < a.rowSize(); i++) {
            for (int j = 0; j < b.colSize(); j++) {
                Scalar sum = Factory.createScalar("0");
                for (int k = 0; k < a.colSize(); k++) {
                    sum = add(sum, multiply(a.getValue(i, k), b.getValue(k, j)));
                }
                result[i][j] = sum;
            }
        }
        return Factory.createMatrix(result);
    }
    public static Matrix attachHMatrix(Matrix m1, Matrix m2) {
        return Matrix.attachHMatrix(m1, m2);
    }
    public static Matrix attachVMatrix(Matrix m1, Matrix m2) {
        return Matrix.attachVMatrix(m1, m2);
    }
    // 필요시 추가 연산도 여기에 래핑 가능
}

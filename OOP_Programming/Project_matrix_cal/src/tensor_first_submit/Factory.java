package tensor;

import java.io.IOException;

public class Factory {

    // Scalar 생성
    public static Scalar createScalar(String val) {
        return null
    }

    public static Scalar createScalar(int i, int j) {
        return null
    }

    // Vector 생성
    public static Vector createVector(int n, Scalar val) {
        return null
    }
    
    public static Vector createVector(int i, int j, int n) {
        return null
    }

    public static Vector createVector(Scalar[] arr) {
        return null
    }

    // Matrix 생성
    public static Matrix createMatrix(int m, int n, Scalar val) {
        return null
    }

    public static Matrix createMatrix(int i, int j, int m, int n) {
        return null
    }

    public static Matrix createMatrix(String csvPath) throws IOException {
        return null
    }

    public static Matrix createMatrix(Scalar[][] arr) {
        return null
    }

    public static Matrix createIdentityMatrix(int size) {
        return null
    }
}
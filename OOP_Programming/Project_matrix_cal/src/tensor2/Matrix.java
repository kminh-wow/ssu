package tensor2;

public interface Matrix {
    // 크기 관련 메서드
    abstract int rowSize();
    abstract int colSize();
    
    // 값 관련 메서드
    abstract void setValue(int row, int col, Scalar value);
    abstract Scalar getValue(int row, int col);
    
    // 연산 메서드
    abstract void add(Matrix other);
    abstract void multiply(Matrix other);
    
    // 비교 메서드
    abstract boolean equals(Object obj);
    
    // 복제 메서드
    abstract Matrix clone();
    
    // 행렬 연산 메서드
    abstract Vector getRowVector(int row);
    abstract Vector getColVector(int col);
    abstract Matrix extractSubMatrix(int startRow, int endRow, int startCol, int endCol);
    abstract Matrix minorSubMatrix(int row, int col);
    abstract Matrix transposeMatrix(Matrix matrix);
    abstract double trace(Matrix matrix);
    abstract boolean isSquare(Matrix matrix);
    abstract boolean isUpperTriangular(Matrix matrix);
    abstract boolean isLowerTriangular(Matrix matrix);
    abstract boolean isIdentity(Matrix matrix);
    abstract boolean isZero(Matrix matrix);
    abstract void rowSwap(int row1, int row2);
    abstract void colSwap(int col1, int col2);
    abstract void rowMultiply(int row, Scalar scalar);
    abstract void colMultiply(int col, Scalar scalar);
    abstract void rowAddOtherRow(int targetRow, int sourceRow, Scalar scalar);
    abstract void colAddOtherCol(int targetCol, int sourceCol, Scalar scalar);
    abstract Matrix getRREF(Matrix matrix);
    abstract Matrix isRREF(Matrix matrix);
    abstract double getDeterminant(Matrix matrix);
    abstract Matrix getInverseMatrix(Matrix matrix);
} 
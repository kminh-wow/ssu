package tensor;

public interface Matrix {

    void setValue(int row, int col, Scalar val);
    Scalar getValue(int row, int col);
    
    int rowSize();//13번
    int colSize();//13번번
    String toString();//14번번
    boolean equals(Object obj);
    Matrix clone();
    void add(Matrix other);
    void multiply(Matrix other);
    Matrix attachHMatrix(Matrix other);//32
    Matrix attachVMatrix(Matrix other);//33
    Vector getRowVector(int row);//34
    Vector getColVector(int col);//35
    Matrix extractSubMatrix(int rowStart, int rowEnd, int colStart, int colEnd);//36

    Matrix minorSubMatrix(int row, int col);//37

    Matrix transposeMatrix();//38번

    Scalar trace();//39번
    boolean isSquare();//40번
    boolean isUpperTriangular();//41번
    boolean isLowerTriangular();//42번
    boolean isIdentity();//43번
    boolean isZero();//44번

    void rowSwap(int row1, int row2);//45번
    void colSwap(int col1, int col2);//46번

    void rowMultiply(int row, Scalar val);//47번
    void colMultiply(int col, Scalar val);//48번

    void rowAddOtherRow(int targetRow, int sourceRow, Scalar val);//49번, targetRow에 sourceRow의 factor배 를더함
    void colAddOtherCol(int targetCol, int sourceCol, Scalar val);//50번, targetCol에 sourceCol의 factor배 더함

    Matrix getRREF();//51번, 본인의 rref 리턴
    boolean isRREF();//52번, 본인의 rref 여부 리턴

    Scalar getDeterminant();//53번, 행렬식 구하기
    Matrix getInverseMatrix();//54번, 본인의 역행렬 리턴

    
}


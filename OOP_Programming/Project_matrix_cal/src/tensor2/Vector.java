package tensor2;

public interface Vector {
    // 크기 관련 메서드
    abstract int size();
    
    // 값 관련 메서드
    abstract void setValue(int index, Scalar value);
    abstract Scalar getValue(int index);
    
    // 연산 메서드
    abstract void add(Vector other);
    abstract void multiply(Scalar scalar);
    
    // 비교 메서드
    abstract boolean equals(Object obj);
    
    // 복제 메서드
    abstract Vector clone();
    
    // 행렬 변환 메서드
    abstract Matrix toVerticalMatrix();
    abstract Matrix toHorizentalMatrix();
} 
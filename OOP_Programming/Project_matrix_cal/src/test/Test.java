package test;
import tensor.*;

public class Test {
    public static void main(String[] args) {
        // 1. 스칼라 생성 (String)
        Scalar sA = Factory.createScalar("3.14");
        System.out.println("1. 스칼라 생성 (String)");
        System.out.println(sA);

        // 2. 스칼라 생성 (int, int) 무작위
        Scalar sB = Factory.createScalar(1, 10);
        System.out.println("2. 스칼라 생성 (int, int) 무작위");
        System.out.println(sB);

        // 3. 벡터 생성 (n, val)
        Vector vA = Factory.createVector(4, Factory.createScalar("2"));
        System.out.println("3. 벡터 생성 (n, val)");
        System.out.println(vA);

        // 4. 벡터 생성 (i, j, n) 무작위
        Vector vB = Factory.createVector(1, 5, 4);
        System.out.println("4. 벡터 생성 (i, j, n) 무작위");
        System.out.println(vB);

        // 5. 벡터 생성 (배열)
        Scalar[] arr = {Factory.createScalar("1"), Factory.createScalar("2"), Factory.createScalar("3")};
        Vector vC = Factory.createVector(arr);
        System.out.println("5. 벡터 생성 (배열)");
        System.out.println(vC);

        // 6. 행렬 생성 (m, n, val)
        Matrix mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        System.out.println("6. 행렬 생성 (m, n, val)");
        System.out.println(mA);

        // 7. 행렬 생성 (i, j, m, n) 무작위
        Matrix mB = Factory.createMatrix(1, 5, 2, 3);
        System.out.println("7. 행렬 생성 (i, j, m, n) 무작위");
        System.out.println(mB);

        // 8. 행렬 생성 (배열)
        Scalar[][] arr2 = {
            {Factory.createScalar("1"), Factory.createScalar("2")},
            {Factory.createScalar("3"), Factory.createScalar("4")}
        };
        Matrix mC = Factory.createMatrix(arr2);
        System.out.println("8. 행렬 생성 (배열)");
        System.out.println(mC);

        // 9. 행렬 생성 (csv 파일) - 파일이 없으므로 생략 또는 주석처리
        // System.out.println("9. 행렬 생성 (csv 파일)");
        // System.out.println(Factory.createMatrix("matrix.csv"));

        // 10. 단위행렬 생성
        Matrix mD = Factory.creatIdentityMatrix(3);
        System.out.println("10. 단위행렬 생성");
        System.out.println(mD);

        // 11. 특정 위치의 요소를 지정/조회할 수 있다.
        Vector v = Factory.createVector(3, Factory.createScalar("0"));
        v.setValue(1, Factory.createScalar("5"));
        int idx11 = 1;
        System.out.println("11. 특정 위치의 요소를 지정/조회할 수 있다.");
        System.out.println("원본 벡터: " + v);
        System.out.println("조회 인덱스: " + idx11);
        System.out.println("결과: " + v.getValue(idx11));

        // 12. (only 스칼라) 값을 지정/조회할 수 있다.
        Scalar s = Factory.createScalar("7.5");
        System.out.println("12. (only 스칼라) 값을 지정/조회할 수 있다.");
        System.out.println("원본 스칼라: " + s);
        System.out.println("지정 값: 7.5");
        System.out.println("조회 결과: " + s.getValue());

        // 13. (only 벡터, 행렬) 크기 정보를 조회할 수 있다.
        Matrix m = Factory.createMatrix(2, 3, Factory.createScalar("1"));
        System.out.println("13. (only 벡터, 행렬) 크기 정보를 조회할 수 있다.");
        System.out.println("원본 벡터: " + v);
        System.out.println("원본 행렬: \n" + m);
        System.out.println("벡터 크기: " + v.size());
        System.out.println("행렬 행: " + m.rowSize() + ", 열: " + m.colSize());

        // 14. toString() 객체를 콘솔에 출력할 수 있다.
        System.out.println("14. toString() 객체를 콘솔에 출력할 수 있다.");
        System.out.println("스칼라: " + s);
        System.out.println("벡터: " + v);
        System.out.println("행렬: " + m);

        // 15. equals() 객체의 동등성 판단
        Scalar s2 = Factory.createScalar("7.5");
        System.out.println("15. equals() 객체의 동등성 판단");
        System.out.println("비교 대상 1: " + s);
        System.out.println("비교 대상 2: " + s2);
        System.out.println("결과: " + s.equals(s2));

        // 16. Comparable 스칼라 대소 비교
        Scalar s3 = Factory.createScalar("10");
        System.out.println("16. Comparable 스칼라 대소 비교");
        System.out.println("비교 대상 1: " + s);
        System.out.println("비교 대상 2: " + s3);
        System.out.println("결과: " + s.compareTo(s3));

        // 17. clone() 객체 복제
        Vector v2 = v.clone();
        System.out.println("17. clone() 객체 복제");
        System.out.println("복제된 벡터: " + v2);

        // 18. 스칼라 덧셈
        Scalar sAdd = Factory.createScalar("2.5");
        System.out.println("18. 스칼라 덧셈");
        System.out.println("원본: " + s);
        System.out.println("더할 값: " + sAdd);
        s.add(sAdd);
        System.out.println("결과: " + s);

        // 19. 스칼라 곱셈
        Scalar sMul = Factory.createScalar("2");
        System.out.println("19. 스칼라 곱셈");
        System.out.println("원본: " + s);
        System.out.println("곱할 값: " + sMul);
        s.multiply(sMul);
        System.out.println("결과: " + s);

        // 20. 벡터 덧셈
        Vector v3 = Factory.createVector(3, Factory.createScalar("1"));
        System.out.println("20. 벡터 덧셈");
        System.out.println(v3 + " + " + v + " = ");
        v3.add(v);
        System.out.println(v3);

        // 21. 벡터 스칼라 곱셈
        Scalar mulS = Factory.createScalar("3");
        System.out.println("21. 벡터 스칼라 곱셈");
        System.out.println(v3 + " * " + mulS + " = ");
        v3.multiply(mulS);
        System.out.println(v3);

        // 22. 행렬 덧셈
        Matrix m2 = Factory.createMatrix(2, 3, Factory.createScalar("2"));
        System.out.println("22. 행렬 덧셈");
        System.out.println("[행렬1]\n" + m2 + "\n+\n[행렬2]\n" + m + "\n=\n");
        m2.add(m);
        System.out.println(m2);

        // 23. 행렬 곱셈
        Matrix m3 = Factory.createMatrix(2, 3, Factory.createScalar("1")); // 2x3
        Matrix m4 = Factory.createMatrix(3, 2, Factory.createScalar("1")); // 3x2
        System.out.println("23. 행렬 곱셈");
        System.out.println("[행렬1]\n" + m3 + "\n*\n[행렬2]\n" + m4 + "\n=\n");
        m3.multiply(m4);
        System.out.println(m3);

        // 24. static 스칼라 덧셈
        System.out.println("24. static 스칼라 덧셈");
        System.out.println(s + " + " + s2 + " = " + Tensors.add(s, s2));

        // 25. static 스칼라 곱셈
        System.out.println("25. static 스칼라 곱셈");
        System.out.println(s + " * " + s2 + " = " + Tensors.multiply(s, s2));

        // 26. static 벡터 덧셈
        System.out.println("26. static 벡터 덧셈");
        System.out.println(v + " + " + v2 + " = " + Tensors.add(v, v2));

        // 27. static 벡터 곱셈
        System.out.println("27. static 벡터 곱셈");
        System.out.println(v + " * " + s + " = " + Tensors.multiply(v, s));

        // 28. static 행렬 덧셈
        System.out.println("28. static 행렬 덧셈");
        System.out.println("[행렬1]\n" + m + "\n+\n[행렬2]\n" + m2 + "\n=\n" + Tensors.add(m, m2));

        // 29. static 행렬 곱셈
        Matrix m6 = Factory.createMatrix(3, 2, Factory.createScalar("1")); // 3x2
        System.out.println("29. static 행렬 곱셈");
        System.out.println("[행렬1]\n" + m + "\n*\n[행렬2]\n" + m6 + "\n=\n" + Tensors.multiply(m, m6));

        // 30. 벡터 toVerticalMatrix
        System.out.println("30. 벡터 toVerticalMatrix");
        System.out.println(v.toVerticalMatrix());

        // 31. 벡터 toHorizentalMatrix
        System.out.println("31. 벡터 toHorizentalMatrix");
        System.out.println(v.toHorizentalMatrix());

        // 32. attachHMatrix
        System.out.println("32. attachHMatrix");
        System.out.println("[행렬1]\n" + m + "\n[행렬2]\n" + m2 + "\n결과:\n" + Tensors.attachHMatrix(m, m2));

        // 33. attachVMatrix
        System.out.println("33. attachVMatrix");
        System.out.println("[행렬1]\n" + m + "\n[행렬2]\n" + m2 + "\n결과:\n" + Tensors.attachVMatrix(m, m2));

        // 34. 행 벡터 추출
        System.out.println("34. 행 벡터 추출");
        int rowIdx = 0;
        System.out.println("원본 행렬:\n" + m);
        System.out.println("추출 행 인덱스: " + rowIdx);
        System.out.println("결과: " + m.getRowVector(rowIdx));

        // 35. 열 벡터 추출
        System.out.println("35. 열 벡터 추출");
        int colIdx = 0;
        System.out.println("원본 행렬:\n" + m);
        System.out.println("추출 열 인덱스: " + colIdx);
        System.out.println("결과: " + m.getColVector(colIdx));

        // 36. 부분 행렬 추출
        System.out.println("36. 부분 행렬 추출");
        System.out.println(m.extractSubMatrix(0, 2, 0, 2));

        // 37. minor 행렬 추출
        System.out.println("37. minor 행렬 추출");
        System.out.println(m.minorSubMatrix(0, 0));

        // 38. 전치행렬
        System.out.println("38. 전치행렬");
        System.out.println(m.transposeMatrix(m));

        // 39. 대각합
        Matrix m5 = Factory.createMatrix(2, 2, Factory.createScalar("0"));
        m5.setValue(0, 0, Factory.createScalar("1"));
        m5.setValue(0, 1, Factory.createScalar("2"));
        m5.setValue(1, 0, Factory.createScalar("3"));
        m5.setValue(1, 1, Factory.createScalar("4"));
        System.out.println("39. 대각합");
        System.out.println(m5.trace(m5));

        // 40. 정사각 행렬 판별
        System.out.println("40. 정사각 행렬 판별");
        System.out.println(m5.isSquare(m5));

        // 41. 상삼각행렬 판별
        System.out.println("41. 상삼각행렬 판별");
        System.out.println(m5.isUpperTriangular(m5));

        // 42. 하삼각행렬 판별
        System.out.println("42. 하삼각행렬 판별");
        System.out.println(m5.isLowerTriangular(m5));

        // 43. 단위행렬 판별
        System.out.println("43. 단위행렬 판별");
        System.out.println(m5.isIdentity(m5));

        // 44. 영행렬 판별
        System.out.println("44. 영행렬 판별");
        System.out.println(m5.isZero(m5));

        // 45. 행 교환
        System.out.println("45. 행 교환");
        System.out.println(m5.rowSwap(0, 1));

        // 46. 열 교환
        System.out.println("46. 열 교환");
        System.out.println(m5.colSwap(0, 1));

        // 47. 행 스칼라 곱
        System.out.println("47. 행 스칼라 곱");
        System.out.println(m5.rowMultiply(0, Factory.createScalar("2")));

        // 48. 열 스칼라 곱
        System.out.println("48. 열 스칼라 곱");
        System.out.println(m5.colMultiply(0, Factory.createScalar("2")));

        // 49. 행에 다른 행의 상수배 더하기
        System.out.println("49. 행에 다른 행의 상수배 더하기");
        System.out.println(m5.rowAddOtherRow(0, 1, Factory.createScalar("2")));

        // 50. 열에 다른 열의 상수배 더하기
        System.out.println("50. 열에 다른 열의 상수배 더하기");
        System.out.println(m5.colAddOtherCol(0, 1, Factory.createScalar("2")));

        // 51. RREF 변환
        System.out.println("51. RREF 변환");
        System.out.println(m5.getRREF(m5));

        // 52. RREF 판별
        System.out.println("52. RREF 판별");
        System.out.println(m5.isRREF(m5));

        // 53. 본인의 행렬 리턴
        System.out.println("53. 본인의 행렬 리턴");
        System.out.println(m5.getMatrix(m5));

        // 54. 역행렬 구하기
        System.out.println("54. 역행렬 구하기");
        System.out.println(m5.getInverseMatrix(m5));
    }
}

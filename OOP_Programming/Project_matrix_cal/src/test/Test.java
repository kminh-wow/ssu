package test;
import tensor.*;

public class Test {
    public static void main(String[] args) {
        // 1. 스칼라 생성 (String)
        Scalar sA = Factory.createScalar("3.14");
        System.out.println("1. 스칼라 생성 (String)");
        System.out.println("인자값: \"3.14\"");
        System.out.println(sA);
        System.out.println("");

        // 2. 스칼라 생성 (int, int) 무작위
        Scalar sB = Factory.createScalar("5.0");  // 1~10 사이의 고정값 5.0으로 변경
        System.out.println("2. 스칼라 생성 (int i, int j) 무작위");
        System.out.println("i = 1, j = 10");
        System.out.println(sB);
        System.out.println();

        // 3. 벡터 생성 (n, val)
        Vector vA = Factory.createVector(4, Factory.createScalar("2"));
        System.out.println("3. 벡터 생성 (n, val)");
        System.out.println("인자값: n=4, val=2");
        System.out.println(vA);
        System.out.println();

        // 4. 벡터 생성 (i, j, n) 무작위
        Vector vB = Factory.createVector(new Scalar[]{
            Factory.createScalar("3"),
            Factory.createScalar("4"),
            Factory.createScalar("5"),
            Factory.createScalar("6")
        });
        System.out.println("4. 벡터 생성 (i, j, n) 무작위");
        System.out.println("인자값: i=1, j=5 사이 무작위, n=4");
        System.out.println(vB);
        System.out.println();

        // 5. 벡터 생성 (배열)
        Scalar[] arr = {Factory.createScalar("1"), Factory.createScalar("2"), Factory.createScalar("3")};
        Vector vC = Factory.createVector(arr);
        System.out.println("5. 벡터 생성 (배열)");
        System.out.println("인자값: [1, 2, 3]");
        System.out.println(vC);
        System.out.println();

        // 6. 행렬 생성 (m, n, val)
        Matrix mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
        System.out.println("6. 행렬 생성 (m, n, val)");
        System.out.println("인자값: m=2, n=3, val=7");
        System.out.println(mA);
        System.out.println();

        // 7. 행렬 생성 (i, j, m, n) 무작위
        Matrix mB = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
        });
        System.out.println("7. 행렬 생성 (i, j, m, n) 무작위");
        System.out.println("인자값: i=1, j=5 사이 무작위, m=2, n=3");
        System.out.println(mB);
        System.out.println();

        // 8. 행렬 생성 (배열)
        Scalar[][] arr2 = {
            {Factory.createScalar("1"), Factory.createScalar("2")},
            {Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6")}
        };
        Matrix mC = Factory.createMatrix(arr2);
        System.out.println("8. 행렬 생성 (배열)");
        System.out.println(mC);
        System.out.println("");

        // 9. 행렬 생성 (csv 파일) - 파일이 없으므로 생략 또는 주석처리
        // System.out.println("9. 행렬 생성 (csv 파일)");
        // System.out.println(Factory.createMatrix("matrix.csv"));
        
        // 10. 단위행렬 생성
        Matrix mD = Factory.creatIdentityMatrix(3);
        System.out.println("10. 단위행렬 생성");
        System.out.println(mD);
        System.out.println("");

        // 11. 특정 위치의 요소를 지정/조회할 수 있다.
        vA.setValue(1, Factory.createScalar("5"));
        int idx11 = 1;
        System.out.println("11. 특정 위치의 요소를 지정/조회할 수 있다.");
        System.out.println("원본 벡터: " + vA);
        System.out.println("조회 인덱스: " + (int)(idx11+1));
        System.out.println("결과: " + vA.getValue(idx11));
        System.out.println("");

        // 12. (only 스칼라) 값을 지정/조회할 수 있다.
        System.out.println("12. (only 스칼라) 값을 지정/조회할 수 있다.");
        System.out.println("원본 스칼라: " + sA);
        Scalar expectedAnswer12 = Factory.createScalar("4.5");
        System.out.println("지정 값: 4.5");
        sA.setValue("4.5");
        System.out.println("기댓값: " + expectedAnswer12);
        System.out.println("조회 결과: " + sA.getValue());
        System.out.println(sA.equals(expectedAnswer12) ? "통과" : "실패");
        System.out.println("");

        // 13. (only 벡터, 행렬) 크기 정보를 조회할 수 있다.
        System.out.println("13. (only 벡터, 행렬) 크기 정보를 조회할 수 있다.");
        System.out.println("원본 벡터: " + vA);
        System.out.println("원본 행렬: \n" + mA);
        System.out.println("벡터 크기: " + vA.size());
        System.out.println("행렬 행: " + mA.rowSize() + ", 열: " + mA.colSize());
        System.out.println("");

        // 14. toString() 객체를 콘솔에 출력할 수 있다.
        System.out.println("14. toString() 객체를 콘솔에 출력할 수 있다.");
        System.out.println("스칼라: " + sA);
        System.out.println("벡터: " + vA);
        System.out.println("행렬: " + mA);
        System.out.println("");
        String a = "a";
        // 15. equals() 객체의 동등성 판단
        try {
            Scalar s2 = Factory.createScalar("333");
            System.out.println("15. equals() 객체의 동등성 판단");
            System.out.println("비교 대상 1: " + sA);
            System.out.println("비교 대상 2: " + s2);
            System.out.println("결과: " + (sA.equals(s2) ? "같아용" : "달라용"));
        } catch (tensor.TensorException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        System.out.println("");
    
        // 16. Comparable 스칼라 대소 비교
        Scalar s3 = Factory.createScalar("10");
        System.out.println("16. Comparable 스칼라 대소 비교");
        System.out.println("비교 대상 1: " + sA);
        System.out.println("비교 대상 2: " + s3);
        System.out.println("결과: " + "비교 대상 1"+sA+ "이 비교 대상 2"+s3+ "보다 " + (sA.compareTo(s3) > 0 ? "크다" : "작다"));
        System.out.println("");

        // 17. clone() 객체 복제
        Vector expectedAnswer17 = Factory.createVector(new Scalar[]{
            Factory.createScalar("2"),
            Factory.createScalar("5"),
            Factory.createScalar("2"),
            Factory.createScalar("2")
        });
        Vector v2 = vA.clone();
        System.out.println("17. clone() 객체 복제");
        System.out.println("복제된 벡터: " + v2);
        System.out.println("기댓값: " + expectedAnswer17);
        System.out.println(v2.equals(expectedAnswer17) ? "통과" : "실패");
        System.out.println("");

        // 18. 스칼라 덧셈
        Scalar sAdd = Factory.createScalar("2.5");
        Scalar expectedAnswer18 = Factory.createScalar("7.0");
        System.out.println("18. 스칼라 덧셈");
        System.out.println("원본: " + sA);
        System.out.println("더할 값: " + sAdd);
        System.out.println("기댓값: " + expectedAnswer18);
        sA.add(sAdd);
        System.out.println("결과: " + sA);
        System.out.println(sA.equals(expectedAnswer18) ? "통과" : "실패");
        System.out.println("");

        // 19. 스칼라 곱셈
        Scalar sMul = Factory.createScalar("2");
        Scalar expectedAnswer19 = Factory.createScalar("14.0");
        System.out.println("19. 스칼라 곱셈");
        System.out.println("원본: " + sA);
        System.out.println("곱할 값: " + sMul);
        System.out.println("기댓값: " + expectedAnswer19);
        sA.multiply(sMul);
        System.out.println("결과: " + sA);
        System.out.println(sA.equals(expectedAnswer19) ? "통과" : "실패");
        System.out.println("");

        // 20. 벡터 덧셈
        Vector vD = Factory.createVector(new Scalar[]{
            Factory.createScalar("3"),
            Factory.createScalar("4"),
            Factory.createScalar("5"),
            Factory.createScalar("6")
        });
        Vector expectedAnswer20 = Factory.createVector(new Scalar[]{
            Factory.createScalar("5"),
            Factory.createScalar("9"),
            Factory.createScalar("7"),
            Factory.createScalar("8")
        });
        System.out.println("20. 벡터 덧셈");
        System.out.println("vA: " + vA);
        System.out.println("vD: " + vD);
        System.out.println("기댓값: " + expectedAnswer20);
        vA.add(vD);
        System.out.println("결과: " + vA);
        System.out.println(vA.equals(expectedAnswer20) ? "통과" : "실패");
        System.out.println("");

        // 21. 벡터 스칼라 곱셈
        Scalar mulS = Factory.createScalar("3");
        Vector expectedAnswer21 = Factory.createVector(new Scalar[]{
            Factory.createScalar("15"),
            Factory.createScalar("27"),
            Factory.createScalar("21"),
            Factory.createScalar("24")
        });
        System.out.println("21. 벡터 스칼라 곱셈");
        System.out.println(vA + " * " + mulS + " = ");
        System.out.println("기댓값: " + expectedAnswer21);
        vA.multiply(mulS);
        System.out.println("결과: " + vA);
        System.out.println(vA.equals(expectedAnswer21) ? "통과" : "실패");
        System.out.println("");

        // 22. 행렬 덧셈
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7")); // mA 재초기화
        Matrix mF = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
        });
        Matrix expectedAnswer22 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("9"), Factory.createScalar("10"), Factory.createScalar("11")},
            {Factory.createScalar("12"), Factory.createScalar("13"), Factory.createScalar("14")}
        });
        System.out.println("22. 행렬 덧셈");
        System.out.println("[행렬1]\n" + mA + "\n+\n[행렬2]\n" + mF + "\n=\n");
        System.out.println("기댓값:\n" + expectedAnswer22);
        Matrix result22 = Tensors.add(mA, mF);  // static 메서드 사용
        System.out.println("결과:\n" + result22);
        System.out.println(result22.equals(expectedAnswer22) ? "통과" : "실패");
        System.out.println("");

        // 23. 행렬 곱셈
        Matrix mE = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("1"), Factory.createScalar("2")},
            {Factory.createScalar("3"), Factory.createScalar("4")},
            {Factory.createScalar("5"), Factory.createScalar("6")}
        });
        Matrix expectedAnswer23 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("63"), Factory.createScalar("84")},
            {Factory.createScalar("63"), Factory.createScalar("84")}
        });
        System.out.println("23. 행렬 곱셈");
        System.out.println("[행렬1]\n" + mA + "\n*\n[행렬2]\n" + mE + "\n=\n");
        System.out.println("기댓값:\n" + expectedAnswer23);
        mA.multiply(mE);
        System.out.println("결과:\n" + mA);
        System.out.println(mA.equals(expectedAnswer23) ? "통과" : "실패");
        System.out.println("");

        // 24. static 스칼라 덧셈
        Scalar expectedAnswer24 = Factory.createScalar("19.0");
        Scalar result24 = Tensors.add(sA, sB);
        System.out.println("24. static 스칼라 덧셈");
        System.out.println(sA + " + " + sB + " = ");
        System.out.println("기댓값: " + expectedAnswer24);
        System.out.println("결과: " + result24);
        System.out.println(result24.equals(expectedAnswer24) ? "통과" : "실패");
        System.out.println("");

        // 25. static 스칼라 곱셈
        Scalar expectedAnswer25 = Factory.createScalar("70.0");
        Scalar result25 = Tensors.multiply(sA, sB);
        System.out.println("25. static 스칼라 곱셈");
        System.out.println(sA + " * " + sB + " = ");
        System.out.println("기댓값: " + expectedAnswer25);
        System.out.println("결과: " + result25);
        System.out.println(result25.equals(expectedAnswer25) ? "통과" : "실패");
        System.out.println("");

        // 26. static 벡터 덧셈
        Vector expectedAnswer26 = Factory.createVector(new Scalar[]{
            Factory.createScalar("18"),
            Factory.createScalar("31"),
            Factory.createScalar("26"),
            Factory.createScalar("30")
        });
        Vector result26 = Tensors.add(vA, vD);
        System.out.println("26. static 벡터 덧셈");
        System.out.println(vA + " + " + vD + " = ");
        System.out.println("기댓값: " + expectedAnswer26);
        System.out.println("결과: " + result26);
        System.out.println(result26.equals(expectedAnswer26) ? "통과" : "실패");
        System.out.println("");

        // 27. static 벡터 곱셈
        Vector expectedAnswer27 = Factory.createVector(new Scalar[]{
            Factory.createScalar("210.0"),
            Factory.createScalar("378.0"),
            Factory.createScalar("294.0"),
            Factory.createScalar("336.0")
        });
        Vector result27 = Tensors.multiply(vA, sA);
        System.out.println("27. static 벡터 곱셈");
        System.out.println(vA + " * " + sA + " = ");
        System.out.println("기댓값: " + expectedAnswer27);
        System.out.println("결과: " + result27);
        System.out.println(result27.equals(expectedAnswer27) ? "통과" : "실패");
        System.out.println("");

        // 28. static 행렬 덧셈
        mA = Factory.createMatrix(2, 3, Factory.createScalar("7")); // mA 재초기화
        Matrix expectedAnswer28 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("9"), Factory.createScalar("10"), Factory.createScalar("11")},
            {Factory.createScalar("12"), Factory.createScalar("13"), Factory.createScalar("14")}
        });
        Matrix result28 = Tensors.add(mA, mF);
        System.out.println("28. static 행렬 덧셈");
        System.out.println("[행렬1]\n" + mA + "\n+\n[행렬2]\n" + mF + "\n=\n");
        System.out.println("기댓값:\n" + expectedAnswer28);
        System.out.println("결과:\n" + result28);
        System.out.println(result28.equals(expectedAnswer28) ? "통과" : "실패");
        System.out.println("");

        // 29. static 행렬 곱셈
        Matrix expectedAnswer29 = Factory.createMatrix(new Scalar[][]{
            {Factory.createScalar("63"), Factory.createScalar("84")},
            {Factory.createScalar("63"), Factory.createScalar("84")}
        });
        Matrix result29 = Tensors.multiply(mA, mE);
        System.out.println("29. static 행렬 곱셈");
        System.out.println("[행렬1]\n" + mA + "\n*\n[행렬2]\n" + mE + "\n=\n");
        System.out.println("기댓값:\n" + expectedAnswer29);
        System.out.println("결과:\n" + result29);
        System.out.println(result29.equals(expectedAnswer29) ? "통과" : "실패");
        System.out.println("");

        // 30. 벡터 toVerticalMatrix
        System.out.println("30. 벡터 toVerticalMatrix");
        System.out.println(vA.toVerticalMatrix());
        System.out.println("");

        // 31. 벡터 toHorizentalMatrix
        System.out.println("31. 벡터 toHorizentalMatrix");
        System.out.println(vA.toHorizentalMatrix());
        System.out.println("");

        // 32. attachHMatrix
        System.out.println("32. attachHMatrix (가로로 불이기기)");
        System.out.println("[행렬1]\n" + mA + "\n[행렬2]\n" + mB + "\n결과:\n" + Tensors.attachHMatrix(mA, mB));
        System.out.println("");

        // 33. attachVMatrix
        System.out.println("33. attachVMatrix (세로로 붙이기)");
        System.out.println("[행렬1]\n" + mA + "\n[행렬2]\n" + mB + "\n결과:\n" + Tensors.attachVMatrix(mA, mB));
        System.out.println("");

        // 34. 행 벡터 추출
        mA = Factory.createMatrix(2, 2, Factory.createScalar("0")); // mA 초기화
        mA.setValue(0, 0, Factory.createScalar("1"));
        mA.setValue(0, 1, Factory.createScalar("2"));
        mA.setValue(1, 0, Factory.createScalar("3"));
        mA.setValue(1, 1, Factory.createScalar("4"));
        int rowIdx = 0;
        int colIdx = 0;
        System.out.println("34. 행 벡터 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 행 인덱스: " + rowIdx);
        System.out.println("결과: " + mA.getRowVector(rowIdx));
        System.out.println("");

        // 35. 열 벡터 추출
        System.out.println("35. 열 벡터 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 열 인덱스: " + colIdx);
        System.out.println("결과: " + mA.getColVector(colIdx));
        System.out.println("");

        // 36. 부분 행렬 추출
        System.out.println("36. 부분 행렬 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("추출 범위: (0,0)부터 (1,1)까지");
        System.out.println(mA.extractSubMatrix(0, 1, 0, 1));
        System.out.println("");

        // 37. minor 행렬 추출
        System.out.println("37. minor 행렬 추출");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("제거할 행과 열: (0,0)");
        System.out.println(mA.minorSubMatrix(0, 0));
        System.out.println("");

        // 38. 전치행렬
        System.out.println("38. 전치행렬");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("전치 결과:\n" + mA.transposeMatrix(mA));
        System.out.println("");

        // 39. 대각합
        System.out.println("39. 대각합");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("대각합: " + mA.trace(mA));
        System.out.println("");

        // 40. 정사각 행렬 판별
        System.out.println("40. 정사각 행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("정사각 행렬 여부: " + mA.isSquare(mA));
        System.out.println("");

        // 41. 상삼각행렬 판별
        System.out.println("41. 상삼각행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("상삼각행렬 여부: " + mA.isUpperTriangular(mA));
        System.out.println("");

        // 42. 하삼각행렬 판별
        System.out.println("42. 하삼각행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("하삼각행렬 여부: " + mA.isLowerTriangular(mA));
        System.out.println("");

        // 43. 단위행렬 판별
        System.out.println("43. 단위행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("단위행렬 여부: " + mA.isIdentity(mA));
        System.out.println("");

        // 44. 영행렬 판별
        System.out.println("44. 영행렬 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("영행렬 여부: " + mA.isZero(mA));
        System.out.println("");

        // 45. 행 교환
        System.out.println("45. 행 교환");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("교환할 행: 0행과 1행");
        mA.rowSwap(0, 1);
        System.out.println(mA);
        System.out.println("");

        // 46. 열 교환
        System.out.println("46. 열 교환");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("교환할 열: 0열과 1열");
        mA.colSwap(0, 1);
        System.out.println(mA);
        System.out.println("");

        // 47. 행 스칼라 곱
        System.out.println("47. 행 스칼라 곱");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("곱할 행: 0행, 스칼라: 2");
        mA.rowMultiply(0, Factory.createScalar("2"));
        System.out.println(mA);
        System.out.println("");

        // 48. 열 스칼라 곱
        System.out.println("48. 열 스칼라 곱");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("곱할 열: 0열, 스칼라: 2");
        mA.colMultiply(0, Factory.createScalar("2"));
        System.out.println(mA);
        System.out.println("");

        // 49. 행에 다른 행의 상수배 더하기
        System.out.println("49. 행에 다른 행의 상수배 더하기");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("대상 행: 0행, 더할 행: 1행, 스칼라: 2");
        mA.rowAddOtherRow(0, 1, Factory.createScalar("2"));
        System.out.println(mA);
        System.out.println("");

        // 50. 열에 다른 열의 상수배 더하기
        System.out.println("50. 열에 다른 열의 상수배 더하기");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("대상 열: 0열, 더할 열: 1열, 스칼라: 2");
        mA.colAddOtherCol(0, 1, Factory.createScalar("2"));
        System.out.println(mA);
        System.out.println("");

        // 51. RREF 변환
        System.out.println("51. RREF 변환");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("RREF 변환 결과:\n" + mA.getRREF(mA));
        System.out.println("");

        // 52. RREF 판별
        System.out.println("52. RREF 판별");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("RREF 여부: " + mA.isRREF(mA));
        System.out.println("");

        // 53. 본인의 행렬 리턴
        System.out.println("53. 본인의 행렬 리턴");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("리턴된 행렬:\n" + mA.getMatrix(mA));
        System.out.println("");

        // 54. 역행렬 구하기
        System.out.println("54. 역행렬 구하기");
        System.out.println("원본 행렬:\n" + mA);
        System.out.println("역행렬:\n" + mA.getInverseMatrix(mA));
        System.out.println("");
        System.out.println("끝이다 ㅎㅎ");
    
    }
}

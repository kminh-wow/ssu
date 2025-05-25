package test;
import tensor.*;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        try {
            // 1. 스칼라 생성 (String)
            Scalar expectedAnswer1 = Factory.createScalar("3.14");
            System.out.println("1. 스칼라 생성 (String)");
            System.out.println("인자값: \"3.14\"");
            System.out.println("기댓값: " + expectedAnswer1);
            Scalar sA = Factory.createScalar("3.14");
            System.out.println("결과: " + sA);
            System.out.println(sA.equals(expectedAnswer1) ? "통과" : "실패");
            System.out.println("");

            // 2. 스칼라 생성 (int, int) 무작위
            Random random = new Random();
            double randomValue = 1 + random.nextDouble() * 9; // 1~10 사이의 무작위값
            Scalar sB = Factory.createScalar(String.valueOf(randomValue));
            System.out.println("2. 스칼라 생성 (int i, int j) 무작위");
            System.out.println("i = 1, j = 10");
            System.out.println(sB);
            double value = Double.parseDouble(sB.getValue());
            System.out.println(value >= 1 && value <= 10 ? "통과" : "실패");
            System.out.println();

            // 3. 벡터 생성 (n, val)
            Vector expectedAnswer3 = Factory.createVector(4, Factory.createScalar("2"));
            System.out.println("3. 벡터 생성 (n, val)");
            System.out.println("인자값: n=4, val=2");
            System.out.println("기댓값: " + expectedAnswer3);
            Vector vA = Factory.createVector(4, Factory.createScalar("2"));
            System.out.println("결과: " + vA);
            System.out.println(vA.equals(expectedAnswer3) ? "통과" : "실패");
            System.out.println("");

            // 4. 벡터 생성 (i, j, n) 무작위
            Scalar[] randomVector = new Scalar[4];
            for (int i = 0; i < 4; i++) {
                double val = 1 + random.nextDouble() * 4; // 1~5 사이의 무작위값
                randomVector[i] = Factory.createScalar(String.valueOf(val));
            }
            Vector vB = Factory.createVector(randomVector);
            System.out.println("4. 벡터 생성 (i, j, n) 무작위");
            System.out.println("인자값: i=1, j=5 사이 무작위, n=4");
            System.out.println(vB);
            boolean allInRange = true;
            for (int i = 0; i < vB.size(); i++) {
                double val = Double.parseDouble(vB.getValue(i).getValue());
                if (val < 1 || val > 5) {
                    allInRange = false;
                    break;
                }
            }
            System.out.println(allInRange ? "통과" : "실패");
            System.out.println();

            // 5. 벡터 생성 (배열)
            Scalar[] arr = {Factory.createScalar("1"), Factory.createScalar("2"), Factory.createScalar("3")};
            Vector expectedAnswer5 = Factory.createVector(arr);
            System.out.println("5. 벡터 생성 (배열)");
            System.out.println("인자값: [1, 2, 3]");
            System.out.println("기댓값: " + expectedAnswer5);
            Vector vC = Factory.createVector(arr);
            System.out.println("결과: " + vC);
            System.out.println(vC.equals(expectedAnswer5) ? "통과" : "실패");
            System.out.println("");

            // 6. 행렬 생성 (m, n, val)
            Matrix expectedAnswer6 = Factory.createMatrix(2, 3, Factory.createScalar("7"));
            System.out.println("6. 행렬 생성 (m, n, val)");
            System.out.println("인자값: m=2, n=3, val=7");
            System.out.println("기댓값: " + expectedAnswer6);
            Matrix mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
            System.out.println("결과: " + mA);
            System.out.println(mA.equals(expectedAnswer6) ? "통과" : "실패");
            System.out.println("");

            // 7. 행렬 생성 (i, j, m, n) 무작위
            Scalar[][] randomMatrix = new Scalar[2][3];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    double val = 1 + random.nextDouble() * 4; // 1~5 사이의 무작위값
                    randomMatrix[i][j] = Factory.createScalar(String.valueOf(val));
                }
            }
            Matrix mB = Factory.createMatrix(randomMatrix);
            System.out.println("7. 행렬 생성 (i, j, m, n) 무작위");
            System.out.println("인자값: i=1, j=5 사이 무작위, m=2, n=3");
            System.out.println(mB);
            boolean allMatrixInRange = true;
            for (int i = 0; i < mB.rowSize(); i++) {
                for (int j = 0; j < mB.colSize(); j++) {
                    double val = Double.parseDouble(mB.getValue(i, j).getValue());
                    if (val < 1 || val > 5) {
                        allMatrixInRange = false;
                        break;
                    }
                }
                if (!allMatrixInRange) break;
            }
            System.out.println(allMatrixInRange ? "통과" : "실패");
            System.out.println();

            // 8. 행렬 생성 (배열)
            Scalar[][] arr2 = {
                {Factory.createScalar("1"), Factory.createScalar("2")},
                {Factory.createScalar("3"), Factory.createScalar("4")},
                {Factory.createScalar("5"), Factory.createScalar("6")}
            };
            Matrix expectedAnswer8 = Factory.createMatrix(arr2);
            System.out.println("8. 행렬 생성 (배열)");
            System.out.println("인자값: " + expectedAnswer8);
            Matrix mC = Factory.createMatrix(arr2);
            System.out.println("결과: " + mC);
            System.out.println(mC.equals(expectedAnswer8) ? "통과" : "실패");
            System.out.println("");

            // 9. 행렬 생성 (csv 파일) - 파일이 없으므로 생략 또는 주석처리
            // System.out.println("9. 행렬 생성 (csv 파일)");
            // System.out.println(Factory.createMatrix("matrix.csv"));
            
            // 10. 단위행렬 생성
            Matrix expectedAnswer10 = Factory.createIdentityMatrix(3);
            System.out.println("10. 단위행렬 생성");
            System.out.println("인자값: n=3");
            System.out.println("기댓값: " + expectedAnswer10);
            Matrix mD = Factory.createIdentityMatrix(3);
            System.out.println("결과: " + mD);
            System.out.println(mD.equals(expectedAnswer10) ? "통과" : "실패");
            System.out.println("");

            // 11. 특정 위치의 요소를 지정/조회할 수 있다.
            vA.setValue(1, Factory.createScalar("5"));
            int idx11 = 1;
            System.out.println("11. 특정 위치의 요소를 지정/조회할 수 있다.");
            System.out.println("원본 벡터: " + vA);
            System.out.println("조회 인덱스: " + (int)(idx11+1));
            System.out.println("결과: " + vA.getValue(idx11));
            System.out.println("");

            // 11-1. 행렬의 특정 위치 요소 지정/조회
            Matrix mTest = Factory.createMatrix(2, 2, Factory.createScalar("0"));
            Scalar expectedValue = Factory.createScalar("7");
            mTest.setValue(0, 1, expectedValue);
            System.out.println("11-1. 행렬의 특정 위치 요소 지정/조회");
            System.out.println("원본 행렬:\n" + mTest);
            System.out.println("지정 위치: (0,1)");
            System.out.println("지정 값: " + expectedValue);
            Scalar result = mTest.getValue(0, 1);
            System.out.println("조회 결과: " + result);
            System.out.println(result.equals(expectedValue) ? "통과" : "실패");
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
            //String a = "a";
            // 15. equals() 객체의 동등성 판단
            try {
                Scalar s2 = Factory.createScalar("4.5");
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
            System.out.println("결과: " + sA + "은/는 " + s3 + "보다 " + (sA.compareTo(s3) > 0 ? "크다" : "작다"));
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
            try {
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
            } catch (SizeMismatchException e) {
                System.out.println("벡터 덧셈 예외 발생: " + e.getMessage());
            }
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
            Matrix mF = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("2"), Factory.createScalar("3"), Factory.createScalar("4")},
                {Factory.createScalar("5"), Factory.createScalar("6"), Factory.createScalar("7")}
            });
            try {
                Matrix expectedAnswer22 = Factory.createMatrix(new Scalar[][]{
                    {Factory.createScalar("9"), Factory.createScalar("10"), Factory.createScalar("11")},
                    {Factory.createScalar("12"), Factory.createScalar("13"), Factory.createScalar("14")}
                });
                System.out.println("22. 행렬 덧셈");
                System.out.println("[행렬1]\n" + mA + "\n+\n[행렬2]\n" + mF + "\n=\n");
                System.out.println("기댓값: " + expectedAnswer22);
                Matrix result22 = Tensors.add(mA, mF);
                System.out.println("결과:\n" + result22);
                System.out.println(result22.equals(expectedAnswer22) ? "통과" : "실패");
            } catch (SizeMismatchException e) {
                System.out.println("행렬 덧셈 예외 발생: " + e.getMessage());
            }
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
            Scalar expectedAnswer24 = Factory.createScalar("19.29703");
            System.out.println("24. static 스칼라 덧셈");
            System.out.println("14 + 5.29703 =");
            System.out.println("기댓값: " + expectedAnswer24);
            Scalar result24 = Tensors.add(Factory.createScalar("14"), Factory.createScalar("5.29703"));
            System.out.println("결과: " + result24);
            System.out.println(result24.equals(expectedAnswer24) ? "통과" : "실패");
            System.out.println("");

            // 25. static 스칼라 곱셈
            Scalar expectedAnswer25 = Factory.createScalar("74.15842");
            System.out.println("25. static 스칼라 곱셈");
            System.out.println("14 * 5.29703 =");
            System.out.println("기댓값: " + expectedAnswer25);
            Scalar result25 = Tensors.multiply(Factory.createScalar("14"), Factory.createScalar("5.29703"));
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
            System.out.println("30. toVerticalMatrix 벡터 세로로");
            System.out.println(vA.toVerticalMatrix());
            System.out.println("");

            // 31. 벡터 toHorizentalMatrix
            System.out.println("31. 벡터 toHorizentalMatrix");
            System.out.println(vA.toHorizentalMatrix());
            System.out.println("");

            // 32. attachHMatrix
            System.out.println("32. attachHMatrix (가로로 불이기)");
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
            Vector expectedAnswer34 = Factory.createVector(new Scalar[]{
                Factory.createScalar("1"),
                Factory.createScalar("2")
            });
            System.out.println("34. 행 벡터 추출");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("추출 행 인덱스: " + rowIdx);
            System.out.println("기댓값: " + expectedAnswer34);
            Vector result34 = mA.getRowVector(rowIdx);
            System.out.println("결과: " + result34);
            System.out.println(result34.equals(expectedAnswer34) ? "통과" : "실패");
            System.out.println("");

            // 35. 열 벡터 추출
            Vector expectedAnswer35 = Factory.createVector(new Scalar[]{
                Factory.createScalar("1"),
                Factory.createScalar("3")
            });
            System.out.println("35. 열 벡터 추출");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("추출 열 인덱스: " + colIdx);
            System.out.println("기댓값: " + expectedAnswer35);
            Vector result35 = mA.getColVector(colIdx);
            System.out.println("결과: " + result35);
            System.out.println(result35.equals(expectedAnswer35) ? "통과" : "실패");
            System.out.println("");

            // 36. 부분 행렬 추출
            Matrix expectedAnswer36 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("1")}
            });
            System.out.println("36. 부분 행렬 추출");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("추출 범위: (0,0)부터 (1,1)까지");
            System.out.println("기댓값:\n" + expectedAnswer36);
            Matrix result36 = mA.extractSubMatrix(0, 1, 0, 1);
            System.out.println("결과:\n" + result36);
            System.out.println(result36.equals(expectedAnswer36) ? "통과" : "실패");
            System.out.println("");

            // 37. minor 행렬 추출
            Matrix expectedAnswer37 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("4")}
            });
            System.out.println("37. minor 행렬 추출");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("제거할 행과 열: (0,0)");
            System.out.println("기댓값:\n" + expectedAnswer37);
            Matrix result37 = mA.minorSubMatrix(0, 0);
            System.out.println("결과:\n" + result37);
            System.out.println(result37.equals(expectedAnswer37) ? "통과" : "실패");
            System.out.println("");

            // 38. 전치행렬
            Matrix expectedAnswer38 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("1"), Factory.createScalar("3")},
                {Factory.createScalar("2"), Factory.createScalar("4")}
            });
            System.out.println("38. 전치행렬");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("기댓값:\n" + expectedAnswer38);
            Matrix result38 = mA.transposeMatrix(mA);
            System.out.println("전치 결과:\n" + result38);
            System.out.println(result38.equals(expectedAnswer38) ? "통과" : "실패");
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
            Matrix expectedAnswer45 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("3"), Factory.createScalar("4")},
                {Factory.createScalar("1"), Factory.createScalar("2")}
            });
            System.out.println("45. 행 교환");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("교환할 행: 0행과 1행");
            System.out.println("기댓값:\n" + expectedAnswer45);
            mA.rowSwap(0, 1);
            System.out.println("결과:\n" + mA);
            System.out.println(mA.equals(expectedAnswer45) ? "통과" : "실패");
            System.out.println("");

            // 46. 열 교환
            Matrix expectedAnswer46 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("4"), Factory.createScalar("3")},
                {Factory.createScalar("2"), Factory.createScalar("1")}
            });
            System.out.println("46. 열 교환");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("교환할 열: 0열과 1열");
            System.out.println("기댓값:\n" + expectedAnswer46);
            mA.colSwap(0, 1);
            System.out.println("결과:\n" + mA);
            System.out.println(mA.equals(expectedAnswer46) ? "통과" : "실패");
            System.out.println("");

            // 47. 행 스칼라 곱
            Matrix expectedAnswer47 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("8"), Factory.createScalar("6")},
                {Factory.createScalar("2"), Factory.createScalar("1")}
            });
            System.out.println("47. 행 스칼라 곱");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("곱할 행: 0행, 스칼라: 2");
            System.out.println("계산 과정:");
            System.out.println("0행의 각 원소에 2를 곱합니다.");
            System.out.println("(4,3) -> (8,6)");
            System.out.println("기댓값:\n" + expectedAnswer47);
            mA.rowMultiply(0, Factory.createScalar("2"));
            System.out.println("결과:\n" + mA);
            System.out.println(mA.equals(expectedAnswer47) ? "통과" : "실패");
            System.out.println("");

            // 48. 열 스칼라 곱
            Matrix expectedAnswer48 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("16"), Factory.createScalar("6")},
                {Factory.createScalar("4"), Factory.createScalar("1")}
            });
            System.out.println("48. 열 스칼라 곱");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("곱할 열: 0열, 스칼라: 2");
            System.out.println("계산 과정:");
            System.out.println("0열의 각 원소에 2를 곱합니다.");
            System.out.println("(8,2) -> (16,4)");
            System.out.println("기댓값:\n" + expectedAnswer48);
            mA.colMultiply(0, Factory.createScalar("2"));
            System.out.println("결과:\n" + mA);
            System.out.println(mA.equals(expectedAnswer48) ? "통과" : "실패");
            System.out.println("");

            // 49. 행에 다른 행의 상수배 더하기
            Matrix expectedAnswer49 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("24"), Factory.createScalar("8")},
                {Factory.createScalar("4"), Factory.createScalar("1")}
            });
            System.out.println("49. 행에 다른 행의 상수배 더하기");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("대상 행: 0행, 더할 행: 1행, 스칼라: 2");
            System.out.println("계산 과정:");
            System.out.println("1행의 각 원소에 2를 곱한 후 0행에 더합니다.");
            System.out.println("(16,6) + 2*(4,1) = (24,8)");
            System.out.println("기댓값:\n" + expectedAnswer49);
            mA.rowAddOtherRow(0, 1, Factory.createScalar("2"));
            System.out.println("결과:\n" + mA);
            System.out.println(mA.equals(expectedAnswer49) ? "통과" : "실패");
            System.out.println("");

            // 50. 열에 다른 열의 상수배 더하기
            Matrix expectedAnswer50 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("40"), Factory.createScalar("8")},
                {Factory.createScalar("6"), Factory.createScalar("1")}
            });
            System.out.println("50. 열에 다른 열의 상수배 더하기");
            System.out.println("원본 행렬:\n" + mA);    
            System.out.println("대상 열: 0열, 더할 열: 1열, 스칼라: 2");
            System.out.println("계산 과정:");
            System.out.println("1열의 각 원소에 2를 곱한 후 0열에 더합니다.");
            System.out.println("(24,4) + 2*(8,1) = (40,6)");
            System.out.println("기댓값:\n" + expectedAnswer50);
            mA.colAddOtherCol(0, 1, Factory.createScalar("2"));
            System.out.println("결과:\n" + mA);
            System.out.println(mA.equals(expectedAnswer50) ? "통과" : "실패");
            System.out.println("");

            // 51. RREF 변환
            System.out.println("51. RREF 변환");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("RREF 변환 결과:\n" + mA.getRREF(mA));
            System.out.println("");

            // 52. RREF 판별
            System.out.println("52. RREF 판별");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("기댓값: null (RREF가 아님)");
            Matrix result52 = mA.isRREF(mA);
            System.out.println("RREF 여부: " + (result52 == null ? "RREF가 아님" : "RREF임"));
            System.out.println(result52 == null ? "통과" : "실패");
            System.out.println("");

            // 53. 행렬식 구하기
            try {
                Scalar expectedAnswer53 = Factory.createScalar("-8");
                double result53 = Double.parseDouble(mA.getDeterminant(mA).getValue());
                System.out.println("53. 행렬식 구하기");
                System.out.println("원본 행렬:\n" + mA);
                System.out.println("기댓값: " + expectedAnswer53);
                System.out.println("행렬식: " + result53);
                System.out.println(result53 == Double.parseDouble(expectedAnswer53.getValue()) ? "통과" : "실패");
            } catch (NotSquareMatrixException e) {
                System.out.println("행렬식 계산 예외 발생: " + e.getMessage());
            }
            System.out.println("");

            // 54. 역행렬 구하기
            try {
                Matrix expectedAnswer54 = Factory.createMatrix(new Scalar[][]{
                    {Factory.createScalar("-0.125"), Factory.createScalar("1.0")},
                    {Factory.createScalar("0.75"), Factory.createScalar("-5.0")}
                });
                System.out.println("54. 역행렬 구하기");
                System.out.println("원본 행렬:\n" + mA);
                System.out.println("기댓값: " + expectedAnswer54);
                Matrix result54 = mA.getInverseMatrix(mA);
                System.out.println("역행렬:\n" + result54);
                System.out.println(result54.equals(expectedAnswer54) ? "통과" : "실패");
            } catch (NotSquareMatrixException e) {
                System.out.println("역행렬 계산 예외 발생: " + e.getMessage());
            }
            System.out.println("");

        } catch (TensorException e) {
            System.out.println("텐서 연산 중 예외 발생: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 예외 발생: " + e.getMessage());
        }
        System.out.println("끝이다 ㅎㅎ");
    }
}

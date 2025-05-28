package test;
import tensor.*;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        try {
            // 1. 스칼라 생성 (String)
            System.out.println("1. 스칼라 생성 (String)");
            System.out.println("인자값: \"3.14\"");
            Scalar sA = Factory.createScalar("3.14");
            String expectedAnswer1 = "3.14";
            System.out.println("기댓값: " + expectedAnswer1);
            System.out.println("결과: " + sA);
            System.out.println(sA.getValue().equals(expectedAnswer1) ? "통과" : "실패");
            System.out.println("");

            // 2. 스칼라 생성 (int, int) 무작위
            Random random = new Random();
            double randomValue = 1 + random.nextDouble() * 9; // 1~10 사이의 무작위값
            Scalar sB = Factory.createScalar(String.valueOf(randomValue));
            System.out.println("2. 스칼라 생성 (int i, int j) 무작위");
            System.out.println("i = 1, j = 10");
            System.out.println("결과: " + sB);
            double value = Double.parseDouble(sB.getValue());
            boolean expectedAnswer2 = value >= 1 && value <= 10;
            System.out.println("기댓값: 1~10 사이의 값");
            System.out.println(expectedAnswer2 ? "통과" : "실패");
            System.out.println();

            // 3. 벡터 생성 (n, val)
            System.out.println("3. 벡터 생성 (n, val)");
            System.out.println("인자값: n=4, val=2");
            Vector vA = Factory.createVector(4, Factory.createScalar("2"));
            Vector expectedAnswer3 = Factory.createVector(new Scalar[]{
                Factory.createScalar("2"), Factory.createScalar("2"), Factory.createScalar("2"), Factory.createScalar("2")
            });
            System.out.println("기댓값: " + expectedAnswer3);
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
            System.out.println("결과: " + vB);
            boolean expectedAnswer4 = true;
            for (int i = 0; i < vB.size(); i++) {
                double val = Double.parseDouble(vB.getValue(i).getValue());
                if (val < 1 || val > 5) {
                    expectedAnswer4 = false;
                    break;
                }
            }
            System.out.println("기댓값: 모든 원소가 1~5 사이");
            System.out.println(expectedAnswer4 ? "통과" : "실패");
            System.out.println();

            // 5. 벡터 생성 (배열)
            Scalar[] arr = {Factory.createScalar("1"), Factory.createScalar("2"), Factory.createScalar("3")};
            System.out.println("5. 벡터 생성 (배열)");
            System.out.println("인자값: [1, 2, 3]");
            Vector vC = Factory.createVector(arr);
            Vector expectedAnswer5 = Factory.createVector(new Scalar[]{
                Factory.createScalar("1"), Factory.createScalar("2"), Factory.createScalar("3")
            });
            System.out.println("기댓값: " + expectedAnswer5);
            System.out.println("결과: " + vC);
            System.out.println(vC.equals(expectedAnswer5) ? "통과" : "실패");
            System.out.println("");

            // 6. 행렬 생성 (m, n, val)
            System.out.println("6. 행렬 생성 (m, n, val)");
            System.out.println("인자값: m=2, n=3, val=7");
            Matrix mA = Factory.createMatrix(2, 3, Factory.createScalar("7"));
            Matrix expectedAnswer6 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7")},
                {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7")}
            });
            System.out.println("기댓값:\n" + expectedAnswer6);
            System.out.println("결과:\n" + mA);
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
            System.out.println("결과:\n" + mB);
            boolean expectedAnswer7 = true;
            for (int i = 0; i < mB.rowSize(); i++) {
                for (int j = 0; j < mB.colSize(); j++) {
                    double val = Double.parseDouble(mB.getValue(i, j).getValue());
                    if (val < 1 || val > 5) {
                        expectedAnswer7 = false;
                        break;
                    }
                }
                if (!expectedAnswer7) break;
            }
            System.out.println("기댓값: 모든 원소가 1~5 사이");
            System.out.println(expectedAnswer7 ? "통과" : "실패");
            System.out.println();

            // 8. 행렬 생성 (csv 파일)
            try {
                // CSV 파일 생성
                java.io.FileWriter fw = new java.io.FileWriter("matrix.csv");
                fw.write("1, 2, 3\n");
                fw.write("4, 5, 6\n");
                fw.write("7, 8, 9\n");
                fw.close();

                System.out.println("8. 행렬 생성 (csv 파일)");
                System.out.println("CSV 파일 내용:");
                System.out.println("1, 2, 3");
                System.out.println("4, 5, 6");
                System.out.println("7, 8, 9");
                
                Matrix mCsv = Factory.createMatrix("matrix.csv");
                Matrix expectedAnswer8 = Factory.createMatrix(new Scalar[][]{
                    {Factory.createScalar("1"), Factory.createScalar("2"), Factory.createScalar("3")},
                    {Factory.createScalar("4"), Factory.createScalar("5"), Factory.createScalar("6")},
                    {Factory.createScalar("7"), Factory.createScalar("8"), Factory.createScalar("9")}
                });
                System.out.println("기댓값:\n" + expectedAnswer8);
                System.out.println("생성된 행렬:\n" + mCsv);
                System.out.println(mCsv.equals(expectedAnswer8) ? "통과" : "실패");
                
                // CSV 파일 삭제
                new java.io.File("matrix.csv").delete();
            } catch (Exception e) {
                System.out.println("CSV 파일 처리 중 예외 발생: " + e.getMessage());
            }
            System.out.println("");

            // 9. 행렬 생성 (배열)
            Scalar[][] arr2 = {
                {Factory.createScalar("1"), Factory.createScalar("2")},
                {Factory.createScalar("3"), Factory.createScalar("4")},
                {Factory.createScalar("5"), Factory.createScalar("6")}
            };
            System.out.println("9. 행렬 생성 (배열)");
            System.out.println("인자값:");
            for (Scalar[] row : arr2) {
                System.out.print("[");
                for (int i = 0; i < row.length; i++) {
                    System.out.print(row[i].getValue());
                    if (i < row.length - 1) System.out.print(", ");
                }
                System.out.println("]");
            }
            Matrix mC = Factory.createMatrix(arr2);
            Matrix expectedAnswer9 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("1"), Factory.createScalar("2")},
                {Factory.createScalar("3"), Factory.createScalar("4")},
                {Factory.createScalar("5"), Factory.createScalar("6")}
            });
            System.out.println("기댓값:\n" + expectedAnswer9);
            System.out.println("결과:\n" + mC);
            System.out.println(mC.equals(expectedAnswer9) ? "통과" : "실패");
            System.out.println("");

            // 10. 단위행렬 생성
            System.out.println("10. 단위행렬 생성");
            System.out.println("인자값: n=3");
            Matrix mD = Factory.createIdentityMatrix(3);
            Matrix expectedAnswer10 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("1"), Factory.createScalar("0"), Factory.createScalar("0")},
                {Factory.createScalar("0"), Factory.createScalar("1"), Factory.createScalar("0")},
                {Factory.createScalar("0"), Factory.createScalar("0"), Factory.createScalar("1")}
            });
            System.out.println("기댓값:\n" + expectedAnswer10);
            System.out.println("결과:\n" + mD);
            System.out.println(mD.equals(expectedAnswer10) ? "통과" : "실패");
            System.out.println("");

            // 11v. 특정 위치의 요소를 지정/조회할 수 있다(벡터).
            System.out.println("11v. 특정 위치의 요소를 지정/조회할 수 있다(벡터).");
            System.out.println("원본 벡터: " + vA);
            System.out.println("지정 위치: " + (2));
            System.out.println("지정 값: " + Factory.createScalar("5"));
            vA.setValue(1, Factory.createScalar("5"));
            System.out.println("지정 결과: " + vA);
        
            int idx11 = 1;
            System.out.println("조회 인덱스: " + (idx11+1));
            System.out.println("조회 결과(2번째 요소): " + vA.getValue(idx11));
            System.out.println("");

            // 11m. 특정 위치의 요소를 지정/조회할 수 있다.(행렬)
            Matrix mTest = Factory.createMatrix(2, 2, Factory.createScalar("0"));
            System.out.println("11m. 특정 위치의 요소를 지정/조회할 수 있다.(행렬)");
            System.out.println("원본 행렬:\n" + mTest);
            System.out.println("지정 위치: (" + (0+1) + "," + (1+1) + ")");
            Scalar expectedAnswer11 = Factory.createScalar("7");
            mTest.setValue(0, 1, expectedAnswer11);
            System.out.println("지정 값: " + expectedAnswer11);
            Scalar result = mTest.getValue(0, 1);
            System.out.println("값 지정 후 행렬:\n" + mTest);
            System.out.println("조회 결과(1,2): " + result);
            System.out.println(result.equals(expectedAnswer11) ? "통과" : "실패");
            System.out.println("");

            // 12. (only 스칼라) 값을 지정/조회할 수 있다.
            System.out.println("12. (only 스칼라) 값을 지정/조회할 수 있다.");
            System.out.println("원본 스칼라: " + sA);
            Scalar expectedAnswer12 = Factory.createScalar("4.5");
            System.out.println("지정 값: 4.5");
            sA.setValue("4.5");
            System.out.println("기댓값: " + expectedAnswer12);
            System.out.println("조회 결과: " + sA.getValue());
            System.out.println(sA.equals(expectedAnswer12) ? "통과. 지정값과 조회 결과가 같습니다." : "실패. 지정값과 조회 결과가 다릅니다.");
            System.out.println("");

            // 13. (only 벡터, 행렬) 크기 정보를 조회할 수 있다.
            System.out.println("13. (only 벡터, 행렬) 크기 정보를 조회할 수 있다.");
            System.out.println("원본 벡터: " + vA);
            System.out.println("원본 행렬: \n" + mA);
            System.out.println("벡터 크기: " + vA.size() + "개");
            System.out.println("행렬 크기 : " + mA.rowSize() +"행" + mA.colSize() + "열");
            
            // 크기 정보 검증
            int expectedVectorSize13 = 4;  // vA는 4개 요소를 가진 벡터
            int expectedMatrixRows13 = 2;  // mA는 2x3 행렬
            int expectedMatrixCols13 = 3;
            
            int resultVectorSize13 = vA.size();
            int resultMatrixRows13 = mA.rowSize();
            int resultMatrixCols13 = mA.colSize();
            
            System.out.println(resultVectorSize13 == expectedVectorSize13 && 
                             resultMatrixRows13 == expectedMatrixRows13 && 
                             resultMatrixCols13 == expectedMatrixCols13 ? "통과" : "실패");
            System.out.println("");

            // 14. toString() 객체를 콘솔에 출력할 수 있다.
            System.out.println("14. toString() 객체를 콘솔에 출력할 수 있다.");
            System.out.println("스칼라: " + sA.toString());
            System.out.println("벡터: " + vA.toString());
            System.out.println("행렬: " + mA.toString());
            
            // toString() 검증 - 스칼라
            String expectedScalar14 = sA.getValue();
            String resultScalar14 = sA.toString();
            
            // toString() 검증 - 벡터
            StringBuilder expectedVector14 = new StringBuilder();
            expectedVector14.append("[");
            for (int i = 0; i < vA.size(); i++) {
                expectedVector14.append(vA.getValue(i).getValue());
                if (i < vA.size() - 1) {
                    expectedVector14.append(", ");
                }
            }
            expectedVector14.append("]");
            String resultVector14 = vA.toString();
            
            // toString() 검증 - 행렬
            StringBuilder expectedMatrix14 = new StringBuilder();
            for (int i = 0; i < mA.rowSize(); i++) {
                expectedMatrix14.append("[");
                for (int j = 0; j < mA.colSize(); j++) {
                    expectedMatrix14.append(mA.getValue(i, j).getValue());
                    if (j < mA.colSize() - 1) {
                        expectedMatrix14.append(", ");
                    }
                }
                expectedMatrix14.append("]");
                if (i < mA.rowSize() - 1) {
                    expectedMatrix14.append("\n");
                }
            }
            String resultMatrix14 = mA.toString();
            
            System.out.println(resultScalar14.equals(expectedScalar14) && 
                             resultVector14.equals(expectedVector14.toString()) && 
                             resultMatrix14.equals(expectedMatrix14.toString()) ? "통과" : "실패");
            System.out.println("");
            


            // 15. equals() 객체의 동등성 판단
            try {
                // 스칼라 비교
                Scalar s2 = Factory.createScalar("4.5");
                System.out.println("15s. equals() 객체의 동등성 판단 (스칼라)");
                System.out.println("비교 대상 1: " + sA);
                System.out.println("비교 대상 2: " + s2);
                System.out.println("기댓값: 같다");
                String scalarResult = sA.equals(s2) ? "같다" : "다르다";
                System.out.println("결과: " + scalarResult);
                System.out.println(scalarResult.equals("같다") ? "통과" : "실패");
                System.out.println("");

                // 벡터 비교
                Vector v2 = Factory.createVector(new Scalar[]{
                    Factory.createScalar("2"),
                    Factory.createScalar("5"),
                    Factory.createScalar("2"),
                    Factory.createScalar("2")
                });
                System.out.println("15v. equals() 객체의 동등성 판단 (벡터)");
                System.out.println("비교 대상 1: " + vA);
                System.out.println("비교 대상 2: " + v2);
                System.out.println("기댓값: 같다");
                String vectorResult = vA.equals(v2) ? "같다" : "다르다";
                System.out.println("결과: " + vectorResult);
                System.out.println(vectorResult.equals("같다") ? "통과" : "실패");
                System.out.println("");

                // 행렬 비교
                Matrix m2 = Factory.createMatrix(new Scalar[][]{
                    {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7")},
                    {Factory.createScalar("7"), Factory.createScalar("7"), Factory.createScalar("7")}
                });
                System.out.println("15m. equals() 객체의 동등성 판단 (행렬)");
                System.out.println("비교 대상 1:\n" + mA);
                System.out.println("비교 대상 2:\n" + m2);
                System.out.println("기댓값: 같다");
                String matrixResult = mA.equals(m2) ? "같다" : "다르다";
                System.out.println("결과: " + matrixResult);
                System.out.println(matrixResult.equals("같다") ? "통과" : "실패");
                System.out.println("");

            } catch (tensor.TensorException e) {
                System.out.println("예외 발생: " + e.getMessage());
                System.out.println("실패");
            }
            System.out.println("");
        
            // 16. Comparable 스칼라 대소 비교
            Scalar s3 = Factory.createScalar("10");
            System.out.println("16. Comparable 스칼라 대소 비교");
            System.out.println("비교 대상 1: " + sA);
            System.out.println("비교 대상 2: " + s3);
            System.out.println("결과: 비교대상1 " + sA + "은(는) 비교대상2 " + s3 + "보다 " + (sA.compareTo(s3) > 0 ? "크다. 실패" : "작다. 통과과"));
            System.out.println("");

            // 17. clone() 객체 복제
            // 스칼라 복제
            Scalar clonedScalar = sA.clone();
            System.out.println("17s. clone() 객체 복제 (스칼라)");
            System.out.println("원본 스칼라: " + sA);
            System.out.println("복제된 스칼라: " + clonedScalar);
            System.out.println("기댓값: " + sA);
            System.out.println(clonedScalar.equals(sA) ? "복제된 스칼라는 원본 스칼라와 같습니다. 통과" : "복제된 스칼라는 원본 스칼라와 다릅니다. 실패");
            System.out.println("");

            // 벡터 복제
            Vector clonedVector = vA.clone();
            System.out.println("17v. clone() 객체 복제 (벡터)");
            System.out.println("원본 벡터: " + vA);
            System.out.println("복제된 벡터: " + clonedVector);
            System.out.println("기댓값: " + vA);
            System.out.println(clonedVector.equals(vA) ? "복제된 벡터는 원본 벡터와 같습니다. 통과" : "복제된 벡터는 원본 벡터와 다릅니다. 실패");
            System.out.println("");

            // 행렬 복제
            Matrix clonedMatrix = mA.clone();
            System.out.println("17m. clone() 객체 복제 (행렬)");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("복제된 행렬:\n" + clonedMatrix);
            System.out.println("기댓값:\n" + mA);
            System.out.println(clonedMatrix.equals(mA) ? "복제된 행렬은 원본 행렬과 같습니다. 통과" : "복제된 행렬은 원본 행렬과 다릅니다. 실패");
            System.out.println("");

            // 18. 스칼라 덧셈
            Scalar sAdd = Factory.createScalar("2.5");
            Scalar expectedAnswer18 = Factory.createScalar("7.0");
            System.out.println("18. 스칼라 덧셈");
            System.out.println("원본(sA): " + sA);
            System.out.println("더할 값(sAdd): " + sAdd);
            System.out.println("기댓값: " + expectedAnswer18);
            sA.add(sAdd);
            System.out.println("결과(sA): " + sA);
            System.out.println(sA.equals(expectedAnswer18) ? "통과" : "실패");
            System.out.println("");

            // 19. 스칼라 곱셈
            Scalar sMul = Factory.createScalar("2");
            Scalar expectedAnswer19 = Factory.createScalar("14.0");
            System.out.println("19. 스칼라 곱셈");
            System.out.println("원본(sA): " + sA);
            System.out.println("곱할 값(sMul): " + sMul);
            System.out.println("기댓값: " + expectedAnswer19);
            sA.multiply(sMul);
            System.out.println("결과(sA): " + sA);
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
                System.out.println("원본(vA): " + vA);
                System.out.println("더할 값(vD): " + vD);
                System.out.println("기댓값: " + expectedAnswer20);
                vA.add(vD);
                System.out.println("결과(vA): " + vA);
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
            System.out.println("원본벡터(vA)" + vA + " * " + "곱할 스칼라(mulS)" + mulS + " = ");
            System.out.println("기댓값: " + expectedAnswer21);
            vA.multiply(mulS);
            System.out.println("결과(vA): " + vA);
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
                System.out.println("[행렬1](mA)\n" + mA + "\n+\n[행렬2](mF)\n" + mF + "\n=\n");
                System.out.println("기댓값: " + expectedAnswer22);
                mA.add(mF);
                System.out.println("결과(mA):\n" + mA);
                System.out.println(mA.equals(expectedAnswer22) ? "통과" : "실패");
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
                {Factory.createScalar("94"), Factory.createScalar("124")},
                {Factory.createScalar("121"), Factory.createScalar("160")}
            });
            System.out.println("23. 행렬 곱셈");
            System.out.println("[행렬1](mA)\n" + mA + "\n*\n[행렬2](mE)\n" + mE + "\n=\n");
            System.out.println("기댓값:\n" + expectedAnswer23);
            Matrix result23 = mA.clone();
            result23.multiply(mE);
            System.out.println("결과:\n" + result23);
            System.out.println(result23.equals(expectedAnswer23) ? "통과" : "실패");
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
            System.out.println("원본 벡터: " + vA);
            System.out.println("세로로 변환된 행렬: " + vA.toVerticalMatrix());
            
            // toVerticalMatrix 검증 - vA는 [15, 27, 21, 24] 벡터
            Matrix expectedAnswer30 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("15")},
                {Factory.createScalar("27")},
                {Factory.createScalar("21")},
                {Factory.createScalar("24")}
            });
            System.out.println("기댓값:\n" + expectedAnswer30);
            Matrix result30 = vA.toVerticalMatrix();
            System.out.println(result30.equals(expectedAnswer30) ? "통과" : "실패");
            System.out.println("");

            // 31. 벡터 toHorizentalMatrix
            System.out.println("31. 벡터 toHorizentalMatrix");
            System.out.println("원본 벡터: " + vA);
            System.out.println("가로로 변환된 행렬: " + vA.toHorizontalMatrix());
            
            // toHorizontalMatrix 검증 - vA는 [15, 27, 21, 24] 벡터
            Matrix expectedAnswer31 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("15"), Factory.createScalar("27"), Factory.createScalar("21"), Factory.createScalar("24")}
            });
            System.out.println("기댓값:\n" + expectedAnswer31);
            Matrix result31 = vA.toHorizontalMatrix();
            System.out.println(result31.equals(expectedAnswer31) ? "통과" : "실패");
            System.out.println("");

            // 32. attachHMatrix
            System.out.println("32. attachHMatrix (가로로 불이기)");
            System.out.println("[행렬1]\n" + mA + "\n[행렬2]\n" + mB + "\n결과:\n" + Tensors.attachHMatrix(mA, mB));
            
            // attachHMatrix 검증 - mA와 mB를 가로로 붙이면 행 수는 같고 열 수는 합쳐짐
            Matrix result32 = Tensors.attachHMatrix(mA, mB);
            System.out.println("기댓값: " + mA.rowSize() + "행 " + (mA.colSize() + mB.colSize()) + "열 행렬 (앞부분은 mA, 뒷부분은 mB)");
            // 결과 행렬의 크기 검증
            boolean sizeCheck32 = (result32.rowSize() == mA.rowSize() && 
                                  result32.colSize() == mA.colSize() + mB.colSize());
            boolean contentCheck32 = true;
            // 첫 부분이 mA와 같은지 확인
            for (int i = 0; i < mA.rowSize(); i++) {
                for (int j = 0; j < mA.colSize(); j++) {
                    if (!result32.getValue(i, j).equals(mA.getValue(i, j))) {
                        contentCheck32 = false;
                    }
                }
            }
            // 나머지 부분이 mB와 같은지 확인
            for (int i = 0; i < mB.rowSize(); i++) {
                for (int j = 0; j < mB.colSize(); j++) {
                    if (!result32.getValue(i, j + mA.colSize()).equals(mB.getValue(i, j))) {
                        contentCheck32 = false;
                    }
                }
            }
            System.out.println(sizeCheck32 && contentCheck32 ? "통과" : "실패");
            System.out.println("");

            // 33. attachVMatrix
            System.out.println("33. attachVMatrix (세로로 붙이기)");
            System.out.println("[행렬1]\n" + mA + "\n[행렬2]\n" + mB + "\n결과:\n" + Tensors.attachVMatrix(mA, mB));
            
            // attachVMatrix 검증 - mA와 mB를 세로로 붙이면 열 수는 같고 행 수는 합쳐짐
            Matrix result33 = Tensors.attachVMatrix(mA, mB);
            System.out.println("기댓값: " + (mA.rowSize() + mB.rowSize()) + "행 " + mA.colSize() + "열 행렬 (위쪽은 mA, 아래쪽은 mB)");
            // 결과 행렬의 크기 검증
            boolean sizeCheck33 = (result33.rowSize() == mA.rowSize() + mB.rowSize() && 
                                  result33.colSize() == mA.colSize());
            boolean contentCheck33 = true;
            // 첫 부분이 mA와 같은지 확인
            for (int i = 0; i < mA.rowSize(); i++) {
                for (int j = 0; j < mA.colSize(); j++) {
                    if (!result33.getValue(i, j).equals(mA.getValue(i, j))) {
                        contentCheck33 = false;
                    }
                }
            }
            // 나머지 부분이 mB와 같은지 확인
            for (int i = 0; i < mB.rowSize(); i++) {
                for (int j = 0; j < mB.colSize(); j++) {
                    if (!result33.getValue(i + mA.rowSize(), j).equals(mB.getValue(i, j))) {
                        contentCheck33 = false;
                    }
                }
            }
            System.out.println(sizeCheck33 && contentCheck33 ? "통과" : "실패");
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
            System.out.println("기댓값:");
            for (int i = 0; i < expectedAnswer35.size(); i++) {
                System.out.println("[" + expectedAnswer35.getValue(i).getValue() + "]");
            }
            Vector result35 = mA.getColVector(colIdx);
            System.out.println("결과:");
            for (int i = 0; i < result35.size(); i++) {
                System.out.println("[" + result35.getValue(i).getValue() + "]");
            }
            System.out.println(result35.equals(expectedAnswer35) ? "통과" : "실패");
            System.out.println("");

            // 36. 부분 행렬 추출
            Matrix expectedAnswer36 = Factory.createMatrix(new Scalar[][]{
                {Factory.createScalar("1")}
            });
            System.out.println("36. 부분 행렬 추출");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("추출 범위: (1,1)부터 (2,2)까지");
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
            System.out.println("제거할 행과 열: (1,1)");
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
            Matrix result38 = mA.transposeMatrix();
            System.out.println("전치 결과:\n" + result38);
            System.out.println(result38.equals(expectedAnswer38) ? "통과" : "실패");
            System.out.println("");

            // 39. 대각합
            System.out.println("39. 대각합");
            System.out.println("원본 행렬:\n" + mA);
            System.out.println("대각합: " + mA.trace());
            Scalar expectedAnswer39 = Factory.createScalar("5");
            System.out.println("기댓값: " + expectedAnswer39);
            System.out.println(mA.trace().equals(expectedAnswer39) ? "통과" : "실패");
            System.out.println("");

            // 40. 정사각 행렬 판별
            System.out.println("40. 정사각 행렬 판별");
            System.out.println("원본 행렬:\n" + mA);
            Boolean expectedAnswer40 = true;  // 2x2 행렬이므로 정사각 행렬
            System.out.println("기댓값: " + expectedAnswer40);
            System.out.println("정사각 행렬 여부: " + mA.isSquare());
            System.out.println(mA.isSquare() == expectedAnswer40 ? "통과" : "실패");
            System.out.println("");

            // 41. 상삼각행렬 판별
            System.out.println("41. 상삼각행렬 판별");
            System.out.println("원본 행렬:\n" + mA);
            Boolean expectedAnswer41 = false;  // [[4,3],[2,1]] - 하삼각 부분에 0이 아닌 값이 있음
            System.out.println("기댓값: " + expectedAnswer41);
            System.out.println("상삼각행렬 여부: " + mA.isUpperTriangular());
            System.out.println(mA.isUpperTriangular() == expectedAnswer41 ? "통과" : "실패");
            System.out.println("");

            // 42. 하삼각행렬 판별
            System.out.println("42. 하삼각행렬 판별");
            System.out.println("원본 행렬:\n" + mA);
            Boolean expectedAnswer42 = false;  // [[4,3],[2,1]] - 상삼각 부분에 0이 아닌 값이 있음
            System.out.println("기댓값: " + expectedAnswer42);
            System.out.println("하삼각행렬 여부: " + mA.isLowerTriangular());
            System.out.println(mA.isLowerTriangular() == expectedAnswer42 ? "통과" : "실패");
            System.out.println("");

            // 43. 단위행렬 판별
            System.out.println("43. 단위행렬 판별");
            System.out.println("원본 행렬:\n" + mA);
            Boolean expectedAnswer43 = false;  // [[4,3],[2,1]] - 단위행렬이 아님
            System.out.println("기댓값: " + expectedAnswer43);
            System.out.println("단위행렬 여부: " + mA.isIdentity());
            System.out.println(mA.isIdentity() == expectedAnswer43 ? "통과" : "실패");
            System.out.println("");

            // 44. 영행렬 판별
            System.out.println("44. 영행렬 판별");
            System.out.println("원본 행렬:\n" + mA);
            Boolean expectedAnswer44 = false;  // [[4,3],[2,1]] - 영행렬이 아님
            System.out.println("기댓값: " + expectedAnswer44);
            System.out.println("영행렬 여부: " + mA.isZero());
            System.out.println(mA.isZero() == expectedAnswer44 ? "통과" : "실패");
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
            System.out.println("RREF 변환 결과:\n" + mA.getRREF());
            System.out.println("");

            // 52. RREF 판별
            System.out.println("52. RREF 판별");
            System.out.println("원본 행렬:\n" + mA);
            boolean isRref = mA.isRREF();
            System.out.println("RREF 여부: " + (isRref ? "RREF임" : "RREF가 아님"));
            System.out.println(!isRref ? "통과" : "실패");
            System.out.println("");

            // 53. 행렬식 구하기
            try {
                Scalar expectedAnswer53 = Factory.createScalar("-8");
                System.out.println("53. 행렬식 구하기");
                System.out.println("원본 행렬:\n" + mA);
                System.out.println("기댓값: " + expectedAnswer53);
                Scalar result53 = mA.getDeterminant();
                System.out.println("행렬식: " + result53);
                System.out.println(result53.equals(expectedAnswer53) ? "통과" : "실패");
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
                Matrix result54 = mA.getInverseMatrix();
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

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("연산 입력>>");
        Scanner scanner = new Scanner(System.in);
        int num1    = scanner.nextInt();
        String operator =  scanner.next();
        int num2 = scanner.nextInt();

        // switch 문으로 연산 처리
        double result = switch (operator) {
            case "더하기":
                yield num1 + num2;
            case "빼기":
                yield num1 - num2;
            case "곱하기":
                yield num1 * num2;
            case "나누기":
                yield num1 / num2;
            default:
                System.out.println("사칙연산이 아닙니다");
                yield 0.0;
        };

        // 출력 형식 맞추기
        if (!operator.equals("더하기") && !operator.equals("빼기") &&
                !operator.equals("곱하기") && !operator.equals("나누기")) {
            // 이미 "사칙연산이 아닙니다"가 출력됨
        } else {
            // 첫 번째 출력 형식: "연산 입력=>25 더하기 2.7 결과 27.7"
           // System.out.println("연산 입력=>" + num1 + " " + operator + " " + num2 + " 결과 " + result);
            // 두 번째 출력 형식: "25.0 곱하기 2.7의 계산 결과는 67.5"
            System.out.println(num1 + " " + operator + " " + num2 + "의 계산 결과는 " + result);
        }

        scanner.close();
    }
}
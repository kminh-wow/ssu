import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("연산 입력>>");
        Scanner scanner = new Scanner(System.in);
        int first_num = scanner.nextInt();
        String operator =  scanner.next();
        int second_num = scanner.nextInt();

        // switch 문으로 연산 처리
        boolean isNotOp = true;
        int result = switch (operator) {
            case "더하기":
                yield first_num + second_num;
            case "빼기":
                yield first_num - second_num;
            case "곱하기":
                yield first_num * second_num;
            case "나누기":
                yield first_num / second_num;
            default:
                System.out.println("사칙연산이 아닙니다");
                isNotOp = false;
                yield 0;
        };

        // 플래그 변수로 출력 제어
        if (isNotOp) {
            System.out.println(first_num + " " + operator + " " + second_num + "의 계산 결과는 " + result);
        }

        scanner.close();
        scanner.close();
    }
}
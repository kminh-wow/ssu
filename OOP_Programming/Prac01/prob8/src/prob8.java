import java.util.Scanner;

public class prob8 {
    public static void main(String[] args) {
        System.out.print("연산 입력>>");
        Scanner scanner = new Scanner(System.in);
        double first_num = scanner.nextDouble();
        String operator =  scanner.next();
        double second_num = scanner.nextDouble();
        boolean isOp = true;

        double result = switch (operator) {
            case "더하기" -> first_num + second_num;
            case "빼기" -> first_num - second_num;
            case "곱하기" -> first_num * second_num;
            case "나누기" ->{
                if (second_num == 0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    isOp = false;
                    yield 0;
                }
                yield first_num / second_num;
            }
            default -> {
                System.out.print("사칙연산이 아닙니다.");
                isOp = false;
                yield 0;
            }
        };
        if (isOp) {
            System.out.print(first_num + " " + operator + " " + second_num + "의 계산 결과는 " + result);
        }
        scanner.close();
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("생일 입력하세요 >>");
        Scanner scanner = new Scanner(System.in);
        int date = scanner.nextInt();
        int year = date / 10000;
        int month = (date / 100) % 100;
        int day = date % 100;
        System.out.print(year + "년" + month + "월" + day + "일");

    }

}
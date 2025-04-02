import java.util.Scanner;

public class prob4 {
    public static void main(String[] args) {

        System.out.print("여행지 >>");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("인원수 >>");
        int person = scanner.nextInt();
        System.out.print("숙박일 >>");
        int day = scanner.nextInt();
        System.out.print("1인당 항공료>>");
        int pricePlain = scanner.nextInt();
        System.out.print("1방 숙박비>>");
        int priceRoom = scanner.nextInt();
        int room = person / 2;
        if (person % 2 != 0) {
            room += 1;
        }

        int finalPrice = room*priceRoom*day + person*pricePlain;
        System.out.print(person+"명의 "+ name + " "+ day+"박 "+(day+1)+"일 여행에는 방이 " + room+ "개 필요하며 경비는 "+ finalPrice + "원입니다.");
    }

}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("여행지 >>");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("인원수 >>");
        int person = scanner.nextInt();
        System.out.print("숙박일 >>");
        int day = scanner.nextInt();
        System.out.print("1인당 항공료>>");
        int price_plain = scanner.nextInt();
        System.out.print("1방 숙박비>>");
        int price_room = scanner.nextInt();
        int room = (person/2)+1;
        int finalPrice = room*price_room*day + person*price_plain;
        System.out.print(person+"명의 "+ name + " "+ day+"박 "+(day+1)+"일 여행에는 방이 " + room+ "개 필요하며 경비는 "+ finalPrice + "원입니다.");
    }

}
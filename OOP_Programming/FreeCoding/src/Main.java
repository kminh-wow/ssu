import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] st1 = new int[2];
        int[] st2 = new int[2];
        Scanner sc = new Scanner(System.in);
        System.out.print("학생1>>");
        String st11 = sc.next();
        st1[0] = sc.nextInt();
        st1[1] = sc.nextInt();
        System.out.print("학생2>>");
        String st22 = sc.next();
        st2[0] = sc.nextInt();
        st2[1] = sc.nextInt();
        int st1_score = 100 - (st1[0]*3 + st1[1]*8);
        int st2_score = 100 - (st2[0]*3 + st2[1]*8);
        if (st1_score > st2_score) {
            System.out.print(st11 + "의 출석 점수가 더 높음." +st11+ "의 출석 점수는 "+st1_score);
        }
        else if (st2_score > st1_score) {
            System.out.print(st22 + "의 출석 점수가 더 높음." +st22+ "의 출석 점수는 "+st2_score);
        }
        else System.out.print("점수 동일");
    }
}
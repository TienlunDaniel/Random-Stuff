package HelloWorld;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class Sequence {

    int A;
    int B;
    int C;
    int N;

    public Sequence(int a, int b, int c, int n) {
        A = a;
        B = b;
        C = c;
        N = n;
    }

    public String solution() {


        //2 1 5 5 3
        int[] result = new int[N]; // filled with 1
        Arrays.fill(result, 1);

        //1st step - make sure first criteria is met

        if (A + B - C > N) {
            return "IMPOSSIBLE";
        }

        Runnable A_minus_C = () -> incrementArr(result, 0, A - C, false); //left
        Runnable B_minus_C = () -> incrementArr(result, N - 1, B - C, true); //right
        Runnable C_from_A = () -> incrementArr(result, A - C, C, false);
        Runnable C_from_B = () -> incrementArr(result, N - 1 - (B - C), C, true);


        if (A > C && B > C) {
            A_minus_C.run();
            B_minus_C.run();
            C_from_A.run();
            C_from_A.run();

        } else if (A == C) {
            B_minus_C.run();
            C_from_A.run();
            C_from_A.run();

        } else if (B == C) {
            A_minus_C.run();
            C_from_B.run();
            C_from_B.run();

        } else { //A==C && B==C

        }

        /*------------------------*/

        return produceString(result);
    }

    public void incrementArr(int[] arr, int startIndex, int count, boolean reverse) {
        for (int i = 0; i < count; i++) {
            int index = reverse ? startIndex - i : startIndex + i;
            arr[index]++;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int[] temp = generateTest();
            int N = temp[0];
            int A = temp[1];
            int B = temp[2];
            int C = temp[3];

            StringBuilder sb = new StringBuilder();
            sb.append(N).append(" ").append(A).append(" ").append(B).append(" ").append(C).append(" ").append("\n");
            Sequence s = new Sequence(A, B, C, N);

            System.out.println(sb.toString() + s.solution());
        }
    }

    public static int[] generateTest() {
        Random r = new Random();

        int N = 0;
        while (N < 5) {
            N = r.nextInt(10);
        }
        int A = 1 + r.nextInt(N - 1);
        int B = 1 + r.nextInt(N - 1);
        int C = Math.max( 1, Math.min(r.nextInt(A), r.nextInt(B)));

        return new int[]{N, A, B, C};
    }

    public static String produceString(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int a : arr) {
            sb.append(a).append(" ");
        }

        return sb.toString().trim();
    }
}
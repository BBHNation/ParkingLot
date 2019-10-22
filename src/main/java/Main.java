import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Length length1 = new Length(10);
        Length length2 = new Length(5);

        if (length1.biggerThan(length2)) {
            System.out.print("length 1 is bigger than length 2");
        } else if (length1.smallerThan(length2)) {
            System.out.print("length 2 is smaller than length 1");
        } else if (length1.equal(length2)){
            System.out.print("length 1 equals length 2");
        }
    }
}

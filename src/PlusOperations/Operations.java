package PlusOperations;

public class Operations {

    public static void main(String[] args) {

        var operations = new Operations();

        System.out.println("Division");

        System.out.println(operations.division(-6, 2));

        System.out.println(operations.division(-7, -2));

        System.out.println(operations.division(16, 2));

        System.out.println(operations.division(16, -2));

        System.out.println(operations.division(0, -1));

        try {
            System.out.println(operations.division(75, 0));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Multiply");

        System.out.println(operations.multiplication(0, -2));

        System.out.println(operations.multiplication(-7, -6));

        System.out.println(operations.multiplication(-7, 2));

        System.out.println(operations.multiplication(16, 2));

        System.out.println(operations.multiplication(16, -8));

        System.out.println("Substruction");

        System.out.println(operations.substruction(-6, 2));

        System.out.println(operations.substruction(-7, -7));

        System.out.println(operations.substruction(6, 6));

        System.out.println(operations.substruction(61, 6));

        System.out.println(operations.substruction(-2, 7));

        System.out.println(operations.substruction(0, 16));
    }

    //enumeration of operations which can change a result sign
    enum Operation {
        sub,
        mul,
        div,
    }

    //returns sum
    public int sum(int a, int b) {
        return a + b;
    }


    public int multiplication(int a, int b) {

        //if one of them is zero
        if (a == 0 || b == 0) {
            return 0;
        }

        //check for a sign in for result
        boolean resultWillBeNegative = cheIfResultIsNegative(Operation.mul, a,b);

        a = Math.abs(a);
        b = Math.abs(b);

        //getting result
        int result = 0;
        for (int i = 0; i < a; i++) {
            result += b;
        }
        //returning result due to its sign
        return resultWillBeNegative ? sum(multiplication(-2, result), result) : result;
    }

    public int division(int a, int b) {

        //division by zero
        if (b == 0) {
            throw new IllegalArgumentException("Argument 'b' was 0. Cannot divide by zero!");
        }

        //checking for a sign
        boolean resultWillBeNegative = cheIfResultIsNegative(Operation.div, a, b);

        a = Math.abs(a);
        b = Math.abs(b);


        int result = 0;
        for (int i = b; i <= a; i += b) {
            result++;
        }

        //returning result with a sign
        return resultWillBeNegative ? multiplication(-1, result) : result;
    }


    public int substruction(int a, int b) {

        //defining a sign
        boolean resultWillBeNegative = cheIfResultIsNegative(Operation.sub, a, b);

        //if second is negative - just sum -(-1) = 1
        if (b < 0) {
            return sum(a, Math.abs(b));
        }

        //getting result
        int result = 0;
        if (a > b) {
            for (int i = b; i < a; i++) {
                result++;
            }
        } else if (b > a) {
            for (int i = a; i < b; i++) {
                result++;
            }
        }

        //returning due to a sign
        return resultWillBeNegative ? multiplication(-1, result) : result;

    }

    //checks if result will be negative or not
    private  boolean cheIfResultIsNegative(Operation operation, int a, int b) {

        //if substruction
        if (operation == Operation.sub) {
            if (a < 0 && b < 0) {
                return Math.abs(a) > Math.abs(b);
            }
            else if (a > 0 && b > 0) {
                return Math.abs(a) < Math.abs(b);
            }
            else return a <= 0;
        }
        //if both - multiplication or division
        else  {
            return (a >= 0 || b >= 0) && (a <= 0 || b <= 0);
        }
    }
}

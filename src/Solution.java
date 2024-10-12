public class Solution {
    public static void main(String[] args) {

        Regex_Test tester = new Regex_Test();
        tester.checker("^(?:[ac]{2})*(?:[bd]{2})*(?:[acbd]{4})*$");
  }
}

class Regex_Test {
    public void checker(String Regex_Pattern){
        java.util.Scanner Input = new java.util.Scanner(System.in);
        String Test_String = Input.nextLine();
        System.out.println(Test_String.matches(Regex_Pattern));
        Input.close();
    }
}

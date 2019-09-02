
public class test {
    public static final String A;
    public static final String B;
    public static final String C;
    static {
        A = "abc";
        B = "abc";
        C = A + B;
    }
    public static void main(String[] args){

        String s = new String("sss");
        String s1 ="sss";
        String s2 ="sss";
        String AA = "abc";
        String BB = "abcabc";
        System.out.println(System.identityHashCode(C));
        System.out.println(System.identityHashCode(A+B));
        System.out.println(System.identityHashCode(BB));

    }
}

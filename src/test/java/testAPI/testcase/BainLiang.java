package testAPI.testcase;

public class BainLiang {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //直接调用测试：
        System.out.println(QuBie.staticInt);
        // System.out.println(QuBie.shiInt);//报错
        QuBie qb1 = new QuBie();
        QuBie qb2 = new QuBie();
        qb1.shiInt = 8;
        qb1.staticInt = 8;//这里改变以后，再创建的对象也是会用改过的数值
        QuBie qb3 = new QuBie();
    }
}

class QuBie {
    public static int staticInt = 0;
    public int shiInt = 0;

    public QuBie() {
        staticInt++;
        shiInt++;
        System.out.println("静态变量" + staticInt + "   实例变量" + shiInt);
    }
}

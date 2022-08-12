public class Test_Planet {
    static Planet p1 = new Planet(3.0,7.0,1,1,1,null);
    static Planet p2 = new Planet(5.0,6.0,2,3,1,null);

    //算出他们之间的距离
    double dis = p1.calcDistance(p2);

    //计算他们间的力
    double F = p1.calcForceExertedBy(p2);

    double F_X = p1.calcForceExertedByX(p2);

    double F_Y = p1.calcForceExertedByY(p2);

    public static void main(String[] args) { 
        p1.update(0.5,3.0,8.0);
        p2.update(0.4,9.0,8.0);

        System.out.println(p1.xxPos);
        System.out.println(p2.yyPos);

        }   
    
}


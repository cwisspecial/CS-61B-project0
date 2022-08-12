public class Planet {

    // private static final String NULL = null;
    public double xxPos; // 它当前的 x 位置
    public double yyPos; // 它当前的 y 位置
    public double xxVel; // 它在 x 方向上的当前速度
    public double yyVel; // 它在 y 方向上的当前速度
    public double mass; // 它的质量
    public String imgFileName; // 与描绘行星的图像对应的文件名（例如，jupiter.gif）
    private static final double G = 6.67e-11;
    // 构造函数
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;

    }

    // 编写一个函数:计算两个行星之间的距离
    public double calcDistance(Planet p){
		return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)
						+ (yyPos - p.yyPos)*(yyPos - p.yyPos));
	}

    // 编写一个函数计算两个行星之间的力
    public double calcForceExertedBy(Planet p){
		double r = calcDistance(p);
		return G * mass * p.mass / (r * r);
	}


    // 计算两个行星之间的水平方向上的力
    public double calcForceExertedByX(Planet p) {

        double r_x;
        r_x = (p.xxPos - this.xxPos);
        double high, down;
        high = this.calcForceExertedBy(p);
        down = r_x / (this.calcDistance(p));

        return high * down;
    }

    // 计算两个行星之间的竖直方向上的力
    public double calcForceExertedByY(Planet p) {

        double r_y;
        r_y = (p.yyPos - this.yyPos);
        double high, down;
        high = this.calcForceExertedBy(p);
        down = r_y / (this.calcDistance(p));

        return high * down;
    }


    public double calcNetForceExertedByX(Planet[] allPlanets)
	{
		double totalForce = 0;
		for (Planet planet : allPlanets) {
			if (this.equals(planet)) {
				continue;	
			}
			totalForce += calcForceExertedByX(planet);
		}
		return totalForce;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets)
	{
		double totalForce = 0;
		for (Planet planet : allPlanets) {
			if (this.equals(planet)) {
				continue;	
			}
			totalForce += calcForceExertedByY(planet);
		}
		return totalForce;
	}

    // 更新位置
    public void update(double dt, double fX, double fY) {
        // 计算加速度

        double a_1 = fX / (this).mass;
        double a_2 = fY / (this).mass;

        // 计算当前速度
        this.xxVel = this.xxVel + (a_1 * dt);
        this.yyVel = this.yyVel + (a_2 * dt);

        // 计算当前位置
        this.xxPos = this.xxPos + (xxVel * dt);
        this.yyPos = this.yyPos + (yyVel * dt);

    }

    // 画行星
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        
    }	

    //画点
    //public void draw_point() {
        //画出每个行星的轨迹
        //StdDraw.setPenRadius(0.02);
        //StdDraw.setPenColor(StdDraw.RED);
		//StdDraw.point(xxPos, yyPos);

        //circle();
    //}
        
    

}
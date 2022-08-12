public class NBody {
//读取宇宙半径
	public static double readRadius(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
//获取星球名字数组
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double planet_radius = in.readDouble();

		//行星初始化
		Planet[] planets = new Planet[num];
		for (int i = 0; i < num; i++) {
			double xp = in.readDouble();
			double yp = in.readDouble();
			double vx = in.readDouble();
			double vy = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xp, yp, vx, vy, m, img);	
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = new Double(args[0]);
		double dt = new Double(args[1]);	
		String filename = args[2];

		//读取宇宙半径
		double r = readRadius(filename);
		//初始化行星
		Planet[] planets = readPlanets(filename);

		// set the universe scale
		StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r);
		StdDraw.enableDoubleBuffering();

		//Planet sum_0 = new Planet(0,0,0,0,0,null);

		double t = 0;
		int num = planets.length;
		while(t <= T){
			

			//行星受到的总力
			double[] xForces = new double[num];
			double[] yForces = new double[num];

			//每个行星受到的总吸引力力
			for(int i = 0; i < num; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < num; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			// draw the backgroud picture
			StdDraw.picture(0, 0, "images/starfield.jpg");

			// draw all the planets
			for (int i = 0; i < num;i++) {
				planets[i].draw();	

				//画出每个行星的运行轨迹
				double R = planets[i].calcDistance(planets[3]);
				//planet.draw_point();
				StdDraw.setPenColor(StdDraw.RED);
				//画圆
				//StdDraw.circle(0,0,R);
			}
			
			StdDraw.show();
			StdDraw.pause(2);
			t += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}
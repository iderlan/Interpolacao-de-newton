public class Main
{
	public static void main(String[] args) {
	    
	    boolean mostra = true; //se true ele mostra apenas a ultima interação
	                           //se false ele mostra todas as interações
	    double[][] set = {{1,1.5,1.5,1,1.5,1.5,0.0000001},  //configurações iniciais de cada exercício
	                          {0,1.5,0,0,0.5,0.5,0.00001},
	                          {0,1,0,-0.5,0,0,0.0000000001}};
	                          
	    //loop para mostrar todos os resultados de todos os exercícios
	    for(int i = 0; i < 3; i++){
	        System.out.println("\nfunçao: " + fToString(i));
	        System.out.println("\nbiseçao:\n");
            bisecao(set[i][0],set[i][1],set[i][6],i,mostra);
	        System.out.println("\nnewton:\n");
            newton(set[i][2],set[i][6],100,i,mostra);
            System.out.println("\nsecante:\n");
            secante(set[i][3],set[i][4],set[i][6],100,i,mostra);
            System.out.println("\nponto fixo:\n");
            fixedPoint(set[i][5],set[i][6],100,i,mostra);
            System.out.println("\n--------------------------------------//----------------------------------------");
	    }
    }
    public static void secante(double x0, double x1, double tol, int max_iter,int in,boolean mostra) {
        double xprox = 0.0;
        int i = 0;

        while (Math.abs(f(x1,in)) > tol && i < max_iter) {
            
            xprox = x1 - f(x1,in) * (x1 - x0) / (f(x1,in) - f(x0,in));
            x0 = x1;
            x1 = xprox;
            i++;
            
            if(!mostra){
                System.out.println(i + " xº = " + x1 + " error = " + Math.abs(f(x1,in)));
            }
        }
        if(mostra){
            System.out.print(i + "º x = " + x1 + " error = " + Math.abs(f(x1,in)));
        }
        System.out.println("\nf(x) = " + f(x1,in));
    }
    
    public static void fixedPoint(double x0, double tol, double max, int in,boolean mostra){
	    double x = 0;
	    int iter = 0;
	    double error = 1;
	    
	    while(error > tol && iter < max){
            iter++;
            x = g(x0,in);
            error = Math.abs(x - x0);
            x0 = x;
            
            if(!mostra){
                System.out.println(iter + "º x = " + x + " error = " + error);
            }
        }
        if(mostra){
            System.out.print(iter + "º x = " + x + " error = " + error);
        }
        System.out.println("\nf(x) = " + f(x,in));
	}
    
    public static void bisecao(double a, double b, double tol, int in,boolean mostra){
        double x = (a+b)/2;
		double xau;
		double erro = x;
		int i = 1; // contador
		
		while(erro > tol){
		    
		    if(f(a,in)*f(x,in) < 0){
		        b = x;
		    }else{
		        a = x;
		    }
		    xau = x;
		    x = (a+b)/2;
		    erro = Math.abs(x - xau);
		    i++;
		    
		    if((Math.abs(x - xau) > tol) && !mostra){
		        System.out.println(i + "º erro = " + erro + " ; a = " + a + " ; b = " + b + " ; x = " + x);
		    }
		    
		}
		if(mostra){
		    System.out.print(i + "º erro = " + erro + " ; a = " + a + " ; b = " + b + " ; x = " + x);
		}
		System.out.println("\nf(x) = " + f(x,in));
    }
    
    public static void newton(double x0, double tol, double max, int in,boolean mostra){
        double dx = 0;
        double x = 0;
        int iter = 0;
        double error = 1000000000;
        
        while(error > tol && iter < max){
            iter++;
            dx = -f(x0,in)/df(x0,in);
            x = x0 + dx;
            error = Math.abs(x - x0);
            x0 = x;
            if(!mostra){
                System.out.println(iter + "º x = " + x + " error = " + error);
            }
        }
        if(mostra){
            System.out.print(iter + "º x = " + x + " error = " + error);
        }
        System.out.println("\nf(x) = " + f(x,in));
    }
    
    //define a função de acordo com a variável i
    public static double f(double x, int i) {
        if(i == 0){
            return Math.pow(2.71828,-x*x) - Math.cos(x);
        }else if(i == 1){
            return (x*x*x)-x-1;
        }else{
            return (x*x*x)-9*x + 2;
        }
    }
    
    //derivada das funções
    public static double df(double x, int i) {
        if(i == 0){
            return (-2*x/(Math.pow(2.71828,Math.pow(x,2))) + Math.sin(x));
        }else if(i == 1){
            return 3*(x*x)-1;
        }else{
            return 3*(x*x)-9;
        }
    }
    
    //g das funções insolando um x
    public static double g(double x, int i){
        if(i == 0){
            return -Math.pow(2.71828,-x*x) + Math.cos(x) + x;
        }else if(i == 1){
            return Math.pow(x + 1, 1.0/3.0);
        }else{
            return ((x*x*x)+2)/9;
        }
    }
    
    //cada função em forma de string para mostrar qual está sendo feita
    public static String fToString(int i){
        String f = "";
        if(i == 0){
            f = "e^(-x^2) -cos(x)";
        }else if(i == 1){
            f = "x³ -x -1";
        }else{
            f = "x³ -9x +2";
        }
        return f;
    }
}

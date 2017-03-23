package ka2.tesa.elements;

public class Brandom extends Player {

	public Brandom(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	int i = -1;
	int wait = 10;
	@Override
	public String run(int id, int power, int x, int y, int[][] view) {
		// TODO Auto-generated method stub
				i+=1;
		if(i==0){
			return "UP";
		}
		if(i==1){
			return "GENERATOR";
		}
		if(i<wait){
			return "WAIT";
		}
		if(i==wait){
			return "GET";
		}
		if(i==wait+1){
			return "DOWN";
		}
		if(i==wait+2){
			return "PUT";
		}
		else{
			i=-1;
		}
		return "PUT";	
	
	}

}

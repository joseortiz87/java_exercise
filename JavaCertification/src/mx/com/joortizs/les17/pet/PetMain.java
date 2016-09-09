package mx.com.joortizs.les17.pet;

public class PetMain {

    public static void main(String[] args) {
        Animal a;
        //test a spider with a spider reference
        Spider s = new Spider();
        s.eat();
        s.walk();
        //test a spider with an animal reference
        a = new Spider();
        a.eat();
        a.walk();
        
        Pet p;
        p = new Fish();
        p.play();
        
        playWithAnimal(a);
    }
    
    public static void playWithAnimal(Animal a) {
        // completed in practice
    	if(a instanceof Pet){
    		((Pet)a).play();
    	}
    }
    
}
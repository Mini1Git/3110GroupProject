import java.util.Random;

public class SimulatedAnnealing {
    double prob = 50.0;
    double temp = 2.0;
    double cooling = 0.02;
    boolean lastAction;
    Random rand = new Random();

    public SimulatedAnnealing(double p, double t, double c){
        prob = p;
        temp = t;
        cooling = c;
    }

    public boolean chose(){
        if (rand.nextDouble() < prob / 100) {
            lastAction = true;
            return true;
        }
        else {
            lastAction = false;
            return false;
        }
    }

    public void changeProb(double probChange){
        if(lastAction){
            prob -= probChange * temp;
            if(prob < 0){
                prob = 0;
            }
        }
        else{
            prob += probChange * temp;
            if(prob > 100){
                prob = 100;
            }
        }

        if(temp <= (0.01 + cooling)){
            temp -= cooling;
        }
    }

}

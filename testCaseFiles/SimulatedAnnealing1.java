import java.util.Random;

public class SimulatedAnnealing {
    double prob;
    double temp;
    double cooling;
    boolean lastAction;

    public SimulatedAnnealing(){
        prob = 50.0;
        temp = 2.0;
        cooling = 0.02;
    }

    public SimulatedAnnealing(double p, double t, double c){
        prob = p;
        temp = t;
        cooling = c;
    }

    public boolean chose(){
        Random rand = new Random();

        if (rand.nextDouble() * 100 < prob) {
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
        }
        else{
            prob += probChange * temp;
        }

        if(prob > 100){
            prob = 100;
        }
        if(prob < 0){
            prob = 0;
        }

        temp -= cooling;

        if(temp <= 0.01){
            temp = 0.01;
        }
    }
}

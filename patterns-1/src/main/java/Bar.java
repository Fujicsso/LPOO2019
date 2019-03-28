import java.util.ArrayList;
import java.util.List;

public class Bar {

    private boolean isHappyhour;
    private List<BarObserver> observers;

    public Bar(List<BarObserver> observers){
        this.observers = observers;
    }

    public Bar(){
        this.observers = new ArrayList<>();
    }

    public boolean isHappyHour(){
        return isHappyhour;
    }

    public void startHappyHour(){
        isHappyhour = true;
        notifyObservers();
    }

    public void endHappyHour(){
        isHappyhour = false;
    }

    void addObserver(BarObserver observer) {
        observers.add(observer);
    }

    void removeObserver(BarObserver observer) {
        observers.remove(observer);
    }

    void notifyObservers() {
        for (BarObserver observer : observers)
            if (isHappyHour()) observer.happyHourStarted(this);
            else observer.happyHourEnded(this);
    }
}

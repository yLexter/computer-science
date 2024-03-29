package Java.ExerciciosLab.Ar4;

public class Fan {

    public static final int slow = 1;
    public static final int average = 2;
    public static final int fast = 3;
    public int currentVelocity;
    public boolean on;
    public String color;
    public double radius;

    Fan() {
        this.setColor("Preto");
        this.setCurrentVelocity(1);
        this.setOn(false);
        this.setRadius(5);
    }

    Fan(String color, int currentVelocity, double radius, boolean on) {
       this.setOn(on);
       this.setCurrentVelocity(currentVelocity);
       this.setRadius(radius);
       this.setColor(color);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(int currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return this.on ?
                String.format("Velocidade: %d | Raio: %.2f | Cor: %s", currentVelocity, radius, color) :
                String.format("Raio: %.2f | Cor: %s | Ventilador Desligado",  radius, color);
    }

    public static void main(String[] args) {

        Fan fan1 = new Fan("Amarela", Fan.fast, 10.0, true);
        Fan fan2 = new Fan("Azul", Fan.average,5.0, false );

        System.out.println(fan1);
        System.out.println(fan2);

    }

}

public class SevenUP extends SoftDrinks {
        /*
Lecture section: TC4L
Tutorial section: T15L
Team number: Group 1
Team members
Name : Maresh,Mohanad Ayoub Ali , Id: 1211303662
Name : CHOO CHEE CHOONG , Id: 1221302637
Name : YAP WENG HONG , Id: 1211103023
Name : MD RAKIBUL HASSAN SHEAM , Id: 1211306276
Name : Veenah Ganesh , Id: 1231300941
     */
     private String name = "SevenUp";
    private double price = 3.00;
    private int quantity = 0;

    @Override
    public String getName() {
     return name;
    }

    @Override
    public double getPrice() {
       return price;
    }

    @Override
    public int getQuantity() {
       return quantity;
    }

    @Override
    public void setQuantity(int num) {
        this.quantity= num;
    }
}

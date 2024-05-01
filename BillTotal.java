public class BillTotal  {
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
    order order;
    private static double total;

    public BillTotal(){
        this.order = new order();
    }
    public static double getTotal() {
        return total;
    }
    public double calculateTotal(){
        if(this.order!=null){
        total = 0.00;
        for(int i = 0 ; i < order.getOrderList().size();i++){
            total = total +(order.getOrderList().get(i).getPrice()*order.getOrderList().get(i).getQuantity());
        }
    }
         return total;
    }
    public void newBill(){
        order.getOrderList().clear();
        total = 0.00;
    }
    
}

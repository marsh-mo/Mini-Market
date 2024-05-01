import java.util.ArrayList;

public class order {
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
    public static ArrayList<Iteams>orderList= new ArrayList<>();
    public order(){}
    public static ArrayList<Iteams> getOrderList() {
        return orderList;
    }
    public void addIteam(Iteams iteam){
        orderList.add(iteam);
    }    
}

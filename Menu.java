import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class Menu extends JFrame {
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
    order o;

    public boolean madeChoice = false;
    protected Pepsi p = new Pepsi();
    protected Cola c = new Cola();
    protected Sprite s = new Sprite();
    protected SevenUP su = new SevenUP();
    public static BillTotal billIssuing = new BillTotal();
    private double total;
    public JList<String> cartList;
    private DefaultListModel<String> cartModel;
    public JLabel[] box;
    public JPanel LeftSide, center, rightSide , down , up;
    public JButton addToCart, removeFromCart, clearAll, pay;
    public JLabel pepsiLabel, sevenUpLabel, coLabel, spritLabel,Total , Name;
    

    public Menu() {
         o = new order(); 
        LeftSide = new JPanel(new GridLayout(4, 1));
        rightSide = new JPanel(new GridLayout(4, 1));
        down = new JPanel(new GridLayout(1, 1));
        up= new JPanel(new GridLayout(1,1));
        center = new JPanel(new FlowLayout(FlowLayout.CENTER));

        pepsiLabel = createSoftDrinkLabel("pepsi", "2.50",  "Pictures.java\\pepsi.jpg");
        pepsiLabel.setPreferredSize(new Dimension(100,100));
        coLabel = createSoftDrinkLabel("cola", "2.50",  "Pictures.java\\coca-cola.jpg");
        coLabel.setPreferredSize(new Dimension(100,100));
        spritLabel = createSoftDrinkLabel("sprite", "3.00", "Pictures.java\\sprite.jpg");
        spritLabel.setPreferredSize(new Dimension(100,100));
        sevenUpLabel = createSoftDrinkLabel("sevenUp", "3.00",  "Pictures.java\\sevenUp.jpg");
        sevenUpLabel.setPreferredSize(new Dimension(100,100));
        
        Total = new JLabel("Total: RM0.00 ");
        Total.setHorizontalAlignment(JLabel.CENTER);
        Name= new JLabel ("Shopping Cart");
        Name.setHorizontalAlignment(JLabel.CENTER);
        

        box = new JLabel[]{pepsiLabel, coLabel, spritLabel, sevenUpLabel};

        addToCart = new JButton("Add To Cart");
        removeFromCart = new JButton("Remove From Cart");
        clearAll = new JButton("Clear");
        pay = new JButton("Pay");

        cartModel = new DefaultListModel<>();
        cartList = new JList<>(cartModel);
//////////////////////////////////////////////////////////////////////////////////
addToCart.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel selectedLabel = getSelectedDrinkLabel();
        if (selectedLabel != null) {
            String itemName = selectedLabel.getText().split(":")[0].trim();
            switch (itemName) {
                case "pepsi":
                    handleItemAddition(p);
                    break;
                case "cola":
                    handleItemAddition(c);
                    break;
                case "sprite":
                    handleItemAddition(s);
                    break;
                case "sevenUp":
                    handleItemAddition(su);
                    break;
            }
        }
    }

    private void handleItemAddition(Iteams item) {
        if (o.getOrderList().contains(item)) {
            item.setQuantity(item.getQuantity() + 1);
        } else {
            o.addIteam(item);
            item.setQuantity(1);
        }
        cartModel.addElement(item.getName() + " Has Been Added " + "RM "+ String.format("%.2f",item.getPrice()));
        updateTotalLabel();
    }
});
//////////////////////////////////////////////////////////////////////////////////////////////////////
     removeFromCart.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = cartList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedItem = cartList.getSelectedValue();
            String itemName = selectedItem.split(" Has Been Added ")[0]; 
            Iteams itemToRemove = getItemByName(itemName);

           
            if (itemToRemove != null) {
                o.getOrderList().remove(itemToRemove);
                cartModel.remove(selectedIndex);
                updateTotalLabel();
            }
        }
    }

    
    private Iteams getItemByName(String name) {
        for (Iteams item : o.getOrderList()) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;

            }
        }
        return null;
    }
});
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    clearAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cartModel.clear();
                o.getOrderList().clear();
                updateTotalLabel();
                
            }
            
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    pay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                billIssuing.calculateTotal();
                total = billIssuing.getTotal();
                JOptionPane.showMessageDialog(null," Payment Processed Successfully\n Total Amount is: "+" RM "+ String.format("%.2f", total));
              
                updateTotalLabel();
                }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        setLayout(new BorderLayout());
        add(new JScrollPane(cartList), BorderLayout.CENTER);
        add(LeftSide, BorderLayout.WEST);
        add(rightSide, BorderLayout.EAST);
        add(down,BorderLayout.SOUTH);
        add(up,BorderLayout.NORTH);
       
        LeftSide.add(pepsiLabel);
        LeftSide.add(coLabel);
        LeftSide.add(spritLabel);
        LeftSide.add(sevenUpLabel);

        rightSide.add(addToCart);
        rightSide.add(removeFromCart);
        rightSide.add(clearAll);
        rightSide.add(pay);

        down.add(Total);
        up.add(Name);

        ImageIcon logo = new ImageIcon( );
        try {
            URL resource1 = getClass().getClassLoader().getResource( "Pictures.java\\logo.jpg");
            logo = new ImageIcon(resource1);

        } catch (Exception e) {
            System.out.println("Error loading image : " + e.getMessage());
        }

        setTitle("Panda Market");
        setIconImage(logo.getImage());
        setVisible(true);
        setResizable(true);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

   private JLabel createSoftDrinkLabel(String name, String price, String imageName) {
  URL resource1 = getClass().getClassLoader().getResource( imageName );
  ImageIcon icon = new ImageIcon( resource1 );
ImageIcon resizedIcon = resizeImageIcon(icon, 100, 100);

    JLabel label = new JLabel(name + ": " +  " RM "+price, resizedIcon, JLabel.CENTER);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.BOTTOM);

    label.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        }
    });

    return label;
}
    private JLabel getSelectedDrinkLabel() {
        for (Component component : LeftSide.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getBorder() != null) {
                    label.setBorder(null); 
                    return label;
                }
            }
        }
        return null;
    }

       public ImageIcon resizeImageIcon(ImageIcon originalIcon, int newWidth, int newHeight) {
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

private void updateTotalLabel() {
     
    Total.setText("Total: RM" + String.format("%.2f", billIssuing.calculateTotal()));
}
  

    
}

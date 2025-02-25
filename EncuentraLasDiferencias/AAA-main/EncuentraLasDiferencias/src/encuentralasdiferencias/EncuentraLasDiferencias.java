
package encuentralasdiferencias;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class EncuentraLasDiferencias {

    public static void main(String[] args) {
        ImageIcon imagenDerecha = new ImageIcon("Derecha.jpg");
        ImageIcon imagenIzquierda = new ImageIcon("Izquierda.jpg");
        
        JFrame frame = new JFrame("Encuentra las diferencias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(590, 325);
        frame.setLayout(null);
        JLabel Izquierda = new JLabel(imagenIzquierda);
        JLabel Derecha = new JLabel(imagenDerecha);
        Izquierda.setBounds(0, 0, 287, 286);
        Derecha.setBounds(287, 0, 287, 286);
        frame.add(Izquierda);
        frame.add(Derecha);
        frame.setVisible(true);
    }
}

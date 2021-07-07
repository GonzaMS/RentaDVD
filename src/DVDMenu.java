import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DVDMenu extends JFrame {
    private JPanel Panel;
    private JButton agregarButton;
    private JButton buscarButton;
    private JButton listaButton;
    private JButton atrasButton;

    private Controlador controlador;

    private void close() {

        this.dispose();
    }

    public DVDMenu() {
        super("DVD");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        atrasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Programa(controlador);
                close();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new DetallesDVD(controlador);
            }
        });
        listaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new ListaDVD(controlador);
            }
        });

    }

    public DVDMenu(Controlador controlador) {
        this();
        this.controlador = controlador;
    }
}

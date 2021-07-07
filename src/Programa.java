import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Programa extends JFrame {
    private JPanel Panel;
    private JButton rentarButton;
    private JButton devolverButton;
    private JButton DVDButton;
    private JButton miembroButton;
    private JLabel totalMiembrosLabel;
    private JLabel totalDVDLabel;

    private Controlador controlador;

    private void close() {
        this.dispose();
    }

    public Programa() {
        super("Renta de DVD");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        rentarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Renta(controlador);
            }
        });
        devolverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Devolver(controlador);
            }
        });
        DVDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DVDMenu(controlador);
                close();
            }
        });
    }


    public Programa(Controlador controlador) {
        this();
        this.controlador = controlador;
        totalDVDLabel.setText("Total DVD : " + controlador.contarDVD());
        totalMiembrosLabel.setText("Total Miembros : " + controlador.contarMiembros());
    }

}

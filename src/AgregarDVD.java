import javax.swing.*;


public class AgregarDVD extends JFrame {
    private JButton okButton;
    private JTextField nombreField;
    private JTextField categoriaField;
    private JTextField tipoField;
    private JTextField precioField;
    private JPanel Panel;



    private Controlador controlador;
    private DVD dvd;



    public AgregarDVD() {
        super("Agregar DVD");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }



}

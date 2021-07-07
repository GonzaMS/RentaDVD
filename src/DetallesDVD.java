import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Comparador.Comparador;

public class DetallesDVD extends JFrame {
    private JPanel Panel;
    private JButton rButton;
    private JButton borrarButton;
    private JButton buscarButton;
    private JTextField dvdIDField;
    private JLabel estadoLable;
    private JTextField nombreField;
    private JTextField categoriaField;
    private JTextField tipoField;
    private JTextField precioField;

    private Controlador controlador;
    private boolean editMode;
    private DVD dvd;

    private void close() {

        this.dispose();
    }

    public DetallesDVD() {
        super("Buscar");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        getRootPane().setDefaultButton(buscarButton);
        this.editMode = false;
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dvdIDText = dvdIDField.getText();
                if (Comparador.esNumero(dvdIDText))
                    setDataDVD(controlador.buscarDVD(Integer.parseInt(dvdIDText)));
                else
                    JOptionPane.showMessageDialog(null, "Error : Porfavor solo numeros en DVD ID", "Error / DVD ID", JOptionPane.ERROR_MESSAGE);
            }
        });
        rButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (dvd.getMiembroID() != -1) {
                    new Devolver(controlador, dvd.getDvdID());
                } else {
                    new Renta(controlador, dvd.getDvdID());
                }
                close();
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dvdIDText = dvdIDField.getText();
                if (Comparador.esNumero(dvdIDText)) {
                    int choice = JOptionPane.showConfirmDialog(null, "Estas seguro ?", "Confimacion", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        controlador.removerDVD(Integer.parseInt(dvdIDText));
                        close();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error : Porfavor solo ingrese numeros en DVD ID", "Error / DVD ID", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public DetallesDVD(Controlador controlador) {
        this();
        this.controlador = controlador;
    }

    public DetallesDVD(Controlador controlador, DVD dvd) {
        this(controlador);
        setDataDVD(dvd);

    }

    private void setDataDVD (DVD dvd) {
        nombreField.setEnabled(false);
        nombreField.setDisabledTextColor(new Color(0, 0, 0));
        categoriaField.setEnabled(false);
        categoriaField.setDisabledTextColor(new Color(0, 0, 0));
        tipoField.setEnabled(false);
        tipoField.setDisabledTextColor(new Color(0, 0, 0));
        precioField.setEnabled(false);
        precioField.setDisabledTextColor(new Color(0, 0, 0));
        if(dvd != null) {
            dvdIDField.setText(dvd.getDvdID() + "");
            nombreField.setText(dvd.getNombre());
            categoriaField.setText(controlador.buscarCategoria(dvd.getCategoriaID()));
            tipoField.setText(dvd.getType() ? "Nuevo" : "Viejo");
            precioField.setText(dvd.getPrice() + "");
            estadoLable.setForeground(dvd.getMiembroID() == -1 ? new Color(0, 150, 0) : new Color(255, 0, 0));
            estadoLable.setText(dvd.getMiembroID() == -1 ? "Disponible" : "Rentado por " + controlador.buscarMiembro(dvd.getMiembroID()).getNombre());
            this.setTitle(dvd.getNombre());
            if(dvd.getMiembroID() == -1) {
                rButton.setText("Rentar");
                rButton.setEnabled(true);
            }
            else {
                rButton.setText("Devolver");
                rButton.setEnabled(true);
            }
            borrarButton.setEnabled(true);
        }
        else {
            nombreField.setText("No encontrado");
            categoriaField.setText("No encontrado");
            tipoField.setText("No encontrado");
            precioField.setText("No encontrado");
            estadoLable.setForeground(new Color(0, 0, 0));
            estadoLable.setText("No encontrado");
            this.setTitle("No encontrado");
            rButton.setEnabled(false);
            borrarButton.setEnabled(false);
        }
        this.dvd = dvd;
    }

}

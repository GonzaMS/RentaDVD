import Comparador.Comparador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Devolver extends JFrame {
    private JPanel Panel;
    private JButton atrasButton;
    private JTextField dvdIDField;
    private JCheckBox rotoBox;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;

    private Controlador controlador;

    private void close() {
        this.dispose();
    }

    public Devolver() {
        super("Devolver");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        getRootPane().setDefaultButton(atrasButton);
        atrasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dvdIDText = dvdIDField.getText();
                if (Comparador.esNumero(dvdIDText)) {
                    int dvdID = Integer.parseInt(dvdIDText);
                    if (controlador.buscarDVD(dvdID) != null) {
                        String dayText = dayField.getText();
                        if (Comparador.esNumero(dayText)) {
                            int day = Integer.parseInt(dayText);
                            String monthText = monthField.getText();
                            if (Comparador.esNumero(monthText)) {
                                int month = Integer.parseInt(monthText);
                                String yearText = yearField.getText();
                                if (Comparador.esNumero(yearText)) {
                                    int year = Integer.parseInt(yearText);
                                    if (year > 0) {
                                        if ((month > 0) && (month < 13)) {
                                            if ((day > 0) && (day <= Comparador.ultimoDiaDelMes(month, year))) {
                                                int memberID = controlador.buscarDVD(dvdID).getMiembroID();
                                                int balance = controlador.devolverDVD(dvdID, day, month, year, rotoBox.isSelected());
                                                if (balance >= 0) {
                                                    JOptionPane.showMessageDialog(null, "OK : " + controlador.buscarMiembro(memberID).getNombre() + " pagar " + balance + " Guaranies", "Completo", JOptionPane.PLAIN_MESSAGE);
                                                    close();
                                                } else if (balance == -1) {
                                                    JOptionPane.showMessageDialog(null, "Error : No rentado", "Error / DVD", JOptionPane.ERROR_MESSAGE);
                                                    close();
                                                } else if (balance == -3) {
                                                    JOptionPane.showMessageDialog(null, "Error : Fecha incorrecta", "Error / Fecha", JOptionPane.ERROR_MESSAGE);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Error : Dia incorrecto", "Error / Dia", JOptionPane.ERROR_MESSAGE);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Error : Mes incorrecto", "Error / Mes", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error : Año incorrecto", "Error / Año", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error : Solo numeros porfavor", "Error / Año", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Error : Solo numeros porfavor", "Error / Mes", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error : Solo numeros porfavor", "Error / Dia", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error : No se encuentra un DVD con ese ID", "Error / DVD ID", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error : Solo numeros porfavor", "Error / DVD ID", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public Devolver(Controlador controlador) {
        this();
        this.controlador = controlador;
    }

    public Devolver(Controlador controlador, int dvdID) {
        this(controlador);
        dvdIDField.setText(dvdID + "");
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Comparador.Comparador;

public class Renta extends JFrame {
    private JButton rentarButton;
    private JPanel Panel;
    private JTextField dvdIDField;
    private JTextField miembroIDField;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;

    private Controlador controlador;

    private void close() {
        this.dispose();
    }

    public Renta() {
        super("Rentar");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        getRootPane().setDefaultButton(rentarButton);
        rentarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dvdIDText = dvdIDField.getText();
                if (Comparador.esNumero(dvdIDText)) {
                    int dvdID = Integer.parseInt(dvdIDText);
                    if (controlador.buscarDVD(dvdID) != null) {
                        String memberIDText = miembroIDField.getText();
                        if (Comparador.esNumero(memberIDText)) {
                            int memberID = Integer.parseInt(memberIDText);
                            if (controlador.buscarMiembro(memberID) != null) {
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
                                                        int balance = controlador.rentarDVD(dvdID, memberID, day, month, year);
                                                        if (balance > 0) {
                                                            JOptionPane.showMessageDialog(null, "OK : " + controlador.buscarMiembro(memberID).getNombre() + " pagar " + balance + " Guaranies", "Completo", JOptionPane.PLAIN_MESSAGE);
                                                            close();
                                                        } else if (balance == -1) {
                                                            JOptionPane.showMessageDialog(null, "Error : Ya rentado", "Error / DVD", JOptionPane.ERROR_MESSAGE);
                                                            close();
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Error : Dia Incorrecto", "Error / Dia", JOptionPane.ERROR_MESSAGE);
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
                                JOptionPane.showMessageDialog(null, "Error : No se encuentra el ID de ese miembro", "Error / Miembro ID", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error : Solo numeros porfavor", "Error / Miembro ID", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error : No se encuentra el ID de ese DVD", "Error / DVD ID", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error : Solo numeros porfavor", "Error / DVD ID", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public Renta(Controlador controlador) {
        this();
        this.controlador = controlador;
    }

    public Renta(Controlador controlador, int dvdID) {
        this(controlador);
        dvdIDField.setText(dvdID + "");
    }
}

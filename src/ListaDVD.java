import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class ListaDVD extends JFrame{
    private JPanel Panel;
    private JComboBox categoryComboBox;
    private JList listaDVD;

    private Controlador controlador;

    private void close() {

        this.dispose();
    }

    public ListaDVD() {
        super("Lita de DVD por categoria");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        listaDVD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    new DetallesDVD(controlador,(DVD) listaDVD.getSelectedValue());
                }
            }
        });
        categoryComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    public ListaDVD(Controlador controlador) {
        this();
        this.controlador = controlador;
        setListaDVD(-1);
        setListaCategoria();
    }

    private void setListaCategoria() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) categoryComboBox.getModel();
        model.removeAllElements();

        for(Map.Entry<Integer,String> entry : controlador.getCategory().entrySet()) {
            model.addElement(entry);
        }
        categoryComboBox.setModel(model);
    }

    private void setListaDVD(int categoryID) {
        DefaultListModel model = new DefaultListModel();
        for(DVD dvd : controlador.getListDVD(categoryID)) {
            model.addElement(dvd);
        }
        listaDVD.setModel(model);
    }


}

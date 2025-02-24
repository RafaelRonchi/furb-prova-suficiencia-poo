package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Municipal;
import models.Intermunicipal;
import models.Viagem;

public class CadastroViagem extends JFrame {
    private JTextField tfPlaca;
    private JTextField tfMotorista;
    private JTextField tfData;
    private JComboBox<String> cbTipo;
    private JButton btnCriarViagem;

    public CadastroViagem() {
        setTitle("Cadastro de Viagem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Placa do Ônibus
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Placa do Ônibus:"), gbc);
        tfPlaca = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfPlaca, gbc);

        // Nome do Motorista
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nome do Motorista:"), gbc);
        tfMotorista = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfMotorista, gbc);

        // Data da Viagem
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Data da Viagem (dd/MM/yyyy):"), gbc);
        tfData = new JTextField(10);
        gbc.gridx = 1;
        panel.add(tfData, gbc);

        // Tipo da Viagem
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Tipo da Viagem:"), gbc);
        cbTipo = new JComboBox<>(new String[] {"Municipal", "Intermunicipal"});
        gbc.gridx = 1;
        panel.add(cbTipo, gbc);

        // Botão para criar a viagem
        btnCriarViagem = new JButton("Criar Viagem");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnCriarViagem, gbc);

        add(panel);

        btnCriarViagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarViagem();
            }
        });
    }

    private void criarViagem() {
        String placa = tfPlaca.getText().trim();
        String motorista = tfMotorista.getText().trim();
        String dataStr = tfData.getText().trim();
        String tipo = (String) cbTipo.getSelectedItem();

        if (placa.isEmpty() || motorista.isEmpty() || dataStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        // Converte a data informada
        Date data;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            data = sdf.parse(dataStr);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Utilize o formato dd/MM/yyyy.");
            return;
        }

        Viagem viagem;
        if ("Municipal".equals(tipo)) {
            viagem = new Municipal(placa, motorista, data);
        } else {
            viagem = new Intermunicipal(placa, motorista, data);
        }

        // Abre a tela para cadastro de passageiros e fecha a tela atual
        CadastroPassageiros cadastroPassageiros = new CadastroPassageiros(viagem);
        cadastroPassageiros.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroViagem().setVisible(true);
            }
        });
    }
}

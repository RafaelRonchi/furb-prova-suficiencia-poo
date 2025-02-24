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
    private JTextField tfPlaca, tfMotorista, tfData;
    private JComboBox<String> cbTipo;
    private JButton btnCriarViagem;

    public CadastroViagem() {
        setTitle("Cadastro de Viagem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblTitle = new JLabel("Cadastro de Viagem");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Placa do Ônibus:"), gbc);
        tfPlaca = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfPlaca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nome do Motorista:"), gbc);
        tfMotorista = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfMotorista, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Data da Viagem (dd/MM/yyyy):"), gbc);
        tfData = new JTextField(10);
        gbc.gridx = 1;
        panel.add(tfData, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Tipo da Viagem:"), gbc);
        cbTipo = new JComboBox<>(new String[]{"Municipal", "Intermunicipal"});
        gbc.gridx = 1;
        panel.add(cbTipo, gbc);

        btnCriarViagem = new JButton("Criar Viagem");
        btnCriarViagem.setFont(new Font("Arial", Font.BOLD, 14));
        btnCriarViagem.setBackground(new Color(52, 152, 219));
        btnCriarViagem.setForeground(Color.WHITE);
        btnCriarViagem.setFocusPainted(false);
        btnCriarViagem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnCriarViagem, gbc);

        add(panel, BorderLayout.CENTER);

        btnCriarViagem.addActionListener(e -> criarViagem());
    }

    private void criarViagem() {
        String placa = tfPlaca.getText().trim();
        String motorista = tfMotorista.getText().trim();
        String dataStr = tfData.getText().trim();
        String tipo = (String) cbTipo.getSelectedItem();

        if (placa.isEmpty() || motorista.isEmpty() || dataStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dataStr);
            Viagem viagem = "Municipal".equals(tipo) ? new Municipal(placa, motorista, data) : new Intermunicipal(placa, motorista, data);

            new CadastroPassageiros(viagem).setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Utilize o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new CadastroViagem().setVisible(true));
    }
}
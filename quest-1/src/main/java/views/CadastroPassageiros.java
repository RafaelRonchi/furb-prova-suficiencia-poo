package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import models.Viagem;
import models.Empresa;
import models.Passageiro;
import models.Idoso;
import models.Estudante;

public class CadastroPassageiros extends JFrame {
    private Viagem viagem;
    private Empresa empresa;

    // Componentes do formulário de cadastro de passageiros
    private JTextField tfNome;
    private JTextField tfIdade;
    private JComboBox<String> cbTipo;
    private JLabel lblRG;
    private JTextField tfRG;
    private JLabel lblEscola;
    private JTextField tfEscola;
    private JButton btnAdicionar;

    // Componentes para exibir a lista de passageiros e o total da viagem
    private DefaultListModel<String> listModel;
    private JList<String> listPassageiros;
    private JLabel lblTotal;


    public CadastroPassageiros(Viagem viagem) {
        this.viagem = viagem;
        setTitle("Cadastro de Passageiros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Painel superior: exibe os dados da viagem
        JPanel panelTrip = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTrip.add(new JLabel("Ônibus: " + viagem.getPlacaOnibus()
                + " - Motorista: " + viagem.getNomeMotorista()
                + " - Data: " + viagem.getDataViagem()));
        mainPanel.add(panelTrip, BorderLayout.NORTH);

        // Painel central: formulário para cadastro de passageiro
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(new JLabel("Nome:"), gbc);
        tfNome = new JTextField(20);
        gbc.gridx = 1;
        panelForm.add(tfNome, gbc);

        // Idade
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(new JLabel("Idade:"), gbc);
        tfIdade = new JTextField(5);
        gbc.gridx = 1;
        panelForm.add(tfIdade, gbc);

        // Tipo do Passageiro
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(new JLabel("Tipo:"), gbc);
        cbTipo = new JComboBox<>(new String[] {"Normal", "Idoso", "Estudante"});
        gbc.gridx = 1;
        panelForm.add(cbTipo, gbc);

        // Campo RG (para Idoso)
        gbc.gridx = 0;
        gbc.gridy = 3;
        lblRG = new JLabel("RG:");
        panelForm.add(lblRG, gbc);
        tfRG = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(tfRG, gbc);

        // Campo Escola (para Estudante)
        gbc.gridx = 0;
        gbc.gridy = 4;
        lblEscola = new JLabel("Escola:");
        panelForm.add(lblEscola, gbc);
        tfEscola = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(tfEscola, gbc);

        // Inicialmente, campos RG e Escola são ocultos
        lblRG.setVisible(false);
        tfRG.setVisible(false);
        lblEscola.setVisible(false);
        tfEscola.setVisible(false);

        // Exibe os campos conforme o tipo selecionado
        cbTipo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbTipo.getSelectedItem();
                if ("Idoso".equals(selected)) {
                    lblRG.setVisible(true);
                    tfRG.setVisible(true);
                    lblEscola.setVisible(false);
                    tfEscola.setVisible(false);
                } else if ("Estudante".equals(selected)) {
                    lblRG.setVisible(false);
                    tfRG.setVisible(false);
                    lblEscola.setVisible(true);
                    tfEscola.setVisible(true);
                } else {
                    lblRG.setVisible(false);
                    tfRG.setVisible(false);
                    lblEscola.setVisible(false);
                    tfEscola.setVisible(false);
                }
                panelForm.revalidate();
                panelForm.repaint();
            }
        });

        // Botão para adicionar passageiro
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        btnAdicionar = new JButton("Adicionar Passageiro");
        panelForm.add(btnAdicionar, gbc);

        mainPanel.add(panelForm, BorderLayout.CENTER);

        // Painel inferior: lista de passageiros e total da viagem
        JPanel panelList = new JPanel(new BorderLayout(5, 5));
        listModel = new DefaultListModel<>();
        listPassageiros = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listPassageiros);
        panelList.add(scrollPane, BorderLayout.CENTER);
        lblTotal = new JLabel("Total da Viagem: R$ 0.00");
        panelList.add(lblTotal, BorderLayout.SOUTH);

        mainPanel.add(panelList, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPassageiro();
            }
        });


        // Botão para criar a empresa e exibir os passageiros mais idosos
        JButton btnCriarEmpresa = new JButton("Buscar passageiros mais idosos");
        gbc.gridy = 6;
        panelForm.add(btnCriarEmpresa, gbc);

        btnCriarEmpresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarEmpresa();
            }
        });

    }

    private void criarEmpresa() {
        empresa = new Empresa();
        empresa.addViagem(viagem);

        // Obtém os passageiros mais idosos
        List<Passageiro> idosos = empresa.getPassageirosMaisIdosos();

        if (idosos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum passageiro idoso encontrado.");
        } else {
            StringBuilder mensagem = new StringBuilder("Passageiro(s) mais idoso(s):\n");
            for (Passageiro p : idosos) {
                mensagem.append(p.getNome()).append(" - ").append(p.getIdade()).append(" anos\n");
            }
            JOptionPane.showMessageDialog(this, mensagem.toString());
        }
    }


    private void adicionarPassageiro() {
        String nome = tfNome.getText().trim();
        String idadeText = tfIdade.getText().trim();
        if (nome.isEmpty() || idadeText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha os campos de nome e idade.");
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeText);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número.");
            return;
        }

        String tipo = (String) cbTipo.getSelectedItem();
        Passageiro passageiro = null;

        if ("Idoso".equals(tipo)) {
            String rg = tfRG.getText().trim();
            if (rg.isEmpty()){
                JOptionPane.showMessageDialog(this, "Informe o RG para o idoso.");
                return;
            }
            try {
                passageiro = new Idoso(nome, idade, rg);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao criar idoso: " + ex.getMessage());
                return;
            }
        } else if ("Estudante".equals(tipo)) {
            String escola = tfEscola.getText().trim();
            if (escola.isEmpty()){
                JOptionPane.showMessageDialog(this, "Informe a escola para o estudante.");
                return;
            }
            passageiro = new Estudante(nome, idade, escola);
        } else {
            passageiro = new Passageiro(nome, idade);
        }

        viagem.addPassageiro(passageiro);
        listModel.addElement(passageiro.getNome() + " - " + passageiro.getIdade() + " anos, Tipo: " + tipo);
        float total = viagem.getValorTotal();
        lblTotal.setText(String.format("Total da Viagem: R$ %.2f", total));

        // Limpa os campos para o próximo cadastro
        tfNome.setText("");
        tfIdade.setText("");
        tfRG.setText("");
        tfEscola.setText("");
    }
}

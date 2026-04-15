package com.br.jheicanama.freelancer.desktop.view.cadastro;

import javax.swing.*;
import java.awt.*;

public class ChooseRegistrationView extends   JFrame {
    public  ChooseRegistrationView() {
        setTitle("Escolha um tipo de cadastro");
        setSize(400,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel titulo = new JLabel("Você é:", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnCandidato = new JButton("Candidato");
        JButton btnContratante = new JButton("Contratante");

        painel.add(titulo);
        painel.add(btnCandidato);
        painel.add(btnContratante);

        add(painel);
        btnCandidato.addActionListener(e -> {
            new CadastroCandidatoView();
            dispose();
        });

        btnContratante.addActionListener(e -> {
            new CadastroContratanteView();
            dispose();
        });

        setVisible(true);
    }
}

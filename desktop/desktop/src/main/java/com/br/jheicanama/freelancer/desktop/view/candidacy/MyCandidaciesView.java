package com.br.jheicanama.freelancer.desktop.view.candidacy;
import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.candidacy.CandidacyResponse;
import com.br.jheicanama.freelancer.desktop.section.candidate.CandidateSection;
import com.br.jheicanama.freelancer.desktop.service.candidacy.CandidacyService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyCandidaciesView extends JFrame {

    private final CandidacyService candidacyService = SpringContext.getBean(CandidacyService.class);
    private final CandidateSection candidateSection = SpringContext.getBean(CandidateSection.class);

    private JTable tabela;
    private DefaultTableModel modelo;

    public MyCandidaciesView() {
        setTitle("Minhas Candidaturas");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);
        add(tabelaPanel(), BorderLayout.CENTER);
        add(footer(), BorderLayout.SOUTH);

        carregarCandidaturas();

        setVisible(true);
    }

    private JPanel header() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Minhas Candidaturas");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> dispose());

        panel.add(titulo, BorderLayout.WEST);
        panel.add(voltar, BorderLayout.EAST);

        return panel;
    }

    private JPanel tabelaPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] colunas = {"Título", "Empresa", "Status"};

        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void carregarCandidaturas() {
        try {
            Long candidateId = candidateSection.getUserLogged().getId();

            List<CandidacyResponse> lista = candidacyService.listCandidacyCandidate(candidateId);// usa seu service

            modelo.setRowCount(0);

            for (CandidacyResponse c : lista) {
                modelo.addRow(new Object[]{
                        c.getJobTitle(),
                        c.getContractorEmpresa(),
                        c.getStatus()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private JPanel footer() {
        JPanel panel = new JPanel();

        JButton atualizar = new JButton("Atualizar");
        atualizar.addActionListener(e -> carregarCandidaturas());

        panel.add(atualizar);

        return panel;
    }
}

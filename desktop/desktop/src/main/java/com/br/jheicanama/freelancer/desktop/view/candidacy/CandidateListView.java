package com.br.jheicanama.freelancer.desktop.view.candidacy;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.candidacy.CandidacyResponse;
import com.br.jheicanama.freelancer.desktop.section.contractor.ContractorSection;
import com.br.jheicanama.freelancer.desktop.service.candidacy.CandidacyService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CandidateListView extends JFrame {

    private final CandidacyService candidacyService = SpringContext.getBean(CandidacyService.class);
    private final ContractorSection contractorSection = SpringContext.getBean(ContractorSection.class);

    private JTable tabela;
    private DefaultTableModel modelo;

    private Long jobId;

    public CandidateListView(Long jobId) {
        this.jobId = jobId;

        setTitle("Candidatos da Vaga");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);
        add(tabelaPanel(), BorderLayout.CENTER);
        add(footer(), BorderLayout.SOUTH);

        carregarCandidatos();

        setVisible(true);
    }

    private JPanel header() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel titulo = new JLabel("Candidatos da Vaga");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> dispose());

        panel.add(titulo, BorderLayout.WEST);
        panel.add(voltar, BorderLayout.EAST);

        return panel;
    }

    private JPanel tabelaPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] colunas = {"ID Candidatura", "Candidato", "Status"};

        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void carregarCandidatos() {
        try {
            List<CandidacyResponse> lista = candidacyService.listCandidacyJob(jobId);

            modelo.setRowCount(0);

            for (CandidacyResponse c : lista) {
                modelo.addRow(new Object[]{
                        c.getId(),
                        c.getCandidateName(),
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
        atualizar.addActionListener(e -> carregarCandidatos());

        JButton aceitar = new JButton("Aceitar");
        aceitar.addActionListener(e -> aceitarCandidatura());

        JButton recusar = new JButton("Recusar");
        recusar.addActionListener(e -> recusarCandidatura());

        panel.add(atualizar);
        panel.add(aceitar);
        panel.add(recusar);

        return panel;
    }

    private void aceitarCandidatura() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma candidatura!");
            return;
        }

        Long candidacyId = Long.valueOf(tabela.getValueAt(linha, 0).toString());

        try {
            Long contractorId = contractorSection.getContractorLogged().getId();

            candidacyService.aceptCandidacy(candidacyId, contractorId);

            JOptionPane.showMessageDialog(this, "Candidatura aceita!");
            carregarCandidatos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void recusarCandidatura() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma candidatura!");
            return;
        }

        Long candidacyId = Long.valueOf(tabela.getValueAt(linha, 0).toString());

        try {

            Long contractorId = contractorSection.getContractorLogged().getId();

            candidacyService.recuseCandidacy(candidacyId, contractorId);

            JOptionPane.showMessageDialog(this, "Candidatura recusada!");
            carregarCandidatos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
package com.br.jheicanama.freelancer.desktop.view.job;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.job.JobResponse;
import com.br.jheicanama.freelancer.desktop.section.candidate.CandidateSection;
import com.br.jheicanama.freelancer.desktop.service.candidacy.CandidacyService;
import com.br.jheicanama.freelancer.desktop.service.job.JobService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JobListCandidateView extends JFrame {
    private final JobService jobService = SpringContext.getBean(JobService.class);
    private final CandidateSection candidateSection = SpringContext.getBean(CandidateSection.class);
    private final CandidacyService candidacyService = SpringContext.getBean(CandidacyService.class);

    private JTable tabela;
    private DefaultTableModel modelo;

    public JobListCandidateView(){
        setTitle("Vagas De Emprego");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);
        add(tabelaPanel(), BorderLayout.CENTER);
        add(footer(), BorderLayout.SOUTH);

        carregarVagas();

        setVisible(true);
    }

    private JPanel header(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel titulo = new JLabel("Vagas De Emprego");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> dispose());

        panel.add(titulo, BorderLayout.WEST);
        panel.add(voltar, BorderLayout.EAST);

        return panel;
    }

    private void candidatarNaVaga() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma vaga!");
            return;
        }

        Long jobId = Long.valueOf(tabela.getValueAt(linha, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Deseja se candidatar a essa vaga?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            Long candidateId = candidateSection.getUserLogged().getId();

            candidacyService.candidatura(candidateId, jobId);

            JOptionPane.showMessageDialog(this, "Candidatura realizada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private JPanel tabelaPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] colunas = {"ID", "Título", "Área", "Salário"};

        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        panel.add(scroll, BorderLayout.CENTER);

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    candidatarNaVaga();
                }
            }
        });

        return panel;
    }

    private void carregarVagas() {
        try {
            List<JobResponse> vagas = jobService.listJob();

            modelo.setRowCount(0);

            for (JobResponse vaga : vagas) {
                modelo.addRow(new Object[]{
                        vaga.getId(),
                        vaga.getTitle(),
                        vaga.getArea(),
                        vaga.getWage()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private JPanel footer() {
        JPanel panel = new JPanel();

        JButton atualizar = new JButton("Atualizar");
        atualizar.addActionListener(e -> carregarVagas());

        JButton candidatar = new JButton("Candidatar-se");
        candidatar.addActionListener(e -> candidatarNaVaga());

        panel.add(atualizar);
        panel.add(candidatar);

        return panel;
    }
}

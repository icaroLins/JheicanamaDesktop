package com.br.jheicanama.freelancer.desktop.view.job;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.job.JobResponse;
import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import com.br.jheicanama.freelancer.desktop.section.contractor.ContractorSection;
import com.br.jheicanama.freelancer.desktop.service.job.JobService;
import com.br.jheicanama.freelancer.desktop.view.candidacy.CandidateListView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JobListView extends JFrame {
    private final JobService jobService = SpringContext.getBean(JobService.class);
    private final ContractorSection contractorSection = SpringContext.getBean(ContractorSection.class);

    private JTable tabela;
    private DefaultTableModel modelo;

    public JobListView() {
        setTitle("Minhas Vagas");
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

    private JPanel header() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel titulo = new JLabel("Minhas Vagas");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> dispose());

        panel.add(titulo, BorderLayout.WEST);
        panel.add(voltar, BorderLayout.EAST);

        return panel;
    }

    private JPanel tabelaPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] colunas = {"ID", "Título", "Área", "Salário"};

        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void carregarVagas() {
        Contractor contractor = contractorSection.getContractorLogged();

        List<JobResponse> vagas = jobService.listContractorJob(contractor.getId());

        modelo.setRowCount(0); // limpa tabela

        for (JobResponse vaga : vagas) {
            modelo.addRow(new Object[]{
                    vaga.getId(),
                    vaga.getTitle(),
                    vaga.getArea(),
                    vaga.getWage()
            });
        }
    }


    private JPanel footer() {
        JPanel panel = new JPanel();

        JButton atualizar = new JButton("Atualizar");
        atualizar.addActionListener(e -> carregarVagas());
        JButton verCandidatos = new JButton("Ver Candidatos");
        verCandidatos.addActionListener(e -> verCandidatos());


        panel.add(atualizar);
        panel.add(verCandidatos);

        return panel;
    }

    private void verCandidatos() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma vaga!");
            return;
        }

        Long jobId = Long.valueOf(tabela.getValueAt(linha, 0).toString());

        new CandidateListView(jobId);
    }
}

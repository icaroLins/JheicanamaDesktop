package com.br.jheicanama.freelancer.desktop.view.dashboard;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.enums.Role;
import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import com.br.jheicanama.freelancer.desktop.model.user.User;
import com.br.jheicanama.freelancer.desktop.section.candidate.CandidateSection;
import com.br.jheicanama.freelancer.desktop.section.contractor.ContractorSection;
import com.br.jheicanama.freelancer.desktop.section.user.UserSection;
import com.br.jheicanama.freelancer.desktop.view.candidacy.MyCandidaciesView;
import com.br.jheicanama.freelancer.desktop.view.job.JobListCandidateView;
import com.br.jheicanama.freelancer.desktop.view.job.JobListView;
import com.br.jheicanama.freelancer.desktop.view.job.JobVacanciesView;

import javax.swing.*;
import java.awt.*;
public class DashboardView extends JFrame {
    private final UserSection userSection = (UserSection) SpringContext.getBean("userSection");
    private final CandidateSection candidateSection = (CandidateSection) SpringContext.getBean("candidateSection");
    private final ContractorSection contractorSection = (ContractorSection) SpringContext.getBean("contractorSection");

    public DashboardView(Role role) {
        setTitle("Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);
        add(content(role), BorderLayout.CENTER);

        setVisible(true);
    }


    private JPanel header() {
        JPanel panel = new JPanel(new BorderLayout());

        User user = userSection.getUserLogged();

        JLabel welcome = new JLabel("Bem-vindo, " + user.getUsername());
        welcome.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            userSection.logout();
            candidateSection.logout();
            contractorSection.logout();

            JOptionPane.showMessageDialog(this, "Logout realizado!");

            new com.br.jheicanama.freelancer.desktop.view.login.LoginView();
            dispose();
        });

        panel.add(welcome, BorderLayout.WEST);
        panel.add(logout, BorderLayout.EAST);

        return panel;
    }

    private JPanel content(Role role) {
        return switch (role) {
            case CANDIDATE -> candidatePanel();
            case CONTRACTOR -> contractorPanel();
            default -> throw new IllegalArgumentException("Role não suportada: " + role);
        };
    }


    private JPanel candidatePanel() {
        JPanel panel = new JPanel(new GridLayout(4,1,10,10));
        panel.setBorder(BorderFactory.createTitledBorder("Área do Candidato"));

        JButton btnPerfil = new JButton("Meu Perfil");
        JButton btnVagas = new JButton("Ver Vagas");
        JButton btnAplicacoes = new JButton("Minhas Aplicações");

        btnPerfil.addActionListener(e -> {
            Candidate candidate = candidateSection.getUserLogged();
            JOptionPane.showMessageDialog(this,
                    "Nome: " + candidate.getUsername() +
                            "\nEmail: " + candidate.getEmail() +
                            "\nCPF: " + candidate.getCpf()
            );
        });

        btnVagas.addActionListener(e -> new JobListCandidateView());

        btnAplicacoes.addActionListener(e -> new MyCandidaciesView());

        panel.add(new JLabel("Bem-vindo, candidato!", SwingConstants.CENTER));
        panel.add(btnPerfil);
        panel.add(btnVagas);
        panel.add(btnAplicacoes);

        return panel;
    }

    private JPanel contractorPanel() {
        JPanel panel = new JPanel(new GridLayout(4,1,10,10));
        panel.setBorder(BorderFactory.createTitledBorder("Área do Contratante"));

        JButton btnPerfil = new JButton("Meu Perfil");
        JButton btnCriarVaga = new JButton("Criar Vaga");
        JButton btnListVagas = new JButton("Listar Vagas");

        btnPerfil.addActionListener(e -> {
            Contractor contractor = contractorSection.getContractorLogged();
            JOptionPane.showMessageDialog(this,
                    "Empresa: " + contractor.getEmpresa() +
                            "\nCNPJ: " + contractor.getCnpj() +
                            "\nEmail: " + contractor.getEmail()
            );
        });

        btnCriarVaga.addActionListener(e -> new JobVacanciesView());

        btnListVagas.addActionListener(e -> new JobListView());

        panel.add(new JLabel("Bem-vindo, contratante!", SwingConstants.CENTER));
        panel.add(btnPerfil);
        panel.add(btnCriarVaga);
        panel.add(btnListVagas);

        return panel;
    }
}

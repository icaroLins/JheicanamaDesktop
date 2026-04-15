package com.br.jheicanama.freelancer.desktop.view.login;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.login.LoginRequest;
import com.br.jheicanama.freelancer.desktop.dto.login.LoginResponse;
import com.br.jheicanama.freelancer.desktop.model.user.Candidate;
import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import com.br.jheicanama.freelancer.desktop.model.user.User;
import com.br.jheicanama.freelancer.desktop.section.candidate.CandidateSection;
import com.br.jheicanama.freelancer.desktop.section.contractor.ContractorSection;
import com.br.jheicanama.freelancer.desktop.section.user.UserSection;
import com.br.jheicanama.freelancer.desktop.service.user.UserService;
import com.br.jheicanama.freelancer.desktop.view.cadastro.ChooseRegistrationView;
import com.br.jheicanama.freelancer.desktop.view.dashboard.DashboardView;

import javax.swing.*;
import java.awt.*;
public class LoginView extends JFrame{
    private JTextField email;
    private JPasswordField senha;

    private final UserSection userSection = (UserSection) SpringContext.getBean("userSection");
    CandidateSection candidateSection = (CandidateSection) SpringContext.getBean("candidateSection");
    ContractorSection contractorSection = (ContractorSection) SpringContext.getBean("contractorSection");

    public LoginView() {
        setTitle("Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel p = new JPanel(new GridLayout(5,1,10,10));
        p.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        email = new JTextField();
        email.setBorder(BorderFactory.createTitledBorder("Email"));

        senha = new JPasswordField();
        senha.setBorder(BorderFactory.createTitledBorder("Senha"));

        JButton login = new JButton("Entrar");
        JButton cadastro = new JButton("Criar conta");

        p.add(email);
        p.add(senha);
        p.add(login);
        p.add(cadastro);

        add(p);

        login.addActionListener(e -> fazerLogin());

        cadastro.addActionListener(e -> {
            new ChooseRegistrationView();
            dispose();
        });

        setVisible(true);
    }

    private void fazerLogin() {
        try {
            UserService service = SpringContext.getBean("userService", UserService.class);

            LoginRequest req = new LoginRequest();
            req.setEmail(email.getText());
            req.setPassword(new String(senha.getPassword()));

            LoginResponse res = service.loginUser(req);


            User user = service.findUserByEmail(res.getEmail());

            userSection.login(user);

            switch (user.getRole()) {
                case CANDIDATE -> {
                    candidateSection.login((Candidate) user);
                }
                case CONTRACTOR -> {
                    contractorSection.login((Contractor) user);
                }
            }

            JOptionPane.showMessageDialog(this, "Bem-vindo " + res.getName());

            new DashboardView(res.getRole());
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}


package com.br.jheicanama.freelancer.desktop.view.cadastro;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.candidate.CandidateRequest;
import com.br.jheicanama.freelancer.desktop.dto.user.UserRequest;
import com.br.jheicanama.freelancer.desktop.enums.Role;
import com.br.jheicanama.freelancer.desktop.service.candidate.CandidateService;
import com.br.jheicanama.freelancer.desktop.service.user.UserService;
import com.br.jheicanama.freelancer.desktop.view.login.LoginView;

import javax.swing.*;
import java.awt.*;

public class CadastroCandidatoView extends CadastroView {
    protected JTextField cpf;
    protected JTextField phone;

    @Override
    protected Role definirRole() {
        return Role.CANDIDATE;
    }

    @Override
    protected void addCamposExtras(JPanel p) {
        cpf = new JTextField();
        phone = new JTextField();

        cpf.setBorder(BorderFactory.createTitledBorder("CPF"));
        phone.setBorder(BorderFactory.createTitledBorder("Telefone"));


        cpf.setBorder(BorderFactory.createTitledBorder("CPF"));
        phone.setBorder(BorderFactory.createTitledBorder("Telefone"));

        p.add(cpf);
        p.add(phone);
    }

    public CadastroCandidatoView() {
        super("Cadastro Candidato");
    }

    @Override
    protected void cadastrar() {
        try {
            CandidateService service = SpringContext.getBean(CandidateService.class);

            CandidateRequest req = new CandidateRequest();
            req.setName(name.getText());
            req.setEmail(email.getText());
            req.setPassword(new String(password.getPassword()));
            req.setRole(definirRole());
            req.setCpf(cpf.getText());
            req.setPhone(phone.getText());

            service.cadastrarUser(req);

            JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");

            new LoginView();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }



}


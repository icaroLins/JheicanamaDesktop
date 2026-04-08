package com.br.jheicanama.freelancer.desktop.view.cadastro;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.contractor.ContractorRequest;
import com.br.jheicanama.freelancer.desktop.enums.Role;
import com.br.jheicanama.freelancer.desktop.service.contractor.ContractorService;
import com.br.jheicanama.freelancer.desktop.view.login.LoginView;

import javax.swing.*;

public class CadastroContratanteView  extends CadastroView {

    protected JTextField cnpj;
    protected JTextField phone;
    protected JTextField empresa;
    public CadastroContratanteView() {
        super("Cadastro Contratante");
    }

    @Override
    protected void addCamposExtras(JPanel p) {
        cnpj = new JTextField();
        phone = new JTextField();
        empresa = new JTextField();

        cnpj.setBorder(BorderFactory.createTitledBorder("CNPJ"));
        phone.setBorder(BorderFactory.createTitledBorder("Telefone"));
        empresa.setBorder(BorderFactory.createTitledBorder("Empresa"));

        p.add(cnpj);
        p.add(phone);
        p.add(empresa);
    }

    @Override
    protected Role definirRole() {
        return Role.CONTRACTOR;
    }

    @Override
    protected void cadastrar() {
        try {
            ContractorService service = SpringContext.getBean(ContractorService.class);

            ContractorRequest req = new ContractorRequest();
            req.setName(name.getText());
            req.setEmail(email.getText());
            req.setPassword(new String(password.getPassword()));
            req.setRole(definirRole());
            req.setCnpj(cnpj.getText());
            req.setPhone(phone.getText());
            req.setEmpresa(empresa.getText());


            service.cadastrarUser(req);

            JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");

            new LoginView();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }
}

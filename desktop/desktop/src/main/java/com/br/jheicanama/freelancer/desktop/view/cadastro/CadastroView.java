package com.br.jheicanama.freelancer.desktop.view.cadastro;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.user.UserRequest;
import com.br.jheicanama.freelancer.desktop.enums.Role;
import com.br.jheicanama.freelancer.desktop.service.user.UserService;
import com.br.jheicanama.freelancer.desktop.view.login.LoginView;

import javax.swing.*;
import java.awt.*;

public class CadastroView  extends JFrame {
    protected JTextField name = new JTextField();
    protected JTextField email = new JTextField();
    protected JPasswordField password = new JPasswordField();

    public CadastroView(String titulo) {
        setTitle(titulo);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel p = new JPanel(new GridLayout(0, 1, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        name.setBorder(BorderFactory.createTitledBorder("Nome"));
        email.setBorder(BorderFactory.createTitledBorder("Email"));
        password.setBorder(BorderFactory.createTitledBorder("Senha"));

        p.add(name);
        p.add(email);
        p.add(password);

        addCamposExtras(p);

        JButton cadastrar = new JButton("Cadastrar");
        cadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(cadastrar);

        add(p);
        setVisible(true);
    }
    protected void addCamposExtras(JPanel p) {

    }

    protected Role definirRole() {
        return Role.ADMIN;
    }
}

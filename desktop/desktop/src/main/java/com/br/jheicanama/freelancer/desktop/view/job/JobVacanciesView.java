package com.br.jheicanama.freelancer.desktop.view.job;

import com.br.jheicanama.freelancer.desktop.config.SpringContext;
import com.br.jheicanama.freelancer.desktop.dto.job.JobRequest;
import com.br.jheicanama.freelancer.desktop.model.user.Contractor;
import com.br.jheicanama.freelancer.desktop.section.contractor.ContractorSection;
import com.br.jheicanama.freelancer.desktop.service.job.JobService;

import javax.swing.*;
import java.awt.*;

public class JobVacanciesView extends JFrame {
    private final ContractorSection contractorSection = SpringContext.getBean(ContractorSection.class);
    private final JobService jobService = SpringContext.getBean(JobService.class);

    private JTextField campoTitulo;
    private JTextField campoDescricao;
    private JTextField campoSalario;
    private JTextField campoArea;

    public JobVacanciesView() {
        setTitle("Criar Vagas");
        setSize(650, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(header(), BorderLayout.NORTH);
        add(form(), BorderLayout.CENTER);
        add(footer(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel header() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel titulo = new JLabel("Criar Nova Vaga");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(titulo, BorderLayout.WEST);

        return panel;
    }

    private JPanel form() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Informações da Vaga"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Contractor contractor = contractorSection.getContractorLogged();

        campoTitulo = new JTextField(20);
        campoArea = new JTextField(20);
        campoSalario = new JTextField(20);
        campoDescricao = new JTextField(20);

        int y = 0;

        addCampo(panel, gbc, y++, "Título:", campoTitulo);
        addCampo(panel, gbc, y++, "Área:", campoArea);
        addCampo(panel, gbc, y++, "Salário:", campoSalario);

        addCampo(panel, gbc, y++, "Empresa:",
                new JLabel(contractor.getEmpresa()));

        addCampo(panel, gbc, y++, "Contratante:",
                new JLabel(contractor.getUsername()));

        addCampo(panel, gbc, y++, "Descrição:", campoDescricao);

        return panel;
    }
    private JPanel footer() {
        JPanel panel = new JPanel();

        JButton btnPublicar = new JButton("Publicar Vaga");
        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(e -> dispose());

        btnPublicar.addActionListener(e -> publicarVaga());

        panel.add(btnPublicar);
        panel.add(btnCancelar);

        return panel;
    }

    private void publicarVaga() {
        String titulo = campoTitulo.getText();
        String area = campoArea.getText();
        String salario = campoSalario.getText();
        String descricao = campoDescricao.getText();

        if (titulo.isEmpty() || area.isEmpty() || salario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!");
            return;
        }

        try {
            Double salarioConvertido = Double.parseDouble(salario);

            Contractor contractor = contractorSection.getContractorLogged();

            JobRequest request = new JobRequest();
            request.setTitle(titulo);
            request.setArea(area);
            request.setWage(salarioConvertido);
            request.setDescription(descricao);
            request.setContractor(contractor);

            jobService.createJob(request);

            JOptionPane.showMessageDialog(this, "Vaga publicada com sucesso!");

            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Salário deve ser um número válido!");
        }
    }

    private void addCampo(JPanel panel, GridBagConstraints gbc, int y, String label, JComponent campo) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(campo, gbc);
    }
}

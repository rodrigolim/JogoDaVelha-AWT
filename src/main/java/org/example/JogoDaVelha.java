package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class JogoDaVelha extends JFrame {

    private final String SET_X = "X";
    private final String SET_O = "O";

    JButton[] bt = new JButton[9];
    boolean xo = false;
    boolean[] click = new boolean[9];

    public JogoDaVelha() {
        super("Jogo da Velha");
        setSize(400, 430);
        setResizable(false);// Desabilitar redimensionamento
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// Centralizar o JFrame no meio da tela

        // Usar GridLayout para organizar os botões
        setLayout(new GridLayout(3, 3, 5, 5));

        for (int i = 0; i < 9; i++) {
            final int botaoId = i;
            bt[botaoId] = new JButton();
            bt[botaoId].setFont(new Font("Arial", Font.BOLD, 110));
            bt[botaoId].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!click[botaoId] || bt[botaoId].getText().isEmpty()) {
                        mudar(bt[botaoId]);
                        click[botaoId] = true;
                    }
                }
            });
            add(bt[botaoId]);
        }

        limpar();
    }

    public void mudar(JButton btn) {
        if (xo) {
            btn.setText(SET_O);
            xo = false;
        } else {
            btn.setText(SET_X);
            xo = true;
        }
        ganhou();
    }

    public void ganhou() {
        String vencedor = null;

        // Verificar linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (bt[i].getText().equals(SET_X) && bt[i + 3].getText().equals(SET_X) && bt[i + 6].getText().equals(SET_X)
                    || bt[i * 3].getText().equals(SET_X) && bt[i * 3 + 1].getText().equals(SET_X) && bt[i * 3 + 2].getText().equals(SET_X)) {
                vencedor = SET_X;
                break;
            } else if (bt[i].getText().equals(SET_O) && bt[i + 3].getText().equals(SET_O) && bt[i + 6].getText().equals(SET_O)
                    || bt[i * 3].getText().equals(SET_O) && bt[i * 3 + 1].getText().equals(SET_O) && bt[i * 3 + 2].getText().equals(SET_O)) {
                vencedor = SET_O;
                break;
            }
        }

        // Verificar diagonais
        if (bt[0].getText().equals(SET_X) && bt[4].getText().equals(SET_X) && bt[8].getText().equals(SET_X)
                || bt[2].getText().equals(SET_X) && bt[4].getText().equals(SET_X) && bt[6].getText().equals(SET_X)) {
            vencedor = SET_X;
        } else if (bt[0].getText().equals(SET_O) && bt[4].getText().equals(SET_O) && bt[8].getText().equals(SET_O)
                || bt[2].getText().equals(SET_O) && bt[4].getText().equals(SET_O) && bt[6].getText().equals(SET_O)) {
            vencedor = SET_O;
        }

        // Verificar empate
        boolean todasPreenchidas = true;
        for (int i = 0; i < 9; i++) {
            if (bt[i].getText().isEmpty()) {
                todasPreenchidas = false;
                break;
            }
        }

        // Exibir mensagem de empate
        if (todasPreenchidas && vencedor == null) {
            JOptionPane.showMessageDialog(null, "Empate!");
            limpar();
            return;
        }

        // Exibir mensagem se houver um vencedor
        if (vencedor != null) {
            JOptionPane.showMessageDialog(null, "Jogador " + vencedor + " Ganhou!");
            limpar();
        }
    }

    public void limpar() {
        for (int i = 0; i < 9; i++) {
            bt[i].setText("");
            click[i] = false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JogoDaVelha().setVisible(true);
        });
    }
}
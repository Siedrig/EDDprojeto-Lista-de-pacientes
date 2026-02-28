import java.awt.Component;
import java.util.PriorityQueue;
import javax.swing.*;

public class FilaPrioridadeGUI {
    public static void main(String[] args) {
       HeapPrioridade heap = new HeapPrioridade();
        String[] opcoes = new String[]{"Adicionar paciente", "Atender paciente", "Ver fila", "Sair"};

        int escolha;
        do {
            escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                    "Sistema de Fila de Prioridade", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0:
                    String nome = JOptionPane.showInputDialog("Nome do paciente: ");
                    String prioridadeStr = JOptionPane.showInputDialog("Prioridade (1 = urgente, maior número = menos urgente): ");

                    try {
                        int prioridade = Integer.parseInt(prioridadeStr);
                        heap.inserir(new Paciente(nome, prioridade));
                        JOptionPane.showMessageDialog(null, "Paciente adicionado com sucesso!");
                    } catch (NumberFormatException var9) {
                        JOptionPane.showMessageDialog(null, "Prioridade inválida!");
                    }
                    break;
                case 1:
                  Paciente atendido = heap.atenderPaciente();
                  if(atendido == null){
                      JOptionPane.showMessageDialog(null, "Fila vazia. Nenhum Paciente a ser atendido");
                  }else {
                      JOptionPane.showMessageDialog(null, "Atendendo paciente: " + atendido);
                  }
                  
                    break;
                case 2:
                    String arvoreTexto = heap.getArvoreComoTexto();
                    if (arvoreTexto == null || arvoreTexto.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Fila vazia.");
                    }else {
                        JTextArea textArea = new JTextArea(arvoreTexto);
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
                        JOptionPane.showMessageDialog(null, scrollPane, "Fila Atual", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;


            }

        }while (escolha!= 3);


    }
 }

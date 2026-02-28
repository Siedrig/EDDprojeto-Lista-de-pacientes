import java.util.LinkedList;
import java.util.Queue;

public class HeapPrioridade {
    private HeapNode root;
    public void inserir(Paciente paciente) {
        HeapNode novo = new HeapNode(paciente);
        if (root == null) {
            root = novo;
        }else {
            Queue<HeapNode> fila = new LinkedList<>();
            fila.add(root);

            while (!fila.isEmpty()) {
                HeapNode atual = fila.poll();

                if(atual.left == null) {
                    atual.left = novo;
                    subir(atual.left, atual);
                    break;
                }else{
                    fila.add(atual.left);
                }

                if (atual.right == null) {
                    atual.right = novo;
                    subir(atual.left, atual);
                    break;
                }else {
                    fila.add(atual.right);
                }
            }
        }
    }

    public Paciente atenderPaciente() {
        if (root == null) return null;

        Paciente atendido = root.paciente;

        HeapNode ultimo = encontrarUltimo();
        if (ultimo == root) {
            root = null;
        }else {
            root.paciente = ultimo.paciente;
            removerUltimo();
            descer(root);
        }
        return atendido;
    }

    private HeapNode encontrarUltimo(){
        Queue<HeapNode> fila = new LinkedList<>();
        fila.add(root);
        HeapNode ultimo = null;

        while (!fila.isEmpty()) {
            ultimo = fila.poll();
            if (ultimo.left != null) fila.add(ultimo.left);
            if (ultimo.right !=null) fila.add(ultimo.right);
        }

        return ultimo;
    }

    private void removerUltimo() {
        Queue<HeapNode> fila = new LinkedList<>();
        fila.add(root);
        HeapNode anterior = null;

        while(!fila.isEmpty()) {
            HeapNode atual = fila.poll();
            if (atual.left != null) {
                if (atual.left.left == null && atual.left.right == null) {
                    atual.left = null;
                    return;
                }
                fila.add(atual.left);
            }
            if (atual.right != null){
                if(atual.right.left == null && atual.right.right == null) {
                    atual.right = null;
                    return;
                }
                fila.add(atual.right);
            }
        }

    }

    private void descer(HeapNode node){
        if (node == null) return;

        HeapNode menor = node;

        if (node.left != null && node.left.paciente.compareTo(menor.paciente) < 0) {
            menor = node.left;
        }

        if (node.right != null && node.right.paciente.compareTo(menor.paciente) < 0){
            menor = node.right;
        }

        if (menor != node){
            Paciente temp = node.paciente;
            node.paciente = menor.paciente;
            menor.paciente = temp;
            descer(menor);
        }


    }

    private void subir(HeapNode filho, HeapNode pai) {
        if (filho.paciente.compareTo(pai.paciente) < 0 ){
            Paciente temp = pai.paciente;
            pai.paciente = filho.paciente;
            filho.paciente = temp;
        }
    }

    public void imprimirArvore() {
        imprimirRecursivo(root, 0);
    }

    private void imprimirRecursivo(HeapNode node, int nivel) {
        if (node == null) return;

        for (int i = 0; i <nivel; i++) System.out.print(" ");
        System.out.println(node.paciente);

        imprimirRecursivo(node.left, nivel + 1);
        imprimirRecursivo(node.right, nivel + 1);
    }

    public String getArvoreComoTexto(){
        StringBuilder sb = new StringBuilder();
        construirTexto(root, 0, sb);
        return sb.toString();
    }

    private void construirTexto(HeapNode node, int nivel, StringBuilder sb){
        if (node== null) return;

        for(int i = 0; i < nivel; i++) sb.append(" ");
        sb.append(node.paciente).append("\n");

        construirTexto(node.left, nivel + 1, sb);
        construirTexto(node.right, nivel+ 1, sb);
    }
}

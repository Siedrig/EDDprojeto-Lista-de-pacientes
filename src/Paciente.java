class Paciente implements Comparable<Paciente> {
    String nome;
    int prioridade;

    public Paciente(String nome, int prioridade){
        this.nome = nome;
        this.prioridade = prioridade;

    }

    public int compareTo(Paciente outro) {
        return Integer.compare(this.prioridade, outro.prioridade);
    }
    public String toString(){
        return this.nome + "(Prioridade: " + this.prioridade + ")";
    }
}
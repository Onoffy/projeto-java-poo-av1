public class Veiculo {
    public String marca;
    public String modelo;
    public String placa;
    public int ano;
    public boolean disponivel;
    public double preco;


    public Veiculo(String marca, String modelo, String placa, int ano, double preco) {

        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.preco = preco;

    }

    public void mudarDisponibilidade(boolean disponivel) {

        this.disponivel = disponivel;

    }

    @Override
    public String toString() {
        return String.format(
                "Veículo [marca=%s, modelo=%s, placa=%s, ano=%d, preco=%.2f, disponivel=%b]",
                this.marca, this.modelo, this.placa, this.ano, this.preco, this.disponivel
        );
    }
}
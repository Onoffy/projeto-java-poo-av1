import java.util.ArrayList;

public class Concessionaria {
    public String nome;
    public ArrayList<Veiculo> veiculos;
    public ArrayList<Cliente> clientes;
    public ArrayList<Venda> vendas;
    public int totalVeiculos;
    public int totalClientes;
    public int totalVendas;

    public Concessionaria(String nome) {
        this.nome = nome;
        this.veiculos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    public boolean adicionarVeiculo(Veiculo veiculo) {
        for (int i = 0; i < this.totalVeiculos; i++) {
            if (this.veiculos.get(i).placa.equals(veiculo.placa)) {
                return false;
            }
        }
        boolean veiculoInserido = this.veiculos.add(veiculo);
        if (veiculoInserido) {
            this.totalVeiculos++;
        }
        return veiculoInserido;
    }

    public boolean removerVeiculo(String placa) {
        for (int i = 0; i < this.totalVeiculos; i++) {
            if (this.veiculos.get(i).placa.equals(placa)) {
                Veiculo veiculoBuscado = this.veiculos.get(i);
                this.veiculos.remove(veiculoBuscado);
                this.totalVeiculos--;
                return true;
            }
        }
        return false;
    }

    public ArrayList<Veiculo> buscarVeiculoPorMarca(String marca) {
        ArrayList<Veiculo> veiculoBuscadoPorMarca = new ArrayList<>();
        for (int i=0; i < this.totalVeiculos; i++){
            if (this.veiculos.get(i).marca.toLowerCase().contains(marca.toLowerCase())){
                Veiculo veiculo = this.veiculos.get(i);
                veiculoBuscadoPorMarca.add(veiculo);
            }
        }
        return veiculoBuscadoPorMarca;
    }

    public ArrayList<Veiculo> buscarVeiculoPorModelo(String modelo) {
        ArrayList<Veiculo> veiculoBuscadoPorModelo = new ArrayList<>();
        for (int i=0; i < this.totalVeiculos; i++){
            if (this.veiculos.get(i).modelo.toLowerCase().contains(modelo.toLowerCase())){
                Veiculo veiculo = this.veiculos.get(i);
                veiculoBuscadoPorModelo.add(veiculo);
            }
        }
        return veiculoBuscadoPorModelo;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        for (int i=0; i < this.totalClientes;i++) {
            if (this.clientes.get(i).id == cliente.id && this.clientes.get(i).email.equals(cliente.email) && this.clientes.get(i).telefone.equals(cliente.telefone)){
                return false;
            }
        }
        boolean clienteCadastrado = this.clientes.add(cliente);
        if (clienteCadastrado){
            this.totalClientes ++;
        }
        return clienteCadastrado;
    }

    public boolean removerCliente(int id) {
        for (int i = 0; i < this.totalClientes; i++) {
            if (this.clientes.get(i).id == id) {
                Cliente clienteBuscado = this.clientes.get(i);
                this.clientes.remove(clienteBuscado);
                this.totalClientes--;
                return true;
            }
        }
        return false;
    }

    public boolean realizarVenda(String placa, int idCliente, String dataVenda, String formaPagamento, double valor) {
        for (int i=0; i < this.totalClientes; i++){
            if (this.clientes.get(i).id == idCliente) {
                for (int index=0; index < this.totalVeiculos; index++) {
                    if (this.veiculos.get(index).placa.equals(placa)) {
                        Cliente clienteEncontrado = this.clientes.get(i);
                        Veiculo veiculoEncontrado = this.veiculos.get(index);
                        Venda venda = new Venda(veiculoEncontrado, clienteEncontrado, formaPagamento, valor, dataVenda);
                        boolean vendaEfetuada = this.vendas.add(venda);
                        if (vendaEfetuada) {
                            this.totalVendas ++;
                            this.totalVeiculos --;
                            veiculoEncontrado.mudarDisponibilidade(false);
                            return vendaEfetuada;
                        }
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Veiculo> listarVeiculosDisponiveis() {
        ArrayList<Veiculo> veiculoDisponivel = new ArrayList<>();
        for (int i=0; i < this.totalVeiculos; i++){
            if (this.veiculos.get(i).disponivel){
                Veiculo veiculoIdentificado = this.veiculos.get(i);
                veiculoDisponivel.add(veiculoIdentificado);
            }
        }
        return veiculoDisponivel;
    }

    public ArrayList<Venda> listarVendasRealizadas() {
        return this.vendas;
    }

    @Override
    public String toString() {
        return String.format(
                "<Concessionaria: \nnome=%s \nveiculos=%s \nclientes=%s \nvendas=%s \ntotalVeiculos=%d \ntotalClientes=%d \ntotalVendas=%d>",
                nome, veiculos, clientes, vendas, totalVeiculos, totalClientes, totalVendas
        );
    }
}

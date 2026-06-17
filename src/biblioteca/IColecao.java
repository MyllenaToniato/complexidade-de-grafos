package biblioteca;

public interface IColecao<T> {

    public void adicionar(T novoValor);

    public T pesquisar(T valor);

    public boolean remover(T valor);

    public int quantidadeNos();


}
package comandinha.lcastrooliveira.com.comandinha.model;

import android.support.annotation.NonNull;

public class ItemComandinha implements Comparable<ItemComandinha> {

    private String nome;
    private int quantidade;

    public ItemComandinha() {
        this.quantidade = 0;
    }

    public ItemComandinha(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(@NonNull ItemComandinha o) {
        return this.nome.compareTo(o.nome);
    }
}
package com.anhembi.livraria_a3.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "compra") // Nome da tabela no banco de dados
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra; 

    
   /*  private Integer idUsuario; // id_usuario no banco */

   @ManyToOne
   @JoinColumn(name = "id_usuario", nullable = false) // Garante que a FK será gerada corretamente
   private Usuario usuario;
   

    private String email;
    private String endereco;
    private String cidade;
    private String estado;

    //construtores
    public Compra(){

    }

    


    public Compra(Usuario usuario, String email, String endereco, String cidade, String estado,
            LocalDateTime dataCompra, Integer quantidade, Double total) {
                super();
        this.usuario = usuario;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.dataCompra = dataCompra;
        this.quantidade = quantidade;
        this.total = total;
    }




   
    @Column(name = "data_compra", nullable = false, updatable = false, insertable = false)
    private LocalDateTime dataCompra;

    private Integer quantidade;
    private Double total;

    // Getters e Setters
    public Integer getIdCompra() {
        return idCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }




    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    /* public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    } */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

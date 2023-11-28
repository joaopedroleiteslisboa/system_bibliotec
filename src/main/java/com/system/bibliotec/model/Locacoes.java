package com.system.bibliotec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_locacoes")
public class Locacoes extends AbstractAuditingEntity {


    /**
     *
     */
    private static final long serialVersionUID = -5647611701017361015L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "quantidadeDeRenovacao")
    private Integer quantidadeDeRenovacao;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "kk:mm:ss")
    @DateTimeFormat(pattern = "kk:mm:ss")
    @Column(name = "horaLocacao")
    private LocalTime horaLocacao;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataLocacao")
    private LocalDate dataLocacao;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataPrevisaoTermino")
    private LocalDate dataPrevisaoTermino;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "kk:mm:ss")
    @DateTimeFormat(pattern = "kk:mm:ss")
    @Column(name = "horaCancelamento")
    private LocalTime horaCancelamento;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataCancelamento")
    private LocalDate dataCancelamento;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "kk:mm:ss")
    @DateTimeFormat(pattern = "kk:mm:ss")
    @Column(name = "horaEncerramento")
    private LocalTime horaEncerramento;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataEncerramento")
    private LocalDate dataEncerramento;

    @NotNull(message = "O Locacao precisar ter no minimo um Cliente")
    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "idLivro")
    private Livro livro;

    @Column(name = "observacoesEntrega")
    @Size(max = 100)
    private String observacoesEntrega;

    @Column(name = "observacoesDevolucao")
    @Size(max = 100)
    private String observacoesDevolucao;



    private Locacoes(Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.quantidadeDeRenovacao = builder.quantidadeDeRenovacao;
        this.horaLocacao = builder.horaLocacao;
        this.dataLocacao = builder.dataLocacao;
        this.dataPrevisaoTermino = builder.dataPrevisaoTerminoLocacao;
        this.horaCancelamento = builder.horaCancelamento;
        this.dataCancelamento = builder.dataCancelamento;
        this.horaEncerramento = builder.horaEncerramento;
        this.dataEncerramento = builder.dataEncerramento;
        this.usuario = builder.usuario;
        this.livro = builder.livro;
        this.observacoesEntrega = builder.observacoesEntrega;
        this.observacoesDevolucao = builder.observacoesDevolucao;
    }

    /**
     * Creates builder to build {@link Locacoes}.
     *
     * @return created builder
     */

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link Locacoes}.
     */

    public static final class Builder {
        private Long id;
        private Status status;
        private int quantidadeDeRenovacao;
        private LocalTime horaLocacao;
        private LocalDate dataLocacao;
        private LocalDate dataPrevisaoTerminoLocacao;
        private LocalTime horaCancelamento;
        private LocalDate dataCancelamento;
        private LocalTime horaEncerramento;
        private LocalDate dataEncerramento;
        private Usuario usuario;
        private Livro livro;
        private String observacoesEntrega;
        private String observacoesDevolucao;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withQuantidadeDeRenovacao(int quantidadeDeRenovacao) {
            this.quantidadeDeRenovacao = quantidadeDeRenovacao;
            return this;
        }

        public Builder withHoraLocacao(LocalTime horaLocacao) {
            this.horaLocacao = horaLocacao;
            return this;
        }

        public Builder withDataLocacao(LocalDate dataLocacao) {
            this.dataLocacao = dataLocacao;
            return this;
        }

        public Builder withDataPrevisaoTerminoLocacao(LocalDate dataPrevisaoTerminoLocacao) {
            this.dataPrevisaoTerminoLocacao = dataPrevisaoTerminoLocacao;
            return this;
        }

        public Builder withHoraCancelamento(LocalTime horaCancelamento) {
            this.horaCancelamento = horaCancelamento;
            return this;
        }

        public Builder withDataCancelamento(LocalDate dataCancelamento) {
            this.dataCancelamento = dataCancelamento;
            return this;
        }

        public Builder withHoraEncerramento(LocalTime horaEncerramento) {
            this.horaEncerramento = horaEncerramento;
            return this;
        }

        public Builder withDataEncerramento(LocalDate dataEncerramento) {
            this.dataEncerramento = dataEncerramento;
            return this;
        }

        public Builder withUsuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder withLivro(Livro livro) {
            this.livro = livro;
            return this;
        }

        public Builder withObservacoesEntrega(String observacoesEntrega) {
            this.observacoesEntrega = observacoesEntrega;
            return this;
        }

        public Builder withObservacoesDevolucao(String observacoesDevolucao) {
            this.observacoesDevolucao = observacoesDevolucao;
            return this;
        }

        public Locacoes build() {
            return new Locacoes(this);
        }
    }

    @Override
    public String toString() {
        return "Locacão [status=" + status + ", quantidadeDeRenovacao=" + quantidadeDeRenovacao + ", horaLocacao="
                + horaLocacao + ", dataLocacao=" + dataLocacao + ", dataPrevisaoTerminoLocacao="
                + dataPrevisaoTermino + ", horaCancelamento=" + horaCancelamento + ", dataCancelamento="
                + dataCancelamento + ", horaEncerramento=" + horaEncerramento + ", dataEncerramento=" + dataEncerramento
                + ", usuario=" + usuario.getNome() + ", livro=" + livro.getNome() + " Ed" + livro.getEdicao() + ", observacoesEntrega=" + observacoesEntrega
                + ", observacoesDevolucao=" + observacoesDevolucao + "]";
    }


}

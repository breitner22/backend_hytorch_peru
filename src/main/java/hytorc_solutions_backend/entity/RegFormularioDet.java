package hytorc_solutions_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "REG_FORMULARIO_DET")
public class RegFormularioDet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg_det")
    private Integer idRegDet;

    @Column(name = "valor", columnDefinition = "TEXT")
    private String valor;

    @ManyToOne
    @JoinColumn(name = "id_reg_cab")
    private RegFormularioCab regFormularioCab;

    @ManyToOne
    @JoinColumn(name = "id_campo")
    private CampoFormulario campo;

    

    public Integer getIdRegDet() {
        return idRegDet;
    }

    public void setIdRegDet(Integer idRegDet) {
        this.idRegDet = idRegDet;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public RegFormularioCab getRegFormularioCab() {
        return regFormularioCab;
    }

    public void setRegFormularioCab(RegFormularioCab regFormularioCab) {
        this.regFormularioCab = regFormularioCab;
    }

    public CampoFormulario getCampo() {
        return campo;
    }

    public void setCampo(CampoFormulario campo) {
        this.campo = campo;
    }
}
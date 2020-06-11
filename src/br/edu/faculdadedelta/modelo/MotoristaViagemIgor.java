package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class MotoristaViagemIgor {

	private Long id;
	private String nomeMotorista;
	private CategoriaCnhIgor categoriaCnhIgor;
	private String destino;
	private Integer distancia;
	private Double valorKm;
	private Date dataCorrida;
	private Double valorDesconto;
	private Double valorTotal;

	public Double getValorDesconto() {
		Double valorTemp = getValorKm() * getDistancia();
		if (valorTemp > 600) {
			valorDesconto = valorTemp * 0.025;
		} else {
			valorDesconto = 0.0;
		}
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Double getValorTotal() {
		valorTotal = (getValorKm() * getDistancia()) - getValorDesconto();
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public MotoristaViagemIgor() {
		super();
	}

	public MotoristaViagemIgor(Long id, String nomeMotorista, CategoriaCnhIgor categoriaCnhIgor, String destino,
			Integer distancia, Double valorKm, Date dataCorrida) {
		super();
		this.id = id;
		this.nomeMotorista = nomeMotorista;
		this.categoriaCnhIgor = categoriaCnhIgor;
		this.destino = destino;
		this.distancia = distancia;
		this.valorKm = valorKm;
		this.dataCorrida = dataCorrida;
	}

	public CategoriaCnhIgor getCategoriaCnhIgor() {
		return categoriaCnhIgor;
	}

	public void setCategoriaCnhIgor(CategoriaCnhIgor categoriaCnhIgor) {
		this.categoriaCnhIgor = categoriaCnhIgor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeMotorista() {
		return nomeMotorista;
	}

	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}

	public CategoriaCnhIgor getCategoriaCnh() {
		return categoriaCnhIgor;
	}

	public void setCategoriaCnh(CategoriaCnhIgor categoriaCnhIgor) {
		this.categoriaCnhIgor = categoriaCnhIgor;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Double getValorKm() {
		return valorKm;
	}

	public void setValorKm(Double valorKm) {
		this.valorKm = valorKm;
	}

	public Date getDataCorrida() {
		return dataCorrida;
	}

	public void setDataCorrida(Date dataCorrida) {
		this.dataCorrida = dataCorrida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MotoristaViagemIgor other = (MotoristaViagemIgor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

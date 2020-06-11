package br.edu.faculdadedelta.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.MotoristaViagemDaoIgor;
import br.edu.faculdadedelta.modelo.CategoriaCnhIgor;
import br.edu.faculdadedelta.modelo.MotoristaViagemIgor;

@ManagedBean
@SessionScoped
public class MotoristaViagemControllerIgor {

	private MotoristaViagemIgor motoristaViagemIgor = new MotoristaViagemIgor();
	private MotoristaViagemDaoIgor dao = new MotoristaViagemDaoIgor();
	private CategoriaCnhIgor categoriaSelecionado = new CategoriaCnhIgor();
	private String PAGINA_CADASTRO_MOTORISTA_VIAGEM = "cadastroMotoristaViagem.xhtml";

	public MotoristaViagemIgor getMotoristaViagemIgor() {
		return motoristaViagemIgor;
	}

	public void setMotoristaViagemIgor(MotoristaViagemIgor motoristaViagemIgor) {
		this.motoristaViagemIgor = motoristaViagemIgor;
	}

	public CategoriaCnhIgor getCategoriaSelecionado() {
		return categoriaSelecionado;
	}

	public void setCategoriaSelecionado(CategoriaCnhIgor categoriaSelecionado) {
		this.categoriaSelecionado = categoriaSelecionado;
	}

	public void limparCampos() {
		categoriaSelecionado = new CategoriaCnhIgor();
		motoristaViagemIgor = new MotoristaViagemIgor();
	}

	private void exibirMensagem(Integer opc, Exception e) {
		String mensagem = null;
		if (opc == 1) {
			mensagem = "Incluido com sucesso!";
		} else if (opc == 2) {
			mensagem = "Modificado com sucesso!";
		} else if (opc == 3) {
			mensagem = "Erro ao tentar a operação " + e.getMessage();
		} else if (opc == 4) {
			mensagem = "A data informada tem que ser maior que a data atual, e menor que 01/01/2022.";
		}
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAtual = null;
		Date dataComparacao = null;
		try {
			try {
				dataComparacao = sdf.parse("01/01/2022");
				dataAtual = sdf.parse(sdf.format(new Date()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			if (motoristaViagemIgor.getDataCorrida().after(dataAtual) && motoristaViagemIgor.getDataCorrida().before(dataComparacao)) {
				if (motoristaViagemIgor.getId() == null) {
					motoristaViagemIgor.setCategoriaCnh(categoriaSelecionado);
					dao.incluir(motoristaViagemIgor);
					limparCampos();
					exibirMensagem(1, null);
				} else {
					motoristaViagemIgor.setCategoriaCnh(categoriaSelecionado);
					dao.modificar(motoristaViagemIgor);
					limparCampos();
					exibirMensagem(2, null);
				}
			} else {
				exibirMensagem(4, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_MOTORISTA_VIAGEM;
	}

	public String editar() {
		categoriaSelecionado = motoristaViagemIgor.getCategoriaCnh();
		return PAGINA_CADASTRO_MOTORISTA_VIAGEM;
	}

	public String excluir() {
		try {
			motoristaViagemIgor.setCategoriaCnh(categoriaSelecionado);
			dao.excluir(motoristaViagemIgor);
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_MOTORISTA_VIAGEM;
	}

	public List<MotoristaViagemIgor> getLista() {
		List<MotoristaViagemIgor> resp = new ArrayList<>();
		try {
			resp = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return resp;
	}
}
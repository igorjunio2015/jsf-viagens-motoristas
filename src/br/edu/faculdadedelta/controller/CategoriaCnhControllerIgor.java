package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.CategoriaCnhDaoIgor;
import br.edu.faculdadedelta.modelo.CategoriaCnhIgor;

@ManagedBean
@SessionScoped
public class CategoriaCnhControllerIgor {

	private CategoriaCnhIgor categoriaCnhIgor = new CategoriaCnhIgor();
	private CategoriaCnhDaoIgor dao = new CategoriaCnhDaoIgor();
	private String PAGINA_CADASTRO_CATEGORIA_CNH = "cadastroCategoriaCnh.xhtml";

	public CategoriaCnhIgor getCategoriaCnhIgor() {
		return categoriaCnhIgor;
	}

	public void setCategoriaCnhIgor(CategoriaCnhIgor categoriaCnhIgor) {
		this.categoriaCnhIgor = categoriaCnhIgor;
	}

	public void limparCampos() {
		categoriaCnhIgor = new CategoriaCnhIgor();
	}

	private void exibirMensagem(Integer opc, Exception e) {
		String mensagem = null;
		if (opc == 1) {
			mensagem = "Incluido com sucesso!";
		} else if (opc == 2) {
			mensagem = "Modificado com sucesso!";
		} else if (opc == 3) {
			mensagem = "Erro ao tentar a operação " + e.getMessage();
		}
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (categoriaCnhIgor.getId() == null) {
				dao.incluir(categoriaCnhIgor);
				limparCampos();
				exibirMensagem(1, null);
			} else {
				dao.modificar(categoriaCnhIgor);
				limparCampos();
				exibirMensagem(2, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_CATEGORIA_CNH;
	}

	public String editar() {
		return PAGINA_CADASTRO_CATEGORIA_CNH;
	}

	public String excluir() {
		try {
			dao.excluir(categoriaCnhIgor);
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_CATEGORIA_CNH;
	}

	public List<CategoriaCnhIgor> getLista() {
		List<CategoriaCnhIgor> resp = new ArrayList<>();
		try {
			resp = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return resp;
	}

}

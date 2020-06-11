package br.edu.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.CategoriaCnhDaoIgor;
import br.edu.faculdadedelta.modelo.CategoriaCnhIgor;

@FacesConverter(value = "categoriaCnhConverterIgor")
public class CategoriaCnhConverterIgor implements Converter {

	private CategoriaCnhDaoIgor dao = new CategoriaCnhDaoIgor();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null) {
			try {
				return dao.pesquisarId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((CategoriaCnhIgor) valor).getId());
		}
		return null;
	}
}

package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.CategoriaCnhIgor;
import br.edu.faculdadedelta.util.Conexao;

public class CategoriaCnhDaoIgor {
	private Connection conn = null;
	private String sql = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public void incluir(CategoriaCnhIgor categoriaCnhIgor) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "insert into bd_avaliacao.categorias_cnh (descricao_cat) values (?)";
		ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, categoriaCnhIgor.getDescricao().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void modificar(CategoriaCnhIgor categoriaCnhIgor) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "update bd_avaliacao.categorias_cnh set descricao_cat = ? where id_cat = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, categoriaCnhIgor.getDescricao().trim());
			ps.setLong(2, categoriaCnhIgor.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(CategoriaCnhIgor categoriaCnhIgor) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "delete from bd_avaliacao.categorias_cnh where id_cat = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, categoriaCnhIgor.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<CategoriaCnhIgor> listar() throws Exception {
		List<CategoriaCnhIgor> resp = new ArrayList<>();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select * from bd_avaliacao.categorias_cnh order by id_cat";
		ps = conn.prepareStatement(sql);
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoriaCnhIgor categoriaCnhIgor = popularCategoriaCnh(rs);
				resp.add(categoriaCnhIgor);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resp;
	}

	public CategoriaCnhIgor pesquisarId(Long id) throws Exception {
		CategoriaCnhIgor resp = new CategoriaCnhIgor();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select * from bd_avaliacao.categorias_cnh where id_cat = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				resp = popularCategoriaCnh(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resp;
	}

	public CategoriaCnhIgor popularCategoriaCnh(ResultSet rs) throws SQLException {
		CategoriaCnhIgor categoriaCnhIgor = new CategoriaCnhIgor();
		categoriaCnhIgor.setId(rs.getLong("id_cat"));
		categoriaCnhIgor.setDescricao(rs.getString("descricao_cat"));
		return categoriaCnhIgor;
	}

}

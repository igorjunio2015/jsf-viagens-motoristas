package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.CategoriaCnhIgor;
import br.edu.faculdadedelta.modelo.MotoristaViagemIgor;
import br.edu.faculdadedelta.util.Conexao;

public class MotoristaViagemDaoIgor {
	private Connection conn = null;
	private String sql = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public void incluir(MotoristaViagemIgor motoristaViagemIgor) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "insert into bd_avaliacao.motoristas_viagens (nome_motorista_mot, id_categoria_mot, destino_mot, distancia_mot, valor_km_mot, data_corrida_mot) values (?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, motoristaViagemIgor.getNomeMotorista().trim());
			ps.setLong(2, motoristaViagemIgor.getCategoriaCnh().getId());
			ps.setString(3, motoristaViagemIgor.getDestino().trim());
			ps.setInt(4, motoristaViagemIgor.getDistancia());
			ps.setDouble(5, motoristaViagemIgor.getValorKm());
			ps.setDate(6, new java.sql.Date(motoristaViagemIgor.getDataCorrida().getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void modificar(MotoristaViagemIgor motoristaViagemIgor) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "update bd_avaliacao.motoristas_viagens set nome_motorista_mot = ?, id_categoria_mot = ?, destino_mot = ?, distancia_mot = ?, valor_km_mot = ?, data_corrida_mot = ? where id_mot = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, motoristaViagemIgor.getNomeMotorista().trim());
			ps.setLong(2, motoristaViagemIgor.getCategoriaCnh().getId());
			ps.setString(3, motoristaViagemIgor.getDestino().trim());
			ps.setInt(4, motoristaViagemIgor.getDistancia());
			ps.setDouble(5, motoristaViagemIgor.getValorKm());
			ps.setDate(6, new java.sql.Date(motoristaViagemIgor.getDataCorrida().getTime()));
			ps.setLong(7, motoristaViagemIgor.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(MotoristaViagemIgor motoristaViagemIgor) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "delete from bd_avaliacao.motoristas_viagens where id_mot = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, motoristaViagemIgor.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<MotoristaViagemIgor> listar() throws Exception {
		List<MotoristaViagemIgor> resp = new ArrayList<>();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select * from bd_avaliacao.motoristas_viagens mv inner join bd_avaliacao.categorias_cnh cc on mv.id_categoria_mot = cc.id_cat";
		ps = conn.prepareStatement(sql);
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				MotoristaViagemIgor motoristaViagemIgor = new MotoristaViagemIgor();
				motoristaViagemIgor.setId(rs.getLong("id_mot"));
				motoristaViagemIgor.setNomeMotorista(rs.getString("nome_motorista_mot").trim());
				motoristaViagemIgor.setDestino(rs.getString("destino_mot").trim());
				motoristaViagemIgor.setDistancia(rs.getInt("distancia_mot"));
				motoristaViagemIgor.setValorKm(rs.getDouble("valor_km_mot"));
				motoristaViagemIgor.setDataCorrida(rs.getDate("data_corrida_mot"));
				CategoriaCnhIgor categoriaCnhIgor = new CategoriaCnhIgor();
				categoriaCnhIgor.setId(rs.getLong("id_cat"));
				categoriaCnhIgor.setDescricao(rs.getString("descricao_cat").trim());
				motoristaViagemIgor.setCategoriaCnh(categoriaCnhIgor);
				resp.add(motoristaViagemIgor);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {

		}
		return resp;
	}
}

package com.junior.contas.web.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.junior.contas.modelo.Conta;
import com.junior.contas.modelo.TipoDaConta;

@Repository
public class ContaDao1 {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ContaDao1(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
        
    }

	public List<Conta> lista() {
		List<Conta> contas = jdbcTemplate.query(
				"select * from contas",
				new RowMapper<Conta>() {
					public Conta mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return buscaConta(rs);
					}
				});
		return contas;
	}

	public void adiciona(Conta conta) {
		jdbcTemplate
				.update("insert into contas (descricao, paga, valor, tipo) values (?,?,?,?)",
						conta.getDescricao(), Boolean.valueOf(conta.isPaga()), Double.valueOf(conta.getValor()), conta.getTipo().name());

	}

	public void remove(Conta conta) {
		// TODO Auto-generated method stub

	}

	public void altera(Conta conta) {
		// TODO Auto-generated method stub

	}
	
	public Conta buscaPorId(Long id) {
		return null;
		
	}
	
	public void paga(Long id) {
		
	}
	
	public Conta buscaConta(ResultSet rs) throws SQLException {
		Conta conta = new Conta();
		conta.setDescricao(rs.getString("descricao"));
		conta.setValor(rs.getDouble("valor"));
		conta.setPaga(rs.getBoolean("paga"));
		Date data = rs.getDate("dataPagamento");
		if (data != null) {
			Calendar dataPagamento = Calendar.getInstance();
			dataPagamento.setTime(data);
			conta.setDataPagamento(dataPagamento);
		}
		conta.setTipo(Enum.valueOf(TipoDaConta.class, rs.getString("tipo")));
		return conta;
	}
}

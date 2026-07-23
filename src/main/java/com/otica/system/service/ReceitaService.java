package com.otica.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otica.system.model.Receita;
import com.otica.system.repository.ReceitaRepository;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	
	public Receita save(Receita receita) {
		return repository.save(receita);
	}
	
	public Receita findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Receita não encontrada"));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Receita updateById(Long id, Receita receita) {
		return repository.findById(id).map( receitaExistente -> {
			//receitaExistente.setCliente(null);
			receitaExistente.setEsferaOD(receita.getEsferaOD());
			receitaExistente.setCilindroOD(receita.getCilindroOD());
			receitaExistente.setEixoOD(receita.getEixoOD());
			receitaExistente.setEsferaOE(receita.getEsferaOE());
			receitaExistente.setCilindroOE(receita.getCilindroOE());
			receitaExistente.setEixoOE(receita.getEixoOE());
			receitaExistente.setDistanciaPupilar(receita.getDistanciaPupilar());
			receitaExistente.setObservacoes(receita.getObservacoes());
			receitaExistente.setDataReceita(receita.getDataReceita());
			
			return repository.save(receitaExistente);
		}).orElseThrow(() -> new RuntimeException("Receita não encontrada"));
	}
}

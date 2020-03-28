package com.projetopos.projetopos.repositories;

import com.projetopos.projetopos.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer> {
}

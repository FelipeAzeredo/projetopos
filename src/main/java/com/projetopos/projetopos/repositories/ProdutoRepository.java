package com.projetopos.projetopos.repositories;

import com.projetopos.projetopos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}

package org.mrkorben.kata.bankaccount.repositories;

import org.mrkorben.kata.bankaccount.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository  extends JpaRepository<Operation,Long>{

}

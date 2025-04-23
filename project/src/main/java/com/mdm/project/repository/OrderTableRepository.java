package com.mdm.project.repository;

import com.mdm.project.entity.OrderTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderTableRepository extends CassandraRepository<OrderTable, OrderTable.OrderKey> {
    Optional<List<OrderTable>> findByKeyUserId(String userId);
}

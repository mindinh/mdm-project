package com.mdm.project.repository;

import com.mdm.project.entity.OrderTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTableRepository extends CassandraRepository<OrderTable, OrderTable.OrderKey> {

}

package com.mdm.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.List;

@Table("orders_by_user")
@Data
@AllArgsConstructor @NoArgsConstructor
public class OrderTable {
    @PrimaryKeyClass
    @Data
    public static class OrderKey {
        @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private String userId;

        @PrimaryKeyColumn(name = "order_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        private Instant orderTime;
    }

    @PrimaryKey
    private OrderKey key;

    private String orderId;
    private String status;
    private Double total;
    private String address;
    private String shipMethod;
    private String paymentMethod;
    private String products;

}

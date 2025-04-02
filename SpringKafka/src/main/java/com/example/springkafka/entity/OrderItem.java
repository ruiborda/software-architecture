package com.example.springkafka.entity;

import com.fasterxml.jackson.annotation.JsonBackReference; // <-- Importar
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString // Mantenemos ToString con Exclude si aún lo necesitas para logs
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ToString.Exclude // Mantenemos la exclusión para logs
    @JsonBackReference // <--- *** CORRECCIÓN AQUÍ: Lado "hijo", no se serializa ***
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // No necesita referencia JSON aquí (Product -> OrderItem es unidireccional en JSON)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
}
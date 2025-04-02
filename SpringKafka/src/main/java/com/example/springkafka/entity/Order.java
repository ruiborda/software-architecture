package com.example.springkafka.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference; // <-- Importar
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"order\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString // Mantenemos ToString con Exclude si aún lo necesitas para logs
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // No necesita referencia JSON aquí (User -> Order es unidireccional en JSON)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(length = 50, nullable = false)
    private String status;

    @ToString.Exclude // Mantenemos la exclusión para logs
    @JsonManagedReference // <--- *** CORRECCIÓN AQUÍ: Lado "padre" de la serialización ***
    @OneToMany(
            mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            // EAGER puede causar problemas de N+1, considera LAZY si no siempre necesitas los items
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

}
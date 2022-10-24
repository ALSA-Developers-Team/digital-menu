package com.alsa.menuapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "place_date", nullable = false)
    private Date placeDate;

    @Column(name = "ammount", nullable = false)
    private double ammount;

    @OneToOne
    @JoinColumn(name = "chef", nullable = true, referencedColumnName = "id")
    private User chef;

    @OneToOne
    @JoinColumn(name = "status", nullable = false, referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name="bill_id", nullable=true, referencedColumnName = "id")
    private Bill bill;
}

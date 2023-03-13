package net.javaguides.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardType;

    private String cardName;

    private String cardNumber;

    private Integer expiryYear;

    private Integer expiryMonth;

    private Integer cvc;

    // map order with the payment so we need orderId in the payment class
    private Long orderId;


}

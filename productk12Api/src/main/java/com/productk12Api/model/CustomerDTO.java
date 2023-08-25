package com.productk12Api.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerDTO {


    private int cusid;
    private String cusname;
    private String phone;
    private int aid;
    private int oid;
    private String odate;
    private Date birthday;

}
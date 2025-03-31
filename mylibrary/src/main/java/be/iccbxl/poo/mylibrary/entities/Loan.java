package be.iccbxl.poo.mylibrary.entities;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private Person person;
    private ArrayList<Book> books;
    private LocalDate loanDate;
}

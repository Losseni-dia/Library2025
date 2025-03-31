package be.iccbxl.poo.mylibrary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineBook extends Book {
    private String content;
    private final static byte EBOOK_LIMIT = 5;
}
